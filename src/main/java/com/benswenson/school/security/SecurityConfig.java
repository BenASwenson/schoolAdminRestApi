package com.benswenson.school.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.benswenson.school.security.filter.AuthenticationFilter;
import com.benswenson.school.security.filter.ExceptionHandlerFilter;
import com.benswenson.school.security.filter.JWTAuthorizationFilter;
import com.benswenson.school.security.manager.CustomAuthenticationManager;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Configuration
public class SecurityConfig  {

    private BCryptPasswordEncoder passwordEncoder;
    CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/authenticate");
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                                .requestMatchers(SecurityConstants.REGISTER_PATH).permitAll()
                                .anyRequest()
                                .authenticated()
                                .and()
                                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
                                .addFilter(authenticationFilter)
                                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
            
    }

    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin-pass"))
            .roles("ADMIN")
            .build();
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder.encode("user-pass"))
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(admin);
    }
    
}
