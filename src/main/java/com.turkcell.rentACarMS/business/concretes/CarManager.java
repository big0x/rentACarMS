package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.CarService;
import com.turkcell.rentACarMS.business.constants.Messages;
import com.turkcell.rentACarMS.business.dtos.CarDto;
import com.turkcell.rentACarMS.business.dtos.ListCarDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCarRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCarRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCarRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import com.turkcell.rentACarMS.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACarMS.core.utilities.results.SuccessResult;
import com.turkcell.rentACarMS.dataAccess.abstracts.BrandDao;
import com.turkcell.rentACarMS.dataAccess.abstracts.CarDao;
import com.turkcell.rentACarMS.dataAccess.abstracts.CityDao;
import com.turkcell.rentACarMS.dataAccess.abstracts.ColorDao;
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
    private BrandDao brandDao;
    private ColorDao colorDao;
    private CityDao cityDao;

    @Autowired
    public CarManager(CarDao carDao, ModelMapperService modelMapperService, BrandDao brandDao, ColorDao colorDao, CityDao cityDao) {
        this.carDao = carDao;
        this.modelMapperService = modelMapperService;
        this.cityDao = cityDao;
        this.colorDao = colorDao;
        this.brandDao = brandDao;
    }

    @Override
    public DataResult<List<ListCarDto>> listAll() {

        List<Car> cars = this.carDao.findAll();
        List<ListCarDto> listCarDto = cars.stream().map(car -> this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ListCarDto>>(listCarDto,listCarDto.size() + " : " + Messages.CARFOUND);
    }

    @Override
    public Result create(CreateCarRequest createCarRequest) throws BusinessException {

        checkBrandId(createCarRequest.getBrandId());
        checkColorId(createCarRequest.getColorId());
        checkCityId(createCarRequest.getCityId());

        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
        this.carDao.save(car);

        return new SuccessResult(Messages.CARADDED);
    }

    @Override
    public Result update(UpdateCarRequest updateCarRequest) throws BusinessException {

        checkCarId(updateCarRequest.getId());
        checkBrandId(updateCarRequest.getBrandId());
        checkColorId(updateCarRequest.getColorId());
        checkCityId(updateCarRequest.getCityId());

        Car car = this.modelMapperService.forRequest().map(updateCarRequest,Car.class);
        this.carDao.save(car);

        return new SuccessResult(Messages.CARUPDATED);

    }

    @Override
    public Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException {

        checkCarId(deleteCarRequest.getId());

        Car car = this.modelMapperService.forRequest().map(deleteCarRequest, Car.class);
        this.carDao.delete(car);

        return new SuccessResult(Messages.CARDELETED);
    }

    @Override
    public DataResult<CarDto> getById(int carId) throws BusinessException {

        checkCarId(carId);

        Car car = this.carDao.getById(carId);
        CarDto carDto = this.modelMapperService.forDto().map(car, CarDto.class);

        return new SuccessDataResult<CarDto>(carDto,Messages.CARFOUND);
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
//      if(cars.isEmpty()) {return new ErrorDataResult<List<ListCarDto>>(null, "There is no results.");}
        List<ListCarDto> listCarDto = cars.stream().map(car -> this.modelMapperService.forDto().map(car,ListCarDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ListCarDto>>(listCarDto,listCarDto.size() + " : " + Messages.CARFOUND);
    }

    private Result checkCarId(int carId) throws BusinessException {

        if (!this.carDao.existsById(carId)) {
            throw new BusinessException(Messages.CARNOTFOUND);
        }
        return new SuccessResult();
    }
    private Result checkBrandId(int brandId) throws BusinessException {

        if (!this.brandDao.existsById(brandId)){
            throw new BusinessException(Messages.BRANDNOTFOUND);
        }
        return new SuccessResult();
    }
    private Result checkColorId(int colorId) throws BusinessException {

        if (!this.colorDao.existsById(colorId)) {
            throw new BusinessException(Messages.COLORNOTFOUND);
        }
        return new SuccessResult();
    }
    private Result checkCityId(int cityId) throws BusinessException{

        if(!this.cityDao.existsById(cityId)){
            throw new BusinessException(Messages.CITYNOTFOUND);
        }
        return new SuccessResult();
    }
}
