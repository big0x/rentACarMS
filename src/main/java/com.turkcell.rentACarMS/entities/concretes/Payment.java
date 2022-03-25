package com.turkcell.rentACarMS.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="payments")
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
//    @Column(name="credit_card_number")
//    private long creditCardNumber;
//    @Column(name = "credit_card_cvv")
//    private int creditCardCVV;
//    @Column(name = "credit_card_expiration_date")
//    private LocalDate creditCardExpirationDate;
    @Column(name = "total_payment")
    private double totalPayment;

    @Transient
    private boolean rememberMe;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental paymentRental;

    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice paymentInvoice;

}
