package com.turkcell.rentACarMS.business.dtos;

import com.turkcell.rentACarMS.entities.enums.CarStates;
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
    private int carKilometer;
    private String carDescription;
    private CarStates carStates;


}
