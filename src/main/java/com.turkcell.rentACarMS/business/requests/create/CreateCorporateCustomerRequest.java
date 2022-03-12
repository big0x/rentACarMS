package com.turkcell.rentACarMS.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {
    @ReadOnlyProperty
    private int id;
    @NotNull
    @Size(min = 2,max = 50)
    private String corporateName;
    @NotNull
    @Min(0)
    @Max(50)
    private int corporateTaxNo;
    @NotNull
    @Email
    private String email;
    @NotNull
    private int password;
}
