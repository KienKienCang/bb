<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.DonHang, dao.DonHangDAO, dao.DBContext" %>
<%@ page session="true" %>
<%
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("admin")) {
        response.sendRedirect("login.jsp");
        return;
    }

    DonHangDAO donHangDAO = new DonHangDAO(DBContext.getConnection());
    List<DonHang> orderList = donHangDAO.getAllDonHang();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Quản lý đơn hàng</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; }
        table { width: 80%; margin: 20px auto; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 10px; }
        th { background-color: #17a2b8; color: white; }
        .btn { padding: 5px 10px; text-decoration: none; color: white; background-color: #007bff; border-radius: 3px; }
        .btn-view { background-color: #28a745; }
    </style>
</head>
<body>

<h2>Quản lý đơn hàng</h2>
<a href="admin.jsp">🔙 Quay lại trang Admin</a>

<table>
    <tr>
        <th>ID</th>
        <th>Người dùng</th>
        <th>Ngày đặt</th>
        <th>Tổng tiền</th>
        <th>Địa chỉ</th>
        <th>Chi tiết</th>
    </tr>
    <% for (DonHang o : orderList) { %>
        <tr>
            <td><%= o.getId() %></td>
            <td><%= o.getNguoiDungId() %></td>
            <td><%= o.getNgayDat() %></td>
            <td><%= o.getTongTien() %> $</td>
            <td><%= o.getDiaChi() %></td>
            <td>
                <a href="vieworderdetail.jsp?id=<%= o.getId() %>" class="btn btn-view">Xem</a>
            </td>
        </tr>
    <% } %>
</table>

</body>
</html>

