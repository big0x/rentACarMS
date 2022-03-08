package com.turkcell.rentACarMS.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="additional_services")
@Entity
public class AdditionalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="additional_service_name")
    private String additionalServiceName;
    @Column(name="additional_service_price")
    private double additionalServicePrice;
    @Column(name="additional_service_description")
    private String additionalServiceDescription;

    @OneToMany(mappedBy = "additionalService")
    private List<OrderedAdditionalService> orderedAdditionalServices;

}
