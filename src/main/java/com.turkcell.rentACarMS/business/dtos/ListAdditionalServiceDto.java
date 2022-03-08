package com.turkcell.rentACarMS.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListAdditionalServiceDto {
    private int additionalServiceId;
    private String additionalServiceName;
    private double additionalServicePrice;
    private String additionalServiceDescription;
}
