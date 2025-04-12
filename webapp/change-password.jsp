<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<%
    String user = (String) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<html>
<head>
    <title>Đổi mật khẩu</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <h2>Đổi mật khẩu</h2>
    <form action="change-password" method="post">
    <label for="old-password">Mật khẩu cũ:</label>
    <input type="password" id="old-password" name="old-password" required>

    <label for="new-password">Mật khẩu mới:</label>
    <input type="password" id="new-password" name="new-password" required>

    <label for="confirm-password">Nhập lại mật khẩu mới:</label>
    <input type="password" id="confirm-password" name="confirm-password" required>

    <button type="submit">Đổi mật khẩu</button>
</form>
</body>
</html>
