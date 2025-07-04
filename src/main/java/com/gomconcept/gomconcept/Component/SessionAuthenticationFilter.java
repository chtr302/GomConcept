package com.gomconcept.gomconcept.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gomconcept.gomconcept.Model.Role;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);

        if (SecurityContextHolder.getContext().getAuthentication() == null && 
            session != null && 
            Boolean.TRUE.equals(session.getAttribute("isLoggedIn"))) {
            
            String email = (String) session.getAttribute("email");
            String vaiTro = (String) session.getAttribute("vaiTro");
            
            if (email != null) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + vaiTro));
                
                if (Role.HBT.getCode().equals(vaiTro)) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_HBT"));
                    authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                }
                
                // Tạo Authentication object
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(email, null, authorities);
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        filterChain.doFilter(request, response);
    }
}
