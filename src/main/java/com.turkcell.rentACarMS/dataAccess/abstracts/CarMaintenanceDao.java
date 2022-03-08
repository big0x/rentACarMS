package com.turkcell.rentACarMS.dataAccess.abstracts;

import com.turkcell.rentACarMS.entities.concretes.CarMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMaintenanceDao extends JpaRepository<CarMaintenance,Integer> {
    boolean existsById(int id);
    //List<CarMaintenance> listAllByCarId(int carId);


}
