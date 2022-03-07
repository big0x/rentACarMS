package com.turkcell.rentACarMS.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

    @FutureOrPresent
    private LocalDate rentDate;

    @Future
    private LocalDate returnDate;
    @NotNull
    private int carId;
    @NotNull
    private int customerId;
}
