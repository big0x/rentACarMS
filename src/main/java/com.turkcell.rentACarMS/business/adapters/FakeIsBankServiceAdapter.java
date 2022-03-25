package com.turkcell.rentACarMS.business.adapters;

import com.turkcell.rentACarMS.business.abstracts.PosService;
import com.turkcell.rentACarMS.business.outServices.FakeIsBankManager;
import com.turkcell.rentACarMS.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.stereotype.Service;

@Service
public class FakeIsBankServiceAdapter implements PosService {
    @Override
    public Result payment(CreatePaymentRequest createPaymentRequest) {
        FakeIsBankManager fakeIsBankManager=new FakeIsBankManager();
        return fakeIsBankManager.makePayment(createPaymentRequest.getCreditCardOwnerName(), createPaymentRequest.getCreditCardNumber(), createPaymentRequest.getCreditCardCVV(),createPaymentRequest.getCreditCardExpirationDate());
    }
}
