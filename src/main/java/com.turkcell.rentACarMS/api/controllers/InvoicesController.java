package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.InvoiceService;
import com.turkcell.rentACarMS.business.dtos.InvoiceDto;
import com.turkcell.rentACarMS.business.dtos.ListInvoiceDto;
import com.turkcell.rentACarMS.business.requests.create.CreateInvoiceRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteInvoiceRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateInvoiceRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {

    private InvoiceService invoiceService;

    @Autowired
    public InvoicesController(InvoiceService invoiceService) {

        this.invoiceService = invoiceService;
    }

    @GetMapping("/listallinvoices")
    public DataResult<List<ListInvoiceDto>> listAll() throws BusinessException {

        return this.invoiceService.listAll();
    }

    @PostMapping("/createinvoice")
    public Result create(@RequestBody @Valid CreateInvoiceRequest createInvoiceRequest) throws BusinessException {
        return this.invoiceService.create(createInvoiceRequest);
    }

    @DeleteMapping("/deleteinvoice")
    public Result delete(@RequestBody @Valid DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException {
        return this.invoiceService.delete(deleteInvoiceRequest);
    }
    @PutMapping("/updateinvoice")
    public Result update(@RequestBody @Valid UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException {
        return this.invoiceService.update(updateInvoiceRequest);
    }
    @GetMapping("/getbyinvoiceid")
    public DataResult<InvoiceDto> getById(@RequestParam int invoiceId) throws BusinessException {
        return this.invoiceService.getById(invoiceId);
    }
}
