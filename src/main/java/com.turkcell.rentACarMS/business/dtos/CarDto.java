package com.turkcell.rentACarMS.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private String brandName;
    private String colorName;
    private double carDailyPrice;
    private int carModelYear;
    private String currentCityName;
    private String carDescription;


}
