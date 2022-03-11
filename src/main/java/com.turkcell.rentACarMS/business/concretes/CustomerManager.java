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
import com.turkcell.rentACarMS.entities.concretes.IndividualCustomer;
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
        List<IndividualCustomer> individualCustomers =this.customerDao.findAll();
        if(!checkCustomerListEmpty(individualCustomers).isSuccess()){
            return new ErrorDataResult<List<ListCustomerDto>>(checkCustomerListEmpty(individualCustomers).getMessage());
        }
        List<ListCustomerDto> listCustomerDto = individualCustomers.stream().map(individualCustomer -> this.modelMapperService
                .forDto().map(individualCustomer, ListCustomerDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListCustomerDto>>(listCustomerDto,listCustomerDto.size() + " : Customers found.");
    }

    @Override
    public Result create(CreateCustomerRequest createCustomerRequest) {
        if(!checkCustomerEmail(createCustomerRequest.getUserEmail()).isSuccess()){
            return new ErrorResult(checkCustomerEmail(createCustomerRequest.getUserEmail()).getMessage());
        }
        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createCustomerRequest, IndividualCustomer.class);
        this.customerDao.save(individualCustomer);
        return new SuccessResult("Customer added with id: " + individualCustomer.getId());
    }

    @Override
    public Result update(UpdateCustomerRequest updateCustomerRequest) {
        if(!checkCustomerId(updateCustomerRequest.getId()).isSuccess()){
            return new ErrorResult(checkCustomerId(updateCustomerRequest.getId()).getMessage());
        }
        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(updateCustomerRequest, IndividualCustomer.class);
        this.customerDao.save(individualCustomer);
        return new SuccessResult(updateCustomerRequest.getId() + " : Customer updated.");
    }

    @Override
    public Result delete(DeleteCustomerRequest deleteCustomerRequest) {
        if (!checkCustomerId(deleteCustomerRequest.getId()).isSuccess()){
            return new ErrorDataResult<CustomerDto>(checkCustomerId(deleteCustomerRequest.getId()).getMessage());
        }
        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(deleteCustomerRequest, IndividualCustomer.class);
        this.customerDao.delete(individualCustomer);
        return new SuccessResult(deleteCustomerRequest.getId() + " : Customer deleted.");
    }

    @Override
    public DataResult<CustomerDto> getById(int customerId) {
        if(!checkCustomerId(customerId).isSuccess()){
            return new ErrorDataResult<CustomerDto>(checkCustomerId(customerId).getMessage());
        }
        IndividualCustomer individualCustomer = this.customerDao.getById(customerId);
        CustomerDto customerDto = this.modelMapperService.forDto().map(individualCustomer, CustomerDto.class);
        return new SuccessDataResult<CustomerDto>(customerDto,"Customer found.");
    }
    private Result checkCustomerId(int customerId) {
        if (!this.customerDao.existsById(customerId)) {
            return new ErrorResult("Customer not found.");
        }
        return new SuccessResult();
    }
    private Result checkCustomerListEmpty(List<IndividualCustomer> individualCustomers){
        if (individualCustomers.isEmpty()){
            return new ErrorDataResult<List<IndividualCustomer>>("Customer list is empty.");
        }
        return new SuccessDataResult<>();
    }
    private Result checkCustomerEmail(String email){
        if(this.customerDao.existsByEmail(email)){
            return new ErrorResult("This customer already exists.");
        }
        return new SuccessResult();
    }
}
