package com.turkcell.rentACarMS.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {

    private double totalPayment;

    private int customerId;

    private String creditCardOwnerName;

    private String creditCardNumber;

    private String creditCardCVV;

    private LocalDate creditCardExpirationDate;

    private boolean rememberMe;
}
