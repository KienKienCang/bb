<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.List, model.DonHang" %>

<%
    String user = (String) session.getAttribute("user");
    List<DonHang> orderList = (List<DonHang>) request.getAttribute("orderList");
%>

<html>
<head>
    <title>Lịch sử đơn hàng</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<header>
    <div class="logo">KKC Store</div>
    <nav>
        <ul>
            <li><a href="index.jsp">Trang chủ</a></li>
            <li><a href="cart.jsp">Giỏ hàng</a></li>
            <% if (user == null) { %>
                <li><a href="login.jsp">Đăng nhập</a></li>
                <li><a href="register.jsp">Đăng ký</a></li>
            <% } else { %>
                <li><a href="logout">Đăng xuất (<%= user %>)</a></li>
            <% } %>
        </ul>
    </nav>
</header>

<section class="order-history">
    <h2>Lịch sử đơn hàng</h2>

    <% if (orderList == null || orderList.isEmpty()) { %>
        <p style="color: red;">Không có đơn hàng nào.</p>
    <% } else { %>
        <table border="1">
            <tr>
                <th>ID Đơn hàng</th>
                <th>Ngày đặt</th>
                <th>Tổng tiền</th>
                <th>Địa chỉ</th>
            </tr>
            <% for (DonHang order : orderList) { %>
                <tr>
                    <td><%= order.getId() %></td>
                    <td><%= order.getNgayDat() %></td>  <%-- Sửa từ getOrderDate() --%>
                    <td><%= order.getTongTien() %> VNĐ</td> <%-- Sửa từ getTotalPrice() --%>
                    <td><%= order.getDiaChi() %></td>
                </tr>
            <% } %>
        </table>
    <% } %>
</section>

<footer>
    <p>© 2025 KKC Store. Liên hệ: 011 315 113 | Email: nguyentkienth.com</p>
</footer>

</body>
</html>
