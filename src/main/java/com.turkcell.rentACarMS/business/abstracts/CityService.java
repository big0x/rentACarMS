package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.CityDto;
import com.turkcell.rentACarMS.business.dtos.ListCityDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCityRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCityRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCityRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface CityService {
    DataResult<List<ListCityDto>> listAll() throws BusinessException;
    Result create(CreateCityRequest createCityRequest)throws BusinessException;
    Result update(UpdateCityRequest updateCityRequest)throws BusinessException;
    Result delete(DeleteCityRequest deleteCityRequest)throws BusinessException;
    DataResult<CityDto> getById(int cityId) throws BusinessException;
}
