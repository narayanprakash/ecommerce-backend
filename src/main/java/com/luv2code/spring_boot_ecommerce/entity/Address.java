package com.luv2code.spring_boot_ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "address")
@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "state")
    private String state;
    @Column(name = "street")
    private String street;
    @Column(name = "zip_code")
    private String zipCode;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;
}
