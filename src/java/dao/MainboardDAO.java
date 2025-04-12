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
import model.Mainboard;

public class MainboardDAO {
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=yourDatabase";
    private String user = "yourUsername";
    private String password = "yourPassword";

    public Mainboard getMainboardById(String id) {
        String sql = "SELECT * FROM mainboard WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Mainboard(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("chipset"),
                            rs.getString("socket"),
                            rs.getInt("ram_slots"),
                            rs.getInt("max_ram"),
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

    public List<Mainboard> getAllMainboards() {
        List<Mainboard> mainboards = new ArrayList<>();
        String sql = "SELECT * FROM mainboard";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                mainboards.add(new Mainboard(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("chipset"),
                        rs.getString("socket"),
                        rs.getInt("ram_slots"),
                        rs.getInt("max_ram"),
                        rs.getDouble("price"),
                        rs.getString("brand"),
                        rs.getInt("stock"),
                        rs.getString("image_url")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mainboards;
    }

    public boolean addMainboard(Mainboard mainboard) {
        String sql = "INSERT INTO mainboard (id, name, chipset, socket, ram_slots, max_ram, price, brand, stock, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mainboard.getId());
            stmt.setString(2, mainboard.getName());
            stmt.setString(3, mainboard.getChipset());
            stmt.setString(4, mainboard.getSocket());
            stmt.setInt(5, mainboard.getRamSlots());
            stmt.setInt(6, mainboard.getMaxRam());
            stmt.setDouble(7, mainboard.getPrice());
            stmt.setString(8, mainboard.getBrand());
            stmt.setInt(9, mainboard.getStock());
            stmt.setString(10, mainboard.getImageUrl());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateMainboard(Mainboard mainboard) {
        String sql = "UPDATE mainboard SET name = ?, chipset = ?, socket = ?, ram_slots = ?, max_ram = ?, price = ?, brand = ?, stock = ?, image_url = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mainboard.getName());
            stmt.setString(2, mainboard.getChipset());
            stmt.setString(3, mainboard.getSocket());
            stmt.setInt(4, mainboard.getRamSlots());
            stmt.setInt(5, mainboard.getMaxRam());
            stmt.setDouble(6, mainboard.getPrice());
            stmt.setString(7, mainboard.getBrand());
            stmt.setInt(8, mainboard.getStock());
            stmt.setString(9, mainboard.getImageUrl());
            stmt.setString(10, mainboard.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteMainboard(String id) {
        String sql = "DELETE FROM mainboard WHERE id = ?";
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
