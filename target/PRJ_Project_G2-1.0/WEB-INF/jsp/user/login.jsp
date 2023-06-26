<%-- Document : login Created on : Jun 25, 2023, 12:24:50 AM Author : TTNhan --%>

    <%@page contentType="text/html" pageEncoding="UTF-8" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>login</title>
        </head>

        <body>

            <h1>Login form</h1>
            <form action="<%=request.getContextPath()%>/user/login" method="post">
                <div>
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username">
                </div>
                <div>
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password">
                </div>
                <div>
                    <input type="submit">
                </div>
            </form>
        </body>

        </html>