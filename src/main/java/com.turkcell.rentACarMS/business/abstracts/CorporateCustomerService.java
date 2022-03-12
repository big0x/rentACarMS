package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.CorporateCustomerDto;
import com.turkcell.rentACarMS.business.dtos.ListCorporateCustomerDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCorporateCustomerRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCorporateCustomerRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCorporateCustomerRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface CorporateCustomerService {
    DataResult<List<ListCorporateCustomerDto>> listAll() throws BusinessException;
    Result create(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException;
    Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException;
    Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException;
    DataResult<CorporateCustomerDto> getById(int corporateCustomerId) throws BusinessException;
}
