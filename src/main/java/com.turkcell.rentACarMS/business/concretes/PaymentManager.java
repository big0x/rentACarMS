package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.*;
import com.turkcell.rentACarMS.business.constants.Messages;
import com.turkcell.rentACarMS.business.dtos.ListPaymentDto;
import com.turkcell.rentACarMS.business.dtos.PaymentDto;
import com.turkcell.rentACarMS.business.requests.create.CreatePaymentInfoRequest;
import com.turkcell.rentACarMS.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentACarMS.core.utilities.exceptions.BusinessException;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import com.turkcell.rentACarMS.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACarMS.core.utilities.results.SuccessResult;
import com.turkcell.rentACarMS.dataAccess.abstracts.PaymentDao;
import com.turkcell.rentACarMS.entities.concretes.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentManager implements PaymentService {

    PosService posService;
    ModelMapperService modelMapperService;
    PaymentDao paymentDao;
    InvoiceService invoiceService;
    OrderedAdditionalServiceService orderedAdditionalServiceService;
    PaymentInfoService paymentInfoService;

    @Autowired
    public PaymentManager(PosService posService, ModelMapperService modelMapperService, PaymentDao paymentDao, InvoiceService invoiceService, OrderedAdditionalServiceService orderedAdditionalServiceService, PaymentInfoService paymentInfoService) {
        this.posService = posService;
        this.modelMapperService = modelMapperService;
        this.paymentDao = paymentDao;
        this.orderedAdditionalServiceService = orderedAdditionalServiceService;
        this.invoiceService = invoiceService;
        this.paymentInfoService = paymentInfoService;
    }

    @Override
    public DataResult<List<ListPaymentDto>> listAll() {

        List<Payment> payments = this.paymentDao.findAll();
        List<ListPaymentDto> listPaymentDto = payments.stream().map(payment -> this.modelMapperService.forDto().map(payment, ListPaymentDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ListPaymentDto>>(listPaymentDto, listPaymentDto.size() + " " + Messages.PAYMENTFOUND);
    }

    @Override
    public DataResult<PaymentDto> getById(int paymentId) {

        checkPaymentExists(paymentId);

        Payment payment = this.paymentDao.getById(paymentId);
        PaymentDto paymentDto = this.modelMapperService.forDto().map(payment, PaymentDto.class);

        return new SuccessDataResult<PaymentDto>(paymentDto,Messages.PAYMENTFOUND);
    }

    @Override
    @Transactional
    public Result create(CreatePaymentRequest createPaymentRequest) {

        //check methods

        Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest , Payment.class);

        this.paymentDao.save(payment);

        saveCardInfoIfRememberMeExists(createPaymentRequest);

    return new SuccessResult(Messages.PAYMENTADDED);
    }

    private Result checkPaymentExists(int paymentId) {
        if (!this.paymentDao.existsById(paymentId)) {
            throw new BusinessException(Messages.PAYMENTNOTFOUND);
        }
        return new SuccessResult();
    }
    private Result saveCardInfoIfRememberMeExists(CreatePaymentRequest createPaymentRequest){

        CreatePaymentInfoRequest createPaymentInfoRequest = new CreatePaymentInfoRequest();

        if(createPaymentRequest.isRememberMe()){

            createPaymentInfoRequest.setCreditCardCVV(createPaymentRequest.getCreditCardCVV());
            createPaymentInfoRequest.setCreditCardNumber(createPaymentRequest.getCreditCardNumber());
            createPaymentInfoRequest.setCreditCardExpirationDate(createPaymentRequest.getCreditCardExpirationDate());
            createPaymentInfoRequest.setCreditCardOwnerName(createPaymentRequest.getCreditCardOwnerName());
            createPaymentInfoRequest.setCustomerId(createPaymentRequest.getCustomerId());

            this.paymentInfoService.save(createPaymentInfoRequest);
        }
        return new SuccessResult();
    }
}
