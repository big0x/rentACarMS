package com.turkcell.rentACarMS.business.requests.update;

import com.turkcell.rentACarMS.business.requests.create.CreateOrderedAdditionalServiceRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderedAdditionalServiceRequest {
    @NotNull
    private int id;
    @NotNull
    private int additionalServiceId;

    private List<CreateOrderedAdditionalServiceRequest> orderedAdditionalServiceRequests;
}
