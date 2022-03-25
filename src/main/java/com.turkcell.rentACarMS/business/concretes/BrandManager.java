package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.BrandService;
import com.turkcell.rentACarMS.business.constants.Messages;
import com.turkcell.rentACarMS.business.dtos.BrandDto;
import com.turkcell.rentACarMS.business.dtos.ListBrandDto;
import com.turkcell.rentACarMS.business.requests.create.CreateBrandRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteBrandRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateBrandRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import com.turkcell.rentACarMS.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACarMS.core.utilities.results.SuccessResult;
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
	public DataResult<List<ListBrandDto>> listAll() {

		List<Brand> brands = this.brandDao.findAll();
		List<ListBrandDto> listBrandDto = brands.stream().map(brand -> this.modelMapperService
				.forDto().map(brand, ListBrandDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<ListBrandDto>>(listBrandDto,listBrandDto.size() + " : " + Messages.BRANDFOUND);
	}

	@Override
	public Result create(CreateBrandRequest createBrandRequest) throws BusinessException {

		checkBrandName(createBrandRequest.getBrandName());

		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandDao.save(brand);

		return new SuccessResult(Messages.BRANDADDED);
		
	}
	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException {

		checkBrandId(deleteBrandRequest.getId());

		Brand brand = this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		this.brandDao.delete(brand);

		return new SuccessResult(Messages.BRANDDELETED);
	}
	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException {

		checkBrandId(updateBrandRequest.getId());

		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);

		return new SuccessResult(Messages.BRANDUPDATED);

	}

	@Override
	public DataResult<BrandDto> getById(int brandId) throws BusinessException {

		checkBrandId(brandId);

		Brand brand = this.brandDao.getById(brandId);
		BrandDto brandDto = this.modelMapperService.forDto().map(brand,BrandDto.class);

		return new SuccessDataResult<BrandDto>(brandDto,Messages.BRANDFOUND);
	}

	private Result checkBrandName(String brandName) throws BusinessException {

		if (this.brandDao.existsByBrandName(brandName)){
			throw new BusinessException(Messages.BRANDEXISTS);
		}
		return new SuccessResult();
	}
	private Result checkBrandId(int brandId) throws BusinessException {

		if (!this.brandDao.existsById(brandId)){
			throw new BusinessException(Messages.BRANDNOTFOUND);
		}
		return new SuccessResult();
	}
}
