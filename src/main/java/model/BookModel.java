package model;

public class BookModel {
    private String maSach;
    private String tieuDe;
    private String tenTacGia;
    private String tenTheLoai;
    private String soLuong;
    private String soLuongHienTai;
    private String giaTien;
    private String tenNXB;

    public BookModel() {
    }

    public BookModel(String maSach, String tieuDe, String tenTacGia, String tenTheLoai, String soLuong, String soLuongHienTai, String giaTien, String tenNXB) {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.tenTacGia = tenTacGia;
        this.tenTheLoai = tenTheLoai;
        this.soLuong = soLuong;
        this.soLuongHienTai = soLuongHienTai;
        this.giaTien = giaTien;
        this.tenNXB = tenNXB;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getSoLuongHienTai() {
        return soLuongHienTai;
    }

    public void setSoLuongHienTai(String soLuongHienTai) {
        this.soLuongHienTai = soLuongHienTai;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

}
