package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.CarService;
import com.turkcell.rentACarMS.business.dtos.CarDto;
import com.turkcell.rentACarMS.business.dtos.ListCarDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCarRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCarRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCarRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public DataResult<List<ListCarDto>> listAll() {
        return this.carService.listAll();
    }
    @PostMapping("/createcar")
    public Result create(@RequestBody @Valid CreateCarRequest createCarRequest){
        return this.carService.create(createCarRequest);
    }
    @DeleteMapping("/deletecar")
    public Result delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest){
        return this.carService.delete(deleteCarRequest);
    }
    @PutMapping("/updatecar")
    public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest){
        return this.carService.update(updateCarRequest);
    }

    @GetMapping("/getbycarid")
    public DataResult<CarDto> getById(@RequestParam int carId){
        return this.carService.getById(carId);
    }
    @GetMapping("/getallpaged")
    DataResult<List<ListCarDto>> getAllPaged(int pageNo,int pageSize){
        return this.carService.getAllPaged(pageNo, pageSize);
    }
    @GetMapping("/getalldescsorted")
    DataResult<List<ListCarDto>> getAllDescSorted(){
        return this.carService.getAllDescSorted();
    }
    @GetMapping("/getallascsorted")
    DataResult<List<ListCarDto>> getAllAscSorted(){
        return this.carService.getAllAscSorted();
    }
    @GetMapping("/findByCarDailyPriceLessThanEqual")
    DataResult<List<ListCarDto>> findByCarDailyPriceLessThanEqual(double dailyPrice) {
        return this.carService.findByCarDailyPriceLessThanEqual(dailyPrice);
    }
}

