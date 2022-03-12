package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentACarMS.business.dtos.ListOrderedAdditionalServiceDto;
import com.turkcell.rentACarMS.business.dtos.OrderedAdditionalServiceDto;
import com.turkcell.rentACarMS.business.requests.create.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteOrderedAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateOrderedAdditionalServiceRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/orderedadditionalservices")
public class OrderedAdditionalServicesController {
    private OrderedAdditionalServiceService orderedAdditionalServiceService;

    public OrderedAdditionalServicesController(OrderedAdditionalServiceService orderedAdditionalServiceService){
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
    }
    @GetMapping("/listallorderedadditionalservices")
    public DataResult<List<ListOrderedAdditionalServiceDto>> listAll() throws BusinessException {
        return this.orderedAdditionalServiceService.listAll();
    }
    @PostMapping("/createorderedadditionalservice")
    public Result create(@RequestBody @Valid CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest) throws BusinessException {
        return this.orderedAdditionalServiceService.create(createOrderedAdditionalServiceRequest);
    }
    @DeleteMapping("/deleteorderedadditionalservice")
    public Result delete(@RequestBody @Valid DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalServiceRequest) throws BusinessException {
        return this.orderedAdditionalServiceService.delete(deleteOrderedAdditionalServiceRequest);
    }
    @PutMapping("/updateorderedadditionalservice")
    public Result update(@RequestBody @Valid UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest) throws BusinessException {
        return this.orderedAdditionalServiceService.update(updateOrderedAdditionalServiceRequest);
    }

    @GetMapping("/getbyorderedadditionalserviceid")
    public DataResult<OrderedAdditionalServiceDto> getById(@RequestParam int orderedAdditionalServiceId) throws BusinessException {
        return this.orderedAdditionalServiceService.getById(orderedAdditionalServiceId);
    }
}
