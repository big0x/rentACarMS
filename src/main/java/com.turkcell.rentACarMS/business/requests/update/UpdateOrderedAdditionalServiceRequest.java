package com.turkcell.rentACarMS.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderedAdditionalServiceRequest {
    @NotNull
    private int id;
    @NotNull
    private int rentalId;
    @NotNull
    private int additionalServiceId;
}
