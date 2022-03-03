package com.turkcell.rentACarMS.dataAccess.abstracts;

import com.turkcell.rentACarMS.entities.concretes.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDao extends JpaRepository<Car,Integer> {

    boolean existsByCarId(int id);
    List<Car> findByCarDailyPriceLessThanEqual(double dailyPrice);
}
