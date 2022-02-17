package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.ColorService;
import com.turkcell.rentACarMS.business.dtos.ColorDto;
import com.turkcell.rentACarMS.business.dtos.ListColorDto;
import com.turkcell.rentACarMS.business.requests.CreateColorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {
	
	private ColorService colorService;

	@Autowired
	public ColorsController(ColorService colorService) {

		this.colorService = colorService;
	}
	
	@GetMapping("/listallcolors")
	public List<ListColorDto> listAll() {

		return this.colorService.listAll();
	}
	
	@PostMapping("/createcolor")
	public void create(@RequestBody CreateColorRequest createColorRequest) throws Exception {
		this.colorService.create(createColorRequest);
	}
	@GetMapping("/getbycolorid")
	public ColorDto getById(@RequestParam int colorId) throws Exception{
		return this.colorService.getById(colorId);
	}
}
