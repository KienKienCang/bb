<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="java.sql.Connection, java.sql.DriverManager" %>
<%@ page import="dao.CartDAO, dao.DBContext" %>
<%@ page import="java.util.List, model.Cart" %>
<%
    String user = (String) session.getAttribute("user");
%>
<%
    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    CartDAO cartDao = new CartDAO(DBContext.getConnection());
    List<Cart> cartList = cartDao.getCartByUser(userId);
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng</title>
    <link rel="stylesheet" href="css/stylecart.css">
    <style>
        /* reset mặc định */
body {
    background-color: lightgray !important;
}
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
}

body {
    background-color: #f5f5f5;
    color: #333;
}

/* header */
header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background: #222;
    padding: 15px 20px;
    color: white;
}

.logo {
    font-size: 24px;
    font-weight: bold;
}

nav ul {
    list-style: none;
    display: flex;
}

nav ul li {
    margin: 0 15px;
}

nav ul li a {
    color: white;
    text-decoration: none;
}

.search-form {
    display: flex;
    align-items: center;
}

.search-form input {
    padding: 5px;
    margin-right: 5px;
}

.search-form button {
    padding: 5px 10px;
    background-color: #ff6600;
    color: white;
    border: none;
    cursor: pointer;
}

/* container của giỏ hàng */
.cart-container {
    width: 80%;
    margin: 50px auto;
    padding: 20px;
    background: #fff;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
}

/* tiêu đề trang */
.cart-title {
    text-align: center;
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 20px;
}

/* bảng giỏ hàng */
.cart-table {
    width: 100%;
    border-collapse: collapse;
    background-color: white;
}

.cart-table th, .cart-table td {
    border: 1px solid #ddd;
    padding: 10px;
    text-align: center;
}

.cart-table th {
    background-color: #007bff;
    color: white;
}

/* hàng tổng tiền */
.total-row td {
    font-weight: bold;
    font-size: 18px;
    text-align: right;
    padding-right: 20px;
    background-color: #f0f0f0;
}

/* nút xóa */
.remove-btn {
    background-color: red;
    color: white;
    border: none;
    padding: 5px 10px;
    cursor: pointer;
    border-radius: 5px;
    text-decoration: none;
}

.remove-btn:hover {
    background-color: darkred;
}

/* nút thanh toán */
.checkout-btn {
    display: inline-block;
    padding: 10px 20px;
    text-align: center;
    background-color: #28a745;
    color: white;
    font-size: 16px;
    font-weight: bold;
    border: none;
    cursor: pointer;
    border-radius: 5px;
    text-decoration: none;
}

.checkout-btn:hover {
    background-color: #218838;
}

    </style>
</head>
<body>

<!-- header với navbar -->

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

<div class="cart-container">
    <h2 class="cart-title">Giỏ hàng của bạn</h2>

    <% if (cartList == null || cartList.isEmpty()) { %>
        <p>Giỏ hàng trống.</p>
    <% } else { %>
        <table class="cart-table">
            <tr>
                <th>Sản phẩm</th>
                <th>Số lượng</th>
                <th>Giá</th>
                <th>Thành tiền</th>
                <th>Thao tác</th>
            </tr>
            <% double total = 0; %>
            <% for (Cart item : cartList) { %>
            <tr>
                <td><%= item.getProductName() %></td>
                <td>
                    <input type="number" min="1" value="<%= item.getQuantity() %>" 
                           onchange="updateQuantity('<%= item.getProductId() %>', this.value)">
                </td>
                <td><%= item.getPrice() %> $</td>
                    <td><%= item.getQuantity() * item.getPrice() %> $</td>
                    <td>
                        <a href="remove-cart?productId=<%= item.getProductId() %>" class="remove-btn">Xóa</a>
                    </td>
                </tr>
                <% total += item.getQuantity() * item.getPrice(); %>
            <% } %>

            <!-- hàng tổng tiền -->
            <tr class="total-row">
                <td colspan="3">Tổng tiền:</td>
                <td colspan="2"><%= total %> $</td>
            </tr>

            <!-- hàng chứa nút thanh toán -->
            <tr>
                <td colspan="5" style="text-align: center; padding: 10px;">
                    <a href="checkout.jsp" class="checkout-btn">Thanh toán</a>
                </td>
            </tr>
        </table>
    <% } %>
</div>


<script>
    function updateQuantity(productId, quantity) {
        window.location.href = "update-cart?productId=" + productId + "&quantity=" + quantity;
    }
</script>

</body>
</html>
