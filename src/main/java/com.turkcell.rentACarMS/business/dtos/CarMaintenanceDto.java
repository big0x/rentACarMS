package com.turkcell.rentACarMS.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceDto {
    private int carMaintenanceId;
    private int carId;
    private String carMaintenanceDescription;
    private LocalDate carMaintenanceReturnDate;
    private boolean carIsActive;

}
