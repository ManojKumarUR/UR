package com.example.controller;

import com.example.entity.Products;
import com.example.pojo.ProductForm;
import com.example.repository.Product;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class AdminController {

    private final Product productRepositary;


    public AdminController(Product productRepositary) { this.productRepositary = productRepositary; }

    @GetMapping("/addProduct")
    public String openAddProducts(){ return "addProducts";}

    @PostMapping("/addProduct")
    public ResponseEntity<?> addproduct(ProductForm productform){
        Products product = new Products();

        product.setName(productform.getName());
        product.setPrice(productform.getPrice());
        product.setAvailableQuantity(productform.getAvailableQuantity());

        productRepositary.save(product);
        System.out.println(product);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/success");
        return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/bill")
    public String openBill(){
        return "bill";
    }

    @PostMapping("/bill")
    public ResponseEntity<?> bill(String name,int quantity){
        System.out.println(name+" "+quantity);
        Products obj=productRepositary.findByName(name);
        int price=obj.getPrice();
        ArrayList<Product> arr= new ArrayList<Product>();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/bill");
        return new ResponseEntity<String>(headers, HttpStatus.FOUND);

    }


}
