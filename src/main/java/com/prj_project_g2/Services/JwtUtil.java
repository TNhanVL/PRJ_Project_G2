/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "ttnhan";

    public static String generateJwt(String username, String password) {
        long expirationTimeMillis = System.currentTimeMillis() + 3600000 * 6; // 1 hour
        return Jwts.builder()
                .claim("username", username)
                .claim("password", password)
                .setExpiration(new Date(expirationTimeMillis))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims parseJwt(String jwt) throws SignatureException {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
    }

    public static boolean isJwtExpired(Claims claims) {
        try {
            long expirationTimeMillis = claims.getExpiration().getTime();
            long currentTimeMillis = System.currentTimeMillis();
            return expirationTimeMillis < currentTimeMillis;
        } catch (ExpiredJwtException e) {
            return true; // JWT has expired
        } catch (Exception e) {
            return true; // Invalid JWT or other error occurred
        }
    }

    public static void main(String[] args) {
//        String s = generateJwt("abc", "asdf");
        String s = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwicGFzc3dvcmQiOiIwZTc1MTcxNDFmYjUzZjIxZWU0MzliMzU1YjVhMWQwYSIsImV4cCI6MTY4Nzk4MjUzMn0.fDepYFBixUF1WiB0RSdzBI4lQzASOp7y0BipKlluudU; JSESSIONID=34e536954076d6c70515aafd6825";
        Claims claim = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(s).getBody();
//        System.out.println(claim);
    }
}
