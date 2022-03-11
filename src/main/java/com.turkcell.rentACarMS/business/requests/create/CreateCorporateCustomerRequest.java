package com.turkcell.rentACarMS.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private int taxNo;
}
