<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="true" %>
<%@ page import="java.util.List, model.Cart, dao.CartDAO, dao.DBContext" %>

<%
    Integer userId = (Integer) session.getAttribute("userId");
    if (userId == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    CartDAO cartDao = new CartDAO(DBContext.getConnection());
    List<Cart> cartList = cartDao.getCartByUser(userId);

    double total = 0;
    for (Cart item : cartList) {
        total += item.getQuantity() * item.getPrice();
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh toán</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }
        .checkout-container {
            width: 50%;
            margin: 20px auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
        }
        th {
            background: #007bff;
            color: white;
        }
        .checkout-btn {
            margin-top: 10px;
            padding: 10px 20px;
            background: green;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
</head>

<body>
    <div class="checkout-container">
        <h2>Xác nhận đơn hàng</h2>

        <% if (cartList == null || cartList.isEmpty()) { %>
            <p>Giỏ hàng trống. <a href="index.jsp">Mua hàng ngay</a></p>
        <% } else { %>
            <form action="checkout" method="post">
                <label for="diaChi">Địa chỉ giao hàng:</label>
                <input type="text" id="diaChi" name="diaChi" required>

                <h3>Danh sách sản phẩm:</h3>
                <table>
                    <tr>
                        <th>Sản phẩm</th>
                        <th>Số lượng</th>
                        <th>Giá</th>
                        <th>Thành tiền</th>
                    </tr>
                    <% for (Cart item : cartList) { %>
                        <tr>
                            <td><%= item.getProductName() %></td>
                            <td><%= item.getQuantity() %></td>
                            <td><%= item.getPrice() %> VND</td>
                            <td><%= item.getQuantity() * item.getPrice() %> VND</td>
                        </tr>
                    <% } %>
                    <tr>
                        <td colspan="3"><b>Tổng tiền:</b></td>
                        <td><b><%= total %> VND</b></td>
                    </tr>
                </table>

                <button type="submit" class="checkout-btn">Đặt hàng</button>
            </form>
        <% } %>
    </div>
</body>
</html>