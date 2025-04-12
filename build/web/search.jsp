<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, model.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%
    String user = (String) session.getAttribute("user");
%>
<% String keyword = (String) request.getAttribute("keyword"); %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    
    <title>Kết quả tìm kiếm</title>
    
    <style>
/* reset */
* {
/* reset */


/* nền trang */


/* tiêu đề kết quả tìm kiếm */
h1 {
    text-align: center;
    font-size: 28px;
    color: #222;
    margin-bottom: 20px;
}

/* danh sách sản phẩm */
.product-list {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
}

/* từng sản phẩm */
.product-item {
    background: white;
    padding: 15px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    text-align: center;
    width: 250px;
    transition: 0.3s;
}

.product-item:hover {
    transform: translateY(-5px);
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

/* ảnh sản phẩm */
.product-item img {
    width: 180px;
    height: auto;
    margin-bottom: 10px;
}

/* thông tin sản phẩm */
.product-item h3 {
    font-size: 18px;
    color: #222;
    margin-bottom: 5px;
}

.product-item p {
    font-size: 14px;
    color: #555;
    margin-bottom: 8px;
}

/* nút xem chi tiết */
.product-item a {
    display: inline-block;
    text-decoration: none;
    background: #ff6600;
    color: white;
    padding: 8px 12px;
    border-radius: 5px;
    font-weight: bold;
    transition: 0.3s;
}

.product-item a:hover {
    background: #e65c00;
}

/* khi không có sản phẩm */
p.no-result {
    text-align: center;
    font-size: 18px;
    color: #888;
    margin-top: 20px;
}

   </style>  
        
    <link rel="stylesheet" href="stylesearch.css">
</head>
<body>
    <header style="margin-top: 0%;">
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
    <h1 style="margin-top:1%">Kết quả tìm kiếm </h1>

    <c:choose>
        <c:when test="${empty products}">
            <p>Không tìm thấy sản phẩm nào.</p>
        </c:when>
        <c:otherwise>
            <div class="product-list">
                <c:forEach var="product" items="${products}">
    <div class="product-item" data-id="${product.id}">
        <img src="<%= request.getContextPath() %>/${product.imageUrl}" alt="${product.name}">
        <h3>${product.name}</h3>
        <p>Thương hiệu: ${product.brand}</p>
        <p>Giá: ${product.price} USD</p>
        <button class="buy-now">Mua ngay</button>
    </div>
</c:forEach>

            </div>
        </c:otherwise>
    </c:choose>
</body>
<footer>
        <p>© 2025 KKC Store. Liên hệ: 011 315 113 | Email: nguyentkienth.com</p>
    </footer>
</html>
<script>
    document.querySelectorAll(".buy-now").forEach(button => {
        button.addEventListener("click", function() {
            let product = this.closest(".product-item"); // Lấy class đúng
            let id = product.dataset.id; // Lấy id từ thuộc tính data-id
            console.log("id được lấy:", id); // Debug kiểm tra id
            let url = "product-detail.jsp?id=" + encodeURIComponent(id);
            console.log("url chuyển hướng:", url);
            window.location.href = url; // Chuyển hướng đến trang chi tiết sản phẩm
        });
    });
</script>
