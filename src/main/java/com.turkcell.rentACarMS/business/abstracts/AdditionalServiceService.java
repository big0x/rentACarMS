package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.AdditionalServiceDto;
import com.turkcell.rentACarMS.business.dtos.ListAdditionalServiceDto;
import com.turkcell.rentACarMS.business.requests.create.CreateAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateAdditionalServiceRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface AdditionalServiceService {
    DataResult<List<ListAdditionalServiceDto>> listAll() throws BusinessException;
    Result create(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException;
    Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException;
    Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException;
    DataResult<AdditionalServiceDto> getById(int additionalServiceId) throws BusinessException;
}
