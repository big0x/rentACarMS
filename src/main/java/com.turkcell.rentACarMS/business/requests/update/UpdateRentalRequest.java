package com.turkcell.rentACarMS.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
    @NotNull
    @Min(0)
    @Max(50)
    private int id;
    @FutureOrPresent
    private LocalDate rentDate;
    @Future
    private LocalDate returnDate;
    @NotNull
    private int carId;
    @NotNull
    private int customerId;
    @NotNull
    private int rentalCityId;
    @NotNull
    private int returnCityId;

}
