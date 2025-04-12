/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
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
import model.Product;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "EditProductServlet", urlPatterns = {"/editproduct"})
public class EditProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String id = request.getParameter("id");
    String name = request.getParameter("name");
    String brand = request.getParameter("brand");
    String specifications = request.getParameter("specifications");
    double price = Double.parseDouble(request.getParameter("price"));
    int stock = Integer.parseInt(request.getParameter("stock"));
    String imageUrl = request.getParameter("imageUrl");
    int categoryId = Integer.parseInt(request.getParameter("categoryId"));

    try (Connection conn = DBContext.getConnection()) {
        ProductDAO productDAO = new ProductDAO(conn);
        Product product = new Product(id, name, brand, specifications, price, stock, imageUrl, categoryId);
        
        // Thực hiện cập nhật sản phẩm
        productDAO.updateProduct(product);
        
        // Debug xem đã cập nhật xong chưa
        System.out.println("✅ Cập nhật sản phẩm thành công: " + name);
        
        // Chuyển hướng về trang quản lý sản phẩm
        response.sendRedirect("quanlysanpham.jsp");

    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("error", "Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        request.getRequestDispatcher("editproduct.jsp?id=" + id).forward(request, response);
    }
    }
}