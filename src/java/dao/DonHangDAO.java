/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ADMIN
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DonHang;

public class DonHangDAO {
    private Connection conn;

    public DonHangDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean isStockAvailable(int productId, int quantity) throws SQLException {
        String sql = "SELECT stock FROM product WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int stock = rs.getInt("stock");
                return stock >= quantity; // ✅ Trả về true nếu còn hàng, false nếu hết hàng
            }
        }
        return false;
    }
    
    public boolean reduceStock(String productId, int quantity) throws SQLException {
    String sql = "UPDATE product SET stock = stock - ? WHERE id = ? AND stock >= ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, quantity);
        stmt.setString(2, productId);  // ✅ Chuyển từ setInt -> setString
        stmt.setInt(3, quantity);
        return stmt.executeUpdate() > 0;
    }
}


    public int insertDonHang(int userId, List<String> productIds, List<Integer> quantities, double totalPrice, String diaChi) throws SQLException {
    String sqlOrder = "INSERT INTO don_hang (id_khachhang, order_date, price, dia_chi) VALUES (?, GETDATE(), ?, ?)";

    try (PreparedStatement stmtOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS)) {

        // ✅ 1. Thêm đơn hàng mới
        stmtOrder.setInt(1, userId);
        stmtOrder.setDouble(2, totalPrice);
        stmtOrder.setString(3, diaChi);
        stmtOrder.executeUpdate();

        // ✅ 2. Lấy ID của đơn hàng vừa tạo
        ResultSet rs = stmtOrder.getGeneratedKeys();
        int orderId = -1;
        if (rs.next()) {
            orderId = rs.getInt(1);
        }

        // ✅ 3. Trừ stock bằng `reduceStock()`
        ProductDAO productDAO = new ProductDAO(conn);
        for (int i = 0; i < productIds.size(); i++) {
            productDAO.reduceStock(productIds.get(i), quantities.get(i));
        }

        return orderId; // ✅ Trả về ID đơn hàng mới
    }
}


    public List<DonHang> getAllDonHang() throws SQLException {
    List<DonHang> list = new ArrayList<>();
    String sql = "SELECT id, id_khachhang, order_date, price, dia_chi FROM don_hang"; // Đúng tên cột trong DB
    try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            DonHang dh = new DonHang(
                rs.getInt("id"),
                rs.getInt("id_khachhang"), // Đổi từ nguoiDungId -> id_khachhang
                rs.getDate("order_date").toLocalDate(),
                rs.getDouble("price"),
                rs.getString("dia_chi")
            );
            list.add(dh);
        }
    }
    return list;
}
public List<DonHang> getOrdersByUserId(int userId) throws SQLException {
        List<DonHang> orderList = new ArrayList<>();
        String sql = "SELECT * FROM don_hang WHERE id_khachhang = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                DonHang order = new DonHang(
                    rs.getInt("id"),
                    rs.getInt("id_khachhang"),
                    rs.getDate("order_date").toLocalDate(),
                    rs.getDouble("price"),
                    rs.getString("dia_chi")
                );
                orderList.add(order);
            }
        }
        return orderList;
    }
    
    
}
