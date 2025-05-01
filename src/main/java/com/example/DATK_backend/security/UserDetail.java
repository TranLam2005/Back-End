package com.example.DATK_backend.security;

import com.example.DATK_backend.entity.User;
import com.example.DATK_backend.repository.RepositoryUser;
import com.example.DATK_backend.service.ServiceUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
@Component
public class UserDetail implements UserDetailsService {
    private final ServiceUser serviceUser;

    public UserDetail(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = serviceUser.findUserByUserName(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getUserPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }


}
