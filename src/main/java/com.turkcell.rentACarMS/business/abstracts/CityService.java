package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.ListCityDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCityRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface CityService {
    DataResult<List<ListCityDto>> listAll();
    Result create(CreateCityRequest createCityRequest);
}
