package com.turkcell.rentACarMS.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceDto {
    private String carMaintenanceDescription;
    private LocalDate carMaintenanceReturnDate;
    private int carId;


}
