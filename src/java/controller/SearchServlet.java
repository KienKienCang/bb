package controller;

import dao.DBContext;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Product;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//       ProductDAO productDAO = new ProductDAO();
//String query = request.getParameter("query"); // lấy từ request
//List<Product> products = productDAO.searchProducts(query);
//
//// debug kết quả
//System.out.println("Tìm thấy: " + products.size() + " sản phẩm.");
//
//// đẩy kết quả lên trang jsp
//request.setAttribute("products", products);
//request.getRequestDispatcher("search.jsp").forward(request, response);
ProductDAO productDAO = new ProductDAO();
String query = request.getParameter("query"); // lấy từ request
List<Product> products = productDAO.searchProducts(query);

// debug kết quả
System.out.println("Tìm thấy: " + products.size() + " sản phẩm.");

// đẩy kết quả lên jsp
request.setAttribute("products", products);
request.getRequestDispatcher("search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
