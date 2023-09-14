package com.msys.smartkart.service;

import com.msys.smartkart.entity.Product;
import com.msys.smartkart.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    final ProductRepository productRepository;

//    @Override
//    public void addProduct(final Product product, final MultipartFile multipartFile) throws IOException {
//    public void addProduct(final Product product,MultipartFile file) throws IOException {

//        byte[] image = multipartFile.getBytes();
//        Product product1 =new Product();
//        product1.setName(product.getName());
//        product1.setId(product.getId());
//        product1.setPrice(product.getPrice());
//        product1.setColor(product.getColor());
//        product1.setDescription(product.getDescription());
//        product1.setDetails(product.getDetails());
//        productRepository.save(product);
//        System.out.println(product);
//        log.info("Producted added successfully");
//    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findByProductId(final Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void addProduct(Product product) throws IOException {
        log.info("product details ");
        log.info(String.valueOf(product));
        productRepository.save(product);
        log.info("saved product and its image");

    }

    @Override
    public List<Product> findProductByName(final String name) {
       final List<Product> productList = productRepository.findProductByProductNameContainsIgnoreCase(name);
       log.info("Finding product : "+name);
       log.info(String.valueOf(productList));
        return productList;
    }

    @Override
    public void deleteProductById(final Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(final Product product) {

       final Product productById = productRepository.findById(product.getId()).get();
        productById.setProductName(product.getProductName());
        productById.setPrice(product.getPrice());
        productById.setColor(product.getColor());
        productById.setDescription(product.getDescription());
        productById.setDetails(product.getDetails());
        log.info("Name ra babu edhi "+product.getProductName());
        productRepository.save(productById);
        return productById;
    }
}
