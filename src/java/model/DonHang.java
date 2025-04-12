/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
import java.time.LocalDate;
import java.util.List;

public class DonHang {
    private int id;
    private int nguoiDungId;
    private LocalDate ngayDat; // sửa kiểu từ String -> LocalDate
    private double tongTien;
    private String diaChi;
     private List<ChiTietDonHang> chiTietDonHang; // ✅ Thêm danh sách chi tiết đơn hàng
    public DonHang() {
    }

    public DonHang(int id, int nguoiDungId, LocalDate ngayDat, double tongTien, String diaChi) {
        this.id = id;
        this.nguoiDungId = nguoiDungId;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
        this.diaChi = diaChi;
    }
    
    public List<ChiTietDonHang> getChiTietDonHang() {
        return chiTietDonHang;
    }

    public void setChiTietDonHang(List<ChiTietDonHang> chiTietDonHang) {
        this.chiTietDonHang = chiTietDonHang;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNguoiDungId() {
        return nguoiDungId;
    }

    public void setNguoiDungId(int nguoiDungId) {
        this.nguoiDungId = nguoiDungId;
    }

    public LocalDate getNgayDat() { // sửa kiểu trả về thành LocalDate
        return ngayDat;
    }

    public void setNgayDat(LocalDate ngayDat) { // sửa kiểu tham số thành LocalDate
        this.ngayDat = ngayDat;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}

