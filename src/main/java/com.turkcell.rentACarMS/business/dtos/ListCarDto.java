package com.turkcell.rentACarMS.business.dtos;

import com.turkcell.rentACarMS.entities.enums.CarStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarDto {

    private int id;
    private String brandName;
    private String colorName;
    private double carDailyPrice;
    private int carModelYear;
    private String currentCity;
    private String carDescription;
    private CarStates carStates;

}
