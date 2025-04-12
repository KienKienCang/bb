/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ADMIN
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Case;

public class CaseDAO {
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=yourDatabase";
    private String user = "yourUsername";
    private String password = "yourPassword";

    public Case getCaseById(String id) {
        String sql = "SELECT * FROM pc_case WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Case(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("form_factor"),
                            rs.getInt("fans"),
                            rs.getString("size"),
                            rs.getDouble("price"),
                            rs.getString("brand"),
                            rs.getInt("stock"),
                            rs.getString("image_url")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Case> getAllCases() {
        List<Case> cases = new ArrayList<>();
        String sql = "SELECT * FROM pc_case";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cases.add(new Case(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("form_factor"),
                        rs.getInt("fans"),
                        rs.getString("size"),
                        rs.getDouble("price"),
                        rs.getString("brand"),
                        rs.getInt("stock"),
                        rs.getString("image_url")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cases;
    }

    public boolean addCase(Case pcCase) {
        String sql = "INSERT INTO pc_case (id, name, form_factor, fans, size, price, brand, stock, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pcCase.getId());
            stmt.setString(2, pcCase.getName());
            stmt.setString(3, pcCase.getFormFactor());
            stmt.setInt(4, pcCase.getFans());
            stmt.setString(5, pcCase.getSize());
            stmt.setDouble(6, pcCase.getPrice());
            stmt.setString(7, pcCase.getBrand());
            stmt.setInt(8, pcCase.getStock());
            stmt.setString(9, pcCase.getImageUrl());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCase(Case pcCase) {
        String sql = "UPDATE pc_case SET name = ?, form_factor = ?, fans = ?, size = ?, price = ?, brand = ?, stock = ?, image_url = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pcCase.getName());
            stmt.setString(2, pcCase.getFormFactor());
            stmt.setInt(3, pcCase.getFans());
            stmt.setString(4, pcCase.getSize());
            stmt.setDouble(5, pcCase.getPrice());
            stmt.setString(6, pcCase.getBrand());
            stmt.setInt(7, pcCase.getStock());
            stmt.setString(8, pcCase.getImageUrl());
            stmt.setString(9, pcCase.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCase(String id) {
        String sql = "DELETE FROM pc_case WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

