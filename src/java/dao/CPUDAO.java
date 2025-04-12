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
import model.CPU;

public class CPUDAO {
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=YourDatabaseName;encrypt=true;trustServerCertificate=true";
    private final String user = "sa";
    private final String password = "your_password";

    public List<CPU> getAllCPUs() {
        List<CPU> cpuList = new ArrayList<>();
        String sql = "SELECT * FROM cpu";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                CPU cpu = new CPU(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("cores"),
                        rs.getInt("threads"),
                        rs.getDouble("base_clock"),
                        rs.getDouble("boost_clock"),
                        rs.getInt("tdp"),
                        rs.getString("socket"),
                        rs.getDouble("price"),
                        rs.getString("brand"),
                        rs.getInt("stock"),
                        rs.getString("image_url")
                );
                cpuList.add(cpu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cpuList;
    }

    public CPU getCPUById(String id) {
    String sql = "SELECT * FROM cpu WHERE id = ?";
    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new CPU(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("cores"),
                        rs.getInt("threads"),
                        rs.getDouble("base_clock"),
                        rs.getDouble("boost_clock"),
                        rs.getInt("tdp"),
                        rs.getString("socket"),
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



    public boolean insertCPU(CPU cpu) {
        String sql = "INSERT INTO cpu (id, name, cores, threads, base_clock, boost_clock, tdp, socket, price, brand, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpu.getId());
            stmt.setString(2, cpu.getName());
            stmt.setInt(3, cpu.getCores());
            stmt.setInt(4, cpu.getThreads());
            stmt.setDouble(5, cpu.getBaseClock());
            stmt.setDouble(6, cpu.getBoostClock());
            stmt.setInt(7, cpu.getTdp());
            stmt.setString(8, cpu.getSocket());
            stmt.setDouble(9, cpu.getPrice());
            stmt.setString(10, cpu.getBrand());
            stmt.setString(11, cpu.getImageUrl());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCPU(CPU cpu) {
        String sql = "UPDATE cpu SET name = ?, cores = ?, threads = ?, base_clock = ?, boost_clock = ?, tdp = ?, socket = ?, price = ?, brand = ?, image_url = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpu.getName());
            stmt.setInt(2, cpu.getCores());
            stmt.setInt(3, cpu.getThreads());
            stmt.setDouble(4, cpu.getBaseClock());
            stmt.setDouble(5, cpu.getBoostClock());
            stmt.setInt(6, cpu.getTdp());
            stmt.setString(7, cpu.getSocket());
            stmt.setDouble(8, cpu.getPrice());
            stmt.setString(9, cpu.getBrand());
            stmt.setString(10, cpu.getImageUrl());
            stmt.setString(11, cpu.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCPU(String id) {
        String sql = "DELETE FROM cpu WHERE id = ?";
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
