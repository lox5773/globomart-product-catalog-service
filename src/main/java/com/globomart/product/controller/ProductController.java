package com.globomart.product.controller;

import com.globomart.product.model.Product;
import com.globomart.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAll(@RequestParam(name = "type", required = false) String productType) {
        if (productType != null) {
            return new ResponseEntity<List<Product>>(productRepository.findAllByType(productType), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<List<Product>>(productRepository.findAll(), HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{productId}")
    public ResponseEntity<Product> find(@PathVariable Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return new ResponseEntity<Product>(productOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product savedProduct = productRepository.save(product);
        return new ResponseEntity(savedProduct, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{productId}")
    public ResponseEntity<String> delete(@PathVariable Long productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
            return new ResponseEntity<String>("Product: "+ productId +" deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product: "+ productId +" Not found!", HttpStatus.NOT_FOUND);
        }

    }


}
