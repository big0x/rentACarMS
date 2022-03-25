package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.ListPaymentDto;
import com.turkcell.rentACarMS.business.dtos.PaymentDto;
import com.turkcell.rentACarMS.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface PaymentService {
    DataResult<List<ListPaymentDto>> listAll();
    DataResult<PaymentDto> getById(int paymentId);
    Result create(CreatePaymentRequest createPaymentRequest);
}
