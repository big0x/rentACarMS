package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.CarDamageDto;
import com.turkcell.rentACarMS.business.dtos.ListCarDamageDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCarDamageRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCarDamageRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCarDamageRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface CarDamageService {
    DataResult<List<ListCarDamageDto>> listAll();
    Result create(CreateCarDamageRequest createCarDamageRequest);
    Result update(UpdateCarDamageRequest updateCarDamageRequest);
    Result delete(DeleteCarDamageRequest deleteCarDamageRequest);
    DataResult<CarDamageDto> getById(int carDamageId);
}
