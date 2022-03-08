package com.turkcell.rentACarMS.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRentalDto {
    private int rentalId;
    private int customerId;
    private int carId;
    private int additionalServiceId;
    private LocalDate rentalRentDate;
    private LocalDate rentalReturnDate;
    private boolean carIsActive;
}