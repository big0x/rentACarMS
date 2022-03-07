package com.turkcell.rentACarMS.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
    @NotNull
    @Min(0)
    @Max(50)
    private int customerId;
    @NotNull
    @Size(min = 0,max = 250)
    private String customerFirstName;
    @NotNull
    @Size(min = 0,max = 250)
    private String customerLastName;
    @NotNull
    @Size(min = 0,max = 250)
    @Email
    private String customerEmail;
}
