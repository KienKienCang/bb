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
import model.Ram;


public class RamDAO {
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=yourDatabase";
    private String user = "yourUsername";
    private String password = "yourPassword";

    public Ram getRamById(String id) {
        String sql = "SELECT * FROM ram WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Ram(
                            rs.getString("id"),
                            rs.getString("brand"),
                            rs.getString("name"),
                            rs.getInt("capacity"),
                            rs.getInt("speed"),
                            rs.getString("type"),
                            rs.getDouble("price"),
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

    public List<Ram> getAllRams() {
        List<Ram> ramList = new ArrayList<>();
        String sql = "SELECT * FROM ram";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ramList.add(new Ram(
                        rs.getString("id"),
                        rs.getString("brand"),
                        rs.getString("name"),
                        rs.getInt("capacity"),
                        rs.getInt("speed"),
                        rs.getString("type"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("image_url")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ramList;
    }

    public boolean addRam(Ram ram) {
        String sql = "INSERT INTO ram (id, brand, name, capacity, speed, type, price, stock, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ram.getId());
            stmt.setString(2, ram.getBrand());
            stmt.setString(3, ram.getName());
            stmt.setInt(4, ram.getCapacity());
            stmt.setInt(5, ram.getSpeed());
            stmt.setString(6, ram.getType());
            stmt.setDouble(7, ram.getPrice());
            stmt.setInt(8, ram.getStock());
            stmt.setString(9, ram.getImageUrl());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateRam(Ram ram) {
        String sql = "UPDATE ram SET brand = ?, name = ?, capacity = ?, speed = ?, type = ?, price = ?, stock = ?, image_url = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ram.getBrand());
            stmt.setString(2, ram.getName());
            stmt.setInt(3, ram.getCapacity());
            stmt.setInt(4, ram.getSpeed());
            stmt.setString(5, ram.getType());
            stmt.setDouble(6, ram.getPrice());
            stmt.setInt(7, ram.getStock());
            stmt.setString(8, ram.getImageUrl());
            stmt.setString(9, ram.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteRam(String id) {
        String sql = "DELETE FROM ram WHERE id = ?";
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
