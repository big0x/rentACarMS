package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.ColorService;
import com.turkcell.rentACarMS.business.dtos.ColorDto;
import com.turkcell.rentACarMS.business.dtos.ListColorDto;
import com.turkcell.rentACarMS.business.requests.create.CreateColorRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteColorRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateColorRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.*;
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
	public DataResult<List<ListColorDto>> listAll() {

		List<Color> colors = this.colorDao.findAll();
		List<ListColorDto> listColorDto = colors.stream().map(color -> this.modelMapperService.forDto().map(color, ListColorDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<ListColorDto>>(listColorDto,listColorDto.size()+" : Colors found.");
	}

	@Override
	public Result create(CreateColorRequest createColorRequest) throws BusinessException {

		checkColorName(createColorRequest.getColorName());

		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		this.colorDao.save(color);

		return new SuccessResult("Color added : " + color.getColorName());

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) throws BusinessException {

		checkColorId(updateColorRequest.getId());

		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorDao.save(color);

		return new SuccessResult(updateColorRequest.getId() + " : Color updated.");
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) throws BusinessException {

		checkColorId(deleteColorRequest.getId());

		Color color = this.modelMapperService.forRequest().map(deleteColorRequest,Color.class);
		this.colorDao.delete(color);

		return new SuccessResult(deleteColorRequest.getId() + " : Color deleted.");

	}

	@Override
	public DataResult<ColorDto> getById(int colorId) throws BusinessException {

		checkColorId(colorId);

		Color color = this.colorDao.getById(colorId);
		ColorDto colorDto = this.modelMapperService.forDto().map(color, ColorDto.class);

		return new SuccessDataResult<ColorDto>(colorDto,"Color found.");
	}

	private Result checkColorName(String brandName) throws BusinessException {

		if (this.colorDao.existsByColorName(brandName)) {
			throw new BusinessException("This color already exists.");
		}
		return new SuccessResult();
	}

	private Result checkColorId(int colorId) throws BusinessException {

		if (!this.colorDao.existsById(colorId)) {
			throw new BusinessException("Color id could not be defined.");
		}
		return new SuccessResult();

	}
}
