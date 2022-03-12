package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.CityService;
import com.turkcell.rentACarMS.business.dtos.CityDto;
import com.turkcell.rentACarMS.business.dtos.ListCityDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCityRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCityRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCityRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import com.turkcell.rentACarMS.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACarMS.core.utilities.results.SuccessResult;
import com.turkcell.rentACarMS.dataAccess.abstracts.CityDao;
import com.turkcell.rentACarMS.entities.concretes.City;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityManager implements CityService {
    private CityDao cityDao;
    private ModelMapperService modelMapperService;

    public CityManager(CityDao cityDao,ModelMapperService modelMapperService){
        this.cityDao= cityDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ListCityDto>> listAll() {

        List<City> cities = this.cityDao.findAll();
        List<ListCityDto> listCityDto = cities.stream().map(city -> this.modelMapperService.forDto().map(city, ListCityDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ListCityDto>>(listCityDto,listCityDto.size() + " : Cities found.");
    }

    @Override
    public Result create(CreateCityRequest createCityRequest) throws BusinessException {

        checkCityName(createCityRequest.getCityName());

        City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);
        this.cityDao.save(city);

        return new SuccessDataResult<CreateCityRequest>(createCityRequest, "City added : " + city.getCityName());
    }

    @Override
    public Result update(UpdateCityRequest updateCityRequest) throws BusinessException {

        checkCityId(updateCityRequest.getId());

        City city = this.modelMapperService.forRequest().map(updateCityRequest, City.class);
        this.cityDao.save(city);

        return new SuccessDataResult<UpdateCityRequest>(updateCityRequest,"City updated : " + city.getCityName());
    }

    @Override
    public Result delete(DeleteCityRequest deleteCityRequest) throws BusinessException {

        checkCityId(deleteCityRequest.getId());

        City city = this.modelMapperService.forRequest().map(deleteCityRequest, City.class);
        this.cityDao.delete(city);

        return new SuccessResult(deleteCityRequest.getId() + " : City deleted.");
    }

    @Override
    public DataResult<CityDto> getById(int cityId) throws BusinessException {

        checkCityId(cityId);

        City city = this.cityDao.getById(cityId);
        CityDto cityDto = this.modelMapperService.forDto().map(city,CityDto.class);

        return new SuccessDataResult<CityDto>(cityDto,"City found.");
    }
    private Result checkCityId(int cityId) throws BusinessException{

        if(!this.cityDao.existsById(cityId)){
            throw new BusinessException("City not found.");
        }
        return new SuccessResult();
    }
    private Result checkCityName(String cityName)throws BusinessException{
        if(this.cityDao.existsByCityName(cityName)){
            throw new BusinessException("City already exists.");
        }
        return new SuccessResult();
    }
}
