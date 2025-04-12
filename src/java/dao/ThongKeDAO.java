package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThongKeDAO {
    private Connection conn;

    public ThongKeDAO(Connection conn) {
        this.conn = conn;
    }

    // Lấy sản phẩm bán chạy nhất
    public String getSanPhamBanChayNhat() throws SQLException {
        String sql = """
            SELECT TOP 1 sp.name
            FROM chi_tiet_don_hang ctdh
            JOIN product sp ON ctdh.id_sanpham = sp.id
            GROUP BY sp.name
            ORDER BY COUNT(*) DESC;
        """;
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("name");
            }
        }
        return "Không có dữ liệu";
    }

    // Lấy sản phẩm bán ít nhất
    public String getSanPhamBanItNhat() throws SQLException {
        String sql = """
            SELECT TOP 1 sp.name
            FROM chi_tiet_don_hang ctdh
            JOIN product sp ON ctdh.id_sanpham = sp.id
            GROUP BY sp.name
            ORDER BY COUNT(*) ASC;
        """;
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("name");
            }
        }
        return "Không có dữ liệu";
    }

    // Lấy doanh thu trung bình/ngày
    public double getAvgDoanhThuNgay() throws SQLException {
        String sql = """
            WITH AllDays AS (
                SELECT DISTINCT 
                    YEAR(order_date) AS nam,
                    MONTH(order_date) AS thang,
                    DAY(EOMONTH(order_date)) AS so_ngay_trong_thang
                FROM don_hang
            )
            SELECT AVG(tong_doanh_thu / so_ngay_trong_thang) AS avg_doanh_thu_ngay
            FROM (
                SELECT 
                    d.nam, d.thang,
                    COALESCE(SUM(h.price), 0) AS tong_doanh_thu,
                    d.so_ngay_trong_thang
                FROM AllDays d
                LEFT JOIN don_hang h 
                    ON YEAR(h.order_date) = d.nam AND MONTH(h.order_date) = d.thang
                GROUP BY d.nam, d.thang, d.so_ngay_trong_thang
            ) AS subquery;
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("avg_doanh_thu_ngay");
            }
        }
        return 0;
    }
    // Lấy tổng doanh thu
public double getTongDoanhThu() throws SQLException {
    String sql = "SELECT COALESCE(SUM(price), 0) AS tong_doanh_thu FROM don_hang";

    try (PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
            return rs.getDouble("tong_doanh_thu");
        }
    }
    return 0;
}
}
