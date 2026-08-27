package com.accounting.app.security;

import com.accounting.app.repository.InvalidatedTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final InvalidatedTokenRepository invalidatedTokenRepository;
    private final UserDetailsService userDetailsService;
    public JwtFilter(JwtUtil jwtUtil,InvalidatedTokenRepository invalidatedTokenRepository, UserDetailsService userDetailsService){
        this.jwtUtil=jwtUtil;
        this.invalidatedTokenRepository = invalidatedTokenRepository;
        this.userDetailsService= userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getHeader("Authorization") == null || !request.getHeader("Authorization").startsWith("Bearer") || invalidatedTokenRepository.existsByToken(request.getHeader("Authorization").substring(7))){
            filterChain.doFilter(request,response);
            return;
        }else if(jwtUtil.isTokenValid(request.getHeader("Authorization").substring(7))){
            String email = jwtUtil.extractEmail(request.getHeader("Authorization").substring(7));
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request,response);
    }
}
