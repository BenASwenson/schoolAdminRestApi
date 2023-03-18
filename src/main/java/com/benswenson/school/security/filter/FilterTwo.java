package com.benswenson.school.security.filter;

import java.io.IOException;

import com.benswenson.school.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class FilterTwo implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

                User user = new ObjectMapper().readValue(((HttpServletRequest)request).getInputStream(), User.class);
                System.out.println(user.getUsername());
                System.out.println(user.getPassword());
                
    }

    
    
}
