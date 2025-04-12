/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DBContext;
import dao.NguoiDungDAO;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
@WebServlet(urlPatterns = {"/change-password"})
public class ChangePasswordServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePasswordServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String username = (String) session.getAttribute("user");
//        if (username == null) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//
//        String oldPassword = request.getParameter("oldPassword");
//        String newPassword = request.getParameter("newPassword");
//        String confirmPassword = request.getParameter("confirmPassword");
//
//        if (!newPassword.equals(confirmPassword)) {
//            request.setAttribute("error", "Mật khẩu xác nhận không khớp!");
//            request.getRequestDispatcher("change-password.jsp").forward(request, response);
//            return;
//        }
//
//        try (Connection conn = DBContext.getConnection()) {
//            NguoiDungDAO userDAO = new NguoiDungDAO(conn);
//            boolean isUpdated = userDAO.changePassword(username, oldPassword, newPassword);
//            if (isUpdated) {
//                request.setAttribute("message", "Đổi mật khẩu thành công!");
//            } else {
//                request.setAttribute("error", "Mật khẩu cũ không đúng!");
//            }
//        } catch (SQLException e) {
//            request.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
//        }
//
//        request.getRequestDispatcher("change-password.jsp").forward(request, response);
HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("user");
        String oldPassword = request.getParameter("old-password");
        String newPassword = request.getParameter("new-password");
        String confirmPassword = request.getParameter("confirm-password");

        // 🛑 Kiểm tra xem có null không
        if (oldPassword == null || newPassword == null || confirmPassword == null) {
            request.setAttribute("error", "❌ Lỗi: Có trường dữ liệu bị thiếu!");
            request.getRequestDispatcher("change-password.jsp").forward(request, response);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "❌ Mật khẩu mới không trùng khớp!");
            request.getRequestDispatcher("change-password.jsp").forward(request, response);
            return;
        }
        System.out.println("DEBUG: newPassword = " + newPassword);
        try (Connection conn = DBContext.getConnection()) {
            NguoiDungDAO userDAO = new NguoiDungDAO(conn);
            boolean success = userDAO.changePassword(username, oldPassword, newPassword);

            if (success) {
                session.invalidate(); // Xoá session
                response.sendRedirect("login.jsp?message=changed");
            } else {
                request.setAttribute("error", "❌ Mật khẩu cũ không đúng hoặc cập nhật thất bại!");
                request.getRequestDispatcher("change-password.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("error", "❌ Lỗi hệ thống: " + e.getMessage());
            request.getRequestDispatcher("change-password.jsp").forward(request, response);
        }
    }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
