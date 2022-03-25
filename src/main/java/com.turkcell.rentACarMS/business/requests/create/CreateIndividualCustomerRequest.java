package com.turkcell.rentACarMS.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {

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
    private String password;
    @FutureOrPresent
    private LocalDate registeredAt;
}
