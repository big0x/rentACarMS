package com.turkcell.rentACarMS.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPaymentInfoDto {
    private String creditCardOwnerName;
    private String creditCardNumber;
    private String creditCardCVV;
    private LocalDate creditCardExpirationDate;
}
