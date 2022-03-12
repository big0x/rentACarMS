package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.RentalService;
import com.turkcell.rentACarMS.business.dtos.ListRentalDto;
import com.turkcell.rentACarMS.business.dtos.RentalDto;
import com.turkcell.rentACarMS.business.requests.create.CreateRentalRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteRentalRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateRentalRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/rentals")
public class RentalsController {
    private RentalService rentalService;

    @Autowired
    public RentalsController(RentalService rentalService){
        this.rentalService = rentalService;
    }
    @GetMapping("/listallrentals")
    public DataResult<List<ListRentalDto>> listAll() throws BusinessException {
        return this.rentalService.listAll();
    }
    @PostMapping("/createrental")
    public Result create(@RequestBody @Valid CreateRentalRequest createRentalRequest) throws BusinessException {
        return this.rentalService.create(createRentalRequest);
    }
    @DeleteMapping("/deleterental")
    public Result delete(@RequestBody @Valid DeleteRentalRequest deleteRentalRequest) throws BusinessException {
        return this.rentalService.delete(deleteRentalRequest);
    }
    @PutMapping("/updaterental")
    public Result update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) throws BusinessException {
        return this.rentalService.update(updateRentalRequest);
    }

    @GetMapping("/getbyrentalid")
    public DataResult<RentalDto> getById(@RequestParam int rentalId) throws BusinessException {
        return this.rentalService.getById(rentalId);
    }

}
