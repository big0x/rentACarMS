package com.turkcell.rentACarMS.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoices")
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private int id;

    @Column(name= "invoice_no",unique = true)
    private long invoiceNo;

    @Column(name= "invoice_date_of_creation")
    private LocalDate invoiceDateOfCreation;

    @Column(name= "rental_days")
    private int rentalDays;

    @Column(name= "rental_date")
    private LocalDate rentalDate;

    @Column(name= "return_date")
    private LocalDate returnDate;

    @Column(name= "rent_total_price")
    private double rentTotalPrice;

    @ManyToOne
    @JoinColumn(name="customer_id",referencedColumnName = "id")
    private Customer customer;

    @OneToOne(mappedBy = "paymentInvoice")
    private Payment invoicePayment;

    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental invoiceRental;


}
