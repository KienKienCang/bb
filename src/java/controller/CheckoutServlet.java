/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CartDAO;
import dao.ChiTietDonHangDAO;
import dao.DBContext;
import dao.DonHangDAO;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.ChiTietDonHang;
import model.DonHang;

/**
 *
 * @author ADMIN
 */
//@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
//public class CheckoutServlet extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CheckoutServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CheckoutServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    
//            protected void doPost(HttpServletRequest request, HttpServletResponse response)
//                throws ServletException, IOException {
//                response.setContentType("text/html;charset=UTF-8");
//    PrintWriter out = response.getWriter();
//
//    out.println("<h3><b>Debug:</b> Đã vào doPost của CheckoutServlet</h3>");
//
//    // Kiểm tra session
//    HttpSession session = request.getSession(false);
//    if (session == null || session.getAttribute("userId") == null) {
//        out.println("<h3><b>Debug:</b> Không tìm thấy session hoặc userId</h3>");
//        return;
//    }
//
//    int userId = (int) session.getAttribute("userId");
//    out.println("<h3><b>Debug:</b> userId = " + userId + "</h3>");
//
//    // Lấy giỏ hàng từ session
//    List<Cart> cartList = (List<Cart>) session.getAttribute("cart");
//    if (cartList == null || cartList.isEmpty()) {
//        out.println("<h3><b>Debug:</b> Session không chứa giỏ hàng, thử lấy từ database...</h3>");
//
//        try (Connection conn = DBContext.getConnection()) {
//            CartDAO cartDao = new CartDAO(conn);
//            cartList = cartDao.getCartByUser(userId);
//        } catch (SQLException e) {
//            out.println("<h3><b>Debug:</b> Lỗi khi lấy giỏ hàng từ database - " + e.getMessage() + "</h3>");
//            return;
//        }
//
//        if (cartList == null || cartList.isEmpty()) {
//            out.println("<h3><b>Debug:</b> Giỏ hàng vẫn trống sau khi lấy từ database!</h3>");
//            return;
//        }
//    }
//
//    out.println("<h3><b>Debug:</b> Giỏ hàng có " + cartList.size() + " sản phẩm</h3>");
//
//    // Lấy thông tin địa chỉ từ form
//    String diaChi = request.getParameter("diaChi");
//    if (diaChi == null || diaChi.trim().isEmpty()) {
//        out.println("<h3><b>Debug:</b> Địa chỉ giao hàng không hợp lệ!</h3>");
//        return;
//    }
//    out.println("<h3><b>Debug:</b> Địa chỉ giao hàng: " + diaChi + "</h3>");
//
//    // Tính tổng tiền
//    double tongTien = cartList.stream().mapToDouble(c -> c.getPrice() * c.getQuantity()).sum();
//    out.println("<h3><b>Debug:</b> Tổng tiền: " + tongTien + " VND</h3>");
//
//    // Thực hiện đặt hàng
//    try (Connection conn = DBContext.getConnection()) {
//        DonHangDAO donHangDAO = new DonHangDAO(conn);
//        ChiTietDonHangDAO chiTietDonHangDAO = new ChiTietDonHangDAO(conn);
//
//        // 1. Tạo đơn hàng mới
//        DonHang donHang = new DonHang(0, userId, LocalDate.now(), tongTien, diaChi);
//        int donHangId = donHangDAO.insertDonHang(donHang);
//
//        if (donHangId == -1) {
//            out.println("<h3><b>Debug:</b> Lỗi: Không thể tạo đơn hàng!</h3>");
//            return;
//        }
//
//        out.println("<h3><b>Debug:</b> Đơn hàng đã tạo thành công! ID: " + donHangId + "</h3>");
//
//        // 2. Thêm chi tiết đơn hàng
//        List<ChiTietDonHang> chiTietList = new ArrayList<>();
//        for (Cart item : cartList) {
//            chiTietList.add(new ChiTietDonHang(0, donHangId, item.getProductId(), item.getQuantity(), item.getPrice()));
//        }
//        chiTietDonHangDAO.insertChiTietDonHang(chiTietList);
//        out.println("<h3><b>Debug:</b> Chi tiết đơn hàng đã được lưu!</h3>");
//
//        // 3. Xóa giỏ hàng sau khi đặt hàng
//        CartDAO cartDAO = new CartDAO(conn);
//        cartDAO.clearCart(userId);
//        session.removeAttribute("cart");
//
//        out.println("<h3><b>Debug:</b> Giỏ hàng đã bị xóa!</h3>");
//
//        // Chuyển hướng sang trang cảm ơn
//        request.setAttribute("message", "Đặt hàng thành công!");
//        request.setAttribute("orderId", donHangId); // Gán mã đơn hàng vào request
//        request.setAttribute("diaChi", diaChi); // Gán địa chỉ giao hàng vào request
//        request.setAttribute("total", tongTien); // Gán tổng tiền vào request
//
//
//        request.getRequestDispatcher("thankyou.jsp").forward(request, response);
//    } catch (SQLException e) {
//        out.println("<h3><b>Debug:</b> Lỗi khi xử lý đơn hàng: " + e.getMessage() + "</h3>");
//    }
//    
//        }
//    
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 📝 Khởi tạo danh sách log để debug trên giao diện
        List<String> debugLog = new ArrayList<>();
        debugLog.add("Debug: 🛒 Đã vào doPost của CheckoutServlet");

        // 🛑 Kiểm tra session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            debugLog.add("Debug: ❌ Không tìm thấy session hoặc userId");
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        debugLog.add("Debug: 👤 userId = " + userId);

        // ✅ Lấy giỏ hàng
        List<Cart> cartList = (List<Cart>) session.getAttribute("cart");
        if (cartList == null || cartList.isEmpty()) {
            debugLog.add("Debug: 🛒 Giỏ hàng trống, lấy từ database...");
            try (Connection conn = DBContext.getConnection()) {
                CartDAO cartDao = new CartDAO(conn);
                cartList = cartDao.getCartByUser(userId);
            } catch (SQLException e) {
                debugLog.add("Debug: ❌ Lỗi khi lấy giỏ hàng từ database - " + e.getMessage());
                request.setAttribute("debugLog", debugLog);
                request.getRequestDispatcher("debug.jsp").forward(request, response);
                return;
            }

            if (cartList == null || cartList.isEmpty()) {
                debugLog.add("Debug: ❌ Giỏ hàng vẫn trống sau khi lấy từ database!");
                response.sendRedirect("cart.jsp");
                return;
            }
        }

        debugLog.add("Debug: 🛍 Giỏ hàng có " + cartList.size() + " sản phẩm");

        // ✅ Lấy địa chỉ từ form
        String diaChi = request.getParameter("diaChi");
        if (diaChi == null || diaChi.trim().isEmpty()) {
            debugLog.add("Debug: ❌ Địa chỉ giao hàng không hợp lệ!");
            request.setAttribute("debugLog", debugLog);
            request.getRequestDispatcher("debug.jsp").forward(request, response);
            return;
        }
        debugLog.add("Debug: 🏠 Địa chỉ giao hàng: " + diaChi);

        // ✅ Tính tổng tiền
        double tongTien = cartList.stream().mapToDouble(c -> c.getPrice() * c.getQuantity()).sum();
        debugLog.add("Debug: 💰 Tổng tiền: " + tongTien + " VND");

        try (Connection conn = DBContext.getConnection()) {
            DonHangDAO donHangDAO = new DonHangDAO(conn);
            ChiTietDonHangDAO chiTietDonHangDAO = new ChiTietDonHangDAO(conn);
            ProductDAO productDAO = new ProductDAO(conn);

            // ✅ Kiểm tra stock trước khi đặt hàng
            debugLog.add("Debug: 🔍 Kiểm tra stock...");
            for (Cart item : cartList) {
                if (!productDAO.isStockAvailable(item.getProductId(), item.getQuantity())) { 
                    debugLog.add("Debug: ❌ Sản phẩm " + item.getProductName() + " đã hết hàng!");
                    request.setAttribute("debugLog", debugLog);
                    request.getRequestDispatcher("debug.jsp").forward(request, response);
                    return;
                }
            }
            debugLog.add("Debug: ✅ Tất cả sản phẩm đều còn hàng!");

            // ✅ Tạo danh sách productIds và quantities để lưu vào DB
            List<String> productIds = new ArrayList<>();

            List<Integer> quantities = new ArrayList<>();
            for (Cart item : cartList) {
                productIds.add(item.getProductId());
                quantities.add(item.getQuantity());
            }

            // ✅ Tạo đơn hàng
            int donHangId = donHangDAO.insertDonHang(userId, productIds, quantities, tongTien, diaChi);
            if (donHangId == -1) {
                debugLog.add("Debug: ❌ Không thể tạo đơn hàng!");
                request.setAttribute("debugLog", debugLog);
                request.getRequestDispatcher("debug.jsp").forward(request, response);
                return;
            }
            debugLog.add("Debug: ✅ Đơn hàng đã tạo thành công! ID: " + donHangId);

            // ✅ Giảm stock sản phẩm
            
            debugLog.add("Debug: 🔻 Đã cập nhật số lượng sản phẩm!");

            // ✅ Xóa giỏ hàng sau khi đặt hàng
            CartDAO cartDAO = new CartDAO(conn);
            cartDAO.clearCart(userId);
            session.removeAttribute("cart");
            debugLog.add("Debug: 🗑 Giỏ hàng đã bị xóa!");

            // ✅ Chuyển hướng sang trang cảm ơn
            request.setAttribute("message", "Đặt hàng thành công!");
            request.setAttribute("orderId", donHangId);
            request.setAttribute("diaChi", diaChi);
            request.setAttribute("total", tongTien);
            debugLog.add("Debug: ✅ Chuyển hướng sang thankyou.jsp");

            request.setAttribute("debugLog", debugLog);
            request.getRequestDispatcher("thankyou.jsp").forward(request, response);
        } catch (SQLException e) {
            debugLog.add("Debug: ❌ Lỗi SQL - " + e.getMessage());
            request.setAttribute("debugLog", debugLog);
            request.getRequestDispatcher("debug.jsp").forward(request, response);
        }
    }
}
