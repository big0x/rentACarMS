package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.IndividualCustomerDto;
import com.turkcell.rentACarMS.business.dtos.ListIndividualCustomerDto;
import com.turkcell.rentACarMS.business.requests.create.CreateIndividualCustomerRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteIndividualCustomerRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateIndividualCustomerRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface IndividualCustomerService {
    DataResult<List<ListIndividualCustomerDto>> listAll() throws BusinessException;
    Result create(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException;
    Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws BusinessException;
    Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException;
    DataResult<IndividualCustomerDto> getById(int individualCustomerId) throws BusinessException;
}
