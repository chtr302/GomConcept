package com.gomconcept.gomconcept.Controller;

// import java.util.List;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gomconcept.gomconcept.Model.Product;
import com.gomconcept.gomconcept.Model.ProductDetail;
import com.gomconcept.gomconcept.Service.ProductDetailService;
import com.gomconcept.gomconcept.Service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping
    public String showAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size,
            @RequestParam(defaultValue = "maSanPham") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            Model model){
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
                    Sort.by(sortBy).descending() : 
                    Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> productsPage = productService.getAllProducts(pageable);
        
        model.addAttribute("products", productsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productsPage.getTotalPages());
        model.addAttribute("totalElements", productsPage.getTotalElements());
        model.addAttribute("size", size);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        
        return "product/products";
    }

    @GetMapping("/{slug}")
    public String showProductDetail(@PathVariable String slug, Model model){
        try {
            Product product = productService.getProductBySlug(slug);

            if(product == null){
                return "redirect:/products?error=product-not-found";
            }
            
            List<ProductDetail> productDetails = productDetailService.getProductDetails(product.getMaSanPham());
            model.addAttribute("product",product);
            model.addAttribute("productDetails", productDetails);

            return "product/product_detail";
        } catch (Exception e){
            System.err.println("Error loading product detail: " + e.getMessage());
            return "redirect:/products?error=product-not-found";
        }
    }
}
