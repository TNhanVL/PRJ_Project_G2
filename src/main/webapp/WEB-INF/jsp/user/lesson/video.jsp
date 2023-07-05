<%-- 
    Document   : video
    Created on : Jul 5, 2023, 8:54:54 PM
    Author     : TTNhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<video src="<%out.print(request.getContextPath());%>/public/media/lesson/<%out.print(lesson.getID() + "/" + post.getContent());%>" controls>
    Trình duyệt của bạn không hỗ trợ video.
</video>