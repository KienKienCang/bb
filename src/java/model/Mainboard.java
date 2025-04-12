/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Mainboard {
    private String id;
    private String name;
    private String chipset;
    private String socket;
    private int ramSlots;
    private int maxRam;
    private double price;
    private String brand;
    private int stock;
    private String imageUrl;

    public Mainboard() {
    }

    public Mainboard(String id, String name, String chipset, String socket, int ramSlots, int maxRam, double price, String brand, int stock, String imageUrl) {
        this.id = id;
        this.name = name;
        this.chipset = chipset;
        this.socket = socket;
        this.ramSlots = ramSlots;
        this.maxRam = maxRam;
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

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public int getRamSlots() {
        return ramSlots;
    }

    public void setRamSlots(int ramSlots) {
        this.ramSlots = ramSlots;
    }

    public int getMaxRam() {
        return maxRam;
    }

    public void setMaxRam(int maxRam) {
        this.maxRam = maxRam;
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
        return "Mainboard{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", chipset='" + chipset + '\'' +
                ", socket='" + socket + '\'' +
                ", ramSlots=" + ramSlots +
                ", maxRam=" + maxRam +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", stock=" + stock +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
