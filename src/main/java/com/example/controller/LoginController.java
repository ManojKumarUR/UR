package com.example.controller;

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

@Controller
public class LoginController {

    private final Users userRepository;

    public LoginController(Users userRepository) {
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
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/adminHome");
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
            //   return ResponseEntity.ok().build();
        }
        else{
//            return ResponseEntity.badRequest().build();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/error");
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
        }
    }

    @GetMapping("/success")
    public String Success(){
        return "RegistrationSuccessful";
    }

    @GetMapping("/error")
    public String Error(){
        return "Error";
    }

    @GetMapping("/adminHome")
    public String adminHome(){ return "adminHome";}



}
