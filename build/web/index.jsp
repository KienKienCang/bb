<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, java.sql.*, dao.ProductDAO, dao.DBContext, model.Product" %>
<%@ page session="true" %>
<%
    String user = (String) session.getAttribute("user");
%>
<%
    // Debug log
    List<String> debugLog = new ArrayList<>();
    debugLog.add("🚀 [DEBUG] Đang gọi ProductDAO trực tiếp từ index.jsp...");

    // Khởi tạo danh sách sản phẩm
    List<Product> bestSellingProducts = new ArrayList<>();

    // Kết nối database và lấy sản phẩm bán chạy
    try (Connection conn = DBContext.getConnection()) {
        ProductDAO productDAO = new ProductDAO(conn);
        bestSellingProducts = productDAO.getBestSellingProducts();

        debugLog.add("✅ [DEBUG] Lấy được " + (bestSellingProducts == null ? "NULL" : bestSellingProducts.size()) + " sản phẩm bán chạy.");

        if (bestSellingProducts != null) {
            for (Product p : bestSellingProducts) {
                debugLog.add("🔥 " + p.getId() + " - " + p.getName() + " - " + p.getPrice() + "$");
            }
        }
    } catch (SQLException e) {
        debugLog.add("❌ [ERROR] Lỗi SQL: " + e.getMessage());
    }
%>

<html>
<head>
    <title>Trang Chủ - Bán Linh Kiện Máy Tính</title>
    <link rel="stylesheet" type="text/css" href="style.css">
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
            <p><a href="change-password.jsp" style="text-decoration: none; color: blue;">🔒 Đổi mật khẩu</a></p>
    </header>

    <section class="banner">
        <h1>Chào mừng đến với KKC Store</h1>
        <p>🔥 Uy tín hàng đầu Việt Nam!</p>
    </section>
            <section class="categories">
        <h2>Danh mục sản phẩm</h2>
        <div class="category-list">
            <button class="category" onclick="window.location.href='ram.jsp'">💻 RAM</button>
            <button class="category" onclick="window.location.href='cpu.jsp'">⚙️ CPU</button>
            <button class="category" onclick="window.location.href='gpu.jsp'">🎮 GPU</button>
            <button class="category" onclick="window.location.href='mainboard.jsp'">🖥️ Mainboard</button>
            <button class="category" onclick="window.location.href='case.jsp'">📦 Case</button>
        </div>
    </section>
    <section class="featured-products">
        <h2>Sản phẩm bán chạy nhất</h2>
        <div class="product-list">
            <% if (bestSellingProducts == null) { %>
                <p style="color: red;">⚠ `bestSellingProducts` đang NULL!</p>
            <% } else if (bestSellingProducts.isEmpty()) { %>
                <p style="color: red;">⚠ Không có sản phẩm bán chạy.</p>
            <% } else { 
                for (Product product : bestSellingProducts) { %>
                    <div class="product" data-id="<%= product.getId() %>">
                        <img src="<%= product.getImageUrl() %>" alt="<%= product.getName() %>">
                        <h3><%= product.getName() %></h3>
                        <p>Giá: <%= product.getPrice() %> $</p>
                        <button class="buy-now">Mua ngay</button>
                    </div>
            <%  } } %>
        </div>
    </section>

    <!-- Debug Log -->
    

    <footer>
        <p>© 2025 KKC Store. Liên hệ: 011 315 113 | Email: nguyentkienth.com</p>
    </footer>

    <script>
        document.querySelectorAll(".buy-now").forEach(button => {
            button.addEventListener("click", function() {
                let product = this.closest(".product");
                let id = product.dataset.id;
                let url = "product-detail.jsp?id=" + encodeURIComponent(id);
                window.location.href = url;
            });
        });
    </script>

</body>
</html>
