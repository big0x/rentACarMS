package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.BrandDto;
import com.turkcell.rentACarMS.business.dtos.ListBrandDto;
import com.turkcell.rentACarMS.business.requests.CreateBrandRequest;

import java.util.List;

public interface BrandService {

	List<ListBrandDto> listAll();
	void create(CreateBrandRequest createBrandRequest) throws Exception;
	BrandDto getById(int brandId) throws Exception;
}
