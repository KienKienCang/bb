package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import dao.ProductDAO;
import dao.DBContext;
import dao.NguoiDungDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import model.NguoiDung;


/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EditUserServlet", urlPatterns = {"/edituser"})
public class EditUserServlet extends HttpServlet {

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    String idParam = request.getParameter("id");
    if (idParam == null || idParam.isEmpty()) {
        out.println("<h3 style='color:red;'>❌ Lỗi: Không có ID người dùng!</h3>");
        response.sendRedirect("quanlynguoidung.jsp?error=NoUserId");
        return;
    }

    int userId = Integer.parseInt(idParam);
    out.println("<h3 style='color:blue;'>🟢 Nhận được ID: " + userId + "</h3>");

    NguoiDung user = null;
    try (Connection conn = DBContext.getConnection()) {
        NguoiDungDAO userDAO = new NguoiDungDAO(conn);
        user = userDAO.getUserById(userId);
    } catch (SQLException e) {
        e.printStackTrace();
        out.println("<h3 style='color:red;'>❌ Lỗi truy vấn database: " + e.getMessage() + "</h3>");
        response.sendRedirect("quanlynguoidung.jsp?error=DatabaseError");
        return;
    }

    if (user == null) {
        out.println("<h3 style='color:red;'>❌ Không tìm thấy người dùng với ID: " + userId + "</h3>");
        response.sendRedirect("quanlynguoidung.jsp?error=UserNotFound");
        return;
    }

    out.println("<h3 style='color:green;'>✔ Người dùng tìm thấy: " + user.getTenTaiKhoan() + "</h3>");
    request.setAttribute("user", user);
    request.getRequestDispatcher("edituser.jsp").forward(request, response);
}   
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    int id = Integer.parseInt(request.getParameter("id"));
    String tenTaiKhoan = request.getParameter("tenTaiKhoan");
    String matKhau = request.getParameter("matKhau");
    String soDienThoai = request.getParameter("sdt");
    String role = request.getParameter("role");

    out.println("<h3 style='color:blue;'>🟢 Nhận request cập nhật người dùng</h3>");
    out.println("<p>ID: " + id + "</p>");
    out.println("<p>Tên tài khoản: " + tenTaiKhoan + "</p>");
    out.println("<p>Mật khẩu: " + matKhau + "</p>");
    out.println("<p>SĐT: " + soDienThoai + "</p>");
    out.println("<p>Quyền: " + role + "</p>");

    NguoiDung user = new NguoiDung(id, tenTaiKhoan, matKhau, soDienThoai, role);

    try (Connection conn = DBContext.getConnection()) {
        NguoiDungDAO userDAO = new NguoiDungDAO(conn);
        boolean updated = userDAO.updateUser(user);

        if (updated) {
            out.println("<h3 style='color:green;'>✔ Cập nhật thành công!</h3>");
            response.sendRedirect("quanlynguoidung.jsp"); // **Chuyển trang sau khi cập nhật**
        } else {
            out.println("<h3 style='color:red;'>❌ Cập nhật thất bại!</h3>");
        }
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<h3 style='color:red;'>❌ Lỗi khi cập nhật người dùng: " + e.getMessage() + "</h3>");
    }
}
    
}