package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentACarMS.business.dtos.ListOrderedAdditionalServiceDto;
import com.turkcell.rentACarMS.business.dtos.OrderedAdditionalServiceDto;
import com.turkcell.rentACarMS.business.requests.create.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteOrderedAdditionalServiceRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateOrderedAdditionalServiceRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.*;
import com.turkcell.rentACarMS.dataAccess.abstracts.AdditionalServiceDao;
import com.turkcell.rentACarMS.dataAccess.abstracts.OrderedAdditionalServiceDao;
import com.turkcell.rentACarMS.dataAccess.abstracts.RentalDao;
import com.turkcell.rentACarMS.entities.concretes.OrderedAdditionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderedAdditionalServiceManager implements OrderedAdditionalServiceService {

    private OrderedAdditionalServiceDao orderedAdditionalServiceDao;
    private ModelMapperService modelMapperService;
    private RentalDao rentalDao;
    private AdditionalServiceDao additionalServiceDao;

    @Autowired
    public OrderedAdditionalServiceManager (OrderedAdditionalServiceDao orderedAdditionalServiceDao,ModelMapperService modelMapperService, RentalDao rentalDao,AdditionalServiceDao additionalServiceDao){

        this.orderedAdditionalServiceDao = orderedAdditionalServiceDao;
        this.modelMapperService = modelMapperService;
        this.rentalDao = rentalDao;
        this.additionalServiceDao = additionalServiceDao;
    }

    @Override
    public DataResult<List<ListOrderedAdditionalServiceDto>> listAll() {

        List<OrderedAdditionalService> orderedAdditionalServices = this.orderedAdditionalServiceDao.findAll();
        List<ListOrderedAdditionalServiceDto> listOrderedAdditionalServiceDto = orderedAdditionalServices.stream().map(orderedAdditionalService -> this.modelMapperService.forDto().map(orderedAdditionalService,ListOrderedAdditionalServiceDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ListOrderedAdditionalServiceDto>>(listOrderedAdditionalServiceDto,listOrderedAdditionalServiceDto.size() + " : Ordered Additional Services found.");
    }

    @Override
    public Result create(CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest) throws BusinessException {

        OrderedAdditionalService orderedAdditionalService =this.modelMapperService.forRequest().map(createOrderedAdditionalServiceRequest,OrderedAdditionalService.class);
        this.orderedAdditionalServiceDao.save(orderedAdditionalService);

        return new SuccessResult("Ordered Additional Service added with id: " + orderedAdditionalService.getId());
    }

    @Override
    public Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest) throws BusinessException {

        checkOrderedAdditionalServiceId(updateOrderedAdditionalServiceRequest.getId());

        OrderedAdditionalService orderedAdditionalService =this.modelMapperService.forRequest().map(updateOrderedAdditionalServiceRequest,OrderedAdditionalService.class);
        this.orderedAdditionalServiceDao.save(orderedAdditionalService);

        return new SuccessResult(updateOrderedAdditionalServiceRequest.getId() + " : Ordered Additional Service updated.");
    }

    @Override
    public Result delete(DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalServiceRequest) throws BusinessException {

        checkOrderedAdditionalServiceId(deleteOrderedAdditionalServiceRequest.getId());

        OrderedAdditionalService orderedAdditionalService = this.modelMapperService.forRequest().map(deleteOrderedAdditionalServiceRequest,OrderedAdditionalService.class);
        this.orderedAdditionalServiceDao.delete(orderedAdditionalService);

        return new SuccessResult(deleteOrderedAdditionalServiceRequest.getId() + " : Ordered Additional Service deleted.");
    }

    @Override
    public DataResult<OrderedAdditionalServiceDto> getById(int orderedAdditionalServiceId) throws BusinessException {

        checkOrderedAdditionalServiceId(orderedAdditionalServiceId);

        OrderedAdditionalService orderedAdditionalService = this.orderedAdditionalServiceDao.getById(orderedAdditionalServiceId);
        OrderedAdditionalServiceDto orderedAdditionalServiceDto = this.modelMapperService.forDto().map(orderedAdditionalService,OrderedAdditionalServiceDto.class);

        return new SuccessDataResult<OrderedAdditionalServiceDto>(orderedAdditionalServiceDto,"Ordered Additional Service found.");
    }

    private Result checkOrderedAdditionalServiceId(int orderedAdditionalServiceId) throws BusinessException {
        if (!this.orderedAdditionalServiceDao.existsById(orderedAdditionalServiceId)) {
            throw new BusinessException("Ordered Additional Service not found.");
        }
        return new SuccessResult();
    }
}
