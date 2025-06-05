package com.example.carwash.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.carwash.security.config.UserInfoUserDetailsService;
import com.example.carwash.service.JwtService;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserInfoUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
//        System.out.println("outside if jwt filter");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//        	System.out.println("in if block jwt filter");
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }
//        else
//        	System.out.println("in jwt filter");

        try {
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//        	System.out.println("in security jwt filter");

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
//            	System.out.println("in validate jwt filter"+userDetails);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        }catch(Exception ex) {
        	System.out.println("error: "+ex.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
