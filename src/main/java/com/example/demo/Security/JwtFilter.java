package com.example.demo.Security;

import com.example.demo.Service.JwtService;
import com.example.demo.Service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private UserDetailsServiceImpl userDetailsService;

    public JwtFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

       String authHeader = request.getHeader("Authorization");
       if(authHeader == null || !authHeader.startsWith("Bearer ")){
           filterChain.doFilter(request, response);
           return;
       }
       String token = authHeader.substring(7);
       String identityNumber = jwtService.getIdentityNumber(token);
       if(identityNumber !=null && SecurityContextHolder.getContext().getAuthentication()==null){
           UserDetails userDetails = userDetailsService.loadUserByUsername(identityNumber);
           if(!jwtService.isTokenExpirate(token)){
               UsernamePasswordAuthenticationToken authentication =
                       new UsernamePasswordAuthenticationToken(
                               userDetails,
                               null,                    // credentials (şifreye gerek yok)
                               userDetails.getAuthorities() // authorities
                       );
               SecurityContextHolder.getContext().setAuthentication(authentication);

           }
       }
       filterChain.doFilter(request,response);
    }
}
