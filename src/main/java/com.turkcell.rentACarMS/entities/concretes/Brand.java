package com.turkcell.rentACarMS.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="brands")
@Entity
public class Brand {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="brand_name")
	private String brandName;

	@OneToMany(mappedBy = "brand")
	@JsonIgnore
	private List<Car> cars;
}
