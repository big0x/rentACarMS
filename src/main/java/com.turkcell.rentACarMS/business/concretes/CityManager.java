package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.CityService;
import com.turkcell.rentACarMS.business.dtos.ListCityDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCityRequest;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import com.turkcell.rentACarMS.core.utilities.results.SuccessDataResult;
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
        List<ListCityDto> listCityDto = cities.stream().map(city -> this.modelMapperService
                .forDto().map(city, ListCityDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListCityDto>>(listCityDto,listCityDto.size() + " : Cities found.");
    }

    @Override
    public Result create(CreateCityRequest createCityRequest) {
        City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);
        this.cityDao.save(city);
        return new SuccessDataResult<CreateCityRequest>(createCityRequest, "Data added : " + city.getCityName());
    }
}
