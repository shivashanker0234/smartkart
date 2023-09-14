package com.msys.smartkart.service;

import com.msys.smartkart.entity.Cart;
import com.msys.smartkart.entity.Orders;
import com.msys.smartkart.entity.Product;
import com.msys.smartkart.entity.User;
import com.msys.smartkart.repository.CartRepository;
import com.msys.smartkart.repository.OrdersRepository;
import com.msys.smartkart.repository.ProductRepository;
import com.msys.smartkart.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    final UserRepository userRepository;
    final ProductRepository productRepository;
    final OrdersRepository ordersRepository;
    final CartRepository cartRepository;

    @Override
    public Orders placeOrder(final Integer userId, final Integer id) {
        log.info("Finding User by User ID");
        final User user = userRepository.findById(userId).get();
        log.info("Finding Product by Product ID");
        final Product product = productRepository.findById(id).get();
        log.info("Fetching product name");
        final String productName = product.getProductName();
        log.info("Fetching product name");
        final Integer productPrice = product.getPrice();

        final Cart cart = cartRepository.findCartItemByUser_IdAndProduct_Id(userId, id);
            if(cart==null){
                Cart newcart= new Cart();
                newcart.setUser(user);
                newcart.setProduct(product);
                newcart.setQuantity(1);
                newcart.setTotalPrice(productPrice);
                cartRepository.save(newcart);

                Orders orders=new Orders();
                orders.setUser(user);
                orders.setProduct(product);
                orders.setQuantity(1);
                orders.setTotalPrice(productPrice);
                orders.setProductName(productName);
                ordersRepository.save(orders);
                return orders;

        }
        log.info("Fetching cart by User ID and Product ID");
        final Integer totalQuantity = cart.getQuantity();
        log.info("Order Quantity : " + totalQuantity);

        Integer totalPrice = cart.getTotalPrice();
        log.info("Total Price : " + totalPrice);

        final Orders orders = new Orders();
        log.info("Creating new instance of object Orders");
        orders.setUser(user);
        orders.setProduct(product);
        orders.setQuantity(totalQuantity);
        orders.setTotalPrice(totalPrice);
        orders.setProductName(productName);
        ordersRepository.save(orders);
        log.info("saving Orders as Request");
        return orders;

    }

    @Override
    public List<Orders> fetchOrders(final Integer userId) {

        final List<Orders> ordersList = ordersRepository.findAllByUser_Id(userId);
        log.info("fetching all orders");
        log.info(ordersList.toString());
        return ordersList;

    }

    @Override
    public void orderCart(final Integer userId) {

        final User user = userRepository.findById(userId).get();

        List<Cart> cartList = cartRepository.findAllByUser_Id(userId);

        Orders orders = new Orders();
        orders.setUser(user);

    }
}
