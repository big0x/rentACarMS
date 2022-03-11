package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.CityService;
import com.turkcell.rentACarMS.business.requests.create.CreateCityRequest;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }
    @PostMapping("/create")
    public Result create(@RequestBody @Valid CreateCityRequest createCityRequest){
        return this.cityService.create(createCityRequest);
    }
}
