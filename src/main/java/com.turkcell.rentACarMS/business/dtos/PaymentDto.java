package com.turkcell.rentACarMS.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private long creditCardNumber;
    private int creditCardCVV;
    private LocalDate creditCardExpirationDate;
    private double totalPayment;
    private int rentalId;
    private int invoiceId;
}
