package org.example.shopping.controller;

import org.example.shopping.entity.Product;
import org.example.shopping.service.ProductService;
import org.example.shopping.service.ProductJdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductJdbcService productJdbcService;

    // JPA endpoints
    @GetMapping("/jpa")
    public List<Product> getAllProductsJpa() {
        return productService.findAll();
    }

    @GetMapping("/jpa/{id}")
    public Optional<Product> getProductByIdJpa(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @PostMapping("/jpa")
    public Product createProductJpa(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/jpa/{id}")
    public Product updateProductJpa(@PathVariable Integer id, @RequestBody Product product) {
        product.setId(Long.valueOf(id));
        return productService.save(product);
    }

    @DeleteMapping("/jpa/{id}")
    public void deleteProductJpa(@PathVariable Integer id) {
        productService.deleteById(id);
    }

    // JDBC endpoints
    @GetMapping("/jdbc")
    public List<Product> getAllProductsJdbc() throws SQLException {
        return productJdbcService.findAll();
    }

    @GetMapping("/jdbc/{id}")
    public Product getProductByIdJdbc(@PathVariable Integer id) throws SQLException {
        return productJdbcService.findById(id);
    }

    @PostMapping("/jdbc")
    public void createProductJdbc(@RequestBody Product product) throws SQLException {
        productJdbcService.save(product);
    }

    @DeleteMapping("/jdbc/{id}")
    public void deleteProductJdbc(@PathVariable Integer id) throws SQLException {
        productJdbcService.deleteById(id);
    }
}
