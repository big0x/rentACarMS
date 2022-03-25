package com.turkcell.rentACarMS.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentInfoRequest {

    private String creditCardOwnerName;

    private String creditCardNumber;

    private String creditCardCVV;

    private LocalDate creditCardExpirationDate;

    private int customerId;


}
