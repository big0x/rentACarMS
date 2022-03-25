package com.turkcell.rentACarMS.business.concretes;

import com.turkcell.rentACarMS.business.abstracts.PaymentInfoService;
import com.turkcell.rentACarMS.business.constants.Messages;
import com.turkcell.rentACarMS.business.dtos.ListPaymentInfoDto;
import com.turkcell.rentACarMS.business.requests.create.CreatePaymentInfoRequest;
import com.turkcell.rentACarMS.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentACarMS.core.utilities.results.DataResult;
import com.turkcell.rentACarMS.core.utilities.results.Result;
import com.turkcell.rentACarMS.core.utilities.results.SuccessDataResult;
import com.turkcell.rentACarMS.core.utilities.results.SuccessResult;
import com.turkcell.rentACarMS.dataAccess.abstracts.PaymentInfoDao;
import com.turkcell.rentACarMS.entities.concretes.PaymentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentInfoManager implements PaymentInfoService {

    PaymentInfoDao paymentInfoDao;
    ModelMapperService modelMapperService;

    @Autowired
    public PaymentInfoManager(PaymentInfoDao paymentInfoDao, ModelMapperService modelMapperService) {
        this.paymentInfoDao = paymentInfoDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<ListPaymentInfoDto>> listAll() {

        List<PaymentInfo> paymentInfos = this.paymentInfoDao.findAll();
        List<ListPaymentInfoDto> listPaymentInfoDto = paymentInfos.stream().map(paymentInfo -> this.modelMapperService.forDto().map(paymentInfo, ListPaymentInfoDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<ListPaymentInfoDto>>(listPaymentInfoDto,listPaymentInfoDto.size() + " " + Messages.PAYMENTINFOFOUND);
    }

    @Override
    public Result save(CreatePaymentInfoRequest createPaymentInfoRequest) {

        checkPaymentInfoExists(createPaymentInfoRequest.getCreditCardNumber());

        PaymentInfo paymentInfo = this.modelMapperService.forRequest().map(createPaymentInfoRequest, PaymentInfo.class);
        this.paymentInfoDao.save(paymentInfo);

        return new SuccessResult(Messages.PAYMENTINFOADDED);
    }

    private void checkPaymentInfoExists(String creditCardNumber) {
        //
    }
}
