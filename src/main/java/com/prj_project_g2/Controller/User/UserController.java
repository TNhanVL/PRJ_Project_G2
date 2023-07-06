package com.prj_project_g2.Controller.User;

import com.prj_project_g2.Database.CourseDB;
import com.prj_project_g2.Database.LessonDB;
import com.prj_project_g2.Database.MoocDB;
import com.prj_project_g2.Database.QuestionResultDB;
import com.prj_project_g2.Database.QuizResultDB;
import com.prj_project_g2.Database.UserDB;
import com.prj_project_g2.Model.Lesson;
import com.prj_project_g2.Model.QuizResult;
import com.prj_project_g2.Model.User;
import com.prj_project_g2.Services.CookieServices;
import com.prj_project_g2.Services.JwtUtil;
import com.prj_project_g2.Services.MD5;
import java.util.Date;
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
    public String login(ModelMap model) {
        return "user/login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(ModelMap model) {
        return "user/signup";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(HttpServletRequest request, HttpServletResponse response, @RequestParam String username, @RequestParam String password) {
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

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(ModelMap model) {
        return "user/cart";
    }

    @RequestMapping(value = "/course/addOrder/{courseID}", method = RequestMethod.GET)
    public String addOrderFromCourse(ModelMap model, HttpServletRequest request, @PathVariable int courseID) {

        //check logged in
        if (!CookieServices.checkUserLoggedIn(request.getCookies())) {
            request.getSession().setAttribute("error", "You need to log in to continue!");
            return "redirect:../../login";
        }

        User user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));

        CourseDB.insertOrderCourse(user.getID(), courseID);

        return "redirect:../../course/" + courseID;
    }

    @RequestMapping(value = "/course/deleteOrder/{courseID}", method = RequestMethod.GET)
    public String deleteOrderFromCourse(ModelMap model, HttpServletRequest request, @PathVariable int courseID) {

        //check logged in
        if (!CookieServices.checkUserLoggedIn(request.getCookies())) {
            request.getSession().setAttribute("error", "You need to log in to continue!");
            return "redirect:../../login";
        }

        User user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));

        CourseDB.deleteOrderCourse(user.getID(), courseID);

        return "redirect:../../course/" + courseID;
    }

    @RequestMapping(value = "/cart/deleteOrder/{courseID}", method = RequestMethod.GET)
    public String deleteOrderFromCart(ModelMap model, HttpServletRequest request, @PathVariable int courseID) {

        //check logged in
        if (!CookieServices.checkUserLoggedIn(request.getCookies())) {
            request.getSession().setAttribute("error", "You need to log in to continue!");
            return "redirect:../../login";
        }

        User user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));

        CourseDB.deleteOrderCourse(user.getID(), courseID);

        return "redirect:../../cart";
    }

    @RequestMapping(value = "/learn/{courseID}", method = RequestMethod.GET)
    public String lesson(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable int courseID) {
        model.addAttribute("courseID", courseID);
        return "user/lesson";
    }

    @RequestMapping(value = "/learn/{courseID}/{lessonID}", method = RequestMethod.GET)
    public String lesson(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable int courseID, @PathVariable int lessonID) {
        model.addAttribute("courseID", courseID);
        model.addAttribute("lessonID", lessonID);
        return "user/lesson";
    }

    @RequestMapping(value = "/markLessonComplete/{lessonID}", method = RequestMethod.POST)
    @ResponseBody
    public String markLessonComplete(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable int lessonID) {
        //check logged in
        if (CookieServices.checkUserLoggedIn(request.getCookies())) {
            User user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));
            LessonDB.insertLessonCompleted(user.getID(), lessonID);
        }
        return "ok";
    }

    @RequestMapping(value = "/startAQuiz/{lessonID}", method = RequestMethod.GET)
    public String startAQuiz(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable int lessonID) {
        //check logged in
        if (!CookieServices.checkUserLoggedIn(request.getCookies())) {
            request.getSession().setAttribute("error", "You need to log in to continue!");
            return "redirect:../../login";
        }

        User user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));
        Lesson lesson = LessonDB.getLesson(lessonID);
        QuizResultDB.insertQuizResult(new QuizResult(0, lessonID, user.getID(), new Date(), new Date((new Date()).getTime() + lesson.getTime() * 60000)));

        return "redirect:../learn/" + MoocDB.getMooc(lesson.getMoocID()).getCourseID() + "/" + lessonID;
    }

    @RequestMapping(value = "/updateQuestionResult/{quizResultID}/{questionID}/{data}", method = RequestMethod.POST)
    @ResponseBody
    public String updateQuestionResult(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable int quizResultID, @PathVariable int questionID, @PathVariable String data) {
        //check logged in
        if (!CookieServices.checkUserLoggedIn(request.getCookies())) {
            request.getSession().setAttribute("error", "You need to log in to continue!");
            return "redirect:../../login";
        }

        User user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));

        //check owner
        QuizResult quizResult = QuizResultDB.getQuizResult(quizResultID);
        if (quizResult.getUserID() != user.getID()) {
            return "not owned";
        }
        
        if(quizResult.getEndTime().before(new Date())){
            return "out of time!";
        }
        
        QuestionResultDB.deleteQuestionResultOfQuestion(quizResultID, questionID);

        String[] answerIDs = data.split("_");
        for (String i : answerIDs) {
            try {
                int answerID = Integer.parseInt(i);
                QuestionResultDB.insertQuestionResult(quizResultID, questionID, answerID);
            } catch (NumberFormatException e) {

            }
        }

        return "ok";
    }

    @RequestMapping(value = "/course/{courseID}", method = RequestMethod.GET)
    public String course(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable int courseID) {
        model.addAttribute("courseID", courseID);
        return "user/course";
    }

    @RequestMapping(value = "/allCourses", method = RequestMethod.GET)
    public String allCourses(HttpServletRequest request, HttpServletResponse response) {
        return "user/allCourses";
    }
}
