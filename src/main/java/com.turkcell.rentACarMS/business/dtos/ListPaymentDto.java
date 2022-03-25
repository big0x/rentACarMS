package com.turkcell.rentACarMS.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPaymentDto {
    private int id;
    private double totalPayment;
    private int rentalId;
    private int invoiceId;
}
