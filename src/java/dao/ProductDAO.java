package dao;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import java.sql.*;
public class ProductDAO {
    private Connection conn;

    // Constructor nhận Connection từ DBContext
    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    public ProductDAO() {
    }
    
    // danh sách từ khóa cần thay thế
    private static final String[][] KEYWORD_REPLACEMENTS = {
        {"case", "pc_case"}
    };
    
    public boolean isStockAvailable(String productId, int quantity) throws SQLException {
    String sql = "SELECT stock FROM product WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, productId); // ✅ Chuyển từ setInt -> setString
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int stock = rs.getInt("stock");
            return stock >= quantity;  // ✅ Kiểm tra hàng còn hay không
        }
    }
    return false;  // ❌ Không tìm thấy sản phẩm hoặc hết hàng
}
   
   
    public boolean reduceStock(String productId, int quantity) throws SQLException {
        String sql = "UPDATE product SET stock = stock - ? WHERE id = ? AND stock >= ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setString(2, productId);
            stmt.setInt(3, quantity);  // Đảm bảo stock không bị âm
            return stmt.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        }
    }
    public List<Product> getAllProducts() throws SQLException {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
        
            while (rs.next()) {
                Product product = new Product(
                        rs.getString("id"), // <-- Đây là int
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getString("specifications"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("image_url"),
                        rs.getInt("category_id")
                );
                productList.add(product);
            }
        }
        return productList;
    }

    // xử lý keyword trước khi tìm kiếm
    private String processKeyword(String keyword) {
        for (String[] replacement : KEYWORD_REPLACEMENTS) {
            if (keyword.equalsIgnoreCase(replacement[0])) {
                return replacement[1]; // thay thế nếu khớp
            }
        }
        return keyword;
    }

    // tìm category_id theo tên danh mục
    private Integer getCategoryIdByName(String categoryName) {
        String sql = "SELECT id FROM category WHERE LOWER(name) = LOWER(?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, categoryName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // không tìm thấy
    }

    public List<Product> searchProducts(String keyword) {
        List<Product> products = new ArrayList<>();

        // xử lý keyword trước khi tìm kiếm
        keyword = processKeyword(keyword);

        Integer categoryId = getCategoryIdByName(keyword); // kiểm tra keyword có phải tên danh mục không

        String sql;
        if (categoryId != null) {
            // nếu keyword là tên danh mục, tìm theo category_id
            sql = "SELECT * FROM product WHERE category_id = ?";
        } else {
            // nếu keyword là tên sản phẩm hoặc thương hiệu
            sql = "SELECT * FROM product WHERE LOWER(name) LIKE LOWER(?) OR LOWER(brand) LIKE LOWER(?)";
        }

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (categoryId != null) {
                ps.setInt(1, categoryId);
            } else {
                String searchKeyword = "%" + keyword + "%";
                ps.setString(1, searchKeyword);
                ps.setString(2, searchKeyword);
            }

            // log truy vấn để debug
            System.out.println("SQL Query: " + sql);
            System.out.println("Keyword: " + keyword);

            // thực thi truy vấn
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("brand"),
                    rs.getString("specifications"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),
                    rs.getString("image_url"),
                    rs.getInt("category_id")
                ));
            }

            // kiểm tra nếu không có sản phẩm nào
            if (products.isEmpty()) {
                System.out.println("Không tìm thấy sản phẩm nào.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
    public Product getProductById(String productId) {
    String sql = "SELECT * FROM product WHERE id = ?";
    try (Connection conn = DBContext.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, productId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Product(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("brand"),
                rs.getString("specifications"),
                rs.getDouble("price"),
                rs.getInt("stock"),
                rs.getString("image_url"),
                rs.getInt("category_id")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null; // nếu không tìm thấy sản phẩm
}
    public boolean deleteProduct(String productId) throws SQLException {
    String sql = "DELETE FROM product WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, productId);
        return stmt.executeUpdate() > 0;
    }
}
    public boolean updateProduct(Product product) throws SQLException {
    String sql = "UPDATE product SET name = ?, brand = ?, specifications = ?, price = ?, stock = ?, image_url = ?, category_id = ? WHERE id = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, product.getName());
        stmt.setString(2, product.getBrand());
        stmt.setString(3, product.getSpecifications());
        stmt.setDouble(4, product.getPrice());
        stmt.setInt(5, product.getStock());
        stmt.setString(6, product.getImageUrl());
        stmt.setInt(7, product.getCategoryId());
        stmt.setString(8, product.getId());
        
        return stmt.executeUpdate() > 0;
    }
}
public void insertProduct(Product product) throws SQLException {
    String sql = "INSERT INTO product (id, name, brand, specifications, price, stock, image_url, category_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, product.getId());
        stmt.setString(2, product.getName());
        stmt.setString(3, product.getBrand());
        stmt.setString(4, product.getSpecifications());
        stmt.setDouble(5, product.getPrice());
        stmt.setInt(6, product.getStock());
        stmt.setString(7, product.getImageUrl());
        stmt.setInt(8, product.getCategoryId());
        stmt.executeUpdate();
    }
}
public List<Product> getBestSellingProducts() throws SQLException {
    List<Product> productList = new ArrayList<>();
    
    String sql = """
        SELECT TOP 2 
            p.id, 
            p.name, 
            p.image_url, 
            p.price, 
            SUM(c.so_luong) AS total_sold
        FROM product p
        JOIN chi_tiet_don_hang c 
            ON p.id = c.id_sanpham
        GROUP BY p.id, p.name, p.image_url, p.price
        ORDER BY total_sold DESC;
    """;

    System.out.println("🔍 [DEBUG] Chạy SQL: " + sql); // Log SQL ra console

    try (PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String imageUrl = rs.getString("image_url");
            double price = rs.getDouble("price");

            Product product = new Product(id, name, "", "", price, 0, imageUrl, 0);
            productList.add(product);
            
            System.out.println("🔥 [DATA] " + id + " - " + name + " - Giá: " + price);
        }
    }
    
    System.out.println("✅ [DEBUG] Tổng số sản phẩm lấy được: " + productList.size());
    return productList;
}

public static void main(String[] args) {
        try (Connection conn = DBContext.getConnection()) {
            ProductDAO productDAO = new ProductDAO(conn);
            System.out.println("🚀 [DEBUG] Bắt đầu test lấy sản phẩm bán chạy...");
            
            List<Product> bestSellingProducts = productDAO.getBestSellingProducts();
            
            if (bestSellingProducts.isEmpty()) {
                System.out.println("⚠ Không có sản phẩm nào bán chạy.");
            } else {
                System.out.println("✅ Danh sách sản phẩm bán chạy:");
                for (Product p : bestSellingProducts) {
                    System.out.println("👉 " + p.getId() + " - " + p.getName() + " - " + p.getPrice() + "$");
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Lỗi kết nối database: " + e.getMessage());
        }
    }
}
