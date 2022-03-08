package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.BrandService;
import com.turkcell.rentACarMS.business.dtos.BrandDto;
import com.turkcell.rentACarMS.business.dtos.ListBrandDto;
import com.turkcell.rentACarMS.business.requests.create.CreateBrandRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteBrandRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateBrandRequest;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.*;
import com.turkcell.rentACarMS.dataAccess.abstracts.BrandDao;
import com.turkcell.rentACarMS.entities.concretes.Brand;
import com.turkcell.rentACarMS.entities.concretes.Color;
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
	public DataResult<List<ListBrandDto>> listAll() {
		List<Brand> brands = this.brandDao.findAll();
		if (!checkBrandListEmpty(brands).isSuccess()){
			return new ErrorDataResult<List<ListBrandDto>>(checkBrandListEmpty(brands).getMessage());
		}
		List<ListBrandDto> listBrandDto = brands.stream().map(brand -> this.modelMapperService
				.forDto().map(brand, ListBrandDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListBrandDto>>(listBrandDto,listBrandDto.size() + " : Brands found.");
	}

	@Override
	public Result create(CreateBrandRequest createBrandRequest){
		if (!checkBrandName(createBrandRequest.getBrandName()).isSuccess()){
			return new ErrorResult(checkBrandName(createBrandRequest.getBrandName()).getMessage());
		}
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult("Brand added : " + brand.getBrandName());
		
	}
	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		if (!checkBrandId(deleteBrandRequest.getBrandId()).isSuccess()){
			return new ErrorResult(checkBrandId(deleteBrandRequest.getBrandId()).getMessage());
		}
		Brand brand = this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		checkBrandId(brand.getId());
		this.brandDao.delete(brand);
		return new SuccessResult("Brand deleted.");
	}
	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		if(!checkBrandId(updateBrandRequest.getBrandId()).isSuccess()){
			return new ErrorResult(checkBrandName(updateBrandRequest.getBrandName()).getMessage());
		}
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult("Brand updated.");

	}

	@Override
	public DataResult<BrandDto> getById(int brandId) {
		if(!checkBrandId(brandId).isSuccess()){
			return new ErrorDataResult<BrandDto>(checkBrandId(brandId).getMessage());
		}
		Brand brand = this.brandDao.getById(brandId);
		BrandDto brandDto = this.modelMapperService.forDto().map(brand,BrandDto.class);
		return new SuccessDataResult<BrandDto>(brandDto);
	}
	private Result checkBrandName(String brandName) {
		if (this.brandDao.existsByBrandName(brandName)){
			return new ErrorResult("This brand already exists.");
		}
		return new SuccessResult();
	}
	private Result checkBrandId(int brandId) {
		if (!this.brandDao.existsById(brandId)){
			return new ErrorResult("Brand id could not be defined.");
		}
		return new SuccessResult();
	}
	private Result checkBrandListEmpty(List<Brand> brands){
		if(brands.isEmpty()){
			return new ErrorDataResult<List<Color>>("Brand list is empty.");
		}
		return new SuccessDataResult<>();

	}


}
