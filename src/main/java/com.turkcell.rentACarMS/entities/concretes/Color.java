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
@Table(name="colors")
@Entity
public class Color {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="color_name")
	private String colorName;

	@OneToMany(mappedBy = "color")
	@JsonIgnore
	private List<Car> cars;
}
