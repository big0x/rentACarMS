package com.turkcell.rentACarMS.business.outServices;

import com.turkcell.rentACarMS.core.utilities.results.Result;
import com.turkcell.rentACarMS.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FakeIsBankManager {
    public Result makePayment(String fullName, String cardNo, String Cvv, LocalDate cardExpDate) {
        return new SuccessResult("Ödeme iş bankası ile yapılmıştır.");
    }
}
