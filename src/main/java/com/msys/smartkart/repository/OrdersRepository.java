package com.msys.smartkart.repository;

import com.msys.smartkart.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {

    List<Orders>findAllByUser_Id(final Integer userId);
}
