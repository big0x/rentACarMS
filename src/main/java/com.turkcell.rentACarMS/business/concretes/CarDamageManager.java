package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.CarDamageService;
import com.turkcell.rentACarMS.business.constants.Messages;
import com.turkcell.rentACarMS.business.dtos.CarDamageDto;
import com.turkcell.rentACarMS.business.dtos.ListCarDamageDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCarDamageRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCarDamageRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCarDamageRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import com.turkcell.rentACarMS.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACarMS.core.utilities.results.SuccessResult;
import com.turkcell.rentACarMS.dataAccess.abstracts.CarDamageDao;
import com.turkcell.rentACarMS.entities.concretes.CarDamage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarDamageManager implements CarDamageService {

    private CarDamageDao carDamageDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService) {
        this.carDamageDao = carDamageDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ListCarDamageDto>> listAll() {

        List<CarDamage> carDamages = this.carDamageDao.findAll();
        List<ListCarDamageDto> listCarDamageDto = carDamages.stream().map(carDamage -> this.modelMapperService
                .forDto().map(carDamage, ListCarDamageDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ListCarDamageDto>>(listCarDamageDto,listCarDamageDto.size() + " : " + Messages.CARDAMAGEFOUND);
    }

    @Override
    public Result create(CreateCarDamageRequest createCarDamageRequest) {

        CarDamage carDamage = this.modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);
        this.carDamageDao.save(carDamage);

        return new SuccessResult(Messages.CARDDAMAGEADDED);

    }

    @Override
    public Result update(UpdateCarDamageRequest updateCarDamageRequest) {

        checkCarDamageId(updateCarDamageRequest.getId());

        CarDamage carDamage = this.modelMapperService.forRequest().map(updateCarDamageRequest, CarDamage.class);
        this.carDamageDao.save(carDamage);

        return new SuccessResult(Messages.CARDAMAGEUPDATED);

    }

    @Override
    public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) {

        checkCarDamageId(deleteCarDamageRequest.getId());

        CarDamage carDamage = this.modelMapperService.forRequest().map(deleteCarDamageRequest, CarDamage.class);
        this.carDamageDao.delete(carDamage);

        return new SuccessResult(Messages.CARDAMAGEDELETED);
    }

    @Override
    public DataResult<CarDamageDto> getById(int carDamageId) {

        checkCarDamageId(carDamageId);

        CarDamage carDamage = this.carDamageDao.getById(carDamageId);
        CarDamageDto carDamageDto = this.modelMapperService.forDto().map(carDamage,CarDamageDto.class);

        return new SuccessDataResult<CarDamageDto>(carDamageDto,Messages.CARDAMAGEFOUND);
    }

    private Result checkCarDamageId(int carDamageId) throws BusinessException {

        if (!this.carDamageDao.existsById(carDamageId)){
            throw new BusinessException(Messages.CARDAMAGENOTFOUND);
        }
        return new SuccessResult();
    }
}
