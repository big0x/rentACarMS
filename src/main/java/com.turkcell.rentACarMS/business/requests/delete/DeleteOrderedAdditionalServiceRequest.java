package com.turkcell.rentACarMS.business.requests.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOrderedAdditionalServiceRequest {
    @NotNull
    private int id;
}
