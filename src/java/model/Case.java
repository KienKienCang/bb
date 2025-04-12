/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Case {
    private String id;
    private String name;
    private String formFactor;
    private int fans;
    private String size;
    private double price;
    private String brand;
    private int stock;
    private String imageUrl;

    public Case() {
    }

    public Case(String id, String name, String formFactor, int fans, String size, double price, String brand, int stock, String imageUrl) {
        this.id = id;
        this.name = name;
        this.formFactor = formFactor;
        this.fans = fans;
        this.size = size;
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

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
        return "Case{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", fans=" + fans +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", stock=" + stock +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
