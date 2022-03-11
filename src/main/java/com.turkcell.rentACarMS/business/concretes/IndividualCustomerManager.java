package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.IndividualCustomerService;
import com.turkcell.rentACarMS.business.dtos.IndividualCustomerDto;
import com.turkcell.rentACarMS.business.dtos.ListIndividualCustomerDto;
import com.turkcell.rentACarMS.business.requests.create.CreateIndividualCustomerRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteIndividualCustomerRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateIndividualCustomerRequest;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.*;
import com.turkcell.rentACarMS.dataAccess.abstracts.IndividualCustomerDao;
import com.turkcell.rentACarMS.entities.concretes.IndividualCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

    private IndividualCustomerDao individualCustomerDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapperService modelMapperService){
        this.individualCustomerDao = individualCustomerDao;
        this.modelMapperService = modelMapperService;
    }
    @Override
    public DataResult<List<ListIndividualCustomerDto>> listAll() {
        List<IndividualCustomer> individualCustomers = this.individualCustomerDao.findAll();
        List<ListIndividualCustomerDto> listIndividualCustomerDto = individualCustomers.stream().map(individualCustomer -> this.modelMapperService
                .forDto().map(individualCustomer, ListIndividualCustomerDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<ListIndividualCustomerDto>>(listIndividualCustomerDto,listIndividualCustomerDto.size() + " : Individual Customers found.");
    }

    @Override
    public Result create(CreateIndividualCustomerRequest createIndividualCustomerRequest) {

        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
        this.individualCustomerDao.save(individualCustomer);
        return new SuccessResult("Individual Customer added with id: " + individualCustomer.getId());
    }

    @Override
    public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
        checkIndividualCustomerId(updateIndividualCustomerRequest.getId());
        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);
        this.individualCustomerDao.save(individualCustomer);
        return new SuccessResult(updateIndividualCustomerRequest.getId() + " :Individual Customer updated.");
    }

    @Override
    public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
        if(!checkIndividualCustomerId(deleteIndividualCustomerRequest.getId()).isSuccess()){
            return new ErrorDataResult<IndividualCustomerDto>(checkIndividualCustomerId(deleteIndividualCustomerRequest.getId()).getMessage());
        }
        IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(deleteIndividualCustomerRequest,IndividualCustomer.class);
        this.individualCustomerDao.delete(individualCustomer);
        return new SuccessResult(deleteIndividualCustomerRequest.getId() + " Individual Customer deleted.");

    }

    @Override
    public DataResult<IndividualCustomerDto> getById(int individualCustomerId) {
        if(!checkIndividualCustomerId(individualCustomerId).isSuccess()){
            return new ErrorDataResult<IndividualCustomerDto>(checkIndividualCustomerId(individualCustomerId).getMessage());
        }
        IndividualCustomer individualCustomer = this.individualCustomerDao.getById(individualCustomerId);
        IndividualCustomerDto individualCustomerDto = this.modelMapperService.forDto().map(individualCustomer,IndividualCustomerDto.class);
        return new SuccessDataResult<IndividualCustomerDto>(individualCustomerDto, " Individual customer found.");

    }
    private Result checkIndividualCustomerId(int individualCustomerId) {
        if (!this.individualCustomerDao.existsById(individualCustomerId)) {
            return new ErrorResult("Individual Customer not found.");
        }
        return new SuccessResult();
    }
}
