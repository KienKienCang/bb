/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
import java.sql.Timestamp;

public class Cart {
    private int id;
    private int userId;
    private String productId;
    private String productName;
    private int quantity;
    private double price;
    private Timestamp createdAt;

    public Cart() {
    }

    public Cart(int id, int userId, String productId, String productName, int quantity, double price, Timestamp createdAt) {
    this.id = id;
    this.userId = userId;
    this.productId = productId;
    this.productName = productName;
    this.quantity = quantity;
    this.price = price;
    this.createdAt = createdAt;
}
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}