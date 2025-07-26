//package com.example.demo.controllerforproducts;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.http.HttpClient;
//@Configuration
//public class SecurityConfig {
//
//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http
//                    .csrf().disable()
//                    .authorizeRequests()
//                    .antMatchers("/v1/user/register").permitAll() // اجازه دسترسی به ثبت نام
//                    .anyRequest().authenticated();
//            return http.build();
//        }
//
//}
