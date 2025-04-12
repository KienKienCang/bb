package controller;

import dao.DBContext;
import dao.NguoiDungDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
        String tenTaiKhoan = request.getParameter("tenTaiKhoan");
        String matKhau = request.getParameter("matKhau");
        String rememberMe = request.getParameter("rememberMe");

        if (tenTaiKhoan == null || matKhau == null || tenTaiKhoan.isEmpty() || matKhau.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DBContext.getConnection()) {
            String sql = "SELECT * FROM nguoi_dung WHERE tenTaiKhoan = ? AND matKhau = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tenTaiKhoan);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");
                String role = rs.getString("role");

                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                session.setAttribute("user", tenTaiKhoan);
                session.setAttribute("role", role);

                // Nếu người dùng chọn "Nhớ mật khẩu"
                if ("on".equals(rememberMe)) {
                    Cookie userCookie = new Cookie("rememberedUsername", tenTaiKhoan);
                    Cookie passCookie = new Cookie("rememberedPassword", matKhau);

                    userCookie.setMaxAge(60 * 60 * 24 * 7); // Lưu trong 7 ngày
                    passCookie.setMaxAge(60 * 60 * 24 * 7);

                    response.addCookie(userCookie);
                    response.addCookie(passCookie);
                } else {
                    // Xóa cookie nếu không chọn "Nhớ mật khẩu"
                    Cookie userCookie = new Cookie("rememberedUsername", "");
                    Cookie passCookie = new Cookie("rememberedPassword", "");
                    userCookie.setMaxAge(0);
                    passCookie.setMaxAge(0);
                    response.addCookie(userCookie);
                    response.addCookie(passCookie);
                }

                // Điều hướng dựa trên vai trò
                if ("admin".equals(role)) {
                    response.sendRedirect("admin.jsp");
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else {
                request.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi hệ thống!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}