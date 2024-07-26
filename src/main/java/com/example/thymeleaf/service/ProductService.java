package com.example.thymeleaf.service;

import com.example.thymeleaf.dto.ProductDto;
import com.example.thymeleaf.model.Product;
import com.example.thymeleaf.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void save(ProductDto productDto) throws Exception {
        Product product = new Product();

        if(productDto.getId() != null){
            product = getById(productDto.getId());
        }

        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setRegistrationDate(productDto.getRegistrationDate());

        productRepository.save(product);
    }

    public List<ProductDto> getAll(){
        List<ProductDto> listProductDto = new ArrayList<>();
        List<Product> listProduct = productRepository.findAll();

        for(Product product : listProduct){
            ProductDto productDto = new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getRegistrationDate());
            listProductDto.add(productDto);
        }

        return listProductDto;
    }

    public Product getById(Long productId) throws Exception {

        return productRepository.findById(productId).orElseThrow(() -> new Exception("Product not found"));
    }
    public void delete(Long productId) throws Exception {
        Product product= productRepository.findById(productId).orElseThrow(() -> new Exception("Product not found"));

        productRepository.delete(product);
    }
}
