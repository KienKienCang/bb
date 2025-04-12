<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .register-container {
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
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-left: 3%;
        }
        button:hover {
            background-color: #0056b3;
        }
        .error {
            color: red;
        }
        .login-link {
            margin-top: 10px;
            display: block;
            text-decoration: none;
            color: #007bff;
        }
        .login-link:hover {
            text-decoration: underline;
        }
    </style>
    <script>
        function validateForm() {
            let password = document.getElementById("matKhau").value;
            let confirmPassword = document.getElementById("xacNhanMatKhau").value;
            if (password !== confirmPassword) {
                document.getElementById("error-message").innerText = "Mật khẩu không khớp!";
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
    <div class="register-container">
        <h2>Đăng ký</h2>
        <form action="register" method="post" onsubmit="return validateForm()">
            <input type="text" name="tenTaiKhoan" placeholder="Tên tài khoản" required>
            <input type="password" id="matKhau" name="matKhau" placeholder="Mật khẩu" required>
            <input type="password" id="xacNhanMatKhau" placeholder="Xác nhận mật khẩu" required>
            <input type="text" name="soDienThoai" placeholder="Số điện thoại" required>
            <button type="submit">Đăng ký</button>
        </form>
        <p id="error-message" class="error"></p>
        <% 
            String error = request.getParameter("error");
            if (error != null) {
        %>
            <p class="error"><%= error %></p>
        <% } %>
        <a href="login.jsp" class="login-link">Đã có tài khoản? Đăng nhập</a>
    </div>
</body>
</html>
