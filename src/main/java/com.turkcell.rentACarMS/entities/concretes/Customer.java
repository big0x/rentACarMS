package com.turkcell.rentACarMS.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="customers")
@Entity
@PrimaryKeyJoinColumn(name ="id")
public class Customer extends User{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
    private LocalDate registeredAt;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Rental> rentals;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private List<Invoice> invoices;

}
