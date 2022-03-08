package com.turkcell.rentACarMS.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ordered_additional_services")
@Entity
public class OrderedAdditionalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "additional_service_id",referencedColumnName = "id")
    private AdditionalService additionalService;
    @ManyToOne
    @JoinColumn(name = "rental_id",referencedColumnName = "id")
    private Rental rental;

}
