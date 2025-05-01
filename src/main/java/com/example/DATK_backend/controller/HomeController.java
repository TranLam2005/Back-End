package com.example.DATK_backend.controller;
import com.example.DATK_backend.entity.*;
import com.example.DATK_backend.security.ConfigSecurity;
import com.example.DATK_backend.security.JwtUtil;
import com.example.DATK_backend.service.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

@RestController
public class HomeController {
    private final ServiceProduct serviceProduct;
    private final ServiceUser serviceUser;
    private final ConfigSecurity configSecurity;
    private final JwtUtil jwtUtil;
    private final Otp otp;
    private final EmailService emailService;
    private final ServiceOrder serviceOrder;
    private final ServiceCart serviceCart;
    private final ServiceListOrder serviceListOrder;


    public HomeController(ServiceProduct serviceProduct, ServiceListOrder serviceListOrder, ServiceCart serviceCart,ServiceOrder serviceOrder,EmailService emailService, Otp otp, ServiceUser serviceUser, JwtUtil jwtUtil, ConfigSecurity configSecurity) {
        this.serviceProduct = serviceProduct;
        this.serviceUser = serviceUser;
        this.configSecurity = configSecurity;
        this.jwtUtil = jwtUtil;
        this.otp = otp;
        this.emailService = emailService;
        this.serviceOrder = serviceOrder;
        this.serviceCart = serviceCart;
        this.serviceListOrder = serviceListOrder;
    }
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        if(serviceUser.existsByUserName(user.getUserName()) || serviceUser.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {
            String password = configSecurity.passwordEncoder().encode(user.getUserPassword());
            user.setUserPassword(password);
            serviceUser.addUser(user);
            return ResponseEntity.ok("tạo thành công");
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> home(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "28") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(serviceProduct.getProducts(pageable));
    }
    @GetMapping("/role")
    public ResponseEntity<?> role(Principal principal) {
        User user = serviceUser.findUserByUserName(principal.getName());
        return ResponseEntity.ok(user.getRole());
    }
    @GetMapping("/find-product")
    public ResponseEntity<?> findByNameProduct(@RequestParam(value = "nameProduct") String nameProduct) {
        return ResponseEntity.ok(serviceProduct.findByNameProduct(nameProduct));
    }
    @PostMapping("/logIn")
    public ResponseEntity<?> logIn (@RequestBody User user) {
        if (serviceUser.existsByUserName(user.getUserName())) {
            User userInDatabase = serviceUser.findUserByUserName(user.getUserName());
            if (configSecurity.passwordEncoder().matches(user.getUserPassword(), userInDatabase.getUserPassword())) {
                return ResponseEntity.ok(jwtUtil.generateToken(user.getUserName()));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword (@RequestParam(value = "email") String email) {
        if(serviceUser.existsByEmail(email)) {
            String Otp = otp.generateOtp();
            emailService.sendOtpMail(email, Otp);
            return ResponseEntity.ok("Đã gửi mã Otp đến email của bạn.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam(value = "otp", required = false) String Otp, @RequestParam(value = "newPassword", required = false) String newPassword, @RequestParam(value = "email", required = false) String email) {
        if (otp.validateOtp(Otp)) {
            serviceUser.updateUserPassword(email, newPassword);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @GetMapping("/detail-product")
    public ResponseEntity<?> detailProduct (@RequestParam(value = "nameProduct", required = false) String nameProduct, @RequestParam(value = "id") int id) {
        return ResponseEntity.ok(serviceProduct.findProductByNameProduct(nameProduct, id));
    }
    @PostMapping("/order")
    public ResponseEntity<?> Order (@RequestBody List<Order> orders) {
        for (Order orderResult : orders) {
            serviceOrder.createOrder(orderResult);
        }
        return ResponseEntity.ok("Đặt hàng thành công");
    }
    @PreAuthorize("hasRole('SELLER')")
    @GetMapping("/order")
    public ResponseEntity<?> Order () {
        return ResponseEntity.ok(serviceOrder.getAllOrders());
    }
    @GetMapping("/{category}")
    public ResponseEntity<?> CategoryProducts (@PathVariable(value = "category") String category) {
        return ResponseEntity.ok(serviceProduct.findProductByCategory(category));
    }
    @DeleteMapping("/final-order")
    public ResponseEntity<?> finalOrder () {
        serviceOrder.deleteOrder();
        return ResponseEntity.ok("Final Order");
    }
    @GetMapping("/cart")
    public ResponseEntity<?> Cart() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = serviceUser.findUserByUserName(userDetails.getUsername());
            if(user != null) {
                System.out.println("User: " + user.getUserName());
                return ResponseEntity.ok(serviceCart.getCartByMaUser(user.getId()));
            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/cart")
    public ResponseEntity<?> Cart(@RequestBody CartRequest cart) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = serviceUser.findUserByUserName(userDetails.getUsername());
            if (user != null) {
                serviceCart.addCart(user.getId(), cart.getMaSanPham(), cart.getQuantity());
                return ResponseEntity.ok("thêm ok");
            }
            else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/list-order")
    public ResponseEntity<?> addListOrder (@RequestBody List<ListOrdersRequest> maSanPhams) {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = serviceUser.findUserByUserName(userDetails.getUsername());
            if (user != null){
                System.out.println("Hello");
                for (ListOrdersRequest maSanPham : maSanPhams) {
                    ListOrder listOrder = new ListOrder();
                    listOrder.setMaSanPham(maSanPham.getMaSanPham());
                    listOrder.setMaUser(user.getId());
                    serviceListOrder.addOrders(listOrder);
                }
                return ResponseEntity.ok("Đặt hàng thành công");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    @GetMapping("/list-order")
    public ResponseEntity<?> getListOrder () {
        try {
            System.out.println("Here");
            return ResponseEntity.ok(serviceListOrder.getListOrder());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}