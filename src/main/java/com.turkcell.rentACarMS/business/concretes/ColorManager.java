package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.ColorService;
import com.turkcell.rentACarMS.business.dtos.ColorDto;
import com.turkcell.rentACarMS.business.dtos.ListColorDto;
import com.turkcell.rentACarMS.business.requests.create.CreateColorRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteColorRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateColorRequest;
import com.turkcell.rentACarMS.core.concretes.BusinessException;
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
	public void create(CreateColorRequest createColorRequest) throws BusinessException {
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		checkColorName(color);
		this.colorDao.save(color);

	}

	@Override
	public void update(UpdateColorRequest updateColorRequest) throws BusinessException {
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		checkColorId(updateColorRequest.getColorId());
		this.colorDao.save(color);

	}

	@Override
	public void delete(DeleteColorRequest deleteColorRequest) throws BusinessException {
		Color color = this.modelMapperService.forRequest().map(deleteColorRequest,Color.class);
		checkColorId(color.getColorId());
		this.colorDao.delete(color);

	}

	@Override
	public ColorDto getById(int colorId) throws BusinessException {
		checkColorId(colorId);
		Color result = this.colorDao.getById(colorId);
		ColorDto response = this.modelMapperService.forDto().map(result, ColorDto.class);
		return response;
	}

	private void checkColorName(Color color) throws BusinessException {
		if (this.colorDao.existsByColorName(color.getColorName())) {
			throw new BusinessException("This color already exists");
		}
	}

	private void checkColorId(int colorId) throws BusinessException {
		if (!this.colorDao.existsByColorId(colorId)) {
			throw new BusinessException("Color id could not be defined");
		}

	}
}
