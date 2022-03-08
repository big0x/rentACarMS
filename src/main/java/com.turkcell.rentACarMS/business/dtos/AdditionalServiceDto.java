package com.turkcell.rentACarMS.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceDto {
    private String additionalServiceName;
    private double additionalServicePrice;
    private String additionalServiceDescription;
}
