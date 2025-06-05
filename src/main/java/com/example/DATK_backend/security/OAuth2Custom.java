//package com.example.DATK_backend.security;
//
//import com.example.DATK_backend.service.ServiceUser;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.web.client.RestTemplate;
//
//public class OAuth2Custom implements AuthenticationSuccessHandler {
//    private final ServiceUser serviceUser;
//    private final PasswordEncoder passwordEncoder;
//    private final RestTemplate restTemplate;
//    private final OAuth2AuthorizedClient authorizedClient;
//    public OAuth2Custom(ServiceUser serviceUser, OAuth2AuthorizedClient authorizedClient, PasswordEncoder passwordEncoder, RestTemplate restTemplate) {
//        this.serviceUser = serviceUser;
//        this.passwordEncoder = passwordEncoder;
//        this.restTemplate = restTemplate;
//        this.authorizedClient = authorizedClient;
//    }
//    @Override
//    public void onAuthenticationSuccess(HttpServletResponse response, HttpServletRequest request, Authentication authentication) {
//        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
//        String email = token.getPrincipal().getAttribute("email");
//        String registrationClientId = token.getAuthorizedClientRegistrationId();
//    }
//}
