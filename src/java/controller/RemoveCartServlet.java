package controller;

import dao.CartDAO;
import dao.DBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Servlet xử lý việc xóa sản phẩm khỏi giỏ hàng
 */
@WebServlet(name = "RemoveCartServlet", urlPatterns = {"/remove-cart"})
public class RemoveCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Kiểm tra user đăng nhập
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy ID sản phẩm cần xóa từ request
        String cartItemIdParam = request.getParameter("id");

        if (cartItemIdParam == null || cartItemIdParam.isEmpty()) {
            response.sendRedirect("cart.jsp?error=InvalidItem");
            return;
        }

        int cartItemId = Integer.parseInt(cartItemIdParam);

        try (Connection conn = DBContext.getConnection()) {
            CartDAO cartDAO = new CartDAO(conn);

            // Gọi phương thức xóa sản phẩm khỏi giỏ hàng
            boolean removed = cartDAO.removeCartItem(cartItemId, userId);

            if (removed) {
                response.sendRedirect("cart.jsp?success=ItemRemoved");
            } else {
                response.sendRedirect("cart.jsp?error=RemoveFailed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("cart.jsp?error=DatabaseError");
        }
    }
}
