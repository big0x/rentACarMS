package com.turkcell.rentACarMS.dataAccess.abstracts;

import com.turkcell.rentACarMS.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer>{

	boolean existsById(int id);
	
	boolean existsByBrandName(String name);
}
