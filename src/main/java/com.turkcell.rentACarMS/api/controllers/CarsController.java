package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.CarService;
import com.turkcell.rentACarMS.business.dtos.CarDto;
import com.turkcell.rentACarMS.business.dtos.ListCarDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCarRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCarRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCarRequest;
import com.turkcell.rentACarMS.core.concretes.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarsController {
    private CarService carService;

    @Autowired
    public CarsController(CarService carService) {

        this.carService = carService;
    }

    @GetMapping("/listallcars")
    public List<ListCarDto> listAll() {

        return this.carService.listAll();
    }

    @PostMapping("/createcar")
    public void create(@RequestBody CreateCarRequest createCarRequest) throws BusinessException {
        this.carService.create(createCarRequest);
    }
    @DeleteMapping("/deletecar")
    public void delete(@RequestBody DeleteCarRequest deleteCarRequest) throws BusinessException {
        this.carService.delete(deleteCarRequest);
    }
    @PutMapping("/updatecar")
    public void update(@RequestBody UpdateCarRequest updateCarRequest) throws BusinessException{
        this.carService.update(updateCarRequest);
    }

    @GetMapping("/getbycarid")
    public CarDto getById(@RequestParam int carId) throws BusinessException {
        return this.carService.getById(carId);
    }

}
