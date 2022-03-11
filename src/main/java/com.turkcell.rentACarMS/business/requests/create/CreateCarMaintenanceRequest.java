package com.turkcell.rentACarMS.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

    @ReadOnlyProperty
    private int id;
    @NotNull
    @Size(min = 2,max = 250)
    private String carMaintenanceDescription;

    @Future
    private LocalDate carMaintenanceReturnDate;

    @NotNull
    private int carId;
}
