package com.turkcell.rentACarMS.api.controllers;

import com.turkcell.rentACarMS.business.abstracts.PaymentService;
import com.turkcell.rentACarMS.business.dtos.ListPaymentDto;
import com.turkcell.rentACarMS.business.dtos.PaymentDto;
import com.turkcell.rentACarMS.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/payments")
public class PaymentsController {
    private PaymentService paymentService;
    @Autowired
    public PaymentsController(PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    @GetMapping("/listallpayments")
    public DataResult<List<ListPaymentDto>> listAll() throws BusinessException {

        return this.paymentService.listAll();
    }

    @PostMapping("/createpayment")
    public Result create(@RequestBody @Valid CreatePaymentRequest createPaymentRequest) throws BusinessException {
        return this.paymentService.create(createPaymentRequest);
    }

//    @DeleteMapping("/deletepayment")
//    public Result delete(@RequestBody @Valid DeletePaymentRequest deletePaymentRequest) throws BusinessException {
//        return this.paymentService.delete(deletePaymentRequest);
//    }
//    @PutMapping("/updatepayment")
//    public Result update(@RequestBody @Valid UpdatePaymentRequest updatePaymentRequest) throws BusinessException {
//        return this.paymentService.update(updatePaymentRequest);
//    }
    @GetMapping("/getbypaymentid")
    public DataResult<PaymentDto> getById(@RequestParam int paymentId) throws BusinessException {
        return this.paymentService.getById(paymentId);
    }
}
