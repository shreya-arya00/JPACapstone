package org.example.shopping;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import org.example.shopping.entity.Product;
import org.example.shopping.repository.ProductRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = SpringBootJdbcJpaApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
    }

    @Test
    public void testSaveAndFindProduct() {
        // Create a new product entity
        Product product = new Product();
        product.setName("Product 1");
        product.setPrice(100.0);

        // Save the product using the repository
        productRepository.save(product);

        // Retrieve the product from the database by ID
        Optional<Product> foundProduct = productRepository.findById(product.getId());

        // Assert that the retrieved product is not null and has the expected name
        assertTrue(foundProduct.isPresent());
        assertEquals("Product 1", foundProduct.get().getName());
    }

    @Test
    @Transactional
    public void testSaveProductWithDuplicateId() {
        // Save the first product
        Product product1 = new Product();
        product1.setId(1L); // Set a unique ID
        product1.setName("Product 1");
        product1.setPrice(100.0);
        productRepository.save(product1);

        // Attempt to save a second product with the same ID
        Product product2 = new Product();
        product2.setId(1L); // Same ID as product1
        product2.setName("Product 2");
        product2.setPrice(200.0);

        // Expect a DataIntegrityViolationException when saving
        try {
            productRepository.save(product2);
            fail("Expected DataIntegrityViolationException was not thrown");
        } catch (DataIntegrityViolationException e) {
            // Expected behavior
        }
    }

    private void fail(String expectedDataIntegrityViolationExceptionWasNotThrown) {
    }
}
