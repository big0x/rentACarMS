package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.CarDto;
import com.turkcell.rentACarMS.business.dtos.ListCarDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCarRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCarRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCarRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface CarService {

    DataResult<List<ListCarDto>> listAll();
    Result create(CreateCarRequest createCarRequest);
    Result update(UpdateCarRequest updateCarRequest);
    Result delete(DeleteCarRequest deleteCarRequest);
    DataResult<CarDto> getById(int carId);
    DataResult<List<ListCarDto>> getAllPaged(int pageNo,int pageSize);
    DataResult<List<ListCarDto>> getAllDescSorted();
    DataResult<List<ListCarDto>> getAllAscSorted();
    DataResult<List<ListCarDto>> findByCarDailyPriceLessThanEqual(double dailyPrice);
}
