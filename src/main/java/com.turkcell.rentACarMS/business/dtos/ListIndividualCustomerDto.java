package com.turkcell.rentACarMS.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListIndividualCustomerDto {
    private int id;
    private String customerFirstName;
    private String customerLastName;
    private LocalDate registeredAt;
}
