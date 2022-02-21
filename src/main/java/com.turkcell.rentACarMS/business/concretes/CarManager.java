package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.CarService;
import com.turkcell.rentACarMS.business.dtos.CarDto;
import com.turkcell.rentACarMS.business.dtos.ListCarDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCarRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCarRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCarRequest;
import com.turkcell.rentACarMS.core.concretes.BusinessException;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.dataAccess.abstracts.CarDao;
import com.turkcell.rentACarMS.entities.concretes.Car;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ListCarDto> listAll() {
        var result = this.carDao.findAll();

        List<ListCarDto> response = result.stream().map(car -> this.modelMapperService
                .forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
        return response;
    }

    @Override
    public void create(CreateCarRequest createCarRequest) throws BusinessException{
        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
        this.carDao.save(car);

    }

    @Override
    public void update(UpdateCarRequest updateCarRequest) throws BusinessException {
        Car car = this.modelMapperService.forRequest().map(updateCarRequest,Car.class);
        checkCarId(updateCarRequest.getCarId());
        this.carDao.save(car);

    }

    @Override
    public void delete(DeleteCarRequest deleteCarRequest) throws BusinessException {
        Car car = this.modelMapperService.forRequest().map(deleteCarRequest, Car.class);
        checkCarId(car.getCarId());
        this.carDao.delete(car);
    }

    @Override
    public CarDto getById(int carId) throws BusinessException {
        checkCarId(carId);
        Car result = this.carDao.getById(carId);
        CarDto response = this.modelMapperService.forDto().map(result, CarDto.class);
        return response;
    }

    private void checkCarId(int carId) throws BusinessException {
        if (!this.carDao.existsByCarId(carId)) {
            throw new BusinessException("Car not found.");
        }
    }
}
