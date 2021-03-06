package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.CorporateCustomerService;
import com.turkcell.rentACarMS.business.constants.Messages;
import com.turkcell.rentACarMS.business.dtos.CorporateCustomerDto;
import com.turkcell.rentACarMS.business.dtos.ListCorporateCustomerDto;
import com.turkcell.rentACarMS.business.requests.create.CreateCorporateCustomerRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteCorporateCustomerRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateCorporateCustomerRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.*;
import com.turkcell.rentACarMS.dataAccess.abstracts.CorporateCustomerDao;
import com.turkcell.rentACarMS.entities.concretes.CorporateCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

    private CorporateCustomerDao corporateCustomerDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService){
        this.corporateCustomerDao = corporateCustomerDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ListCorporateCustomerDto>> listAll() {

        List<CorporateCustomer> corporateCustomers =this.corporateCustomerDao.findAll();
        List<ListCorporateCustomerDto> listCorporateCustomerDto = corporateCustomers.stream().map(corporateCustomer -> this.modelMapperService.forDto().map(corporateCustomer, ListCorporateCustomerDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ListCorporateCustomerDto>>(listCorporateCustomerDto,listCorporateCustomerDto.size() + " : " + Messages.CORPORATECUSTOMERFOUND);
    }

    @Override
    public Result create(CreateCorporateCustomerRequest createCorporateCustomerRequest) {

        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
        corporateCustomer.setRegisteredAt(LocalDate.now());
        this.corporateCustomerDao.save(corporateCustomer);

        return new SuccessResult(Messages.CORPORATECUSTOMERADDED);
    }

    @Override
    public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException {

        checkCorporateCustomerId(updateCorporateCustomerRequest.getId());

        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);
        this.corporateCustomerDao.save(corporateCustomer);

        return new SuccessResult(Messages.CORPORATECUSTOMERUPDATED);
    }

    @Override
    public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException {

        checkCorporateCustomerId(deleteCorporateCustomerRequest.getId());

        CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(deleteCorporateCustomerRequest, CorporateCustomer.class);
        this.corporateCustomerDao.delete(corporateCustomer);

        return new SuccessResult(Messages.CORPORATECUSTOMERDELETED);
    }
    @Override
    public DataResult<CorporateCustomerDto> getById(int corporateCustomerId) throws BusinessException {

        checkCorporateCustomerId(corporateCustomerId);

        CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(corporateCustomerId);
        CorporateCustomerDto corporateCustomerDto = this.modelMapperService.forDto().map(corporateCustomer,CorporateCustomerDto.class);

        return new SuccessDataResult<CorporateCustomerDto>(corporateCustomerDto, Messages.CORPORATECUSTOMERFOUND);
    }
    private Result checkCorporateCustomerId(int corporateCustomerId) throws BusinessException {

        if (!this.corporateCustomerDao.existsById(corporateCustomerId)) {
            throw new BusinessException(Messages.CORPORATECUSTOMERNOTFOUND);
        }
        return new SuccessResult();
    }
}
