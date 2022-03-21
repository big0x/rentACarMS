package com.turkcell.rentACarMS.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UpdateCarDamageRequest {
    @NotNull
    private int id;
    @NotNull
    private int carId;
    @NotNull
    @Size(min = 2,max = 250)
    private String carDamageDescription;
}
