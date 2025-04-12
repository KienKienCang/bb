package controller;

import dao.DBContext;
import dao.DonHangDAO;
import dao.ChiTietDonHangDAO;
import model.DonHang;
import model.ChiTietDonHang;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OrderHistoryServlet", urlPatterns = {"/order-history"})

public class OrderHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp"); // Nếu chưa đăng nhập thì quay về trang đăng nhập
            return;
        }

        int userId = (int) session.getAttribute("userId");

        try (Connection conn = DBContext.getConnection()) {
            DonHangDAO donHangDAO = new DonHangDAO(conn);
            List<DonHang> orderList = donHangDAO.getOrdersByUserId(userId);
            
            request.setAttribute("orderList", orderList);
            request.getRequestDispatcher("order-history.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Lỗi khi lấy danh sách đơn hàng: " + e.getMessage(), e);
        }
    }
}