package com.msys.smartkart.service;

import com.msys.smartkart.entity.Product;
import com.msys.smartkart.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void addProduct(final Product product) throws IOException;

    List<Product> findAllProducts();

    Product findByProductId(final Integer id);

//    void addProduct(Product product, MultipartFile file) throws IOException;

    List<Product> findProductByName(final String name);

    void deleteProductById(final Integer id);

    Product updateProduct(final Product id);
}
