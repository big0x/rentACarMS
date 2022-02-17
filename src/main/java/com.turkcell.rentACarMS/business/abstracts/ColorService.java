package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.ColorDto;
import com.turkcell.rentACarMS.business.dtos.ListColorDto;
import com.turkcell.rentACarMS.business.requests.CreateColorRequest;

import java.util.List;

public interface ColorService {

	List<ListColorDto> listAll();
	void create(CreateColorRequest createColorRequest) throws Exception;
	ColorDto getById(int colorId) throws Exception;
}
