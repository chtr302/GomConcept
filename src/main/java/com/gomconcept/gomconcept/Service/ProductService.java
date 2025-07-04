package com.gomconcept.gomconcept.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gomconcept.gomconcept.Model.Product;
import com.gomconcept.gomconcept.Repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
    public Product getProductBySlug(String slug){
        return productRepository.findBySlug(slug);
    }
    
}
