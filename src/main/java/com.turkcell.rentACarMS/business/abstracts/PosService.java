package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentACarMS.core.utilities.results.Result;

public interface PosService {
    public Result payment(CreatePaymentRequest createPaymentRequest);
}
