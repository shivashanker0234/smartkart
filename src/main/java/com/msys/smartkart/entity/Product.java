package com.msys.smartkart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Product {
    @Id
    Integer id;
    String productName;
    String color;
    String description;
    String details;
    Integer price;
//    @Lob
    String image;
    @CreationTimestamp
    Date date;

}
