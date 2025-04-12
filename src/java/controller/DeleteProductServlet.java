/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProductDAO;
import dao.DBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/deleteproduct"})
public class DeleteProductServlet extends HttpServlet {
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String productId = request.getParameter("id"); // Lấy ID sản phẩm từ request

        try (Connection conn = DBContext.getConnection()) {
            ProductDAO productDAO = new ProductDAO(conn);
            boolean success = productDAO.deleteProduct(productId);

            if (success) {
                request.setAttribute("message", "Xóa sản phẩm thành công!");
            } else {
                request.setAttribute("error", "Không thể xóa sản phẩm!");
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Lỗi hệ thống khi xóa sản phẩm: " + e.getMessage());
        }

        // Chuyển hướng về trang quản lý sản phẩm
        response.sendRedirect("quanlysanpham.jsp");
    
}
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("id"); // Lấy ID sản phẩm từ request

        try (Connection conn = DBContext.getConnection()) {
            ProductDAO productDAO = new ProductDAO(conn);
            boolean success = productDAO.deleteProduct(productId);

            if (success) {
                request.setAttribute("message", "Xóa sản phẩm thành công!");
            } else {
                request.setAttribute("error", "Không thể xóa sản phẩm!");
            }
        } catch (SQLException e) {
            request.setAttribute("error", "Lỗi hệ thống khi xóa sản phẩm: " + e.getMessage());
        }

        // Chuyển hướng về trang quản lý sản phẩm
        response.sendRedirect("quanlysanpham.jsp");
    }
}
