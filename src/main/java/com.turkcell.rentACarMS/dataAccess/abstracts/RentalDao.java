package com.turkcell.rentACarMS.dataAccess.abstracts;

import com.turkcell.rentACarMS.entities.concretes.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalDao extends JpaRepository<Rental,Integer> {
    //List<Rental> listAllByCustomerId(int customerId);
}
