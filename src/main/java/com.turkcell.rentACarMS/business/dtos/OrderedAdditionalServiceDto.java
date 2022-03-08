package com.turkcell.rentACarMS.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedAdditionalServiceDto {
    private int rentalId;
    private int additionalServiceDto;
}
