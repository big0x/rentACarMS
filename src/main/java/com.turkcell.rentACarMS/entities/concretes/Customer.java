package com.turkcell.rentACarMS.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="customers")
@Entity
@EqualsAndHashCode(callSuper = false)
public class Customer extends User{

    @Column(name="customer_first_name")
    private String customerFirstName;
    @Column(name="customer_last_name")
    private String customerLastName;
    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Rental> rentals;
}
