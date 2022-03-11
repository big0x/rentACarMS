package com.turkcell.rentACarMS.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cities")
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="city_name")
    private String cityName;

    @OneToMany(mappedBy = "currentCity")
    private List<Car> carsCurrentCity;

    @OneToMany(mappedBy = "returnCity")
    private List<Rental> rentalCarsReturnCity;

    @OneToMany(mappedBy = "city")
    private List<Rental> rentals;
}

