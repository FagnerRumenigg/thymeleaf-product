package com.example.thymeleaf.controller;

import com.example.thymeleaf.dto.ProductDto;
import com.example.thymeleaf.model.Product;
import com.example.thymeleaf.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/list")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("list", productService.getAll());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable("id") Long id, Model model) throws Exception {
        Product product = productService.getById(id);

        ProductDto productDto = new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getRegistrationDate());

        model.addAttribute("item", productDto);
        return "edit";
    }

    @GetMapping("/create")
    public String createItem() throws Exception {

        return "create";
    }

    @PostMapping("/create")
    public String registerProduct(ProductDto productDto) throws Exception {
        productService.save(productDto);

        return "redirect:/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) throws Exception {
        productService.delete(id);

        return "redirect:/list";
    }
}
