package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.BrandService;
import com.turkcell.rentACarMS.business.dtos.BrandDto;
import com.turkcell.rentACarMS.business.dtos.ListBrandDto;
import com.turkcell.rentACarMS.business.requests.CreateBrandRequest;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.dataAccess.abstracts.BrandDao;
import com.turkcell.rentACarMS.entities.concretes.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandManager implements BrandService {

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<ListBrandDto> listAll() {
		var result = this.brandDao.findAll();
		
		List<ListBrandDto> response = result.stream().map(brand -> this.modelMapperService
				.forDto().map(brand, ListBrandDto.class)).collect(Collectors.toList());
		return response;
	}

	@Override
	public void create(CreateBrandRequest createBrandRequest) throws Exception{
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		checkBrandName(brand);
		this.brandDao.save(brand);
		
	}

	@Override
	public BrandDto getById(int brandId) throws Exception {
		checkBrandId(brandId);
		Brand result = this.brandDao.getById(brandId);
		BrandDto response = this.modelMapperService.forDto().map(result,BrandDto.class);
		return response;
	}
	private void checkBrandName(Brand brand) throws Exception{
		if (this.brandDao.existsByBrandName(brand.getBrandName())){
			throw new Exception("Brand name is already exists");
		}
	}
	private void checkBrandId(int brandId) throws Exception{
		if (!this.brandDao.existsByBrandId(brandId)){
			throw new Exception("Brand id can not defined");
		}
	}


}
