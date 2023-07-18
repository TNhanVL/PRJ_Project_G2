package com.prj_project_g2.Controller.User;

import com.mservice.enums.RequestType;
import com.mservice.momo.MomoPay;
import com.prj_project_g2.Database.CourseDB;
import com.prj_project_g2.Database.LessonDB;
import com.prj_project_g2.Database.MoocDB;
import com.prj_project_g2.Database.QuestionDB;
import com.prj_project_g2.Database.QuestionResultDB;
import com.prj_project_g2.Database.QuizResultDB;
import com.prj_project_g2.Database.UserDB;
import com.prj_project_g2.Model.Course;
import com.prj_project_g2.Model.GooglePojo;
import com.prj_project_g2.Model.Lesson;
import com.prj_project_g2.Model.Mooc;
import com.prj_project_g2.Model.QuizResult;
import com.prj_project_g2.Model.User;
import com.prj_project_g2.Services.CookieServices;
import com.prj_project_g2.Services.GoogleUtils;
import com.prj_project_g2.Services.JwtUtil;
import com.prj_project_g2.Services.MD5;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "user/login";
    }

    @RequestMapping(value = "/loginWithGG", method = RequestMethod.GET)
//    @ResponseBody
    public String loginWithGG(HttpServletRequest request, HttpServletResponse response, @RequestParam String code) {
        if (code == null || code.isEmpty()) {
            request.getSession().setAttribute("error", "Error when login with Google!");
            return "redirect:./login";
        } else {
            try {
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);

                User user = UserDB.getUserByEmail(googlePojo.getEmail());

//                System.out.println(googlePojo);

                if (user != null) {
                    String TokenBody = JwtUtil.generateJwt(user.getUsername(), user.getPassword());
                    System.out.println(user);
                    Cookie cookie = new Cookie("jwtToken", TokenBody);
                    cookie.setMaxAge(60 * 60 * 6);
                    request.getSession().setAttribute("success", "Login succeed!");
                    response.addCookie(cookie);
                    return "redirect:./main";
                }

                user = new User(googlePojo);

                request.setAttribute("userSignUp", user);

            } catch (IOException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                request.getSession().setAttribute("error", "Error when login with Google!");
                return "redirect:./login";
            }

        }
        return "user/signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getAttribute("userSignUp");
        System.out.println(user);
        return "user/signup";
    }

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        //The date format to parse or output your dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Create a new CustomDateEditor
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(ModelMap model, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user") User user) {
        if (user.getCountryID() == 0) {
            user.setCountryID(16);
        }
        user.setPassword(MD5.getMd5(user.getPassword()));
        UserDB.insertUser(user);
        request.getSession().setAttribute("success", "Signup successful!");
        return "redirect:./login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(HttpServletRequest request, HttpServletResponse response, @RequestParam String username, @RequestParam String password) {
        int status = UserDB.checkUser(username, password, false);
        
        if (status < 0) {
            request.getSession().setAttribute("error", "Some error with database!");
            return "redirect:./login";
        }
        
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
        cookie.setMaxAge(60 * 60 * 6);
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

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String selfProfile(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        //check logged in
        if (!CookieServices.checkUserLoggedIn(request.getCookies())) {
            return "redirect:./main";
        }

        User user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));

        model.addAttribute("username", user.getUsername());
        return "user/profile/profile";
    }

    @RequestMapping(value = "/profile/{username}", method = RequestMethod.GET)
    public String profile(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable String username) {
        model.addAttribute("username", username);
        return "user/profile/profile";
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

    @RequestMapping(value = "/checkOut", method = RequestMethod.POST)
    public String checkOutPost(ModelMap model, HttpServletRequest request) {

        //check logged in
        if (!CookieServices.checkUserLoggedIn(request.getCookies())) {
            request.getSession().setAttribute("error", "You need to log in to continue!");
            return "redirect:./login";
        }

        User user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));

        //get all courses ID
        String[] courseIDStrs = request.getParameterValues("course");
        ArrayList<Course> courses = new ArrayList<>();

        if (courseIDStrs != null) {
            for (String courseIDStr : courseIDStrs) {
                try {
                    int courseID = Integer.parseInt(courseIDStr);

                    //check in cart
                    if (!CourseDB.checkOrderCourse(user.getID(), courseID)) {
                        continue;
                    }

                    if (CourseDB.existCourse(courseID)) {
                        courses.add(CourseDB.getCourse(courseID));
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        }

        model.addAttribute("courses", courses);

        if (courses.isEmpty()) {
            request.getSession().setAttribute("error", "No chosing cart to checkout!");
            return "redirect:./cart";
        }

        return "user/checkOut";
    }

    @RequestMapping(value = "/checkOutWithPayment", method = RequestMethod.POST)
    public String checkOutWithPayment(ModelMap model, HttpServletRequest request, @RequestParam long price, @RequestParam String paymentMethod) {

        //check logged in
        if (!CookieServices.checkUserLoggedIn(request.getCookies())) {
            request.getSession().setAttribute("error", "You need to log in to continue!");
            return "redirect:./login";
        }

        User user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));

        //get all courses ID
        String[] courseIDStrs = request.getParameterValues("course");

        //get pay type
        RequestType requestType;
        if ("captureWallet".equals(paymentMethod)) {
            requestType = RequestType.CAPTURE_WALLET;
        } else {
            requestType = RequestType.PAY_WITH_ATM;
        }

        String payLink = MomoPay.getPayLink(request, requestType, user.getID(), courseIDStrs, price);

        if (payLink == null) {
            request.getSession().setAttribute("error", "There are some error when checkout!");
            return "redirect:./cart";
        } else {
            return "redirect:" + payLink;
        }
    }

    @RequestMapping(value = "/finishedPayment", method = RequestMethod.GET)
    public String finishedPayment(ModelMap model, HttpServletRequest request, @RequestParam String userID, @RequestParam int resultCode) {

        User user = null;

        try {
            user = UserDB.getUser(Integer.parseInt(userID));
            if (resultCode != 0) {
                throw new Exception();
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
            request.getSession().setAttribute("error", "There are some error!");
            return "redirect:./main";
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //get all courses ID
        String[] courseIDStrs = request.getParameterValues("course");

        if (courseIDStrs != null) {
            for (String courseIDStr : courseIDStrs) {
                try {
                    int courseID = Integer.parseInt(courseIDStr);

                    //check in cart
                    if (!CourseDB.checkOrderCourse(user.getID(), courseID)) {
                        continue;
                    }

                    CourseDB.deleteOrderCourse(user.getID(), courseID);
                    CourseDB.insertPurchasedCourse(user.getID(), courseID);

                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        }

        request.getSession().setAttribute("success", "Pay successful!");

        return "redirect:./profile";
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

    @RequestMapping(value = "/markLessonComplete/{lessonID}", method = RequestMethod.GET)
//    @ResponseBody
    public String markLessonComplete(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable int lessonID) {
        //check logged in
        if (CookieServices.checkUserLoggedIn(request.getCookies())) {
            User user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));
            LessonDB.insertLessonCompleted(user.getID(), lessonID, request);
        }

        Lesson lesson = LessonDB.getLesson(lessonID);
        Mooc mooc = MoocDB.getMooc(lesson.getMoocID());
        Course course = CourseDB.getCourse(mooc.getCourseID());
        return "redirect:../learn/" + course.getID() + "/" + lessonID;
    }

    @RequestMapping(value = "/markLessonComplete/{lessonID}", method = RequestMethod.POST)
    @ResponseBody
    public String markLessonCompletePost(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable int lessonID) {
        //check logged in
        if (CookieServices.checkUserLoggedIn(request.getCookies())) {
            User user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));
            LessonDB.insertLessonCompleted(user.getID(), lessonID, request);
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

        if (quizResult.getEndTime().before(new Date())) {
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

    @RequestMapping(value = "/endAQuiz/{quizResultID}", method = RequestMethod.GET)
    public String endAQuiz(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable int quizResultID) {
        //check logged in
        if (!CookieServices.checkUserLoggedIn(request.getCookies())) {
            request.getSession().setAttribute("error", "You need to log in to continue!");
            return "redirect:../login";
        }

        User user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));

        //check quizResult exist
        QuizResult quizResult = QuizResultDB.getQuizResult(quizResultID);
        if (quizResult == null) {
            return "redirect:../main";
        }

        //check owner
        if (quizResult.getUserID() != user.getID()) {
            return "redirect:../main";
        }

        Lesson lesson = LessonDB.getLesson(quizResult.getLessonID());
        Mooc mooc = MoocDB.getMooc(lesson.getMoocID());

        //if quiz end yet
        if (quizResult.getEndTime().before(new Date())) {
            return "redirect:../learn/" + mooc.getCourseID() + "/" + lesson.getID();
        }

        //set endTime to current
        quizResult.setEndTime(new Date());
        QuizResultDB.updateQuizResult(quizResult);

        int numberOfCorrectQuestion = QuizResultDB.getQuizResultPoint(quizResultID);
        int numberOfQuestion = QuestionDB.getNumberQuestionByLessonID(lesson.getID());
        if (numberOfCorrectQuestion * 100 >= numberOfQuestion * 80) {
            LessonDB.insertLessonCompleted(user.getID(), lesson.getID(), request);
        }

        return "redirect:../learn/" + mooc.getCourseID() + "/" + lesson.getID();
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
