/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter implements Filter {
    private static final String SECRET_KEY = "your-secret-key";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            try {
                Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
                // Perform further validation or processing based on the claims
                // For example, you can retrieve the subject: claims.getSubject()
                // ...
                filterChain.doFilter(servletRequest, servletResponse); // Continue with the request
            } catch (SignatureException e) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    // Implement other methods of the Filter interface (init() and destroy()) as needed
}
