package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.ColorService;
import com.turkcell.rentACarMS.business.dtos.ColorDto;
import com.turkcell.rentACarMS.business.dtos.ListColorDto;
import com.turkcell.rentACarMS.business.requests.create.CreateColorRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteColorRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateColorRequest;
import com.turkcell.rentACarMS.core.concretes.BusinessException;
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
	public void create(@RequestBody CreateColorRequest createColorRequest) throws BusinessException {
		this.colorService.create(createColorRequest);
	}

	@DeleteMapping("/deletecolor")
	public void delete(@RequestBody DeleteColorRequest deleteColorRequest) throws BusinessException {
		this.colorService.delete(deleteColorRequest);
	}
	@PutMapping("/updatecolor")
	public void update(@RequestBody UpdateColorRequest updateColorRequest) throws BusinessException{
		this.colorService.update(updateColorRequest);
	}
	@GetMapping("/getbycolorid")
	public ColorDto getById(@RequestParam int colorId) throws BusinessException {
		return this.colorService.getById(colorId);
	}

}
