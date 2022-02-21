package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.ColorDto;
import com.turkcell.rentACarMS.business.dtos.ListColorDto;
import com.turkcell.rentACarMS.business.requests.create.CreateColorRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteColorRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateColorRequest;
import com.turkcell.rentACarMS.core.concretes.BusinessException;

import java.util.List;

public interface ColorService {

	List<ListColorDto> listAll();
	void create(CreateColorRequest createColorRequest) throws BusinessException;
	void update(UpdateColorRequest updateColorRequest) throws BusinessException;
	void delete(DeleteColorRequest deleteColorRequest) throws BusinessException;
	ColorDto getById(int colorId) throws BusinessException;
}
