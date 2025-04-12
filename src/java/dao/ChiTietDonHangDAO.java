    package dao;

    import model.ChiTietDonHang;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;
    import java.sql.*;
    public class ChiTietDonHangDAO {
        private Connection conn;

        public ChiTietDonHangDAO(Connection conn) {
            this.conn = conn;
        }

        public void insertChiTietDonHang(List<ChiTietDonHang> chiTietList) throws SQLException {
            String sql = "INSERT INTO chi_tiet_don_hang (id_donhang, id_sanpham, so_luong, don_gia) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                for (ChiTietDonHang ctdh : chiTietList) {
                    stmt.setInt(1, ctdh.getDonHangId());
                    stmt.setString(2, ctdh.getSanPhamId());
                    stmt.setInt(3, ctdh.getSoLuong());
                    stmt.setDouble(4, ctdh.getDonGia());
                    stmt.addBatch(); // thêm vào batch để giảm số lần truy vấn DB
                }
                stmt.executeBatch(); // thực hiện tất cả truy vấn cùng lúc
            }
        }
        public List<ChiTietDonHang> getChiTietDonHangById(int donHangId) throws SQLException {
            List<ChiTietDonHang> list = new ArrayList<>();
            String sql = "SELECT * FROM chi_tiet_don_hang WHERE id_donhang = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, donHangId);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    ChiTietDonHang ctdh = new ChiTietDonHang(
                        rs.getInt("id"),
                        rs.getInt("id_donhang"),
                        rs.getString("id_sanpham"),
                        rs.getInt("so_luong"),
                        rs.getDouble("don_gia")
                    );
                    list.add(ctdh);
                }
            }
            return list;
        }
    }
