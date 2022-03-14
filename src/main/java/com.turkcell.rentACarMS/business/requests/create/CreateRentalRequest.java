package com.turkcell.rentACarMS.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
    @ReadOnlyProperty
    private int id;
    @FutureOrPresent
    @NotNull
    private LocalDate rentDate;
    @Future
    @NotNull
    private LocalDate returnDate;
    @NotNull
    private int carId;
    @NotNull
    private int customerId;
    @NotNull
    private int rentalCityId;
    @NotNull
    private int returnCityId;

    private List<CreateOrderedAdditionalServiceRequest> orderedAdditionalServiceRequests;

}
