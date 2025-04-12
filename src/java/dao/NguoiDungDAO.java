package dao;

import model.NguoiDung;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    private Connection conn;

    public NguoiDungDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertNguoiDung(NguoiDung nguoiDung) throws SQLException {
        String sql = "INSERT INTO nguoi_dung (tenTaiKhoan, matKhau, sdt, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nguoiDung.getTenTaiKhoan());
            stmt.setString(2, nguoiDung.getMatKhau());
            stmt.setString(3, nguoiDung.getSoDienThoai());
            stmt.setString(4, nguoiDung.getRole()); // Thêm role vào DB
            stmt.executeUpdate();
        }
    }

    public NguoiDung login(String tenTaiKhoan, String matKhau) {
    String sql = "SELECT * FROM nguoi_dung WHERE tenTaiKhoan = ? AND matKhau = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, tenTaiKhoan);
        stmt.setString(2, matKhau);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new NguoiDung(
                    rs.getInt("id"),
                    rs.getString("tenTaiKhoan"),
                    rs.getString("matKhau"),
                    rs.getString("sdt"),
                    rs.getString("role") // lấy role từ database
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    public List<NguoiDung> getAllNguoiDung() throws SQLException {
    List<NguoiDung> list = new ArrayList<>();
    String sql = "SELECT * FROM nguoi_dung";
    try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
        while (rs.next()) {
            NguoiDung nd = new NguoiDung(
                rs.getInt("id"),
                rs.getString("tenTaiKhoan"),
                rs.getString("matKhau"),
                rs.getString("sdt"),
                rs.getString("role")  // Thêm role vào đây
            );
            list.add(nd);
        }
    }
    return list;
}
public boolean deleteUser(int userId) throws SQLException {
    String sql = "DELETE FROM nguoi_dung WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, userId);
        return stmt.executeUpdate() > 0;
    }
}
public boolean updateUser(NguoiDung user) throws SQLException {
    String sql = "UPDATE nguoi_dung SET tenTaiKhoan = ?, matKhau = ?, sdt = ?, role = ? WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, user.getTenTaiKhoan());
        stmt.setString(2, user.getMatKhau());
        stmt.setString(3, user.getSoDienThoai());
        stmt.setString(4, user.getRole());
        stmt.setInt(5, user.getId());
        
        return stmt.executeUpdate() > 0;
    }
}
public NguoiDung getUserById(int userId) throws SQLException {
    String sql = "SELECT * FROM nguoi_dung WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new NguoiDung(
                rs.getInt("id"),
                rs.getString("tenTaiKhoan"),
                rs.getString("matKhau"),
                rs.getString("sdt"),
                rs.getString("role")
            );
        } else {
            System.out.println("❌ Không tìm thấy user với ID: " + userId);
        }
    }
    return null; // Trả về null nếu không tìm thấy user
}
public boolean changePassword(String username, String oldPassword, String newPassword) throws SQLException {
    String sql = "UPDATE nguoi_dung SET matkhau = ? WHERE tentaikhoan = ? AND matkhau = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, newPassword);
        stmt.setString(2, username);
        stmt.setString(3, oldPassword);
        return stmt.executeUpdate() > 0;
    }
}
}
