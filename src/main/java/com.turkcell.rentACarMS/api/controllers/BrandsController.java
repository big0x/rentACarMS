package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.BrandService;
import com.turkcell.rentACarMS.business.dtos.BrandDto;
import com.turkcell.rentACarMS.business.dtos.ListBrandDto;
import com.turkcell.rentACarMS.business.requests.create.CreateBrandRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteBrandRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateBrandRequest;
import com.turkcell.rentACarMS.core.concretes.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public List<ListBrandDto> listAll(){

		return this.brandService.listAll();
	}
	
	@PostMapping("/createbrand")
	public void create(@RequestBody CreateBrandRequest createBrandRequest) throws BusinessException {
		this.brandService.create(createBrandRequest);
	}
	@DeleteMapping("/deletebrand")
	public void delete(@RequestBody DeleteBrandRequest deleteBrandRequest) throws BusinessException{
		this.brandService.delete(deleteBrandRequest);
	}
	@PutMapping("/updatebrand")
	public void update(@RequestBody UpdateBrandRequest updateBrandRequest)throws BusinessException{
		this.brandService.update(updateBrandRequest);
	}

	@GetMapping("/getbybrandid")
	public BrandDto getById(@RequestParam int brandId) throws BusinessException{
		return this.brandService.getById(brandId);
	}


}
