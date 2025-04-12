<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.ChiTietDonHang, dao.ChiTietDonHangDAO, dao.DBContext" %>
<%@ page session="true" %>

<%
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("admin")) {
        response.sendRedirect("login.jsp");
        return;
    }

    int donHangId = Integer.parseInt(request.getParameter("id"));
    ChiTietDonHangDAO chiTietDAO = new ChiTietDonHangDAO(DBContext.getConnection());
    List<ChiTietDonHang> chiTietList = chiTietDAO.getChiTietDonHangById(donHangId);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Chi tiết đơn hàng</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; }
        table { width: 80%; margin: 20px auto; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 10px; }
        th { background-color: #28a745; color: white; }
        .btn-back { display: inline-block; margin-top: 10px; padding: 10px 15px; background-color: #007bff; color: white; text-decoration: none; border-radius: 5px; }
    </style>
</head>
<body>

<h2>Chi tiết đơn hàng #<%= donHangId %></h2>
<a href="quanlydonhang.jsp" class="btn-back">🔙 Quay lại</a>

<table>
    <tr>
        <th>ID Sản phẩm</th>
        <th>Số lượng</th>
        <th>Đơn giá</th>
        <th>Thành tiền</th>
    </tr>
    <% for (ChiTietDonHang ct : chiTietList) { %>
        <tr>
            <td><%= ct.getSanPhamId() %></td>
            <td><%= ct.getSoLuong() %></td>
            <td><%= ct.getDonGia() %> VND</td>
            <td><%= ct.getSoLuong() * ct.getDonGia() %> VND</td>
        </tr>
    <% } %>
</table>

</body>
</html>
