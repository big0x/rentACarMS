package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.ListOrderedAdditionalServiceDto;
import com.turkcell.rentACarMS.business.dtos.OrderedAdditionalServiceDto;
import com.turkcell.rentACarMS.business.requests.create.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteOrderedAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateOrderedAdditionalServiceRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface OrderedAdditionalServiceService {
    DataResult<List<ListOrderedAdditionalServiceDto>> listAll() throws BusinessException;
    Result create(CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest) throws BusinessException;
    Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest) throws BusinessException;
    Result delete(DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalServiceRequest) throws BusinessException;
    DataResult<OrderedAdditionalServiceDto> getById(int orderedAdditionalServiceId) throws BusinessException;
}
