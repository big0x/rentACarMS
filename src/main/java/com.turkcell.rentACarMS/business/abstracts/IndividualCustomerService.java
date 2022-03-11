package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.IndividualCustomerDto;
import com.turkcell.rentACarMS.business.dtos.ListIndividualCustomerDto;
import com.turkcell.rentACarMS.business.requests.create.CreateIndividualCustomerRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteIndividualCustomerRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateIndividualCustomerRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface IndividualCustomerService {
    DataResult<List<ListIndividualCustomerDto>> listAll();
    Result create(CreateIndividualCustomerRequest createIndividualCustomerRequest);
    Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
    Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);
    DataResult<IndividualCustomerDto> getById(int individualCustomerId);
}
