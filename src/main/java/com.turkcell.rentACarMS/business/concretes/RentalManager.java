package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.RentalService;
import com.turkcell.rentACarMS.business.dtos.ListRentalDto;
import com.turkcell.rentACarMS.business.dtos.RentalDto;
import com.turkcell.rentACarMS.business.requests.create.CreateRentalRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteRentalRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateRentalRequest;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.*;
import com.turkcell.rentACarMS.dataAccess.abstracts.CarDao;
import com.turkcell.rentACarMS.dataAccess.abstracts.RentalDao;
import com.turkcell.rentACarMS.entities.concretes.Car;
import com.turkcell.rentACarMS.entities.concretes.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalManager implements RentalService {
    private CarDao carDao;
    private RentalDao rentalDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarDao carDao){
        this.rentalDao = rentalDao;
        this.modelMapperService = modelMapperService;
        this.carDao = carDao;
    }
    @Override
    public DataResult<List<ListRentalDto>> listAll() {
        List<Rental> rentals = this.rentalDao.findAll();
        if (!checkRentalListEmpty(rentals).isSuccess()){
            return new ErrorDataResult<List<ListRentalDto>>(checkRentalListEmpty(rentals).getMessage());
        }
        List<ListRentalDto> listRentalDto = rentals.stream().map(rental -> this.modelMapperService
                .forDto().map(rental, ListRentalDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListRentalDto>>(listRentalDto,listRentalDto.size() + " : Rentals found.");
    }

    @Override
    public Result create(CreateRentalRequest createRentalRequest) {
        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        rental.setId(0);
        Car car = this.carDao.findById(createRentalRequest.getCarId());
        if(checkIfCarActive(car.getId()).isSuccess()){
            car.setActive(false);//Araç kiralandığı için pasif hale getirildi.
            this.carDao.save(car);
            this.rentalDao.save(rental);
            return new SuccessResult("Rental added with id: " + rental.getId());
        }
        return new ErrorResult(checkIfCarActive(createRentalRequest.getCarId()).getMessage());
    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest,Rental.class);
        checkRentalId(updateRentalRequest.getRentalId());
        this.rentalDao.save(rental);
        return new SuccessResult(updateRentalRequest.getRentalId() + " : Rental updated.");

    }

    @Override
    public Result delete(DeleteRentalRequest deleteRentalRequest) {
        if (!checkRentalId(deleteRentalRequest.getRentalId()).isSuccess()){
            return new ErrorDataResult<RentalDto>(checkRentalId(deleteRentalRequest.getRentalId()).getMessage());
        }
        Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
        checkRentalId(rental.getId());
        this.rentalDao.delete(rental);
        return new SuccessResult(deleteRentalRequest.getRentalId() + " : Rental deleted.");
    }

    @Override
    public DataResult<RentalDto> getById(int rentalId) {
        if(!checkRentalId(rentalId).isSuccess()){
            return new ErrorDataResult<RentalDto>(checkRentalId(rentalId).getMessage());
        }
        Rental rental = this.rentalDao.getById(rentalId);
        RentalDto rentalDto = this.modelMapperService.forDto().map(rental, RentalDto.class);
        return new SuccessDataResult<RentalDto>(rentalDto,"Rental found.");
    }

    private Result checkRentalId(int rentalId) {
        if (!this.rentalDao.existsById(rentalId)) {
            return new ErrorResult("Rental not found.");
        }
        return new SuccessResult();
    }
    private Result checkRentalListEmpty(List<Rental> rentals){
        if (rentals.isEmpty()){
            return new ErrorDataResult<List<Rental>>("Rental list is empty.");
        }
        return new SuccessDataResult<>();
    }
    private Result checkIfCarActive(int carId){
        Car car = this.carDao.findById(carId);
        if (car.isActive()){
            return new SuccessResult();
        }
        return new ErrorResult("Car is passive.");
    }
}
