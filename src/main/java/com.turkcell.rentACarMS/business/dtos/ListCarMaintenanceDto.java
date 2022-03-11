package com.turkcell.rentACarMS.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarMaintenanceDto {
    private int id;
    private int carId;
    private String brandName;
    private String colorName;
    private String carMaintenanceDescription;
    private LocalDate carMaintenanceReturnDate;
}
