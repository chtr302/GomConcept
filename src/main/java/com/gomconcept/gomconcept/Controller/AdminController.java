package com.gomconcept.gomconcept.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hbt")
public class AdminController {
    @GetMapping("/products")
    public String showProducts(){
        return "product/product_management";
    }
}
