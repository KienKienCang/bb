<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm Sản Phẩm</title>
</head>
<body>
    <h2>Thêm Sản Phẩm</h2>
    
    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
        <p style="color: red;"><%= error %></p>
    <% } %>
    
    <form action="addproduct" method="post">
        <label for="id">ID Sản phẩm:</label>
        <input type="text" name="id" required><br>
        
        <label for="name">Tên sản phẩm:</label>
        <input type="text" name="name" required><br>
        
        <label for="brand">Thương hiệu:</label>
        <input type="text" name="brand" required><br>
        
        <label for="specifications">Thông số:</label>
        <input type="text" name="specifications" required><br>
        
        <label for="price">Giá:</label>
        <input type="number" name="price" step="0.01" required><br>
        
        <label for="stock">Số lượng:</label>
        <input type="number" name="stock" required><br>
        
        <label for="imageUrl">Hình ảnh URL:</label>
        <input type="text" name="imageUrl" required><br>
        
        <label for="categoryId">Loại sản phẩm:</label>
        <input type="number" name="categoryId" required><br>
        
        <button type="submit">Thêm sản phẩm</button>
    </form>

    <a href="quanlysanpham.jsp">Quay lại</a>
</body>
</html>