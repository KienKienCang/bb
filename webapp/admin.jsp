<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.Product, model.NguoiDung" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page import="dao.ThongKeDAO, dao.DBContext" %>
<%
    // Kết nối DB và lấy thống kê
    ThongKeDAO thongKeDAO = new ThongKeDAO(DBContext.getConnection());
    String sanPhamBanChay = thongKeDAO.getSanPhamBanChayNhat();
    String sanPhamBanIt = thongKeDAO.getSanPhamBanItNhat();
    double avgDoanhThuNgay = thongKeDAO.getAvgDoanhThuNgay();
    double tongDoanhThu = thongKeDAO.getTongDoanhThu();
%>
<%
    // Kiểm tra nếu user chưa đăng nhập hoặc không phải admin thì chuyển hướng về trang chủ
    String user = (String) session.getAttribute("user");
    String role = (String) session.getAttribute("role");

    if (user == null || !"admin".equals(role)) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản trị viên - KKC Store</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 30px;
        }

        h1 {
            color: #ff6600;
        }

        .nav {
            background: #333;
            padding: 10px;
        }

        .nav a {
            color: white;
            text-decoration: none;
            margin: 0 15px;
        }

        .admin-actions {
            display: flex;
            justify-content: center;
            margin-top: 20px;
            gap: 20px;
        }

        .admin-actions a {
            display: inline-block;
            text-decoration: none;
            background: #ff6600;
            color: white;
            padding: 12px 20px;
            border-radius: 5px;
            font-weight: bold;
            transition: 0.3s;
        }

        .admin-actions a:hover {
            background: #e65c00;
        }
        .dashboard {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            margin-top: 20px;
        }

        .dashboard-card {
            background: white;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 300px;
            margin: 10px;
        }

        .dashboard-card h3 {
            font-size: 18px;
            color: #333;
            margin-bottom: 10px;
        }

        .dashboard-card p {
            font-size: 20px;
            font-weight: bold;
            color: #28a745;
        }

    </style>
</head>
<body>

    <div class="nav">
        <a href="index.jsp">Trang chủ</a>
        <a href="logout">Đăng xuất (<%= user %>)</a>
    </div>

    <div class="container">
        <h1>Xin chào, Admin!</h1>
        <p>Chọn chức năng quản trị:</p>

        <div class="admin-actions">
            <a href="quanlysanpham.jsp">Quản lý sản phẩm</a>
            <a href="quanlynguoidung.jsp">Quản lý người dùng</a>
            <a href="quanlydonhang.jsp">Quản lý đơn hàng</a>
        </div>
    </div>
    <div class="dashboard">
        <div class="dashboard-card">
            <h3>🔥 Sản phẩm bán chạy nhất</h3>
            <p><strong><%= sanPhamBanChay %></strong></p>
        </div>

        <div class="dashboard-card">
            <h3>❄️ Sản phẩm bán ít nhất</h3>
            <p><strong><%= sanPhamBanIt %></strong></p>
        </div>

        <div class="dashboard-card">
            <h3>📊 Doanh thu trung bình/ngày</h3>
            <p><strong><%= String.format("%.2f", avgDoanhThuNgay) %> $</strong></p>
        </div>

        <div class="dashboard-card">
            <h3>💰 Tổng doanh thu</h3>
            <p><strong><%= String.format("%.2f", tongDoanhThu) %> $</strong></p>
        </div>
    </div>

</body>
</html>
