package com.turkcell.rentACarMS.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderedAdditionalServiceRequest {
    @NotNull
    private int rentalId;
    @NotNull
    private int additionalServiceId;
}
