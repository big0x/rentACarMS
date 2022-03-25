package com.turkcell.rentACarMS.business.outServices;

import com.turkcell.rentACarMS.core.utilities.results.Result;
import com.turkcell.rentACarMS.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FakeHalkBankManager {
    public Result odemeYap(String Cvv, String fullName, String cardNo, LocalDate cardExpDate) {
        return new SuccessResult("Ödeme halk bankası ile yapılmıştır.");
    }
}
