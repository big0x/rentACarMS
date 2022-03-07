package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.ColorService;
import com.turkcell.rentACarMS.business.dtos.ColorDto;
import com.turkcell.rentACarMS.business.dtos.ListColorDto;
import com.turkcell.rentACarMS.business.requests.create.CreateColorRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteColorRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateColorRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
	public DataResult<List<ListColorDto>> listAll() {

		return this.colorService.listAll();
	}

	@PostMapping("/createcolor")
	public Result create(@RequestBody @Valid CreateColorRequest createColorRequest){
		return this.colorService.create(createColorRequest);
	}

	@DeleteMapping("/deletecolor")
	public Result delete(@RequestBody @Valid DeleteColorRequest deleteColorRequest) {
		return this.colorService.delete(deleteColorRequest);
	}
	@PutMapping("/updatecolor")
	public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest){
		return this.colorService.update(updateColorRequest);
	}
	@GetMapping("/getbycolorid")
	public DataResult<ColorDto> getById(@RequestParam int colorId){
		return this.colorService.getById(colorId);
	}

}
