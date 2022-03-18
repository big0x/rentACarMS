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

    @Column(name="rental_price")
    private double rentalPrice;

    @Column(name="rental_kilometer")
    private int rentalKilometer;

    @Column(name="return_kilometer")
    private int returnKilometer;

    @ManyToOne
    @JoinColumn(name = "rental_city_id",referencedColumnName = "id")
    private City rentalCity;

    @ManyToOne
    @JoinColumn(name = "return_city_id",referencedColumnName = "id")
    private City returnCity;

    @ManyToOne
    @JoinColumn(name="car_id",referencedColumnName = "id")
    private Car car;

//    @ManyToOne
//    @JoinColumn(name="city_id",referencedColumnName = "id")
//    private City city;

    @ManyToOne
    @JoinColumn(name="customer_id",referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "rental",fetch = FetchType.LAZY)
    private List<OrderedAdditionalService> orderedAdditionalService;

    @OneToOne(mappedBy = "rental")
    private Invoice invoice;
}
