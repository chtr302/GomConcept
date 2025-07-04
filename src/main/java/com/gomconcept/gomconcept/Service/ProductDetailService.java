package com.gomconcept.gomconcept.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gomconcept.gomconcept.Model.Product;
import com.gomconcept.gomconcept.Model.ProductDetail;
import com.gomconcept.gomconcept.Repository.ProductDetailRepository;
import com.gomconcept.gomconcept.Repository.ProductRepository;

@Service
public class ProductDetailService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;

    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }
    public List<ProductDetail> getProductDetails(Long productId){
        return productDetailRepository.findByMaSanPhamOrderByKichThuocAsc(productId);
    }
}
