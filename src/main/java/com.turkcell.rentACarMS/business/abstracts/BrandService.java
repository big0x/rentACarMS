package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.BrandDto;
import com.turkcell.rentACarMS.business.dtos.ListBrandDto;
import com.turkcell.rentACarMS.business.requests.create.CreateBrandRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteBrandRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateBrandRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface BrandService {

	DataResult<List<ListBrandDto>> listAll();
	Result create(CreateBrandRequest createBrandRequest);
	Result update(UpdateBrandRequest updateBrandRequest);
	Result delete(DeleteBrandRequest deleteBrandRequest);
	DataResult<BrandDto> getById(int brandId);
}
