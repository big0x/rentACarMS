package com.turkcell.rentACarMS.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCustomerDto {
    private int customerId;
    private String customerFirstName;
    private String customerLastName;
    private String userEmail;
    private String userPassword;
}
