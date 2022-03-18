package com.turkcell.rentACarMS.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
    private long invoiceNo;
    private LocalDate invoiceDateOfCreation;
    private int rentalDays;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private double rentTotalPrice;
    private int customerId;
    private int rentalId;


}
