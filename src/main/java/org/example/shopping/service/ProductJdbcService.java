package org.example.shopping.service;

import org.example.shopping.entity.Product;
import org.example.shopping.jdbc.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductJdbcService {

    @Autowired
    private ProductDAO productDAO;

    public List<Product> findAll() {
        ProductJdbcService productRepository = new ProductJdbcService();
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        return null;
    }

    public void save(Product product) {
    }

    public void deleteById(Integer id) {
    }
}
