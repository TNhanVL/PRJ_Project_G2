<%-- 
    Document   : popupMessage
    Created on : Jun 29, 2023, 2:38:53 AM
    Author     : TTNhan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    document.getElementById("cancelButton").onclick = function () {
        var a = document.getElementsByClassName("alert");
        if (a.length)
            a[0].remove();
        document.getElementsByName("username")[0].value = "";
        document.getElementsByName("password")[0].value = "";
    };


    $(function () {
        $('#loginForm').validate({
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true,
                    minlength: 5
                }
            },
            messages: {
                username: {
                    required: "Please enter a username"
                },
                password: {
                    required: "Please provide a password",
                    minlength: "Your password must be at least 5 characters long"
                }
            },
            errorElement: 'span',
            errorPlacement: function (error, element) {
                error.addClass('invalid-feedback');
                element.closest('.form-group').append(error);
            },
            highlight: function (element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            }
        });
    });

</script>