<%@ page import="model.Product, dao.ProductDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="model.Product" %>
<%@ page import="dao.ProductDAO" %>
<%@ page import="java.sql.*" %>
<%@ page session="true" %>
<%
    String user = (String) session.getAttribute("user");
%>
<%  
    String productId = request.getParameter("id");
    Product product = null;

    if (productId != null && !productId.trim().isEmpty()) {
        ProductDAO dao = new ProductDAO();
        product = dao.getProductById(productId);
    }

    if (product == null) { 
%>
    <h2>không tìm thấy sản phẩm</h2>
<% return; } %>
    
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styleproduct-detail.css"/>
    <title><%= product.getName() %></title>
</head>
<body>
    <header>
        <div class="logo">KKC Store</div>
        <form action="<%= request.getContextPath() %>/search" method="get" class="search-form" style="
    margin-right: -21%">
    <input type="text" name="query" placeholder="Tìm kiếm sản phẩm..." required>
    <button type="submit">Tìm kiếm</button>
</form>
        <nav>
            <ul>
                <li><a href="index.jsp">Trang chủ</a></li>
                
                <li><a href="<%= request.getContextPath() %>/cart">Giỏ hàng</a></li>
                <% if (user == null) { %>
            <li><a href="login.jsp">Đăng nhập</a></li>
            <li><a href="register.jsp">Đăng ký</a></li>
        <% } else { %>
            <li><a href="logout">Đăng xuất (<%= user %>)</a></li>
        <% } %>
            </ul>
        </nav>
    </header>
    <h1><%= product.getName() %></h1>
    <div class="presence">
        <div class="info">   
    <img src="<%= product.getImageUrl() != null ? product.getImageUrl() : "default-image.jpg" %>" alt="Hình ảnh sản phẩm" width="300px">
    <div class="stat">
        <p><strong>Thương hiệu:</strong> <%= product.getBrand() %></p>
    <p><strong>Thông số kỹ thuật:</strong> <%= product.getSpecifications() %></p>
    <p><strong>Giá:</strong> <%= product.getPrice() %> $</p>
    <p><strong>Tồn kho:</strong> <%= product.getStock() %></p>
    </div>
    <form action="cart" method="post">
            <input type="hidden" name="id" value="<%= product.getId() %>">
            <input type="hidden" name="quantity" value="1">
            <input type="hidden" name="price" value="<%= product.getPrice() %>">
            <button class="buy-now" style="margin-left: -318%;
    margin-top: 450px;background-color: #ff6600;
    color: white;
    font-size: 18px;
    padding: 12px 24px;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-weight: bold;
    transition: 0.3s;" type="submit">Mua ngay</button>
        </form>
    </div>
    

        
    
    
</body>
</html>
