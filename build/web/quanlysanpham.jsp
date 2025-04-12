<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.Product, dao.ProductDAO, dao.DBContext" %>
<%@ page session="true" %>
<%
    // Kiểm tra quyền admin
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("admin")) {
        response.sendRedirect("login.jsp");
        return;
    }

    ProductDAO productDAO = new ProductDAO(DBContext.getConnection());
    List<Product> productList = productDAO.getAllProducts();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý sản phẩm</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f8f8f8; text-align: center; }
        table { width: 80%; margin: 20px auto; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 10px; }
        th { background-color: #ff6600; color: white; }
        .btn { padding: 5px 10px; text-decoration: none; color: white; background-color: #007bff; border-radius: 3px; }
        .btn:hover { background-color: #0056b3; }
        .btn-delete { background-color: red; }
    </style>
</head>
<body>

<h2>Quản lý sản phẩm</h2>
<a href="admin.jsp">🔙 Quay lại trang Admin</a>

<table>
    <tr>
        <th>ID</th>
        <th>Tên sản phẩm</th>
        <th>Giá</th>
        <th>Hình ảnh</th>
        <th>Hành động</th>
    </tr>
    <% for (Product p : productList) { %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getName() %></td>
            <td><%= p.getPrice() %> USD</td>
            <td><img src="<%= p.getImageUrl() %>" width="50"></td>
            <td>
                <a class="btn" href="editproduct.jsp?id=<%= p.getId() %>">Sửa</a>
                <form action="deleteproduct" method="post" onsubmit="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?');">
    <input type="hidden" name="id" value="<%= p.getId() %>">
    <button type="submit" class="btn btn-delete">Xóa</button>
</form>
            </td>
        </tr>
    <% } %>
</table>

<a class="btn" href="addproduct.jsp">➕ Thêm sản phẩm</a>

</body>
</html>
