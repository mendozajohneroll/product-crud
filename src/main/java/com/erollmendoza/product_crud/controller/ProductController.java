package com.erollmendoza.product_crud.controller;


import com.erollmendoza.product_crud.model.Product;
import com.erollmendoza.product_crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAll")
    public List<Product> getAll() {
        return productService.findAll();
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product product) {
        productService.save(product);
        return "Product added";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable long id) {
        try{
            Product product = productService.get(id);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }catch (NoSuchElementException ex) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product,@PathVariable long id) {
        try{
//            Product existingProduct = productService.get(id);
            productService.save(product);
            return new ResponseEntity<Product>(HttpStatus.OK);
        }catch (NoSuchElementException ex) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id) {
        productService.delete(id);
        return "Product deleted: "+id;
    }

}
