package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.CarDamageService;
import com.turkcell.rentACarMS.business.dtos.CarDamageDto;
import com.turkcell.rentACarMS.business.dtos.ListCarDamageDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCarDamageRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCarDamageRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCarDamageRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("api/cardamages")
public class CarDamagesController {

    private CarDamageService carDamageService;

    @Autowired
    public CarDamagesController(CarDamageService carDamageService) {

        this.carDamageService = carDamageService;

    }

    @GetMapping("/listallcardamages")
    public DataResult<List<ListCarDamageDto>> listAll() throws BusinessException {
        return this.carDamageService.listAll();
    }
    @PostMapping("/createcardamage")
    public Result create(@RequestBody @Valid CreateCarDamageRequest createCarDamageRequest) throws BusinessException {
        return this.carDamageService.create(createCarDamageRequest);
    }
    @DeleteMapping("/deletecardamage")
    public Result delete(@RequestBody @Valid DeleteCarDamageRequest deleteCarDamageRequest) throws BusinessException {
        return this.carDamageService.delete(deleteCarDamageRequest);
    }
    @PutMapping("/updatecardamage")
    public Result update(@RequestBody @Valid UpdateCarDamageRequest updateCarDamageRequest) throws BusinessException {
        return this.carDamageService.update(updateCarDamageRequest);
    }

    @GetMapping("/getbycardamageid")
    public DataResult<CarDamageDto> getById(@RequestParam int carDamageId) throws BusinessException {
        return this.carDamageService.getById(carDamageId);
    }
}
