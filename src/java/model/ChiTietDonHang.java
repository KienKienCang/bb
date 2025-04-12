package model;

public class ChiTietDonHang {
    private int id;
    private int donHangId;
    private String sanPhamId;
    private int soLuong;
    private double donGia;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(int id, int donHangId, String sanPhamId, int soLuong, double donGia) {
        this.id = id;
        this.donHangId = donHangId;
        this.sanPhamId = sanPhamId;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDonHangId() {
        return donHangId;
    }

    public void setDonHangId(int donHangId) {
        this.donHangId = donHangId;
    }

    public String getSanPhamId() {
        return sanPhamId;
    }

    public void setSanPhamId(String sanPhamId) {
        this.sanPhamId = sanPhamId;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}