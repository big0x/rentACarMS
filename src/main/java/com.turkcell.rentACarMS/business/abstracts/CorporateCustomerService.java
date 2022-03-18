package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.CorporateCustomerDto;
import com.turkcell.rentACarMS.business.dtos.ListCorporateCustomerDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCorporateCustomerRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCorporateCustomerRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCorporateCustomerRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface CorporateCustomerService {
    DataResult<List<ListCorporateCustomerDto>> listAll();
    Result create(CreateCorporateCustomerRequest createCorporateCustomerRequest);
    Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
    Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);
    DataResult<CorporateCustomerDto> getById(int corporateCustomerId);
}
