package model;

import java.util.Date;

public class ReaderModel {
    private String maDocGia;
    private String maTheTV;
    private String hoTen;
    private Date ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private String soCCCD;
    private String soSachDaMuon;

    public ReaderModel() {
    }

    public ReaderModel(String maDocGia, String maTheTV, String hoTen, Date ngaySinh, String diaChi, String soDienThoai, String soCCCD, String soSachDaMuon) {
        this.maDocGia = maDocGia;
        this.maTheTV = maTheTV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.soCCCD = soCCCD;
        this.soSachDaMuon = soSachDaMuon;
    }


    public String getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public String getMaTheTV() {
        return maTheTV;
    }

    public void setMaTheTV(String maTheTV) {
        this.maTheTV = maTheTV;
    }

    public String getSoSachDaMuon() {
        return soSachDaMuon;
    }

    public void setSoSachDaMuon(String soSachDaMuon) {
        this.soSachDaMuon = soSachDaMuon;
    }
    
    
}
