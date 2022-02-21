package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.CarDto;
import com.turkcell.rentACarMS.business.dtos.ListCarDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCarRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCarRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCarRequest;
import com.turkcell.rentACarMS.core.concretes.BusinessException;

import java.util.List;

public interface CarService {

    List<ListCarDto> listAll();
    void create(CreateCarRequest createCarRequest) throws BusinessException;
    void update(UpdateCarRequest updateCarRequest) throws BusinessException;
    void delete(DeleteCarRequest deleteCarRequest) throws BusinessException;
    CarDto getById(int carId) throws BusinessException;
}
