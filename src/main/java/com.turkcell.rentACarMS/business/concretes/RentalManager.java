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
import com.turkcell.rentACarMS.dataAccess.abstracts.CustomerDao;
import com.turkcell.rentACarMS.dataAccess.abstracts.RentalDao;
import com.turkcell.rentACarMS.entities.concretes.Car;
import com.turkcell.rentACarMS.entities.enums.CarStates;
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
    private CustomerDao customerDao;

    @Autowired
    public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarDao carDao,CustomerDao customerDao){
        this.rentalDao = rentalDao;
        this.modelMapperService = modelMapperService;
        this.carDao = carDao;
        this.customerDao = customerDao;
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
        if(!checkIfCarActive(createRentalRequest.getCarId()).isSuccess()){
            return new ErrorResult(checkIfCarActive(createRentalRequest.getCarId()).getMessage());
        }
//        if(!checkCarId(createRentalRequest.getCarId()).isSuccess()){
//            return new ErrorResult(checkCarId(createRentalRequest.getCarId()).getMessage());
//        }
        if(!checkCustomerId(createRentalRequest.getCustomerId()).isSuccess()){
            return new ErrorResult(checkCustomerId(createRentalRequest.getCustomerId()).getMessage());
        }
        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        Car car = this.carDao.findById(createRentalRequest.getCarId());
        car.setCarStates(CarStates.ON_RENT);//Araç kiralandığı için tipi "kirada" hale getirildi.
        this.carDao.save(car);
        this.rentalDao.save(rental);
        return new SuccessResult("Rental added with id: " + rental.getId());
    }

    @Override
    public Result update(UpdateRentalRequest updateRentalRequest) {
        if(!checkRentalId(updateRentalRequest.getId()).isSuccess()){
            return new ErrorResult(checkRentalId(updateRentalRequest.getId()).getMessage());
        }
        Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest,Rental.class);
        this.rentalDao.save(rental);
        return new SuccessResult(updateRentalRequest.getId() + " : Rental updated.");
    }

    @Override
    public Result delete(DeleteRentalRequest deleteRentalRequest) {
        if (!checkRentalId(deleteRentalRequest.getId()).isSuccess()){
            return new ErrorDataResult<RentalDto>(checkRentalId(deleteRentalRequest.getId()).getMessage());
        }
        Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
//        Car car = this.carDao.findById(rental.getCar().getId());
//        car.setCarStates(CarStates.AVAILABLE);
//        this.carDao.save(car);
        this.rentalDao.delete(rental);
        return new SuccessResult(deleteRentalRequest.getId() + " : Rental deleted.");
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
        CarStates carStates = car.getCarStates();
        if (carStates.name()=="AVAILABLE"){
            return new SuccessResult();
        }
        return new ErrorResult("Car is not available.");
    }
    private Result checkCarId(int carId) {
        if (!this.carDao.existsById(carId)) {
            return new ErrorResult("Car not found.");
        }
        return new SuccessResult();
    }
    private Result checkCustomerId(int customerId) {
        if (!this.customerDao.existsById(customerId)) {
            return new ErrorResult("Customer not found.");
        }
        return new SuccessResult();
    }
}
