package com.example.DATK_backend.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomOAuth extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser (OAuth2UserRequest userRequest) {
        String client = userRequest.getClientRegistration().getRegistrationId();
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if ("google".equals(client)) {
            if ("trandinhlam27052005@gmail.com".equals(email)) {
                authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
            }
            else {
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
        }
        else if ("facebook".equals(client)) {
            if ("tranlam27052005@gmail.com".equals(email)) {
                authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
            }
            else {
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
        }
        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "email");
    }
}
