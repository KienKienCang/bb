<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%
    // Lấy thông tin nhớ mật khẩu từ Cookie
    Cookie[] cookies = request.getCookies();
    String rememberedUsername = "";
    String rememberedPassword = "";
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("rememberedUsername".equals(cookie.getName())) {
                rememberedUsername = cookie.getValue();
            }
            if ("rememberedPassword".equals(cookie.getName())) {
                rememberedPassword = cookie.getValue();
            }
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập</title>
    <style>
        
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
            padding-right: 3%;
        }
        input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding-right: 4%;
        }
        button {
            display-content: flex;
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            justify-self: center;
            margin-left: 3%;
                
        }
        button:hover {
            background-color: #0056b3;
        }
        .error {
            color: red;
        }
        .register-link {
            margin-top: 10px;
            display: block;
            text-decoration: none;
            color: #007bff;
            margin-left: 5%;
        }
        .register-link:hover {
            text-decoration: underline;
        }
        .remember-me-container {
            display: flex;
            align-items: center;
            justify-content: flex-start; /* Căn trái */
            margin: 10px 0;
        }
        .remember-me-container input {
            margin-right: 5px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Đăng nhập</h2>
        <form action="login" method="post">
            <input type="text" name="tenTaiKhoan" placeholder="Tên tài khoản" value="<%= rememberedUsername %>" required>
            <input type="password" name="matKhau" placeholder="Mật khẩu" value="<%= rememberedPassword %>" required>

            <!-- Thêm checkbox nhớ mật khẩu -->
            <div style="text-align: left;">
                <input type="checkbox" style="margin-left: -47%" name="rememberMe" <%= !rememberedUsername.isEmpty() ? "checked" : "" %>> 
                <h5 style="
                    margin-top: -2%;
    margin-left: 1%;">Nhớ mật khẩu</h5>
            </div>

            <button type="submit">Đăng nhập</button>
        </form>

        <% String error = (String) request.getAttribute("error"); %>
        <% if (error != null) { %>
        <p class="error"><%= error %></p>
        <% } %>

        <a href="register.jsp" class="register-link">Tạo tài khoản</a>
        
        
    </div>
        
</body>
</html>