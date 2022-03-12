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

    @OneToMany(mappedBy = "city",fetch = FetchType.LAZY)
    private List<Car> cars;

    @OneToMany(mappedBy = "returnCity",fetch = FetchType.LAZY)
    private List<Rental> rentalCarsReturnCity;

    @OneToMany(mappedBy = "city",fetch = FetchType.LAZY)
    private List<Rental> rentals;
}

