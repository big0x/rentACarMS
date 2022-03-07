package com.turkcell.rentACarMS.entities.concretes;

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

    @ManyToOne
    @JoinColumn(name="brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name="color_id")
    private Color color;

    @Column(name="is_active")
    private boolean isActive = true;

    @OneToMany(mappedBy = "car")
    private List<CarMaintenance> carMaintenances;

    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;


}
