package model;

public class NguoiDung {
    private int id;
    private String tenTaiKhoan;
    private String matKhau;
    private String soDienThoai;
    private String role; // Thêm thuộc tính role

    public NguoiDung() {}

    public NguoiDung(int id, String tenTaiKhoan, String matKhau, String soDienThoai, String role) {
        this.id = id;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "id=" + id +
                ", tenTaiKhoan='" + tenTaiKhoan + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}