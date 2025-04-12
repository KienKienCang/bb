<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng ký thất bại</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8d7da;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .message-container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            width: 350px;
            text-align: center;
            border-left: 5px solid #dc3545;
        }
        h2 {
            color: #dc3545;
        }
        .error {
            color: #721c24;
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            padding: 10px;
            border-radius: 5px;
        }
        a {
            display: block;
            margin-top: 15px;
            padding: 10px;
            text-decoration: none;
            background-color: #dc3545;
            color: white;
            border-radius: 5px;
            transition: 0.3s;
        }
        a:hover {
            background-color: #c82333;
        }
        .debug-info {
            margin-top: 10px;
            font-size: 0.85em;
            color: #6c757d;
        }
    </style>
</head>
<body>
    <div class="message-container">
        <h2>❌ Đăng ký thất bại</h2>
        <p class="error"><%= request.getAttribute("errorMessage") %></p>

        <% 
            String detailedError = (String) request.getAttribute("detailedError");
            if (detailedError != null) { 
        %>
            <p class="debug-info">Chi tiết lỗi: <%= detailedError %></p>
        <% } %>

        <a href="register.jsp">🔄 Thử lại</a>
    </div>
</body>
</html>
