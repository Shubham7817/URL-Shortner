package com.example.url_shortner_practice.security.jwt;

import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            // 1. get jwt from header
            // 2.  validate token
            // 3. if valid, gets user Detail
               // 4. --getUsername -> 5. load User  -> 6. set the auth context

            String jwt = jwtTokenProvider.getJwtFromHeader(request); //step1

            if(jwt!=null && jwtTokenProvider.validateToken(jwt)){  //step2 and step3
                String username = jwtTokenProvider.getUsernameFromJwtToken(jwt); //step 4
                UserDetails userDetails = userDetailsService.loadUserByUsername(username); //step 5

                if(userDetails != null){ //step 6
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }
}
