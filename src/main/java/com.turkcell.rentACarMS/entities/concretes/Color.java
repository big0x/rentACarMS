package com.turkcell.rentACarMS.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="colors")
@Entity
public class Color {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="color_id")
	private int colorId;
	
	@Column(name="color_name")
	private String colorName;

	@OneToMany
	private List<Car> cars;
}