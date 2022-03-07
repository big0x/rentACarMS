package com.turkcell.rentACarMS.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="carMaintenances")
@Entity
public class CarMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="car_maintenance_description")
    private String carMaintenanceDescription;

    @Column(name="car_maintenance_return_date")
    private LocalDate carMaintenanceReturnDate;

    @ManyToOne
    @JoinColumn(name="car_id",referencedColumnName = "id")
    private Car car;
}
