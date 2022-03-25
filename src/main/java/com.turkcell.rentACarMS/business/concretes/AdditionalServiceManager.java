package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.AdditionalServiceService;
import com.turkcell.rentACarMS.business.constants.Messages;
import com.turkcell.rentACarMS.business.dtos.AdditionalServiceDto;
import com.turkcell.rentACarMS.business.dtos.ListAdditionalServiceDto;
import com.turkcell.rentACarMS.business.requests.create.CreateAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateAdditionalServiceRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.*;
import com.turkcell.rentACarMS.dataAccess.abstracts.AdditionalServiceDao;
import com.turkcell.rentACarMS.entities.concretes.AdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {


    private AdditionalServiceDao additionalServiceDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {
        this.additionalServiceDao = additionalServiceDao;
        this.modelMapperService = modelMapperService;
    }


        @Override
        public DataResult<List<ListAdditionalServiceDto>> listAll () {

            List<AdditionalService> additionalServices = this.additionalServiceDao.findAll();
            List<ListAdditionalServiceDto> listAdditionalServiceDto = additionalServices.stream().map(additionalService -> this.modelMapperService
                    .forDto().map(additionalService, ListAdditionalServiceDto.class)).collect(Collectors.toList());

            return new SuccessDataResult<List<ListAdditionalServiceDto>>(listAdditionalServiceDto,listAdditionalServiceDto.size() + " : " + Messages.ADDITIONALSERVICEFOUND);
        }

        @Override
        public Result create (CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException {

            checkAdditionalServiceName(createAdditionalServiceRequest.getAdditionalServiceName());

            AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
            this.additionalServiceDao.save(additionalService);

            return new SuccessResult(Messages.ADDITIONALSERVICEADDED);
        }

        @Override
        public Result update (UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException {

            checkAdditionalServiceId(updateAdditionalServiceRequest.getId());

            AdditionalService additionalService = this.modelMapperService.forRequest().map(updateAdditionalServiceRequest, AdditionalService.class);
            this.additionalServiceDao.save(additionalService);

            return new SuccessResult(Messages.ADDITIONALSERVICEUPDATED);
        }

        @Override
        public Result delete (DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException {

            checkAdditionalServiceId(deleteAdditionalServiceRequest.getId());

            AdditionalService additionalService = this.modelMapperService.forRequest().map(deleteAdditionalServiceRequest, AdditionalService.class);
            this.additionalServiceDao.delete(additionalService);

            return new SuccessResult(Messages.ADDITIONALSERVICEDELETED);
        }
        @Override
        public DataResult<AdditionalServiceDto> getById ( int additionalServiceId) throws BusinessException {

            checkAdditionalServiceId(additionalServiceId);

            AdditionalService additionalService = this.additionalServiceDao.getById(additionalServiceId);
            AdditionalServiceDto additionalServiceDto = this.modelMapperService.forDto().map(additionalService,AdditionalServiceDto.class);

            return new SuccessDataResult<AdditionalServiceDto>(additionalServiceDto,Messages.ADDITIONALSERVICEFOUND);
        }

        private Result checkAdditionalServiceId(int additionalServiceId) throws BusinessException {

            if (!this.additionalServiceDao.existsById(additionalServiceId)) {
            throw  new BusinessException(Messages.ADDITIONALSERVICENOTFOUND);
            }
         return new SuccessResult();
        }
        private Result checkAdditionalServiceName(String additionalServiceName) throws BusinessException {

            if (this.additionalServiceDao.existsByAdditionalServiceName(additionalServiceName)){
            throw new BusinessException(Messages.ADDITIONALSERVICEEXISTS);
            }
         return new SuccessResult();
        }
}
