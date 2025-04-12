<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page session="true" %>
<%
    String user = (String) session.getAttribute("user");
%>
<html>
<head>
    <title>RAM - KKC Store</title>
    <link rel="stylesheet" type="text/css" href="styleram.css">
    <style>
        .grid {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
            margin-top: 20px;
        }
        .product {
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 15px;
            text-align: center;
            width: 250px;
            background-color: #f9f9f9;
        }
        .product img {
            max-width: 100%;
            height: auto;
            border-radius: 5px;
        }
        .buy-now {
            background-color: #ff8800;
            color: white;
            border: none;
            padding: 10px;
            cursor: pointer;
            border-radius: 5px;
            margin-top: 10px;
        }
        .buy-now:hover {
            background-color: #cc6e00;
        }
        .sort-options {
            text-align: center;
            margin: 20px 0;
        }
        .sort-options select {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        .search-bar {
    text-align: center;
    margin: 20px 0;
}

.search-bar input[type="text"] {
    width: 300px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
}

.search-bar button {
    padding: 10px 15px;
    border: none;
    background-color: #ff8800;
    color: white;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
}

.search-bar button:hover {
    background-color: #cc6e00;
}

    </style>
</head>
<body>
    <!-- Thanh điều hướng -->
    <header>
        <div class="logo">KKC Store</div>
        <form action="<%= request.getContextPath() %>/search" method="get" class="search-form" style="
    margin-right: -21%" >
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

    <!-- Lựa chọn sắp xếp -->
    <section class="sort-options">
        <form method="get" action="ram.jsp">
            <label for="sort">Sắp xếp theo:</label>
            <select name="sort" id="sort" onchange="this.form.submit()">
                <option value="">Mặc định</option>
                <option value="price_asc">Giá tăng dần</option>
                <option value="price_desc">Giá giảm dần</option>
                <option value="name_asc">Tên A-Z</option>
                <option value="name_desc">Tên Z-A</option>
            </select>
        </form>
    </section>

    <!-- Danh sách RAM -->
    <section class="product-list">
        <h2>Danh sách RAM</h2>
        <div class="grid">
            <% 
                String url = "jdbc:sqlserver://LAPTOP-6DLI6IKF:1433;databaseName=project;encrypt=true;trustServerCertificate=true;";
                String dbUser = "kien";  // Đổi tên biến 'user' thành 'dbUser'
                String password = "222823";
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    conn = DriverManager.getConnection(url, dbUser, password);
                    String query = "SELECT id, name, price, image_url FROM product WHERE category_id = 5";
                    ps = conn.prepareStatement(query);
                    rs = ps.executeQuery();
            %>
            <% while (rs.next()) { %>
    <div class="product" data-id="<%= rs.getString("id") %>">
        <img src="<%= rs.getString("image_url") %>" alt="<%= rs.getString("name") %>">
        <h3><%= rs.getString("name") %></h3>
        <p>Giá: <%= rs.getDouble("price") %> $</p>
        <button class="buy-now">Mua ngay</button>
    </div>
<% } %>
            
            <%
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (rs != null) rs.close();
                    if (ps != null) ps.close();
                    if (conn != null) conn.close();
                }
            %>
        </div>
    </section>

    <!-- Footer -->
    <footer>
        <p>© 2025 KKC Store. Liên hệ: 011 315 113 | Email: nguyentkienth.com</p>
    </footer>
</body>
</html>
<script>
    document.querySelectorAll(".buy-now").forEach(button => {
        button.addEventListener("click", function() {
            let product = this.closest(".product");
            let id = product.dataset.id;
            console.log("id được lấy:", id); // kiểm tra id
            let url = "product-detail.jsp?id=" + encodeURIComponent(id);
            console.log("url chuyển hướng:", url);
            window.location.href = url;
        });
    });
</script>