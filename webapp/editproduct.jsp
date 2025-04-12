<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, model.Product, dao.ProductDAO, dao.DBContext" %>
<%@ page session="true" %>

<%
    // Kiểm tra đăng nhập (chỉ admin mới có thể chỉnh sửa sản phẩm)
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("admin")) {
        response.sendRedirect("index.jsp");
        return;
    }

    // Lấy id sản phẩm từ URL
    String productId = request.getParameter("id");

    if (productId == null || productId.isEmpty()) {
        response.sendRedirect("quanlysanpham.jsp?error=Không tìm thấy sản phẩm");
        return;
    }

    // Kết nối database và lấy thông tin sản phẩm
    Product product = null;
    try (Connection conn = DBContext.getConnection()) {
        ProductDAO productDAO = new ProductDAO(conn);
        product = productDAO.getProductById(productId);
    } catch (SQLException e) {
        response.sendRedirect("quanlysanpham.jsp?error=Lỗi hệ thống: " + e.getMessage());
        return;
    }

    if (product == null) {
        response.sendRedirect("quanlysanpham.jsp?error=Sản phẩm không tồn tại");
        return;
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa sản phẩm</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            text-align: center;
        }
        .form-container {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            background: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            padding: 10px;
            background: #28a745;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
        }
        button:hover {
            background: #218838;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>

<h2>Chỉnh sửa sản phẩm</h2>

<div class="form-container">
    <form action="editproduct" method="post">
        <input type="hidden" name="id" value="<%= product.getId() %>">

        <label>Tên sản phẩm:</label>
        <input type="text" name="name" value="<%= product.getName() %>" required>

        <label>Thương hiệu:</label>
        <input type="text" name="brand" value="<%= product.getBrand() %>" required>

        <label>Thông số kỹ thuật:</label>
        <input type="text" name="specifications" value="<%= product.getSpecifications() %>" required>

        <label>Giá:</label>
        <input type="number" name="price" value="<%= product.getPrice() %>" required>

        <label>Số lượng trong kho:</label>
        <input type="number" name="stock" value="<%= product.getStock() %>" required>

        <label>URL hình ảnh:</label>
        <input type="text" name="imageUrl" value="<%= product.getImageUrl() %>" required>

        <label>Danh mục:</label>
        <select name="categoryId">
            <option value="1" <%= product.getCategoryId() == 1 ? "selected" : "" %>>CPU</option>
            <option value="2" <%= product.getCategoryId() == 2 ? "selected" : "" %>>GPU</option>
            <option value="3" <%= product.getCategoryId() == 3 ? "selected" : "" %>>RAM</option>
            <option value="4" <%= product.getCategoryId() == 4 ? "selected" : "" %>>Mainboard</option>
            <option value="5" <%= product.getCategoryId() == 5 ? "selected" : "" %>>Case</option>
        </select>

        <button type="submit">Lưu thay đổi</button>
    </form>
</div>

</body>
</html>
