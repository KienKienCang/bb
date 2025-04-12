<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.DonHang, dao.DonHangDAO, dao.DBContext" %>
<%@ page session="true" %>

<%
    // Kiểm tra nếu người dùng chưa đăng nhập
    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    DonHangDAO donHangDAO = new DonHangDAO(DBContext.getConnection());
    List<DonHang> orderList = donHangDAO.getOrdersByUserId(userId);
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách đơn hàng</title>
    <style>
        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #28a745;
            color: white;
        }
    </style>
</head>
<body>

<h2 style="text-align: center;">Danh sách đơn hàng của bạn</h2>

<table>
    <tr>
        <th>ID Đơn hàng</th>
        <th>Ngày đặt hàng</th>
        <th>Tổng tiền</th>
        <th>Địa chỉ giao hàng</th>
        <th>Chi tiết</th>
    </tr>
    <% for (DonHang order : orderList) { %>
        <tr>
            <td><%= order.getId() %></td>
            <td><%= order.getNgayDat() %></td>  <!-- ✅ Sửa từ getOrderDate() -> getNgayDat() -->
            <td><%= order.getTongTien() %> VND</td>  <!-- ✅ Sửa từ getPrice() -> getTongTien() -->
            <td><%= order.getDiaChi() %></td>
            <td>
                <a href="orderdetail.jsp?id=<%= order.getId() %>">Chi tiết</a>
            </td>
        </tr>
    <% } %>
</table>

</body>
</html>