/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class GPU {
    private String id;
    private String name;
    private int vram;
    private String memoryType;
    private int cudaCores;
    private double price;
    private String brand;
    private int stock;
    private String imageUrl;

    public GPU() {
    }

    public GPU(String id, String name, int vram, String memoryType, int cudaCores, double price, String brand, int stock, String imageUrl) {
        this.id = id;
        this.name = name;
        this.vram = vram;
        this.memoryType = memoryType;
        this.cudaCores = cudaCores;
        this.price = price;
        this.brand = brand;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVram() {
        return vram;
    }

    public void setVram(int vram) {
        this.vram = vram;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public int getCudaCores() {
        return cudaCores;
    }

    public void setCudaCores(int cudaCores) {
        this.cudaCores = cudaCores;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
        return "GPU{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", vram=" + vram +
                ", memoryType='" + memoryType + '\'' +
                ", cudaCores=" + cudaCores +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", stock=" + stock +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
