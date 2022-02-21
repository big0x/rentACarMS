package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.BrandDto;
import com.turkcell.rentACarMS.business.dtos.ListBrandDto;
import com.turkcell.rentACarMS.business.requests.create.CreateBrandRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteBrandRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateBrandRequest;
import com.turkcell.rentACarMS.core.concretes.BusinessException;

import java.util.List;

public interface BrandService {

	List<ListBrandDto> listAll();
	void create(CreateBrandRequest createBrandRequest) throws BusinessException;
	void update(UpdateBrandRequest updateBrandRequest) throws BusinessException;
	void delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException;
	BrandDto getById(int brandId) throws BusinessException;
}
