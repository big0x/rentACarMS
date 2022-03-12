package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.IndividualCustomerService;
import com.turkcell.rentACarMS.business.dtos.IndividualCustomerDto;
import com.turkcell.rentACarMS.business.dtos.ListIndividualCustomerDto;
import com.turkcell.rentACarMS.business.requests.create.CreateIndividualCustomerRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteIndividualCustomerRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateIndividualCustomerRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/individual_customers")
public class IndividualCustomersController {
    private IndividualCustomerService individualCustomerService;

    @Autowired
    public IndividualCustomersController(IndividualCustomerService individualCustomerService){
        this.individualCustomerService = individualCustomerService;
    }
    @GetMapping("/listallindividualcustomers")
    public DataResult<List<ListIndividualCustomerDto>> listAll() throws BusinessException {
        return this.individualCustomerService.listAll();
    }
    @PostMapping("/createindividualcustomer")
    public Result create(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException {
        return this.individualCustomerService.create(createIndividualCustomerRequest);
    }
    @DeleteMapping("/deleteindividualcustomer")
    public Result delete(@RequestBody @Valid DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException {
        return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
    }
    @PutMapping("/updateindividualcustomer")
    public Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws BusinessException {
        return this.individualCustomerService.update(updateIndividualCustomerRequest);
    }

    @GetMapping("/getbyindividualcustomerid")
    public DataResult<IndividualCustomerDto> getById(@RequestParam int individualCustomerId) throws BusinessException {
        return this.individualCustomerService.getById(individualCustomerId);
    }
}
