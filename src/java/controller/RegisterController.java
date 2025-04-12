package controller;

import dao.DBContext;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tenTaiKhoan = request.getParameter("tenTaiKhoan");
        String matKhau = request.getParameter("matKhau");
        String soDienThoai = request.getParameter("soDienThoai");

        try (Connection conn = DBContext.getConnection()) {
            String sql = "INSERT INTO nguoi_dung (tenTaiKhoan, matKhau, sdt) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, tenTaiKhoan);
                stmt.setString(2, matKhau);
                stmt.setString(3, soDienThoai);
                stmt.executeUpdate();
            }
            response.sendRedirect("register-success.jsp");
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi ra console server
            System.err.println("Lỗi SQL: " + e.getMessage());
            System.err.println("Mã lỗi SQL: " + e.getErrorCode());
            System.err.println("SQL State: " + e.getSQLState());

            // Xử lý lỗi thân thiện với người dùng
            String errorMessage = "Đăng ký không thành công. Vui lòng thử lại!";
            if (e.getMessage().contains("Duplicate entry")) {
                errorMessage = "Tên tài khoản đã tồn tại. Vui lòng chọn tên khác.";
            } else if (e.getMessage().contains("Unknown column")) {
                errorMessage = "Hệ thống đang gặp lỗi. Vui lòng thử lại sau.";
            }

            request.setAttribute("errorMessage", errorMessage);
            request.setAttribute("detailedError", e.getMessage()); // Gửi thêm lỗi chi tiết về JSP
            request.getRequestDispatcher("register-fail.jsp").forward(request, response);
        }
    }
}