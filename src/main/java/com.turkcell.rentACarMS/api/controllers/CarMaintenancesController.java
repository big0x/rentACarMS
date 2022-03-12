package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.CarMaintenanceService;
import com.turkcell.rentACarMS.business.dtos.CarMaintenanceDto;
import com.turkcell.rentACarMS.business.dtos.ListCarMaintenanceDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCarMaintenanceRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCarMaintenanceRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCarMaintenanceRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/carmaintenances")
public class CarMaintenancesController {

    private CarMaintenanceService carMaintenanceService;

    @Autowired
    public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
        this.carMaintenanceService = carMaintenanceService;
    }

    @GetMapping("/listallcarmaintenances")
    public DataResult<List<ListCarMaintenanceDto>> listAll() throws BusinessException {
        return this.carMaintenanceService.listAll();
    }
    @PostMapping("/createcarmaintenance")
    public Result create(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException {
        return this.carMaintenanceService.create(createCarMaintenanceRequest);
    }
    @DeleteMapping("/deletecarmaintenance")
    public Result delete(@RequestBody @Valid DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException {
        return this.carMaintenanceService.delete(deleteCarMaintenanceRequest);
    }
    @PutMapping("/updatecarmaintenance")
    public Result update(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException {
        return this.carMaintenanceService.update(updateCarMaintenanceRequest);
    }

    @GetMapping("/getbycarmaintenanceid")
    public DataResult<CarMaintenanceDto> getById(@RequestParam int carMaintenanceId) throws BusinessException {
        return this.carMaintenanceService.getById(carMaintenanceId);
    }
}
