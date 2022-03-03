package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.CarService;
import com.turkcell.rentACarMS.business.dtos.CarDto;
import com.turkcell.rentACarMS.business.dtos.ListCarDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCarRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCarRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCarRequest;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.*;
import com.turkcell.rentACarMS.dataAccess.abstracts.CarDao;
import com.turkcell.rentACarMS.entities.concretes.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CarManager implements CarService {
    private CarDao carDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
        this.carDao = carDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ListCarDto>> listAll() {
        List<Car> cars = this.carDao.findAll();
        if (!checkCarListEmpty(cars).isSuccess()){
            return new ErrorDataResult<List<ListCarDto>>(checkCarListEmpty(cars).getMessage());
        }
        List<ListCarDto> listCarDto = cars.stream().map(car -> this.modelMapperService
                .forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListCarDto>>(listCarDto,"Data Listed.");
    }

    @Override
    public Result create(CreateCarRequest createCarRequest) {
        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
        this.carDao.save(car);
        return new SuccessResult("Car Added with id: " + car.getCarId());
    }

    @Override
    public Result update(UpdateCarRequest updateCarRequest) {
        Car car = this.modelMapperService.forRequest().map(updateCarRequest,Car.class);
        checkCarId(updateCarRequest.getCarId());
        this.carDao.save(car);
        return new SuccessResult("Car.Updated");

    }

    @Override
    public Result delete(DeleteCarRequest deleteCarRequest) {
        if (!checkCarId(deleteCarRequest.getCarId()).isSuccess()){
            return new ErrorDataResult<CarDto>(checkCarId(deleteCarRequest.getCarId()).getMessage());
        }
        Car car = this.modelMapperService.forRequest().map(deleteCarRequest, Car.class);
        checkCarId(car.getCarId());
        this.carDao.delete(car);
        return new SuccessResult("Car.Deleted");
    }

    @Override
    public DataResult<CarDto> getById(int carId) {
        if(!checkCarId(carId).isSuccess()){
            return new ErrorDataResult<CarDto>(checkCarId(carId).getMessage());
        }
        Car car = this.carDao.getById(carId);
        CarDto carDto = this.modelMapperService.forDto().map(car, CarDto.class);
        return new SuccessDataResult<CarDto>(carDto);
    }

    @Override
    public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        List<Car> result = this.carDao.findAll(pageable).getContent();
        List<ListCarDto> response = result.stream().map(car -> this.modelMapperService.forDto().map(car,ListCarDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListCarDto>>(response);
    }

    @Override
    public DataResult<List<ListCarDto>> getAllDescSorted() {
        Sort sort = Sort.by(Sort.Direction.DESC,"carDailyPrice");
        List<Car> descCars = this.carDao.findAll(sort);
        List<ListCarDto> listCarDto = descCars.stream().map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListCarDto>>(listCarDto);
    }

    @Override
    public DataResult<List<ListCarDto>> getAllAscSorted() {
        Sort sort = Sort.by(Sort.Direction.ASC,"carDailyPrice");
        List<Car> ascCars = this.carDao.findAll(sort);
        List<ListCarDto> listCarDto = ascCars.stream().map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListCarDto>>(listCarDto);
    }

    @Override
    public DataResult<List<ListCarDto>> findByCarDailyPriceLessThanEqual(double dailyPrice) {
        List<Car> cars = this.carDao.findByCarDailyPriceLessThanEqual(dailyPrice);
        if(cars.isEmpty()){
            return new ErrorDataResult<List<ListCarDto>>(null,"There is no results.");
        }
        List<ListCarDto> listCarDto = cars.stream().map(car -> this.modelMapperService.forDto().map(car,ListCarDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListCarDto>>(listCarDto);
    }

    private Result checkCarId(int carId) {
        if (!this.carDao.existsByCarId(carId)) {
            return new ErrorResult("Car not found.");
        }
        return new SuccessResult();
    }
    private Result checkCarListEmpty(List<Car> cars){
        if (cars.isEmpty()){
            return new ErrorDataResult<List<Car>>("Car list is empty.");
        }
        return new SuccessResult();
    }
}
