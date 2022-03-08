package com.turkcell.rentACarMS.business.dtos;

import com.turkcell.rentACarMS.entities.enums.CarStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarDto {

    private int carId;
    private String brandName;
    private String colorName;
    private double carDailyPrice;
    private int carModelYear;
    private String description;
    private CarStates carStates;

}
