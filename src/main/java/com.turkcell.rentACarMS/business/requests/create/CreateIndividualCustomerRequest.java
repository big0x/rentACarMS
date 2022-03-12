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
public class CreateIndividualCustomerRequest {
    @ReadOnlyProperty
    private int id;
    @NotNull
    @Size(min = 2,max = 50)
    private String customerFirstName;
    @NotNull
    @Size(min = 2,max = 50)
    private String customerLastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private int password;
}
