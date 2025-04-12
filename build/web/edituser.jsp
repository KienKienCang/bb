<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.NguoiDung" %>

<%
    NguoiDung user = (NguoiDung) request.getAttribute("user");

    if (user == null) {
        response.sendRedirect("quanlynguoidung.jsp?error=UserNotFound");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Chỉnh sửa người dùng</title>
</head>
<body>
    <h2>Chỉnh sửa thông tin người dùng</h2>
    <form action="edituser" method="post">
    <input type="hidden" name="id" value="<%= user.getId() %>">
    
    <label>Tên tài khoản:</label>
    <input type="text" name="tenTaiKhoan" value="<%= user.getTenTaiKhoan() %>" required><br>

    <label>Mật khẩu:</label>
    <input type="password" name="matKhau" value="<%= user.getMatKhau() %>" required><br>

    <label>Số điện thoại:</label>
    <input type="text" name="sdt" value="<%= user.getSoDienThoai() %>" required><br>

    <label>Quyền:</label>
    <select name="role">
        <option value="user" <%= "user".equals(user.getRole()) ? "selected" : "" %>>User</option>
        <option value="admin" <%= "admin".equals(user.getRole()) ? "selected" : "" %>>Admin</option>
    </select><br>

    <button type="submit">Cập nhật</button>
</form>
</body>
</html>