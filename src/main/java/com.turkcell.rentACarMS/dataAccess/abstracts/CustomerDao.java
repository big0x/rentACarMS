package com.turkcell.rentACarMS.dataAccess.abstracts;

import com.turkcell.rentACarMS.entities.concretes.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<IndividualCustomer,Integer> {
    boolean existsById(int id);
    boolean existsByEmail(String email);
}
