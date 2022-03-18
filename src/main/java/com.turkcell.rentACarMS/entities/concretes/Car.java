package com.turkcell.rentACarMS.entities.concretes;

import com.turkcell.rentACarMS.entities.enums.CarStates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cars")
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="car_daily_price")
    private double carDailyPrice;

    @Column(name="car_model_year")
    private int carModelYear;

    @Column(name="car_description")
    private String carDescription;

    @Column(name="kilometer")
    private int carKilometer;

    @Column(name="state")
    private CarStates carStates;

//    @ManyToOne
//    @JoinColumn(name = "city_id",referencedColumnName = "id")
//    private City city;

    @ManyToOne
    @JoinColumn(name="brand_id",referencedColumnName = "id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name="color_id",referencedColumnName = "id")
    private Color color;

    @OneToMany(mappedBy = "car",fetch = FetchType.LAZY)
    private List<CarMaintenance> carMaintenances;

    @OneToMany(mappedBy = "car",fetch = FetchType.LAZY)
    private List<Rental> rentals;

//    @OneToMany(mappedBy = "rentalKilometer",fetch = FetchType.LAZY)
//    private List<Rental> rentalKilometers;
//
//    @OneToMany(mappedBy = "returnKilometer",fetch = FetchType.LAZY)
//    private List<Rental> returnKilometers;

}
