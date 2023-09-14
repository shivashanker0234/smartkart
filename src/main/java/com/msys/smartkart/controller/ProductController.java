package com.msys.smartkart.controller;

import com.msys.smartkart.config.GenericResponseDto;
import com.msys.smartkart.entity.Product;
import com.msys.smartkart.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class ProductController {
    final ProductService productService;

        @PostMapping("/addProduct")
//    @PostMapping(path = "/addProduct", consumes = {MediaType.APPLICATION_JSON_VALUE,
//            MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<GenericResponseDto> addProduct(@RequestBody Product product) throws Exception {
        log.info("Adding Product To database");
//        byte[] photoByte = file.getBytes();
        productService.addProduct(product);
//        productService.addProduct(product);
        log.info("checking product reached");

        return new ResponseEntity<>(new GenericResponseDto(200, "Product added successfully"), HttpStatus.ACCEPTED);
    }

    @GetMapping("/fetchProducts")
    public ResponseEntity<GenericResponseDto> fetchAllProducts() {
            log.info("Fetching All products @GetMapping fetchProducts");
        List<Product> allProducts = productService.findAllProducts();

        return new ResponseEntity<>(new GenericResponseDto(200, allProducts), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getProductById")
    public ResponseEntity<GenericResponseDto> getProductById(@RequestParam("productId") final Integer id) {
        log.info("fetching Product By ProductId");
        final Product product = productService.findByProductId(id);
        return new ResponseEntity<>(new GenericResponseDto<>(200, product), HttpStatus.ACCEPTED);

    }

    @GetMapping("/search")
    public ResponseEntity<GenericResponseDto> findByName(@RequestParam("name") final String name) {

        final List<Product> productByName = productService.findProductByName(name);
//        if(productByName.isEmpty()){
//            log.info("Product with name "+name+" not found");
//            return new ResponseEntity<>(new GenericResponseDto(404,"product not found"),HttpStatus.ACCEPTED);
//        }
        log.info("Finding products by name : " + name);
        log.info(productByName.toString());
        return new ResponseEntity<>(new GenericResponseDto<>(2000, productByName), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteProductById")
    public ResponseEntity<GenericResponseDto> deleteProduct(@RequestParam("productId") final Integer id) {

        productService.deleteProductById(id);
        return new ResponseEntity<>(new GenericResponseDto<>(200, "Product deleted successfully"),
                HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<GenericResponseDto> updateProductById(@RequestBody final Product product) {
        final Product updatedProduct = productService.updateProduct(product);

        log.info("Controllerrrr " + updatedProduct);
        return new ResponseEntity<>(new GenericResponseDto<>(200, "Product updated sucessfully"), HttpStatus.ACCEPTED);
    }




    @PostMapping("/demo")
    public void demo(@RequestBody Product product) throws Exception{

            log.info(product.getProductName());
            log.info(product.getColor());

            log.info("Fetching log method");
    }
}
