package com.msys.smartkart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    Integer quantity;

    Integer totalPrice;

    @OneToOne
    Product product;

    @ManyToOne
    User user;

    @CreationTimestamp
    Date date;
}
