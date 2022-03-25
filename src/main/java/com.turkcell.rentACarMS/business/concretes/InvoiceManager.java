package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.AdditionalServiceService;
import com.turkcell.rentACarMS.business.abstracts.InvoiceService;
import com.turkcell.rentACarMS.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentACarMS.business.abstracts.RentalService;
import com.turkcell.rentACarMS.business.constants.Messages;
import com.turkcell.rentACarMS.business.dtos.InvoiceDto;
import com.turkcell.rentACarMS.business.dtos.ListInvoiceDto;
import com.turkcell.rentACarMS.business.dtos.RentalDto;
import com.turkcell.rentACarMS.business.requests.create.CreateInvoiceRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteInvoiceRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateInvoiceRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import com.turkcell.rentACarMS.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACarMS.core.utilities.results.SuccessResult;
import com.turkcell.rentACarMS.dataAccess.abstracts.CustomerDao;
import com.turkcell.rentACarMS.dataAccess.abstracts.InvoiceDao;
import com.turkcell.rentACarMS.entities.concretes.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceManager implements InvoiceService {

    private InvoiceDao invoiceDao;
    private ModelMapperService modelMapperService;
    private RentalService rentalService;
    private OrderedAdditionalServiceService orderedAdditionalServiceService;
    private AdditionalServiceService additionalServiceService;
    private CustomerDao customerDao;

    @Autowired
    public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService, RentalService rentalService, OrderedAdditionalServiceService orderedAdditionalServiceService, AdditionalServiceService additionalServiceService, CustomerDao customerDao) {
        this.invoiceDao = invoiceDao;
        this.customerDao = customerDao;
        this.modelMapperService = modelMapperService;
        this.rentalService = rentalService;
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
        this.additionalServiceService = additionalServiceService;
    }

    @Override
    public DataResult<List<ListInvoiceDto>> listAll() {

        List<Invoice> invoices = this.invoiceDao.findAll();
        List<ListInvoiceDto> listInvoiceDto = invoices.stream().map(invoice -> this.modelMapperService.forDto().map(invoice, ListInvoiceDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ListInvoiceDto>>(listInvoiceDto, listInvoiceDto.size() + " : " + Messages.INVOICEFOUND);
    }

    @Override
    public Result create(CreateInvoiceRequest createInvoiceRequest) {

        checkInvoiceNoExists(createInvoiceRequest.getInvoiceNo());
        checkCustomerId(createInvoiceRequest.getCustomerId());
        checkRentalId(createInvoiceRequest.getRentalId());

        DataResult<RentalDto> rentalDto = this.rentalService.getById(createInvoiceRequest.getRentalId());
        Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest,Invoice.class);

        invoice.setRentTotalPrice(rentalDto.getData().getRentalPrice());
        invoice.setInvoiceDateOfCreation(LocalDate.now());

        this.invoiceDao.save(invoice);

        return new SuccessResult(Messages.INVOICEADDED);
    }

    @Override
    public Result update(UpdateInvoiceRequest updateInvoiceRequest) {

        checkInvoiceExists(updateInvoiceRequest.getId());
        checkInvoiceNoExists(updateInvoiceRequest.getInvoiceNo());
        checkCustomerId(updateInvoiceRequest.getCustomerId());
        checkRentalId(updateInvoiceRequest.getRentalId());

        DataResult<RentalDto> rentalDto = this.rentalService.getById(updateInvoiceRequest.getRentalId());
        Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest,Invoice.class);

        invoice.setRentTotalPrice(rentalDto.getData().getRentalPrice());

        this.invoiceDao.save(invoice);

        return new SuccessResult(Messages.INVOICEUPDATED);
    }

    @Override
    public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {

        checkInvoiceExists(deleteInvoiceRequest.getId());

        Invoice invoice = this.modelMapperService.forRequest().map(deleteInvoiceRequest,Invoice.class);

        this.invoiceDao.delete(invoice);

        return new SuccessResult(Messages.INVOICEDELETED);
    }

    @Override
    public DataResult<InvoiceDto> getById(int invoiceId) {

        checkInvoiceExists(invoiceId);

        Invoice invoice = this.invoiceDao.getById(invoiceId);
        InvoiceDto invoiceDto = this.modelMapperService.forDto().map(invoice, InvoiceDto.class);

        return new SuccessDataResult<InvoiceDto>(invoiceDto, Messages.INVOICEFOUND);
    }

    private Result checkInvoiceExists(int invoiceId){
        if(!this.invoiceDao.existsById(invoiceId)){
            throw new BusinessException(Messages.INVOICENOTFOUND);
        }
        return new SuccessResult();
    }
    private Result checkInvoiceNoExists(long invoiceNo){
        if(this.invoiceDao.existsByInvoiceNo(invoiceNo)){
            throw new BusinessException(Messages.INVOICENOEXISTS);
        }
        return new SuccessResult();
    }
    private Result checkRentalId(int rentalId) throws BusinessException {

        if (this.rentalService.getById(rentalId)==null) {
            throw new BusinessException(Messages.RENTALNOTFOUND);
        }
        return new SuccessResult();
    }
    private Result checkCustomerId(int customerId) throws BusinessException{
        if(!this.customerDao.existsById(customerId)){
            throw new BusinessException(Messages.CUSTOMERNOTFOUND);
        }
        return new SuccessResult();
    }
}
