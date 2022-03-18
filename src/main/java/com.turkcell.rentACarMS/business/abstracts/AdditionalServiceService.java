package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.AdditionalServiceDto;
import com.turkcell.rentACarMS.business.dtos.ListAdditionalServiceDto;
import com.turkcell.rentACarMS.business.requests.create.CreateAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateAdditionalServiceRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface AdditionalServiceService {
    DataResult<List<ListAdditionalServiceDto>> listAll();
    Result create(CreateAdditionalServiceRequest createAdditionalServiceRequest);
    Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);
    Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest);
    DataResult<AdditionalServiceDto> getById(int additionalServiceId);
}
