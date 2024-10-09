package com.luv2code.spring_boot_ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Table ( name = "order_item" )
@Getter
@Setter
@Entity
public class OrderItem{
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    @Column ( name = "id" )
    private Long id;
    @Column ( name = "imageUrl" )
    private String imageUrl;
    @Column ( name = "unitPrice" )
    private BigDecimal unitPrice;
    @Column ( name = "quantity" )
    private int quantity;
    @Column ( name = "product_d" )
    private Long productId;
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "order_id")
    private Order order;


}
