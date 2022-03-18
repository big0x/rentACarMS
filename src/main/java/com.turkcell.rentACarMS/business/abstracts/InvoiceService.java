package com.turkcell.rentACarMS.business.abstracts;

import com.turkcell.rentACarMS.business.dtos.InvoiceDto;
import com.turkcell.rentACarMS.business.dtos.ListInvoiceDto;
import com.turkcell.rentACarMS.business.requests.create.CreateInvoiceRequest;
import com.turkcell.rentACarMS.business.requests.delete.DeleteInvoiceRequest;
import com.turkcell.rentACarMS.business.requests.update.UpdateInvoiceRequest;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;

import java.util.List;

public interface InvoiceService {
    DataResult<List<ListInvoiceDto>> listAll();
    Result create(CreateInvoiceRequest createInvoiceRequest);
    Result update(UpdateInvoiceRequest updateInvoiceRequest);
    Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
    DataResult<InvoiceDto> getById(int invoiceId);
}
