package com.turkcell.rentACarMS.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderedAdditionalServiceRequest {
    @ReadOnlyProperty
    private int id;
    @NotNull
    private int rentalId;
    @NotNull
    private int additionalServiceId;
}
