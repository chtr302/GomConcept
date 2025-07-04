package com.gomconcept.gomconcept.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gomconcept.gomconcept.Model.ProductDetail;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    
    List<ProductDetail> findByMaSanPhamOrderByKichThuocAsc(Long maSanPham);

    ProductDetail findByMaSanPhamAndKichThuoc(Long maSanPham, String kichThuoc);

    
}
