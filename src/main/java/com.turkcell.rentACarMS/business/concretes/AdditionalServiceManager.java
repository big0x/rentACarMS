package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.AdditionalServiceService;
import com.turkcell.rentACarMS.business.dtos.AdditionalServiceDto;
import com.turkcell.rentACarMS.business.dtos.ListAdditionalServiceDto;
import com.turkcell.rentACarMS.business.requests.create.CreateAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateAdditionalServiceRequest;
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
            if (!checkAdditionalServiceListEmpty(additionalServices).isSuccess()){
                return new ErrorDataResult<List<ListAdditionalServiceDto>>(checkAdditionalServiceListEmpty(additionalServices).getMessage());
            }
            List<ListAdditionalServiceDto> listAdditionalServiceDto = additionalServices.stream().map(additionalService -> this.modelMapperService
                    .forDto().map(additionalService, ListAdditionalServiceDto.class)).collect(Collectors.toList());
            return new SuccessDataResult<List<ListAdditionalServiceDto>>(listAdditionalServiceDto,listAdditionalServiceDto.size() + " : Additional Services found.");
        }

        @Override
        public Result create (CreateAdditionalServiceRequest createAdditionalServiceRequest){
            if(!checkAdditionalServiceName(createAdditionalServiceRequest.getAdditionalServiceName()).isSuccess()){
                return new ErrorResult(checkAdditionalServiceName(createAdditionalServiceRequest.getAdditionalServiceName()).getMessage());
            }
            AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
            this.additionalServiceDao.save(additionalService);
            return new SuccessResult("Additional Service added : " + additionalService.getAdditionalServiceName());
        }

        @Override
        public Result update (UpdateAdditionalServiceRequest updateAdditionalServiceRequest){
            if(!checkAdditionalServiceId(updateAdditionalServiceRequest.getId()).isSuccess()){
                return new ErrorResult(checkAdditionalServiceId(updateAdditionalServiceRequest.getId()).getMessage());
            }
            AdditionalService additionalService = this.modelMapperService.forRequest().map(updateAdditionalServiceRequest, AdditionalService.class);
            this.additionalServiceDao.save(additionalService);
            return new SuccessResult("Additional Service updated.");
        }

        @Override
        public Result delete (DeleteAdditionalServiceRequest deleteAdditionalServiceRequest){
            if (!checkAdditionalServiceId(deleteAdditionalServiceRequest.getId()).isSuccess()){
                return new ErrorDataResult<AdditionalServiceDto>(checkAdditionalServiceId(deleteAdditionalServiceRequest.getId()).getMessage());
            }
            AdditionalService additionalService = this.modelMapperService.forRequest().map(deleteAdditionalServiceRequest, AdditionalService.class);
            checkAdditionalServiceId(additionalService.getId());
            this.additionalServiceDao.delete(additionalService);
            return new SuccessResult("Additional Service deleted.");
        }
        @Override
        public DataResult<AdditionalServiceDto> getById ( int additionalServiceId){
            if(!checkAdditionalServiceId(additionalServiceId).isSuccess()){
                return new ErrorDataResult<AdditionalServiceDto>(checkAdditionalServiceId(additionalServiceId).getMessage());
            }
            AdditionalService additionalService = this.additionalServiceDao.getById(additionalServiceId);
            AdditionalServiceDto additionalServiceDto = this.modelMapperService.forDto().map(additionalService,AdditionalServiceDto.class);
            return new SuccessDataResult<AdditionalServiceDto>(additionalServiceDto,"Additional Service found.");
        }

    private Result checkAdditionalServiceId(int additionalServiceId) {
        if (!this.additionalServiceDao.existsById(additionalServiceId)) {
            return new ErrorResult("Additional Service not found.");
        }
        return new SuccessResult();
    }
    private Result checkAdditionalServiceListEmpty(List<AdditionalService> additionalServices){
        if (additionalServices.isEmpty()){
            return new ErrorDataResult<List<AdditionalService>>("Additional Service list is empty.");
        }
        return new SuccessDataResult<>();
    }
    private Result checkAdditionalServiceName(String additionalServiceName) {
        if (this.additionalServiceDao.existsByAdditionalServiceName(additionalServiceName)){
            return new ErrorResult("This Additional Service already exists.");
        }
        return new SuccessResult();
    }
}
