package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.CarMaintenanceService;
import com.turkcell.rentACarMS.business.dtos.CarMaintenanceDto;
import com.turkcell.rentACarMS.business.dtos.ListCarMaintenanceDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCarMaintenanceRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCarMaintenanceRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCarMaintenanceRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.*;
import com.turkcell.rentACarMS.dataAccess.abstracts.CarDao;
import com.turkcell.rentACarMS.dataAccess.abstracts.CarMaintenanceDao;
import com.turkcell.rentACarMS.entities.concretes.Car;
import com.turkcell.rentACarMS.entities.concretes.CarMaintenance;
import com.turkcell.rentACarMS.entities.enums.CarStates;
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
        List<ListCarMaintenanceDto> listCarMaintenanceDto = carMaintenances.stream().map(carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, ListCarMaintenanceDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ListCarMaintenanceDto>>(listCarMaintenanceDto,listCarMaintenanceDto.size()+" : Car maintenance found.");
    }

    @Override
    public Result create(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException {

        checkCarId(createCarMaintenanceRequest.getCarId());
        checkIfCarActive(createCarMaintenanceRequest.getCarId());

        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
        Car car = this.carDao.findById(createCarMaintenanceRequest.getCarId());
        car.setCarStates(CarStates.IN_MAINTENANCE);
        this.carMaintenanceDao.save(carMaintenance);
        this.carDao.save(car);

        return new SuccessResult("Car maintenance added with id: " + carMaintenance.getId());
    }

    @Override
    public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException {

        checkCarMaintenanceId(updateCarMaintenanceRequest.getId());

        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
        this.carMaintenanceDao.save(carMaintenance);

        return new SuccessResult("Car maintenance updated.");
    }

    @Override
    public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException {

        checkCarMaintenanceId(deleteCarMaintenanceRequest.getId());

        CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(deleteCarMaintenanceRequest, CarMaintenance.class);
        this.carMaintenanceDao.delete(carMaintenance);

        return new SuccessResult("Car maintenance deleted.");
    }

    @Override
    public DataResult<CarMaintenanceDto> getById(int carMaintenanceId) throws BusinessException {

        checkCarMaintenanceId(carMaintenanceId);

        CarMaintenance carMaintenance = this.carMaintenanceDao.getById(carMaintenanceId);
        CarMaintenanceDto carMaintenanceDto = this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceDto.class);

        return new SuccessDataResult<CarMaintenanceDto>(carMaintenanceDto,"Car maintenance found.");
    }
    private Result checkCarId(int carId) throws BusinessException{

        if(!this.carDao.existsById(carId)){
            throw new BusinessException("Car not found.");
        }
        return new SuccessResult();
    }

    private Result checkCarMaintenanceId(int carMaintenanceId) throws BusinessException {

        if (!this.carMaintenanceDao.existsById(carMaintenanceId)){
            throw new BusinessException("Car maintenance not found.");
        }
        return new SuccessResult();
    }
    private Result checkIfCarActive(int carId) throws BusinessException {

        Car car = this.carDao.findById(carId);
        CarStates carStates = car.getCarStates();

        if (carStates.name()=="AVAILABLE"){
            return new SuccessResult();
        }
        throw new BusinessException("Car is not available.");
    }
}
