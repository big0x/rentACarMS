package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.CustomerService;
import com.turkcell.rentACarMS.business.dtos.CustomerDto;
import com.turkcell.rentACarMS.business.dtos.ListCustomerDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCustomerRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCustomerRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCustomerRequest;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.*;
import com.turkcell.rentACarMS.dataAccess.abstracts.CustomerDao;
import com.turkcell.rentACarMS.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements CustomerService {
    private CustomerDao customerDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CustomerManager(CustomerDao customerDao,ModelMapperService modelMapperService){
        this.customerDao = customerDao;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public DataResult<List<ListCustomerDto>> listAll() {
        List<Customer> customers =this.customerDao.findAll();
        if(!checkCustomerListEmpty(customers).isSuccess()){
            return new ErrorDataResult<List<ListCustomerDto>>(checkCustomerListEmpty(customers).getMessage());
        }
        List<ListCustomerDto> listCustomerDto = customers.stream().map(customer -> this.modelMapperService
                .forDto().map(customer, ListCustomerDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListCustomerDto>>(listCustomerDto,listCustomerDto.size() + " : Customers found.");
    }

    @Override
    public Result create(CreateCustomerRequest createCustomerRequest) {
        Customer customer = this.modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
        this.customerDao.save(customer);
        return new SuccessResult("Customer added with id: " + customer.getId());
    }

    @Override
    public Result update(UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = this.modelMapperService.forRequest().map(updateCustomerRequest,Customer.class);
        checkCustomerId(updateCustomerRequest.getCustomerId());
        this.customerDao.save(customer);
        return new SuccessResult(updateCustomerRequest.getCustomerId() + " : Customer updated.");
    }

    @Override
    public Result delete(DeleteCustomerRequest deleteCustomerRequest) {
        if (!checkCustomerId(deleteCustomerRequest.getCustomerId()).isSuccess()){
            return new ErrorDataResult<CustomerDto>(checkCustomerId(deleteCustomerRequest.getCustomerId()).getMessage());
        }
        Customer customer = this.modelMapperService.forRequest().map(deleteCustomerRequest, Customer.class);
        checkCustomerId(customer.getId());
        this.customerDao.delete(customer);
        return new SuccessResult(deleteCustomerRequest.getCustomerId() + " : Customer deleted.");
    }

    @Override
    public DataResult<CustomerDto> getById(int customerId) {
        if(!checkCustomerId(customerId).isSuccess()){
            return new ErrorDataResult<CustomerDto>(checkCustomerId(customerId).getMessage());
        }
        Customer customer = this.customerDao.getById(customerId);
        CustomerDto customerDto = this.modelMapperService.forDto().map(customer, CustomerDto.class);
        return new SuccessDataResult<CustomerDto>(customerDto,"Customer found.");
    }
    private Result checkCustomerId(int customerId) {
        if (!this.customerDao.existsById(customerId)) {
            return new ErrorResult("Customer not found.");
        }
        return new SuccessResult();
    }
    private Result checkCustomerListEmpty(List<Customer> customers){
        if (customers.isEmpty()){
            return new ErrorDataResult<List<Customer>>("Customer list is empty.");
        }
        return new SuccessDataResult<>();
    }
}
