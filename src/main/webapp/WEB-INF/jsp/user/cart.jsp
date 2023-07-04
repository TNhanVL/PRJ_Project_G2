<%-- 
    Document   : cart
    Created on : Jul 4, 2023, 10:56:30 PM
    Author     : TTNhan
--%>

<%@page import="com.prj_project_g2.Model.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //check user loggedIn
    User user = null;
    if (!CookieServices.checkUserLoggedIn(request.getCookies())) {
        request.getSession().setAttribute("error", "You must be logged in before enter cart!");
        response.sendRedirect("./login");
        return;
    } else {
        user = UserDB.getUserByUsername(CookieServices.getUserName(request.getCookies()));
    }

    ArrayList<Course> courses = CourseDB.getAllOrderCourses(user.getID());
%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="head.jsp">
            <jsp:param name="title" value="Cart"/>
        </jsp:include>
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/paysite.css">
    </head>

    <body>
        <!-- HEADER -->
        <%@include file="header.jsp" %>
        <!--END HEADER -->

        <div id="body-paysite">
            <div class="shopping-cart">
                <h2 class="shopping-cart-header">
                    Shopping Cart
                </h2>

                <h4 class="number-of-course">
                    <%out.print(courses.size());%> course in Cart
                </h4>
                <!-- When cart is empty display this -->
                <div class="keepShopping ">
                    <!-- <video src="<%out.print(request.getContextPath());%>/public/assets/videos/DoggieCorgi-4.mp4"></video> -->
                    <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/logoooooo.png" alt="" class="description-img">
                    <h5>Your cart is empty, keeping shopping to find a course!</h5>
                    <div class="keepshopping-btn"><a href="">Keep Shopping</a></div>
                </div>
                <!-- Course in cart -->
                <form class="courseInCart" method="post">

                    <div class="items">

                        <%
                            int courseIndex = 0;
                            for (Course course : courses) {
                                courseIndex++;
                        %>

                        <!-- an item -->
                        <div class="item">
                            <input type="checkbox" id="course<%out.print(courseIndex);%>" name="course<%out.print(courseIndex);%>" value="<%out.print(course.getID());%>">
                            <img src="<%out.print(request.getContextPath());%>/public/media/course/<%out.print(course.getID() + "/" + course.getImage());%>" class="itemImg" alt="">
                            <div class="itemInformation">
                                <p class="itemName"><%out.print(course.getTitle());%></p>
                                <div class="itemRating"><%out.print(course.getRate());%><i class="fa-solid fa-star"></i> (2503 reviewer)</div>
                            </div>
                            <p class="itemPrice"><%out.print(course.getPrice());%>$</p>

                            <div class="itemEdit"><i class="fa-solid fa-trash"></i></div>
                        </div>
                        <!-- end item -->

                        <%}%>

                    </div>
                    <div class="popup-checkout">
                        <i class="fa-solid fa-xmark close"></i>
                        <h4>Order Summary</h4>
                        <div class="component balance">
                            <p>Total Price</p><span>$0</span>
                        </div>
                        <div class="component price">
                            <p>Sale Amount</p><span>$0</span>
                        </div>
                        <!-- <div class="component remainBalance">
                            <p>Remaining Balance</p><span>$0</span>
                        </div> -->
                        <div class="component totalCost">
                            <p>Total Cost</p> <span>$0</span>
                        </div>
                        <!-- <div class="component promotionCode">
                            <form action="" method="post">
                                <input type="text" placeholder="Promotion Code">
                                <input type="submit" value="Apply">
                            </form>
                        </div> -->
                        <div class="checkout-btn"><input type="submit" value="Check out"></div>
                    </div>
                </form>
            </div>
        </div>

        <%@include file="footer.jsp" %>

        <%@include file="foot.jsp" %>

        <script src="<%out.print(request.getContextPath());%>/public/assets/js/cart.js"></script>

        <%@include file="popUpMessage.jsp" %>

    </body>

</html>