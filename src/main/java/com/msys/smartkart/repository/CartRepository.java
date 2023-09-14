package com.msys.smartkart.repository;

import com.msys.smartkart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findCartItemByUser_IdAndProduct_Id(final Integer userId, final Integer id);

    void deleteByUser_IdAndProduct_Id(final Integer userId, final Integer id);

    List<Cart> findAllByUser_Id(final Integer userId);

    @Query(value = "select sum(quantity) FROM cart ", nativeQuery = true)
    Integer countQuantity(final Integer userId);

    @Query(value = "select sum(total_price) from cart",nativeQuery = true)
    Long countCartPrice(final Integer userId);
}
