package com.prj_project_g2.Controller.User;

import com.prj_project_g2.Database.UserDB;
import com.prj_project_g2.Services.JwtUtil;
import com.prj_project_g2.Services.MD5;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    @ResponseBody
    public String login(ModelMap model) {
//        model.addAttribute("title", "Index!");
        return "user/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ResponseBody
    public String loginPost(HttpServletRequest request, HttpServletResponse response, @RequestParam String username, @RequestParam String password) {
//        model.addAttribute("title", "Index!");
        int status = UserDB.checkUser(username, password, false);
        if (status == 1) {
            request.getSession().setAttribute("error", "Username not exist!");
            return "redirect:./login";
        }

        if (status == 2) {
            request.getSession().setAttribute("error", "Incorrect password!");
            return "redirect:./login";
        }

        String TokenBody = JwtUtil.generateJwt(username, MD5.getMd5(password));
        Cookie cookie = new Cookie("jwtToken", TokenBody);
        cookie.setMaxAge(60 * 60);
        request.getSession().setAttribute("success", "Login succeed!");
        response.addCookie(cookie);
        return "redirect:./main";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
//        model.addAttribute("title", "Index!");
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("jwtToken")) {
                cookie.setValue(null);
                response.addCookie(cookie);
                break;
            }
        }
        request.getSession().setAttribute("success", "Logout succeed!");
        return "redirect:./login";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        return "user/main";
    }

    @RequestMapping(value = "/lesson", method = RequestMethod.GET)
    public String lesson(HttpServletRequest request, HttpServletResponse response, @RequestParam String lessonID) {
        return "user/lesson";
    }
    
    @RequestMapping(value = "/course/{courseID}", method = RequestMethod.GET)
    public String course(HttpServletRequest request, HttpServletResponse response, @PathVariable int courseID) {
        System.out.println(courseID);
        return "user/course";
    }
    
    @RequestMapping(value = "/allCourses", method = RequestMethod.GET)
    public String allCourses(HttpServletRequest request, HttpServletResponse response) {
        return "user/allCourses";
    }
}
