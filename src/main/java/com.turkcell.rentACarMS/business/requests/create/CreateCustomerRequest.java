package com.turkcell.rentACarMS.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
    @ReadOnlyProperty
    private int id;
    @NotNull
    @Size(min = 0,max = 250)
    private String customerFirstName;
    @NotNull
    @Size(min = 0,max = 250)
    private String customerLastName;
    @NotNull
    @Size(min = 0,max = 250)
    @Email
    private String userEmail;
    @NotNull
    @Size(min=4,max = 10)
    private String userPassword;
}
