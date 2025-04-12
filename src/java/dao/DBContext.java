/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBContext {

  private static final String URL = "jdbc:sqlserver://LAPTOP-6DLI6IKF:1433;databaseName=project;encrypt=true;trustServerCertificate=true;";
    private static final String USERNAME = "kien";
    private static final String PASSWORD = "222823";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Lỗi tải driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

   
    
}
    
    

