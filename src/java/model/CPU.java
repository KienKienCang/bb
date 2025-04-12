/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class CPU {
    private String id;
    private String name;
    private int cores;
    private int threads;
    private double baseClock;
    private double boostClock;
    private int tdp;
    private String socket;
    private double price;
    private String brand;
    private int stock;
    private String imageUrl;

    public CPU() {
    }

    public CPU(String id, String name, int cores, int threads, double baseClock, double boostClock, int tdp, String socket, double price, String brand, int stock, String imageUrl) {
        this.id = id;
        this.name = name;
        this.cores = cores;
        this.threads = threads;
        this.baseClock = baseClock;
        this.boostClock = boostClock;
        this.tdp = tdp;
        this.socket = socket;
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

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public double getBaseClock() {
        return baseClock;
    }

    public void setBaseClock(double baseClock) {
        this.baseClock = baseClock;
    }

    public double getBoostClock() {
        return boostClock;
    }

    public void setBoostClock(double boostClock) {
        this.boostClock = boostClock;
    }

    public int getTdp() {
        return tdp;
    }

    public void setTdp(int tdp) {
        this.tdp = tdp;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
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
        return "CPU{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cores=" + cores +
                ", threads=" + threads +
                ", baseClock=" + baseClock +
                ", boostClock=" + boostClock +
                ", tdp=" + tdp +
                ", socket='" + socket + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", stock=" + stock +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
