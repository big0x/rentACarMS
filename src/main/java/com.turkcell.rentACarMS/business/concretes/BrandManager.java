package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.BrandService;
import com.turkcell.rentACarMS.business.dtos.BrandDto;
import com.turkcell.rentACarMS.business.dtos.ListBrandDto;
import com.turkcell.rentACarMS.business.requests.create.CreateBrandRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteBrandRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateBrandRequest;
import com.turkcell.rentACarMS.core.concretes.BusinessException;
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
	public void create(CreateBrandRequest createBrandRequest) throws BusinessException {
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		checkBrandName(brand);
		this.brandDao.save(brand);
		
	}
	@Override
	public void delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException {
		Brand brand = this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		checkBrandId(brand.getBrandId());
		this.brandDao.delete(brand);
	}
	@Override
	public void update(UpdateBrandRequest updateBrandRequest) throws BusinessException {
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		checkBrandId(updateBrandRequest.getBrandId());
		this.brandDao.save(brand);

	}

	@Override
	public BrandDto getById(int brandId) throws BusinessException {
		checkBrandId(brandId);
		Brand result = this.brandDao.getById(brandId);
		BrandDto response = this.modelMapperService.forDto().map(result,BrandDto.class);
		return response;
	}
	private void checkBrandName(Brand brand) throws BusinessException{
		if (this.brandDao.existsByBrandName(brand.getBrandName())){
			throw new BusinessException("This brand already exists");
		}
	}
	private void checkBrandId(int brandId) throws BusinessException{
		if (!this.brandDao.existsByBrandId(brandId)){
			throw new BusinessException("Brand id could not be defined");
		}
	}


}
