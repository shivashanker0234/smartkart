package com.msys.smartkart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    Integer quantity;

    Integer totalPrice;

    String productName;

    @ManyToOne
    User user;

    @ManyToOne
    Product product;

    @CreationTimestamp
    Date date;
}
