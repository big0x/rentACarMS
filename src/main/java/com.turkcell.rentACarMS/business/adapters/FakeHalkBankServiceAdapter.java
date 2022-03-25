package com.turkcell.rentACarMS.business.adapters;

import com.turkcell.rentACarMS.business.abstracts.PosService;
import com.turkcell.rentACarMS.business.outServices.FakeHalkBankManager;
import com.turkcell.rentACarMS.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class FakeHalkBankServiceAdapter implements PosService {
    @Override
    public Result payment(CreatePaymentRequest createPaymentRequest) {
        FakeHalkBankManager fakeHalkBankManager=new FakeHalkBankManager();
        return fakeHalkBankManager.odemeYap(createPaymentRequest.getCreditCardCVV(), createPaymentRequest.getCreditCardOwnerName(), createPaymentRequest.getCreditCardNumber(),createPaymentRequest.getCreditCardExpirationDate());
    }
}
