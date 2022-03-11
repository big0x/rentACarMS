package com.turkcell.rentACarMS.business.requests.update;

import com.turkcell.rentACarMS.entities.enums.CarStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
    @NotNull
    @Min(0)
    @Max(50)
    private int id;
    @Min(0)
    @Max(100)
    private double carDailyPrice;
    @NotNull
    @Min(0)
    @Max(2022)
    private int carModelYear;
    @NotNull
    @Size(min = 0,max = 250)
    private String carDescription;
    @NotNull
    private int brandId;
    @NotNull
    private int colorId;
    @NotNull
    private CarStates carStates;
}
