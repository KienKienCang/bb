package dao;

import model.Product;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("🚀 [DEBUG] Bắt đầu test lấy sản phẩm bán chạy...");
        
        try (Connection conn = DBContext.getConnection()) {
            ProductDAO productDAO = new ProductDAO(conn);
            
            // 🔥 Lấy danh sách sản phẩm bán chạy
            List<Product> bestSellingProducts = productDAO.getBestSellingProducts();
            
            if (bestSellingProducts == null) {
                System.out.println("❌ [ERROR] bestSellingProducts NULL!");
            } else if (bestSellingProducts.isEmpty()) {
                System.out.println("⚠ bestSellingProducts rỗng!");
            } else {
                System.out.println("✅ Lấy được " + bestSellingProducts.size() + " sản phẩm bán chạy.");
                for (Product p : bestSellingProducts) {
                    System.out.println("👉 " + p.getId() + " - " + p.getName() + " - Giá: " + p.getPrice() + "$");
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ [ERROR] Lỗi khi lấy sản phẩm bán chạy: " + e.getMessage());
        }
    }
}
