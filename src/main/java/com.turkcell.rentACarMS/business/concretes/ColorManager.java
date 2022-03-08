package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.ColorService;
import com.turkcell.rentACarMS.business.dtos.ColorDto;
import com.turkcell.rentACarMS.business.dtos.ListColorDto;
import com.turkcell.rentACarMS.business.requests.create.CreateColorRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteColorRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateColorRequest;
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
		if (!checkColorListEmpty(colors).isSuccess()){
			return new ErrorDataResult<List<ListColorDto>>(checkColorListEmpty(colors).getMessage());
		}
		List<ListColorDto> listColorDto = colors.stream().map(color -> this.modelMapperService
				.forDto().map(color, ListColorDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListColorDto>>(listColorDto,listColorDto.size()+" : Colors found.");
	}

	@Override
	public Result create(CreateColorRequest createColorRequest)  {
		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
//		Result result = checkColorName(color);
//		if(!result.isSuccess()){
//			return new ErrorResult(result.getMessage());
//		}
		checkColorName(color);

		this.colorDao.save(color);
		return new SuccessResult("Color added : " + color.getColorName());

	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		checkColorId(updateColorRequest.getColorId());
		this.colorDao.save(color);
		return new SuccessResult(updateColorRequest.getColorId() + " : Color updated.");

	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		if (!checkColorId(deleteColorRequest.getColorId()).isSuccess()){
			return new ErrorDataResult<ColorDto>(checkColorId(deleteColorRequest.getColorId()).getMessage());
		}
		Color color = this.modelMapperService.forRequest().map(deleteColorRequest,Color.class);
		checkColorId(color.getId());
		this.colorDao.delete(color);
		return new SuccessResult(deleteColorRequest.getColorId() + " : Color deleted.");

	}

	@Override
	public DataResult<ColorDto> getById(int colorId) {
		if(!checkColorId(colorId).isSuccess()){
			return new ErrorDataResult<ColorDto>(checkColorId(colorId).getMessage());
		}
		Color color = this.colorDao.getById(colorId);
		ColorDto colorDto = this.modelMapperService.forDto().map(color, ColorDto.class);
		return new SuccessDataResult<ColorDto>(colorDto,"Color found.");
	}

	private Result checkColorName(Color color) {
		if (this.colorDao.existsByColorName(color.getColorName())) {
			return new ErrorResult("This color already exists.");
		}
		return new SuccessResult();
	}

	private Result checkColorId(int colorId)  {
		if (!this.colorDao.existsById(colorId)) {
			return new ErrorResult("Color id could not be defined.");
		}
		return new SuccessResult();

	}
	private Result checkColorListEmpty(List<Color> colors){
		if(colors.isEmpty()){
			return new ErrorDataResult<List<Color>>("Color list is empty.");
		}
		return new SuccessDataResult<>();

	}
}
