package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.AdditionalServiceService;
import com.turkcell.rentACarMS.business.dtos.AdditionalServiceDto;
import com.turkcell.rentACarMS.business.dtos.ListAdditionalServiceDto;
import com.turkcell.rentACarMS.business.requests.create.CreateAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateAdditionalServiceRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/additionalservices")
public class AdditionalServicesController {
    private AdditionalServiceService additionalServiceService;

    @Autowired
    public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
        this.additionalServiceService = additionalServiceService;
    }

    @GetMapping("/listalladditionalservices")
    public DataResult<List<ListAdditionalServiceDto>> listAll() {
        return this.additionalServiceService.listAll();
    }
    @PostMapping("/createadditionalservice")
    public Result create(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest){
        return this.additionalServiceService.create(createAdditionalServiceRequest);
    }
    @DeleteMapping("/deleteadditionalservice")
    public Result delete(@RequestBody @Valid DeleteAdditionalServiceRequest deleteAdditionalServiceRequest){
        return this.additionalServiceService.delete(deleteAdditionalServiceRequest);
    }
    @PutMapping("/updateadditionalservice")
    public Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest){
        return this.additionalServiceService.update(updateAdditionalServiceRequest);
    }

    @GetMapping("/getbyadditionalserviceid")
    public DataResult<AdditionalServiceDto> getById(@RequestParam int additionalServiceId){
        return this.additionalServiceService.getById(additionalServiceId);
    }
}
