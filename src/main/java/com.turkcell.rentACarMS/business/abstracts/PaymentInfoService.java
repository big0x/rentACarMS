package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.ListPaymentInfoDto;
import com.turkcell.rentACarMS.business.requests.create.CreatePaymentInfoRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface PaymentInfoService {
    DataResult<List<ListPaymentInfoDto>> listAll();
    Result save(CreatePaymentInfoRequest createPaymentInfoRequest);
}
