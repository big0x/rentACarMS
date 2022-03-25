package com.turkcell.rentACarMS.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="payment_infos")
@Entity
public class PaymentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="credit_card_owner_name")
    private String creditCardOwnerName;
    @Column(name="credit_card_number")
    private String creditCardNumber;
    @Column(name = "credit_card_cvv")
    private String creditCardCVV;
    @Column(name = "credit_card_expiration_date")
    private LocalDate creditCardExpirationDate;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

}
