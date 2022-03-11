package com.turkcell.rentACarMS.business.requests.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteColorRequest {
    @NotNull
    @Min(0)
    @Max(50)
    private int id;

}
