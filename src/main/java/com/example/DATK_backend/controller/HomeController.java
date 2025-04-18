package com.example.DATK_backend.controller;

import com.example.DATK_backend.entity.User;
import com.example.DATK_backend.security.ConfigSecurity;
import com.example.DATK_backend.security.JwtUtil;
import com.example.DATK_backend.security.UserDetail;
import com.example.DATK_backend.service.ServiceProduct;
import com.example.DATK_backend.service.ServiceUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class HomeController {
    private final ServiceProduct serviceProduct;
    private final ServiceUser serviceUser;
    private final ConfigSecurity configSecurity;
    private final JwtUtil jwtUtil;
    private final UserDetail userDetail;

    public HomeController(ServiceProduct serviceProduct, UserDetail userDetail, ServiceUser serviceUser, JwtUtil jwtUtil, ConfigSecurity configSecurity) {
        this.serviceProduct = serviceProduct;
        this.serviceUser = serviceUser;
        this.configSecurity = configSecurity;
        this.jwtUtil = jwtUtil;
        this.userDetail = userDetail;
    }
    @CrossOrigin("*")
    @GetMapping("/")
    public ResponseEntity<?> home() {
        return ResponseEntity.ok(serviceProduct.getAllProducts());
    }
    @CrossOrigin("*")
    @GetMapping("/find-product")
    public ResponseEntity<?> findByNameProduct(@RequestParam(value = "nameProduct") String nameProduct) {
        return ResponseEntity.ok(serviceProduct.findByNameProduct(nameProduct));
    }
    @CrossOrigin("*")
    @PostMapping("/logIn")
    public ResponseEntity<?> logIn (@RequestBody User user, @RequestHeader(value = "Authorization", required = false) String authorization) {
        System.out.println("this is: " + user.getUserName());
        if (authorization != null && authorization.startsWith("Bearer ")) {
            System.out.println("this is step 1");
            String token = authorization.substring(7);
            try {
                String userFromToken = jwtUtil.extractUserName(token);
                if (jwtUtil.validateToken(token, userDetail.loadUserByUsername(userFromToken))) {
                    return ResponseEntity.status(HttpStatus.OK).body(userDetail.loadUserByUsername(userFromToken));
                }
            }
            catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
        System.out.println("this is before step 2");
        if (serviceUser.existsByUserName(user.getUserName())) {
            System.out.println("this is step 2");
            User userInDatabase = serviceUser.findUserByUserName(user.getUserName());
            if (configSecurity.passwordEncoder().matches(user.getUserPassword(), userInDatabase.getUserPassword())) {
                return ResponseEntity.ok(jwtUtil.generateToken(user.getUserName()));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
