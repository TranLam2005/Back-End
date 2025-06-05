package com.example.DATK_backend.security;
import com.example.DATK_backend.entity.User;
import com.example.DATK_backend.service.CustomOAuth;
import com.example.DATK_backend.service.ServiceUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.web.cors.CorsConfiguration;
import java.util.Arrays;
import java.util.List;

@Configuration
public class ConfigSecurity {
    private final JwtAuth jwtAuth;
    private final JwtUtil jwtUtil;
    private final ServiceUser  serviceUser;
    private final CustomOAuth customOAuth;
    private final UserDetail userDetail;
    public ConfigSecurity(JwtAuth jwtAuth, UserDetail userDetail, CustomOAuth customOAuth,JwtUtil jwtUtil, ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
        this.jwtUtil = jwtUtil;
        this.jwtAuth = jwtAuth;
        this.customOAuth = customOAuth;
        this.userDetail = userDetail;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of("http://localhost:3000")); // domain của frontend
                    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    configuration.setAllowedHeaders(List.of("*"));
                    configuration.setAllowCredentials(true);
                    return configuration;
                }))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/logIn").permitAll()
                        .requestMatchers("/forgot-password").permitAll()
                        .requestMatchers("/reset-password").permitAll()
                        .requestMatchers("/login/oauth2/code/google").permitAll()
                        .requestMatchers("/signUp").permitAll()
                        .requestMatchers("/cart").authenticated()
                        .requestMatchers("/list-order").authenticated()
                        .requestMatchers("/").authenticated()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth))
                        .successHandler(successHandler())
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(1)
                        .expiredUrl("/logIn?expired=true")
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuth, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetail);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            String email = oAuth2User.getAttribute("email");
            String token = jwtUtil.generateToken(email);
            String redirectUrl = "http://localhost:3000/logIn?token=" + URLEncoder.encode(token, StandardCharsets.UTF_8);
            if (serviceUser.existsByUserName(email)) {
                response.sendRedirect(redirectUrl);
            }
            else {
                User user = new User();
                user.setUserPassword(passwordEncoder().encode("12345"));
                user.setUserName(email);
                user.setEmail(email);
                serviceUser.addUser(user);
                response.sendRedirect(redirectUrl);
            }
        };
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}