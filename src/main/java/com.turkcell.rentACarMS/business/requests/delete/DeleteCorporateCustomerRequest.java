package com.turkcell.rentACarMS.business.requests.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCorporateCustomerRequest {
    @ReadOnlyProperty
    private int id;
}
