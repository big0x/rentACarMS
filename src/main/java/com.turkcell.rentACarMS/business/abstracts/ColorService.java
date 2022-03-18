package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.ColorDto;
import com.turkcell.rentACarMS.business.dtos.ListColorDto;
import com.turkcell.rentACarMS.business.requests.create.CreateColorRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteColorRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateColorRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface ColorService {

	DataResult<List<ListColorDto>> listAll();
	Result create(CreateColorRequest createColorRequest);
	Result update(UpdateColorRequest updateColorRequest);
	Result delete(DeleteColorRequest deleteColorRequest);
	DataResult<ColorDto> getById(int colorId);
}
