<%-- 
    Document   : foot
    Created on : Jun 1, 2023, 1:34:45 PM
    Author     : TTNhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="toast"></div>

<script>
    <%
        String message = (String) request.getSession().getAttribute("success");
        if(message != null && message != ""){
            out.println("showSuccesToast('" + message + "');");
            request.getSession().removeAttribute("success");
        }
        message = (String) request.getSession().getAttribute("error");
        if(message != null && message != ""){
            out.println("showErrorToast('" + message + "');");
            request.getSession().removeAttribute("error");
        }
    %>
</script>

<!-- jquery-validation -->
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>

</body>
</html>
