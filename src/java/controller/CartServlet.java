/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    private CartDAO cartDAO;
@Override
public void init() {
    cartDAO = new CartDAO();
}
    @Override
    

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if ("remove".equals(action)) {
            int cartId = Integer.parseInt(request.getParameter("cartId"));
            cartDAO.removeFromCart(cartId);
            response.sendRedirect("cart");
            return;
        }

        List<Cart> cartList = cartDAO.getCartByUser(userId);
        request.setAttribute("cartList", cartList);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            String productId = request.getParameter("id");
            String quantityStr = request.getParameter("quantity");
            if (quantityStr == null || quantityStr.trim().isEmpty()) {
                request.setAttribute("error", "Số lượng không hợp lệ.");
                request.getRequestDispatcher("cart.jsp").forward(request, response);
                return;
            }
            int quantity = Integer.parseInt(quantityStr);
            String priceStr = request.getParameter("price");
            if (priceStr == null || priceStr.trim().isEmpty()) {
                request.setAttribute("error", "Giá không hợp lệ.");
                request.getRequestDispatcher("cart.jsp").forward(request, response);
                return;
            }
            double price = Double.parseDouble(priceStr);

            if (quantity <= 0 || price < 0) {
                request.setAttribute("error", "Số lượng hoặc giá không hợp lệ.");
                request.getRequestDispatcher("cart.jsp").forward(request, response);
                return;
            }

            cartDAO.addToCart(userId, productId, quantity, price);
            response.sendRedirect("cart");
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Dữ liệu nhập vào không hợp lệ.");
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        }
    }
}