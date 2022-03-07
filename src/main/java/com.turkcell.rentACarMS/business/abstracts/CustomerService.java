package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.CustomerDto;
import com.turkcell.rentACarMS.business.dtos.ListCustomerDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCustomerRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCustomerRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCustomerRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface CustomerService {
    DataResult<List<ListCustomerDto>> listAll();
    Result create(CreateCustomerRequest createCustomerRequest);
    Result update(UpdateCustomerRequest updateCustomerRequest);
    Result delete(DeleteCustomerRequest deleteCustomerRequest);
    DataResult<CustomerDto> getById(int customerId);
}
