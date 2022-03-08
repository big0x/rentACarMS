package com.turkcell.rentACarMS.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="rentals")
@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="rental_rent_date")
    private LocalDate rentalRentDate;
    @Column(name="rental_return_date")
    private LocalDate rentalReturnDate;
    @ManyToOne
    @JoinColumn(name="car_id",referencedColumnName = "id")
    private Car car;
    @ManyToOne
    @JoinColumn(name="customer_id",referencedColumnName = "id")
    private Customer customer;
    @OneToMany(mappedBy = "rental")
    private List<OrderedAdditionalService> orderedAdditionalService;
}