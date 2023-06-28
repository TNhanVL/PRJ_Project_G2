/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prj_project_g2.Services;

import com.prj_project_g2.Database.AdminDB;
import io.jsonwebtoken.Claims;
import javax.servlet.http.Cookie;

/**
 *
 * @author TTNhan
 */
public class CheckLoggedIn {

    public static boolean check(Cookie[] cookies) {
        boolean ok = false;
        
        try {
            String jwtToken = null;

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("jwtToken")) {
                    jwtToken = cookie.getValue();
                    break;
                }
            }

            Claims claims = JwtUtil.parseJwt(jwtToken);
            String username = (String) claims.get("username");
            String password = (String) claims.get("password");
            if (AdminDB.checkAdmin(username, password, true) == 0) {
                ok = true;
            }

        } catch (Exception e) {
        }

        return ok;
    }
}
