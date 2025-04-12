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
import model.GPU;

public class GPUDAO {
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=yourDatabase";
    private String user = "yourUsername";
    private String password = "yourPassword";

    public GPU getGPUById(String id) {
        String sql = "SELECT * FROM gpu WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new GPU(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getInt("vram"),
                            rs.getString("memory_type"),
                            rs.getInt("cuda_cores"),
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

    public List<GPU> getAllGPUs() {
        List<GPU> gpus = new ArrayList<>();
        String sql = "SELECT * FROM gpu";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                gpus.add(new GPU(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("vram"),
                        rs.getString("memory_type"),
                        rs.getInt("cuda_cores"),
                        rs.getDouble("price"),
                        rs.getString("brand"),
                        rs.getInt("stock"),
                        rs.getString("image_url")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gpus;
    }

    public boolean addGPU(GPU gpu) {
        String sql = "INSERT INTO gpu (id, name, vram, memory_type, cuda_cores, price, brand, stock, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, gpu.getId());
            stmt.setString(2, gpu.getName());
            stmt.setInt(3, gpu.getVram());
            stmt.setString(4, gpu.getMemoryType());
            stmt.setInt(5, gpu.getCudaCores());
            stmt.setDouble(6, gpu.getPrice());
            stmt.setString(7, gpu.getBrand());
            stmt.setInt(8, gpu.getStock());
            stmt.setString(9, gpu.getImageUrl());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateGPU(GPU gpu) {
        String sql = "UPDATE gpu SET name = ?, vram = ?, memory_type = ?, cuda_cores = ?, price = ?, brand = ?, stock = ?, image_url = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, gpu.getName());
            stmt.setInt(2, gpu.getVram());
            stmt.setString(3, gpu.getMemoryType());
            stmt.setInt(4, gpu.getCudaCores());
            stmt.setDouble(5, gpu.getPrice());
            stmt.setString(6, gpu.getBrand());
            stmt.setInt(7, gpu.getStock());
            stmt.setString(8, gpu.getImageUrl());
            stmt.setString(9, gpu.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteGPU(String id) {
        String sql = "DELETE FROM gpu WHERE id = ?";
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
