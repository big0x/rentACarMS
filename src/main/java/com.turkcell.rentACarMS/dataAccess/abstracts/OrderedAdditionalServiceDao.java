package com.turkcell.rentACarMS.dataAccess.abstracts;

import com.turkcell.rentACarMS.entities.concretes.OrderedAdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedAdditionalServiceDao extends JpaRepository<OrderedAdditionalService,Integer> {

    //List<OrderedAdditionalService> findAllByRentalId(int rentalId);
}
