package com.msys.smartkart.service;

import com.msys.smartkart.entity.Cart;

import java.util.List;

public interface CartService {
    void addToCart(final Integer id, final Integer userId);

//    CartItem findByUserIdAndProductId(final Integer id, final Integer userId);

    Cart updateCartItems(final Integer id, final Integer userId, final Integer totalPrice, final Integer quantity);

    Cart getByUserIdAndProductId(final Integer userId, final Integer id);

    void deleteFromCart(final Integer userId,final Integer id);

    List<Cart> fetchAllByUserId(final Integer userId);

    Integer countQuantity(final Integer userId);

    Long countCartPrice(final Integer userId);
}
