<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cảm ơn bạn đã mua hàng</title>
    <link rel="stylesheet" href="stylethankyou.css"> <!-- CSS riêng cho trang này -->
</head>
<body>

<header>
    <div class="logo">KKC Store</div>
    <nav>
        <ul>
            <li><a href="index.jsp">Trang chủ</a></li>
            <li><a href="cart.jsp">Giỏ hàng</a></li>
            <% if (session.getAttribute("user") == null) { %>
                <li><a href="login.jsp">Đăng nhập</a></li>
                <li><a href="register.jsp">Đăng ký</a></li>
            <% } else { %>
                <li><a href="logout">Đăng xuất (<%= session.getAttribute("user") %>)</a></li>
            <% } %>
        </ul>
    </nav>
</header>

<div class="thankyou-container">
    <h2>🎉 Cảm ơn bạn đã mua hàng! 🎉</h2>
    <p>Đơn hàng của bạn đã được xác nhận. Chúng tôi sẽ xử lý đơn hàng và giao hàng sớm nhất có thể.</p>

    <h3>Thông tin đơn hàng:</h3>
    <ul>
        <li><strong>Mã đơn hàng:</strong> <%= request.getAttribute("orderId") %></li>
        <li><strong>Địa chỉ giao hàng:</strong> <%= request.getAttribute("diaChi") %></li>
        <li><strong>Tổng tiền:</strong> <%= request.getAttribute("total") %> VND</li>
    </ul>

    <p>Bạn có thể theo dõi đơn hàng trong phần <a href="order-history.jsp">Lịch sử đơn hàng</a>.</p>

    <a href="index.jsp" class="btn-home">Tiếp tục mua sắm</a>
</div>

</body>
</html>
