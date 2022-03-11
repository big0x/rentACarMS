package com.turkcell.rentACarMS.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="individual_customers")
@Entity
//@EqualsAndHashCode(callSuper = false)
public class IndividualCustomer extends Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="customer_first_name")
    private String customerFirstName;
    @Column(name="customer_last_name")
    private String customerLastName;


}
