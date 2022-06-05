package com.example.controller;

import antlr.collections.List;
import com.example.entity.Registration;
import com.example.pojo.LoginForm;
import com.example.pojo.RegistrationForm;
import com.example.repository.Users;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.Id;

@Controller("/")
public class ExampleController {
    private final Users userRepository;

    public ExampleController(Users userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String openRegister() {
         return "registration";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(RegistrationForm registrationForm) {
        Registration registration = new Registration();

        String password=registrationForm.getRetypePassword();
        String retypePassword=registrationForm.getPassword();
        if(password.equals(retypePassword)) {
            registration.setPassword(registrationForm.getPassword());
            registration.setName(registrationForm.getUsername());
            registration.setPhoneNumber(registrationForm.getPhoneNumber());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
        userRepository.save(registration);
        System.out.println(registration);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/success");
        return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/login")
    public String openLogin(){return "login";}

    @PostMapping("/login")
    public  ResponseEntity<?> login(LoginForm loginForm){
        Registration registration = new Registration();
        String username=loginForm.getUsername();
        String password=loginForm.getPassword();

        Registration obj=userRepository.findByName(username);
        if(password.equals(obj.getPassword()))
        {
            return ResponseEntity.ok().build();
        }


//        if(registration.getUsername()){
//            System.out.println("hello");
//        }
        if(username.equals("hello") && password.equals("123")){
            return  ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/success")
    public String Success(){
        return "RegestrationSuccdessful";
    }

}
