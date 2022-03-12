package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.CityService;
import com.turkcell.rentACarMS.business.dtos.CityDto;
import com.turkcell.rentACarMS.business.dtos.ListCityDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCityRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCityRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCityRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/listallcitys")
    public DataResult<List<ListCityDto>> listAll() throws BusinessException {
        return this.cityService.listAll();
    }
    @PostMapping("/createcity")
    public Result create(@RequestBody @Valid CreateCityRequest createCityRequest) throws BusinessException {
        return this.cityService.create(createCityRequest);
    }
    @PutMapping("/updatecity")
    public Result update(@RequestBody @Valid UpdateCityRequest updateCityRequest) throws BusinessException {
        return this.cityService.update(updateCityRequest);
    }
    @DeleteMapping("/deletecity")
    public Result delete(@RequestBody @Valid DeleteCityRequest deleteCityRequest) throws BusinessException {
        return this.cityService.delete(deleteCityRequest);
    }
    @GetMapping("/getbycityid")
    public DataResult<CityDto> getById(@RequestParam int cityId) throws BusinessException {
        return this.cityService.getById(cityId);
    }
}
