package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.BrandService;
import com.turkcell.rentACarMS.business.dtos.BrandDto;
import com.turkcell.rentACarMS.business.dtos.ListBrandDto;
import com.turkcell.rentACarMS.business.requests.create.CreateBrandRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteBrandRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateBrandRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/brands")
public class BrandsController {

	private BrandService brandService;

	@Autowired
	public BrandsController(BrandService brandService) {
		this.brandService = brandService;
	}
	
	@GetMapping("/listallbrands")
	public DataResult<List<ListBrandDto>> listAll(){

		return this.brandService.listAll();
	}
	
	@PostMapping("/createbrand")
	public Result create(@RequestBody @Valid CreateBrandRequest createBrandRequest){
		return this.brandService.create(createBrandRequest);
	}
	@DeleteMapping("/deletebrand")
	public Result delete(@RequestBody @Valid DeleteBrandRequest deleteBrandRequest){
		return this.brandService.delete(deleteBrandRequest);
	}
	@PutMapping("/updatebrand")
	public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest){
		return this.brandService.update(updateBrandRequest);
	}

	@GetMapping("/getbybrandid")
	public DataResult<BrandDto> getById(@RequestParam int brandId){
		return this.brandService.getById(brandId);
	}


}
