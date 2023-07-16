<%-- 
    Document   : checkout
    Created on : Jul 16, 2023, 9:24:49 PM
    Author     : TTNhan
--%>

<%@page import="java.text.DecimalFormat"%>
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

    ArrayList<Course> courses = (ArrayList<Course>) request.getAttribute("courses");
    double priceDouble = 0;
    for (Course course : courses) {
        priceDouble += course.getPrice();
    }
    priceDouble *= 23600;
    long price = (long) priceDouble;
%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="head.jsp">
            <jsp:param name="title" value="Cart"/>
        </jsp:include>
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/checkout.css">
    </head>

    <body>
        <!-- HEADER -->
        <%@include file="header.jsp" %>
        <!--END HEADER -->

        <div id="body-checkout">

            <div class="ticket">
                <div class="ticket-header">
                    <div class="paymentInfo">
                        <h1 class="text-center mb-4">Almost complete</h1>
                        <h4 class="text-center">Pay for <b><%out.print(courses.size());%></b> course<%if (courses.size() > 1) {
                                out.print("s");
                            }%></h4>
                        <h4 class="text-center mb-4">Total payment: <b><%
                            DecimalFormat decimalFormat = new DecimalFormat("#,##0");
                            decimalFormat.setGroupingUsed(true);
                            out.print(decimalFormat.format(price));
                            %>đ</b></h4>
                        <h5 class="text-start me-auto">Choose your payment methods</h5>
                    </div>

                    <div class="paymentMethod">
                        <div>
                            <div class="momo-radio"><label class="label" for="momoPay"><img
                                        src="https://test-payment.momo.vn/v2/gateway/images/logo-momo-3b8deb874c6d3b77f976f35310d8e50a.png"
                                        alt="captureWallet" class="image">
                                    <div style="overflow:hidden">
                                        <div class="label-title" style="white-space:nowrap">Ví MoMo</div>
                                    </div>
                                </label><input id="momoPay" type="radio" name="paymentMethod" value="captureWallet"></div>
                        </div>
                        <div>
                            <div class="momo-radio "><label class="label" for="atmPay"><img
                                        src="https://test-payment.momo.vn/v2/gateway/images/credit/atm-a2eaf925d65062641bdef898d3cc0fe8.svg"
                                        alt="payWithATM" class="image">
                                    <div style="overflow:hidden">
                                        <div class="label-title" style="white-space:nowrap">Thẻ ATM nội địa</div>
                                    </div>
                                </label><input id="atmPay" type="radio" name="paymentMethod" value="payWithATM"></div>
                        </div>
                    </div>
                </div>
                <div class="ticket-devider">
                    <div class="ticket-norch"></div>
                    <div class="ticket-norch ticket-norch-right"></div>
                </div>
                <div class="ticket-body">
                    <div>
                        <button class="btn-momo active">Pay now</button>
                    </div>
                </div>
            </div>


        </div>

        <%@include file="footer.jsp" %>

        <%@include file="foot.jsp" %>

        <script src="<%out.print(request.getContextPath());%>/public/assets/js/cart.js"></script>

        <%@include file="popUpMessage.jsp" %>

    </body>

</html>