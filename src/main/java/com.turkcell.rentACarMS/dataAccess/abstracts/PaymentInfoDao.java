package com.turkcell.rentACarMS.dataAccess.abstracts;

import com.turkcell.rentACarMS.entities.concretes.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentInfoDao extends JpaRepository<PaymentInfo,Integer> {
}
