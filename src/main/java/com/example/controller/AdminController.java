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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
/**

 */
public class AdminController {

    private final Product productRepositary;
    int price=0;

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

    @GetMapping("/removeProduct")
    public String openRemoveProduct(){return "removeProduct";}

    @PostMapping("/removeProduct")
    public  ResponseEntity<?> removeProduct(ProductForm productForm){
        Products product =new Products();
        String name= productForm.getName();

        product =productRepositary.findByName(name);
        int id = product.getId();
        productRepositary.deleteById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/success");
        return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    }

    @RequestMapping("/bill")
    public String openBill(Model model, @ModelAttribute("product") Products product){

        product.setPrice(15);
        return "bill";
    }


//    @RequestMapping("/bill")
//    public String bill(HttpServletRequest request, Model theModel){
//
//            String name = request.getParameter("name");
////        String quantity=request.getParameter("quantity");
////        int quantity1=Integer.parseInt(quantity);
////        System.out.println(name+" "+quantity1);
//            System.out.println(name);
//            Products obj = productRepositary.findByName(name);
//            price = obj.getPrice();
//            theModel.addAttribute("Price", price);
////        ArrayList<Product> arr= new ArrayList<Product>();
////
////        HttpHeaders headers = new HttpHeaders();
////        headers.add("Location", "/bill");
////        return new ResponseEntity<String>(headers, HttpStatus.FOUND);
//
//        return "bill";
//    }

}
