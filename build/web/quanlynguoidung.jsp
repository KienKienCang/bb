<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.NguoiDung, dao.NguoiDungDAO, dao.DBContext" %>
<%@ page session="true" %>
<%
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("admin")) {
        response.sendRedirect("login.jsp");
        return;
    }

    NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(DBContext.getConnection());
    List<NguoiDung> userList = nguoiDungDAO.getAllNguoiDung();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý người dùng</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; }
        table { width: 80%; margin: 20px auto; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 10px; }
        th { background-color: #28a745; color: white; }
        .btn { padding: 5px 10px; text-decoration: none; color: white; background-color: #007bff; border-radius: 3px; }
        .btn-delete { background-color: red; }
    </style>
</head>
<body>

<h2>Quản lý người dùng</h2>
<a href="admin.jsp">🔙 Quay lại trang Admin</a>

<table>
    <tr>
        <th>ID</th>
        <th>Tên tài khoản</th>
        <th>Số điện thoại</th>
        <th>Vai trò</th>
        <th>Hành động</th>
    </tr>
    <% for (NguoiDung u : userList) { %>
    <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getTenTaiKhoan() %></td>
        <td><%= u.getSoDienThoai() %></td>
        <td><%= u.getRole() %></td>
        <td>
            <a href="edituser?id=<%= u.getId() %>" class="btn btn-primary">Sửa</a>
            <a class="btn btn-delete" href="deleteuser?id=<%= u.getId() %>">Xóa</a>
        </td>   
    </tr>
    <% } %>
</table>

</body>
</html>
