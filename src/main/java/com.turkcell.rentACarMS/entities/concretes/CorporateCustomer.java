package com.turkcell.rentACarMS.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="corporate_customers")
@Entity
public class CorporateCustomer extends Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="corporate_name")
    private String corporateName;
    @Column(name="corporate_tax_no")
    private int taxNo;
}
