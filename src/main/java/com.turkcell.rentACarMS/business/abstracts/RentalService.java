package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.ListRentalDto;
import com.turkcell.rentACarMS.business.dtos.RentalDto;
import com.turkcell.rentACarMS.business.requests.create.CreateRentalRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteRentalRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateRentalRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface RentalService {
    DataResult<List<ListRentalDto>> listAll() throws BusinessException;
    Result create(CreateRentalRequest createRentalRequest) throws BusinessException;
    Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException;
    Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException;
    DataResult<RentalDto> getById(int rentalId) throws BusinessException;
}
