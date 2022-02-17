package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.BrandService;
import com.turkcell.rentACarMS.business.dtos.BrandDto;
import com.turkcell.rentACarMS.business.dtos.ListBrandDto;
import com.turkcell.rentACarMS.business.requests.CreateBrandRequest;
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
	public void create(@RequestBody CreateBrandRequest createBrandRequest) throws Exception{
		this.brandService.create(createBrandRequest);
	}
	@GetMapping("/getbybrandid")
	public BrandDto getById(@RequestParam int brandId) throws Exception{
		return this.brandService.getById(brandId);
	}
}
