package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.CustomerService;
import com.turkcell.rentACarMS.business.dtos.CustomerDto;
import com.turkcell.rentACarMS.business.dtos.ListCustomerDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCustomerRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCustomerRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCustomerRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("api/customers")
public class CustomersController {
    private CustomerService customerService;

    @Autowired
    public CustomersController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/listallcustomers")
    public DataResult<List<ListCustomerDto>> listAll() {
        return this.customerService.listAll();
    }
    @PostMapping("/createcustomer")
    public Result create(@RequestBody @Valid CreateCustomerRequest createCustomerRequest){
        return this.customerService.create(createCustomerRequest);
    }
    @DeleteMapping("/deletecustomer")
    public Result delete(@RequestBody @Valid DeleteCustomerRequest deleteCustomerRequest){
        return this.customerService.delete(deleteCustomerRequest);
    }
    @PutMapping("/updatecustomer")
    public Result update(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest){
        return this.customerService.update(updateCustomerRequest);
    }

    @GetMapping("/getbycustomerid")
    public DataResult<CustomerDto> getById(@RequestParam int customerId){
        return this.customerService.getById(customerId);
    }
}
