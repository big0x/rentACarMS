package com.turkcell.rentACarMS.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {
    @NotNull
    @Min(0)
    private int id;

    @NotNull
    @Size(min = 2,max = 50)
    private String brandName;

}
