/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Ram {
    private String id;
    private String brand;
    private String name;
    private int capacity;
    private int speed;
    private String type;
    private double price;
    private int stock;
    private String imageUrl;

    public Ram() {
    }

    public Ram(String id, String brand, String name, int capacity, int speed, String type, double price, int stock, String imageUrl) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.capacity = capacity;
        this.speed = speed;
        this.type = type;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
    }

    // getter & setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Ram{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", speed=" + speed +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
