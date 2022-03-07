package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.CarMaintenanceService;
import com.turkcell.rentACarMS.business.dtos.CarMaintenanceDto;
import com.turkcell.rentACarMS.business.dtos.ListCarMaintenanceDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCarMaintenanceRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCarMaintenanceRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCarMaintenanceRequest;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.*;
import com.turkcell.rentACarMS.dataAccess.abstracts.CarDao;
import com.turkcell.rentACarMS.dataAccess.abstracts.CarMaintenanceDao;
import com.turkcell.rentACarMS.entities.concretes.Car;
import com.turkcell.rentACarMS.entities.concretes.CarMaintenance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CarMaintenanceManager implements CarMaintenanceService {

    private CarDao carDao;
    private CarMaintenanceDao carMaintenanceDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService, CarDao carDao){
        this.carMaintenanceDao = carMaintenanceDao;
        this.modelMapperService = modelMapperService;
        this.carDao = carDao;
    }

    @Override
    public DataResult<List<ListCarMaintenanceDto>> listAll() {
        List<CarMaintenance> carMaintenances = this.carMaintenanceDao.findAll();
        if(!checkCarMaintanceListEmpty(carMaintenances).isSuccess()){
            return new ErrorDataResult<List<ListCarMaintenanceDto>>(checkCarMaintanceListEmpty(carMaintenances).getMessage());
        }
        List<ListCarMaintenanceDto> listCarMaintenanceDto = carMaintenances.stream().map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, ListCarMaintenanceDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListCarMaintenanceDto>>(listCarMaintenanceDto,listCarMaintenanceDto.size()+" : Car maintenance found.");
    }

    @Override
    public Result create(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
        carMaintenance.setId(0);
        Car car = this.carDao.findById(createCarMaintenanceRequest.getCarId());
        if(checkIfCarActive(car.getId()).isSuccess()){
            car.setActive(false);//Araç bakıma girdiği için pasif hale getirildi.
            this.carDao.save(car);
            this.carMaintenanceDao.save(carMaintenance);
            return new SuccessResult("Car maintenance added with id: " + carMaintenance.getId());
        }
        return new ErrorResult(checkIfCarActive(createCarMaintenanceRequest.getCarId()).getMessage());
    }

    @Override
    public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
        checkCarMaintenanceId(updateCarMaintenanceRequest.getCarMaintenanceId());
        this.carMaintenanceDao.save(carMaintenance);
        return new SuccessResult("Car maintenance updated.");
    }

    @Override
    public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
        if (!checkCarMaintenanceId(deleteCarMaintenanceRequest.getCarMaintenanceId()).isSuccess()){
            return new ErrorDataResult<CarMaintenanceDto>(checkCarMaintenanceId(deleteCarMaintenanceRequest.getCarMaintenanceId()).getMessage());
        }
        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(deleteCarMaintenanceRequest, CarMaintenance.class);
        checkCarMaintenanceId(carMaintenance.getId());
        this.carMaintenanceDao.delete(carMaintenance);
        return new SuccessResult("Car maintenance deleted.");
    }

    @Override
    public DataResult<CarMaintenanceDto> getById(int carMaintenanceId) {
        if(!checkCarMaintenanceId(carMaintenanceId).isSuccess()){
            return new ErrorDataResult<CarMaintenanceDto>(checkCarMaintenanceId(carMaintenanceId).getMessage());
        }
        CarMaintenance carMaintenance = this.carMaintenanceDao.getById(carMaintenanceId);
        CarMaintenanceDto carMaintenanceDto = this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceDto.class);
        return new SuccessDataResult<CarMaintenanceDto>(carMaintenanceDto,"Car maintenance found.");
    }

    private Result checkCarMaintenanceId(int carMaintenanceId) {
        if (!this.carMaintenanceDao.existsById(carMaintenanceId)){
            return new ErrorResult("Car maintenance not found.");
        }
        return new SuccessResult();
    }
    private Result checkCarMaintanceListEmpty(List<CarMaintenance> carMaintenances){
        if(carMaintenances.isEmpty()){
            return new ErrorDataResult<List<CarMaintenance>>("Car maintenance list is empty.");
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
