package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cart;

public class CartDAO {
    private Connection conn;

    public CartDAO() {
        try {
            this.conn = DBContext.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kết nối CSDL: " + e.getMessage());
        }
    }

    public CartDAO(Connection conn) {
        this.conn = conn;
    }
    
    // thêm sản phẩm vào giỏ hàng
    public boolean addToCart(int userId, String productId, int quantity, double price) {
        String sql = "INSERT INTO cart (user_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, productId);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, price);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("lỗi khi thêm vào giỏ hàng: " + e.getMessage());
        }
        return false;
    }

    // lấy danh sách giỏ hàng của một user
    public List<Cart> getCartByUser(int userId) {
    List<Cart> cartList = new ArrayList<>();
    String sql = "SELECT c.id, c.user_id, c.product_id, p.name, c.quantity, c.price, c.created_at " +
             "FROM cart c JOIN product p ON c.product_id = p.id " +
             "WHERE c.user_id = ?";

    try (Connection conn = DBContext.getConnection();
     PreparedStatement stmt = conn.prepareStatement(sql)) {
    stmt.setInt(1, userId);
    try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            cartList.add(new Cart(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("product_id"),
                rs.getString("name"), // lấy product_name từ bảng product
                rs.getInt("quantity"),
                rs.getDouble("price"),
                rs.getTimestamp("created_at")
            ));
        }
    }
} catch (SQLException e) {
    System.err.println("lỗi khi lấy giỏ hàng: " + e.getMessage());
}
    return cartList;
}

    // cập nhật số lượng sản phẩm trong giỏ hàng
    public boolean updateQuantity(int cartId, int quantity) {
        String sql = "UPDATE cart SET quantity = ? WHERE id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, cartId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("lỗi khi cập nhật số lượng: " + e.getMessage());
        }
        return false;
    }

    // xóa sản phẩm khỏi giỏ hàng
    public boolean removeFromCart(int cartId) {
        String sql = "DELETE FROM cart WHERE id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cartId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("lỗi khi xóa sản phẩm khỏi giỏ hàng: " + e.getMessage());
        }
        return false;
    }

    // xóa toàn bộ giỏ hàng của một user
    public boolean clearCart(int userId) {
        String sql = "DELETE FROM cart WHERE user_id = ?";
        try (Connection conn = DBContext.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("lỗi khi xóa giỏ hàng: " + e.getMessage());
        }
        return false;
    }
    public boolean removeCartItem(int cartItemId, int userId) throws SQLException {
    String sql = "DELETE FROM cart WHERE id = ? AND user_id = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, cartItemId);
        stmt.setInt(2, userId);
        
        return stmt.executeUpdate() > 0;
    }
}
    public boolean updateCartQuantity(int userId, String productId, int quantity) throws SQLException {
    String sql = "UPDATE cart SET quantity = ? WHERE user_id = ? AND product_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, quantity);
        stmt.setInt(2, userId);
        stmt.setString(3, productId);
        return stmt.executeUpdate() > 0;
    }
}
}
