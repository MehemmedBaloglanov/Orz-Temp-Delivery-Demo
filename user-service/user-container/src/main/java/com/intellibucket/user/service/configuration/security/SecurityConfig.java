//package com.intellibucket.user.service.configuration.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.context.SecurityContextHolderStrategy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
////    private final CompanyRegisterCommandHandler companyRegisterCommandHandler;
//
//    private final AuthenticationProvider authenticationProvider;
//
//    public SecurityConfig(AuthenticationProvider authenticationProvider) {
//        this.authenticationProvider = authenticationProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(request -> {
//                    request
//                            .requestMatchers(HttpMethod.POST, "/api/1.0/users/register/company", "/api/1.0/users/register/customer", "/api/1.0/users/login", "/swagger-ui.html").permitAll()
//                            .requestMatchers(HttpMethod.POST, "/users/changePassword").hasAnyRole("CUSTOMER", "COMPANY", "ADMIN")
//                            .requestMatchers(HttpMethod.DELETE, "/products/delete/{productId}").hasAnyRole("ADMIN", "CUSTOMER")
//                            .requestMatchers(HttpMethod.GET, "/users/allCustomers", "/users/allSellers", "/users/allUsers").hasRole("ADMIN")
//                            .requestMatchers(HttpMethod.GET, "/products/allProducts", "/products/{productId}", "/products/{productName}").hasAnyRole("CUSTOMER", "SELLER")
//                            .requestMatchers(HttpMethod.POST, "/products/change/product/{id}").hasRole("SELLER")
//                            .requestMatchers(HttpMethod.POST, "/orders/placeOrder/{userId}/{productId}").hasRole("CUSTOMER")
//                            .requestMatchers(HttpMethod.POST, "/products/add").hasRole("SELLER")
//                            .requestMatchers(HttpMethod.GET, "/ratings/{productId}", "/orders/orderHistory").hasAnyRole("CUSTOMER", "SELLER")
//                            .requestMatchers(HttpMethod.GET, "/orders/orderHistory").hasRole("CUSTOMER")
//                            .requestMatchers(HttpMethod.GET, "/ratings/addRating").hasRole("CUSTOMER")
//                            .anyRequest().permitAll();
//                })
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider)
//                .securityContext(securityContext -> securityContext.requireExplicitSave(false))
//                .build();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
////        authenticationProvider.setUserDetailsService(userService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }
//
//    @Bean
//    public SecurityContextHolderStrategy securityContextHolderStrategy() {
//        return SecurityContextHolder.getContextHolderStrategy();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}