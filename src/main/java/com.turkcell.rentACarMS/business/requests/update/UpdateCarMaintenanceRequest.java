package com.turkcell.rentACarMS.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {

    @NotNull
    @Min(0)
    private int carMaintenanceId;

    @NotNull
    @Size(min = 2,max = 250)
    private String carMaintenanceDescription;

    @Future
    private LocalDate returnDate;

    @NotNull
    @Min(0)
    private int carId;

}
