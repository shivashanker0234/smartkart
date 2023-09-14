package com.msys.smartkart.service;

import com.msys.smartkart.entity.Cart;
import com.msys.smartkart.entity.Product;
import com.msys.smartkart.entity.User;
import com.msys.smartkart.repository.CartRepository;
import com.msys.smartkart.repository.ProductRepository;
import com.msys.smartkart.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CartServiceImpl implements CartService {
    final CartRepository cartRepository;

    final UserRepository userRepository;

    final ProductRepository productRepository;

    @Override
    // where parameter id is Product Id
    public void addToCart(final Integer id, final Integer userId) {

        final Product product = productRepository.findById(id).get();
        log.info("Fetching  the Product by product Id ");
        log.info(product + "found where Id is " + id);

        final User user = userRepository.findById(userId).get();
        log.info("Fetching  the User by user  Id ");
        log.info(user + "found where Id is " + userId);

        final Cart cart = new Cart();
        log.info("Creating new object of CartItem");
        cart.setUser(user);
        cart.setProduct(product);
        cart.setTotalPrice(product.getPrice());
        cart.setQuantity(1);

        cartRepository.save(cart);
        log.info("Product added to cart Successfully");

    }

    @Override
    public Cart getByUserIdAndProductId(final Integer userId, final Integer id) {

        log.info("Finding product by cartItem Id nd Product Id");
        return cartRepository.findCartItemByUser_IdAndProduct_Id(userId, id);
    }

    @Override
    public void deleteFromCart(final Integer userId, final Integer id) {
        log.warn("Product Removed From Cart");
        cartRepository.deleteByUser_IdAndProduct_Id(userId, id);

    }

    @Override
    public List<Cart> fetchAllByUserId(final Integer userId) {

        final List<Cart> cartList = cartRepository.findAllByUser_Id(userId);
        log.info("list of products");
        log.info(String.valueOf(cartList));
        if (cartList.isEmpty()) {
            log.info("Cart is empty");
        }
        return cartList;
    }

    @Override
    public Integer countQuantity(final Integer userId) {
        final Integer totalQuantity = cartRepository.countQuantity(userId);
        log.info("The User ID is : " + userId);
        return totalQuantity;
    }

    @Override
    public Long countCartPrice(final Integer userId) {
        final Long totalPrice = cartRepository.countCartPrice(userId);
        log.info("Total Cart Price is : "+totalPrice);
        return totalPrice;
    }

    @Override
    public Cart updateCartItems(final Integer id, final Integer userId, final Integer totalPrice, final Integer quantity) {

        final Product product = productRepository.findById(id).get();
        log.info("Finding product by Product ID");
        final User user = userRepository.findById(userId).get();
        log.info("Finding User by User ID");

        final Cart cart = cartRepository.findCartItemByUser_IdAndProduct_Id(userId, id);
        log.info("finding Cart by User ID and Product ID");

        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(quantity);
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
        log.info("Updating CartItems ");
        return cart;

    }
}
