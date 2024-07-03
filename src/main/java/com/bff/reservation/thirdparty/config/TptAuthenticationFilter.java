package com.bff.reservation.thirdparty.config;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bff.reservation.thirdparty.service.TptAuthenticationService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TptAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/api/thirdparty/")) {
            try {
                Authentication authentication = TptAuthenticationService.getAuthentication(request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (BadCredentialsException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                PrintWriter writer = response.getWriter();
                writer.print("{\"error\": \"Unauthorized - " + e.getMessage() + "\"}");
                writer.flush();
                writer.close();
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
// public class TptAuthenticationFilter extends GenericFilterBean{
  
//     @Override
//     public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
//             throws java.io.IOException, ServletException {
//         try{
//             Authentication authentication = TptAuthenticationService.getAuthentication((HttpServletRequest) request);
//             SecurityContextHolder.getContext().setAuthentication(authentication);
//         }catch(Exception exp){
//             HttpServletResponse httpResponse = (HttpServletResponse) response;
//             httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);;
//             PrintWriter writer = httpResponse.getWriter();
//             writer.print(exp.getMessage());
//             writer.flush();
//             writer.close();
//         }
//         filterChain.doFilter(request, response);
//     }

// }
