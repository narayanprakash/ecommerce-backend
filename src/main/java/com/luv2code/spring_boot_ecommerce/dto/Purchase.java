package com.luv2code.spring_boot_ecommerce.dto;

import com.luv2code.spring_boot_ecommerce.entity.Address;
import com.luv2code.spring_boot_ecommerce.entity.Customer;
import com.luv2code.spring_boot_ecommerce.entity.Order;
import com.luv2code.spring_boot_ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase{
    private Customer customer;
    private Address address;
    private Address billingAddress;
    private Order order;
    private Set< OrderItem > orderItems;

}
