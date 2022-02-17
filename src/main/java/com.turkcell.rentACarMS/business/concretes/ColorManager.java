package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.ColorService;
import com.turkcell.rentACarMS.business.dtos.ColorDto;
import com.turkcell.rentACarMS.business.dtos.ListColorDto;
import com.turkcell.rentACarMS.business.requests.CreateColorRequest;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.dataAccess.abstracts.ColorDao;
import com.turkcell.rentACarMS.entities.concretes.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorManager implements ColorService {

	private ColorDao colorDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<ListColorDto> listAll() {
		var result = this.colorDao.findAll();

		List<ListColorDto> response = result.stream().map(color -> this.modelMapperService
				.forDto().map(color, ListColorDto.class)).collect(Collectors.toList());
		return response;
	}

	@Override
	public void create(CreateColorRequest createColorRequest) throws Exception {
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		checkColorName(color);
		this.colorDao.save(color);

	}

	@Override
	public ColorDto getById(int colorId) throws Exception {
		checkColorId(colorId);
		Color result = this.colorDao.getById(colorId);
		ColorDto response = this.modelMapperService.forDto().map(result, ColorDto.class);
		return response;
	}

	private void checkColorName(Color color) throws Exception {
		if (this.colorDao.existsByColorName(color.getColorName())) {
			throw new Exception("Color is already exists");
		}
	}

	private void checkColorId(int colorId) throws Exception {
		if (!this.colorDao.existsByColorId(colorId)) {
			throw new Exception("Color id can not defined");
		}

	}
}
