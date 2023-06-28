package com.prj_project_g2.Controller.Admin;

import com.prj_project_g2.Database.UserDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    @ResponseBody
    public String login(ModelMap model) {
//        model.addAttribute("title", "Index!");
        return "admin/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String loginPost(ModelMap model, @RequestParam(name = "username") String username) {
//        model.addAttribute("title", "Index!");
        return String.valueOf(UserDB.existUser(username));
    }
}
