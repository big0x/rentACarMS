package com.turkcell.rentACarMS.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRentalDto {
    private int id;
    private int customerId;
    private int carId;
    private LocalDate rentalRentDate;
    private LocalDate rentalReturnDate;
    private String rentalCity;
    private String returnCity;
    private double rentalPrice;
    private int orderedAdditionalServiceId;
}
