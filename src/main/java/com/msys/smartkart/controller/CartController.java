package com.msys.smartkart.controller;

import com.msys.smartkart.config.GenericResponseDto;
import com.msys.smartkart.entity.Cart;
import com.msys.smartkart.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CartController {
    final CartService cartService;

    @GetMapping("/addToCart")
    public ResponseEntity<GenericResponseDto> addToCart(@RequestParam("userId") final Integer userId,
                                                        @RequestParam("productId") final Integer id) {

        final Cart cart = cartService.getByUserIdAndProductId(userId, id);

        if (cart != null) {
             Integer quantity = cart.getQuantity() + 1;
            if(quantity>5){
                quantity=5;
                return new ResponseEntity<>(new GenericResponseDto<>(404,"Please check the cart, max quantity you can add is 5"),HttpStatus.ACCEPTED);
            }
            final Integer totalPrice = cart.getProduct().getPrice() * quantity;
            final Cart updateCartItems = cartService.updateCartItems(id, userId, totalPrice, quantity);
            log.info("CartItems Are updated successfully");
            return new ResponseEntity<>(new GenericResponseDto(202, updateCartItems), HttpStatus.ACCEPTED);
        }

        cartService.addToCart(id, userId);
        log.info(String.valueOf(id), userId);
        log.info("Added to cart");
        return new ResponseEntity<>(new GenericResponseDto(200, "Successfully added to cart"), HttpStatus.ACCEPTED);
    }

    @GetMapping("/removeFromCart")
    public ResponseEntity<GenericResponseDto> removeFromCart(@RequestParam("userId") final Integer userId,
                                                             @RequestParam("productId") final Integer id) {

        cartService.deleteFromCart(userId, id);
        log.info("Product Deleted from cart by cart Id");
        return new ResponseEntity<>(new GenericResponseDto<>(200, "Removed from cart successfully ")
                , HttpStatus.ACCEPTED);

    }

    @GetMapping("/getCart")
    public ResponseEntity<GenericResponseDto> cartList(@RequestParam("userId") Integer userId) {

        log.info("List of Cart Items of User ID : " + userId);
        final List<Cart> cartList = cartService.fetchAllByUserId(userId);

        if (cartList.isEmpty()) {
            return new ResponseEntity<>(new GenericResponseDto(404, "Your cart is empty"), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(new GenericResponseDto<>(200, cartList), HttpStatus.ACCEPTED);
    }

    @GetMapping("/increaseQuantity")
    public ResponseEntity<GenericResponseDto> increaseQuantity(@RequestParam("userId") final Integer userId,
                                                               @RequestParam("productId") final Integer id) {

        final Cart cart = cartService.getByUserIdAndProductId(userId, id);

        Integer quantity = cart.getQuantity() + 1;
        log.info(String.valueOf(quantity));
        Integer totalPrice = cart.getProduct().getPrice() * quantity;
        log.info(String.valueOf(totalPrice));
        if (quantity > 5) {
            quantity = 5;
            totalPrice = cart.getProduct().getPrice() * quantity;
            cartService.updateCartItems(id, userId, totalPrice, quantity);

        }

        Cart updateCartItems = cartService.updateCartItems(id, userId, totalPrice, quantity);
        return new ResponseEntity<>(new GenericResponseDto(200, updateCartItems), HttpStatus.ACCEPTED);
    }

    @GetMapping("/decreaseQuantity")
    public ResponseEntity<GenericResponseDto> decreaseQuantity(@RequestParam("userId") final Integer userId,
                                                               @RequestParam("productId") final Integer id) {

        final Cart cart = cartService.getByUserIdAndProductId(userId, id);
        Integer quantity = cart.getQuantity() - 1;
        log.info(String.valueOf(quantity));
        Integer totalPrice = cart.getProduct().getPrice() * quantity;
        log.info(String.valueOf(totalPrice));
        if (quantity < 1) {
            quantity = 1;
            totalPrice = cart.getProduct().getPrice();
            cartService.updateCartItems(id, userId, totalPrice, quantity);
        }
        cartService.updateCartItems(id, userId, totalPrice, quantity);

        Cart updateCartItems = cartService.updateCartItems(id, userId, totalPrice, quantity);

        return new ResponseEntity<>(new GenericResponseDto(200, updateCartItems), HttpStatus.ACCEPTED);

    }

    @GetMapping("/countCartQuantity")
    public ResponseEntity<GenericResponseDto> countCartQuantity(@RequestParam final Integer userId) {
        final Integer totalQuantity = cartService.countQuantity(userId);
        log.info("The Total Quantity of above UserId is : " + totalQuantity);
        return new ResponseEntity<>(new GenericResponseDto(200, totalQuantity), HttpStatus.ACCEPTED);
    }

    @GetMapping("/countCartPrice")
    public ResponseEntity<GenericResponseDto> countCartPrice(@RequestParam final Integer userId){
        log.info("The User ID is : "+userId);
       final Long totalPrice = cartService.countCartPrice(userId);
       return new ResponseEntity<>(new GenericResponseDto(200,totalPrice),HttpStatus.ACCEPTED);

    }

}
