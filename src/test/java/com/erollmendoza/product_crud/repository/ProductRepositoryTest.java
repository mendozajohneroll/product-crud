package com.erollmendoza.product_crud.repository;


import com.erollmendoza.product_crud.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void ProductRepository_getAllProduct(){

        Product product1 = Product.builder()
                .productName("Air Purifier")
                .productDescription("Pure A9 air purifier")
                .productType("Electronic Appliance")
                .unitPrice(12000)
                .productQuantity(7)
                .requirements("no requirements")
                .build();

        Product product2 = Product.builder()
                .productName("Microphone")
                .productDescription("Magic Sing")
                .productType("Electronic Appliance")
                .unitPrice(3500)
                .productQuantity(11)
                .requirements("no requirements")
                .build();

        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> savedProducts = productRepository.findAll();

        Assertions.assertNotNull(savedProducts);
        Assertions.assertFalse(savedProducts.isEmpty());

    }

    @Test
    public void ProductRepository_getProductByID(){

        Product product = Product.builder()
                .productName("Air Purifier")
                .productDescription("Pure A9 air purifier")
                .productType("Electronic Appliance")
                .unitPrice(12000)
                .productQuantity(7)
                .requirements("no requirements")
                .build();

       productRepository.save(product);

       Product savedProduct = productRepository.findById(product.getId()).get();

        Assertions.assertNotNull(savedProduct);

    }

    @Test
    public void ProductRepository_getProductByProductName(){

        Product product = Product.builder()
                .productName("Air Purifier")
                .productDescription("Pure A9 air purifier")
                .productType("Electronic Appliance")
                .unitPrice(12000)
                .productQuantity(7)
                .requirements("no requirements")
                .build();

        productRepository.save(product);

        Product savedProduct = productRepository.findByProductName(product.getProductName()).get();

        Assertions.assertNotNull(savedProduct);

    }

    @Test
    public void ProductRepository_getProductByProductDescription(){

        Product product = Product.builder()
                .productName("Air Purifier")
                .productDescription("Pure A9 air purifier")
                .productType("Electronic Appliance")
                .unitPrice(12000)
                .productQuantity(7)
                .requirements("no requirements")
                .build();

        productRepository.save(product);

        Product savedProduct = productRepository.findByProductDescription(product.getProductDescription()).get();

        Assertions.assertNotNull(savedProduct);

    }

    @Test
    public void ProductRepository_getProductByProductType(){

        Product product = Product.builder()
                .productName("Air Purifier")
                .productDescription("Pure A9 air purifier")
                .productType("Electronic Appliance")
                .unitPrice(12000)
                .productQuantity(7)
                .requirements("no requirements")
                .build();

        productRepository.save(product);

        Product savedProduct = productRepository.findByProductType(product.getProductType()).get();

        Assertions.assertNotNull(savedProduct);

    }

    @Test
    public void ProductRepository_addProduct(){

        Product product = Product.builder()
                .productName("Air Purifier")
                .productDescription("Pure A9 air purifier")
                .productType("Electronic Appliance")
                .unitPrice(12000)
                .productQuantity(7)
                .requirements("no requirements")
                .build();

        Product savedProduct = productRepository.save(product);

        Assertions.assertNotNull(savedProduct);
        Assertions.assertTrue(savedProduct.getId() > 0);

    }

    @Test
    public void ProductRepository_updateProduct(){

        Product product = Product.builder()
                .productName("Air Purifier")
                .productDescription("Pure A9 air purifier")
                .productType("Electronic Appliance")
                .unitPrice(12000)
                .productQuantity(7)
                .requirements("no requirements")
                .build();

        productRepository.save(product);

        Product savedProduct = productRepository.findById(product.getId()).get();
        savedProduct.setProductName("Microphone");
        savedProduct.setProductDescription("Magic Sing");
        savedProduct.setProductType("Electronic Appliance");
        savedProduct.setUnitPrice(3500);
        savedProduct.setProductQuantity(11);
        savedProduct.setRequirements("no requirements");

        Product updatedProduct = productRepository.save(savedProduct);

        Assertions.assertNotNull(updatedProduct);
        Assertions.assertNotNull(updatedProduct.getProductName());
        Assertions.assertNotNull(updatedProduct.getProductDescription());
        Assertions.assertNotNull(updatedProduct.getProductType());
        Assertions.assertTrue(updatedProduct.getUnitPrice() > 0);
        Assertions.assertTrue(updatedProduct.getProductQuantity() > 0);
        Assertions.assertNotNull(updatedProduct.getRequirements());

    }

    @Test
    public void ProductRepository_deleteProductByID(){

        Product product = Product.builder()
                .productName("Air Purifier")
                .productDescription("Pure A9 air purifier")
                .productType("Electronic Appliance")
                .unitPrice(12000)
                .productQuantity(7)
                .requirements("no requirements")
                .build();

        productRepository.save(product);

        productRepository.deleteById(product.getId());
        Optional<Product> productCheck = productRepository.findById(product.getId());

        Assertions.assertTrue(productCheck.isEmpty());

    }

}
