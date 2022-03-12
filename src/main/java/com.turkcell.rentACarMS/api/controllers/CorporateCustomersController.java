package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.CorporateCustomerService;
import com.turkcell.rentACarMS.business.dtos.CorporateCustomerDto;
import com.turkcell.rentACarMS.business.dtos.ListCorporateCustomerDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCorporateCustomerRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCorporateCustomerRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCorporateCustomerRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/corporate_customers")
public class CorporateCustomersController {
    private CorporateCustomerService corporateCustomerService;

    @Autowired
    public CorporateCustomersController(CorporateCustomerService corporateCustomerService){
        this.corporateCustomerService = corporateCustomerService;
    }

    @GetMapping("/listallcorporatecustomers")
    public DataResult<List<ListCorporateCustomerDto>> listAll() throws BusinessException {
        return this.corporateCustomerService.listAll();
    }
    @PostMapping("/createcorporatecustomer")
    public Result create(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException {
        return this.corporateCustomerService.create(createCorporateCustomerRequest);
    }
    @DeleteMapping("/deletecorporatecustomer")
    public Result delete(@RequestBody @Valid DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException {
        return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
    }
    @PutMapping("/updatecorporatecustomer")
    public Result update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException {
        return this.corporateCustomerService.update(updateCorporateCustomerRequest);
    }

    @GetMapping("/getbycorporatecustomerid")
    public DataResult<CorporateCustomerDto> getById(@RequestParam int corporateCustomerId) throws BusinessException {
        return this.corporateCustomerService.getById(corporateCustomerId);
    }
}
