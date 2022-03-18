package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.ListRentalDto;
import com.turkcell.rentACarMS.business.dtos.RentalDto;
import com.turkcell.rentACarMS.business.requests.create.CreateRentalRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteRentalRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateRentalRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface RentalService {
    DataResult<List<ListRentalDto>> listAll();
    Result create(CreateRentalRequest createRentalRequest);
    Result update(UpdateRentalRequest updateRentalRequest);
    Result delete(DeleteRentalRequest deleteRentalRequest);
    DataResult<RentalDto> getById(int rentalId);
}
