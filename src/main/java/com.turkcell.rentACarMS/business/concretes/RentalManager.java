package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.RentalService;
import com.turkcell.rentACarMS.business.dtos.ListRentalDto;
import com.turkcell.rentACarMS.business.dtos.RentalDto;
import com.turkcell.rentACarMS.business.requests.create.CreateRentalRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteRentalRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateRentalRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import com.turkcell.rentACarMS.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACarMS.core.utilities.results.SuccessResult;
import com.turkcell.rentACarMS.dataAccess.abstracts.*;
import com.turkcell.rentACarMS.entities.concretes.Car;
import com.turkcell.rentACarMS.entities.concretes.Rental;
import com.turkcell.rentACarMS.entities.enums.CarStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalManager implements RentalService {


    private CarDao carDao;
    private RentalDao rentalDao;
    private ModelMapperService modelMapperService;
    private CustomerDao customerDao;
    private CityDao cityDao;
    private OrderedAdditionalServiceDao orderedAdditionalServiceDao;

    @Autowired
    public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarDao carDao, CustomerDao customerDao, CityDao cityDao, OrderedAdditionalServiceDao orderedAdditionalServiceDao) {

        this.rentalDao = rentalDao;
        this.modelMapperService = modelMapperService;
        this.carDao = carDao;
        this.customerDao = customerDao;
        this.cityDao = cityDao;
        this.orderedAdditionalServiceDao = orderedAdditionalServiceDao;
    }

    @Override
    public DataResult<List<ListRentalDto>> listAll() {

        List<Rental> rentals = this.rentalDao.findAll();
        List<ListRentalDto> listRentalDto = rentals.stream().map(rental -> this.modelMapperService.forDto().map(rental, ListRentalDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ListRentalDto>>(listRentalDto, listRentalDto.size() + " : Rentals found.");
    }

    @Override
    @Transactional
    public Result create(CreateRentalRequest createRentalRequest) throws BusinessException {

        checkCustomerId(createRentalRequest.getCustomerId());
        checkCarId(createRentalRequest.getCarId());
        checkCityId(createRentalRequest.getRentalCityId());
        checkCityId(createRentalRequest.getReturnCityId());
        checkIfCarActive(createRentalRequest.getCarId());


        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        Car car = this.carDao.findById(createRentalRequest.getCarId());
        car.setCarStates(CarStates.ON_RENT);//Araç kiralandığı için tipi "kirada" hale getirildi.
        this.carDao.save(car);
        Rental rentPayment = this.rentalDao.save(rental);

        calculateRentPrice(rentPayment);

        return new SuccessResult("Rental added with id: " + rental.getId());
    }

    @Override
    @Transactional
    public Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException {

        checkRentalId(updateRentalRequest.getId());
        checkCarId(updateRentalRequest.getCarId());
        checkCustomerId(updateRentalRequest.getCustomerId());
        checkCityId(updateRentalRequest.getRentalCityId());
        checkCityId(updateRentalRequest.getReturnCityId());

        Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
        Car car = this.carDao.findById(updateRentalRequest.getCarId());
        car.setCarKilometer(updateRentalRequest.getReturnKilometer());
        this.carDao.save(car);
        this.rentalDao.save(rental);

        return new SuccessResult(updateRentalRequest.getId() + " : Rental updated.");
    }

    @Override
    @Transactional
    public Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException {

        checkRentalId(deleteRentalRequest.getId());

        Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
        Car car = this.carDao.findById(rental.getCar().getId());
        car.setCarStates(CarStates.AVAILABLE);
        this.carDao.save(car);
        this.rentalDao.delete(rental);

        return new SuccessResult(deleteRentalRequest.getId() + " : Rental deleted.");
    }

    @Override
    public DataResult<RentalDto> getById(int rentalId) throws BusinessException {

        checkRentalId(rentalId);

        Rental rental = this.rentalDao.getById(rentalId);
        RentalDto rentalDto = this.modelMapperService.forDto().map(rental, RentalDto.class);

        return new SuccessDataResult<RentalDto>(rentalDto, "Rental found.");
    }

    private Result checkRentalId(int rentalId) throws BusinessException {

        if (!this.rentalDao.existsById(rentalId)) {
            throw new BusinessException("Rental not found.");
        }
        return new SuccessResult();
    }

    private Result checkIfCarActive(int carId) throws BusinessException {

        Car car = this.carDao.findById(carId);
        CarStates carStates = car.getCarStates();
        if (carStates.name() != "AVAILABLE") {
            throw new BusinessException("Car is not available.");
        }
        return new SuccessResult();
    }

    private Result checkCarId(int carId) throws BusinessException {

        if (!this.carDao.existsById(carId)) {
            throw new BusinessException("Car not found.");
        }
        return new SuccessResult();
    }

    private Result checkCustomerId(int customerId) throws BusinessException {

        if (!this.customerDao.existsById(customerId)) {
            throw new BusinessException("Customer not found.");
        }
        return new SuccessResult();
    }

    private Result checkCityId(int cityId) throws BusinessException {

        if (!this.cityDao.existsById(cityId)) {
            throw new BusinessException("City not found.");
        }
        return new SuccessResult();
    }

    private Result calculateRentPrice(Rental rentPayment) {

        Rental rental = this.rentalDao.getById(rentPayment.getId());
        Car car = this.carDao.getById(rentPayment.getCar().getId());

        long rentDays = ChronoUnit.DAYS.between(rentPayment.getRentalRentDate(), rentPayment.getRentalReturnDate()) + 1;
        double dropFee = 750;
        double rentPrice = car.getCarDailyPrice() * rentDays;

        if(rentPayment.getRentalCity().getId()==rentPayment.getReturnCity().getId()){
            rental.setRentalPrice(rentPrice);
            this.rentalDao.save(rental);
        }
        if (rentPayment.getRentalCity().getId()!=rentPayment.getReturnCity().getId()){
            rental.setRentalPrice(rentPrice+dropFee);
            this.rentalDao.save(rental);
        }
       return new SuccessResult();
    }
}
