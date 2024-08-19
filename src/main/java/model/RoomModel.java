package model;

import java.util.Date;

public class RoomModel {
    private String maMuon;
    private String maPhong;
    private String maTTV;
    private Date ngayMuon;
    private String tgBatDau;
    private String tgKetThuc;
    private String trangThaiSD;

    public RoomModel(String maMuon, String maPhong, String maTTV, Date ngayMuon, String tgBatDau, String tgKetThuc, String trangThaiSD) {
        this.maMuon = maMuon;
        this.maPhong = maPhong;
        this.maTTV = maTTV;
        this.ngayMuon = ngayMuon;
        this.tgBatDau = tgBatDau;
        this.tgKetThuc = tgKetThuc;
        this.trangThaiSD = trangThaiSD;
    }

    public String getMaMuon() {
        return maMuon;
    }

    public void setMaMuon(String maMuon) {
        this.maMuon = maMuon;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaTTV() {
        return maTTV;
    }

    public void setMaTTV(String maTTV) {
        this.maTTV = maTTV;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public String getTgBatDau() {
        return tgBatDau;
    }

    public void setTgBatDau(String tgBatDau) {
        this.tgBatDau = tgBatDau;
    }

    public String getTgKetThuc() {
        return tgKetThuc;
    }

    public void setTgKetThuc(String tgKetThuc) {
        this.tgKetThuc = tgKetThuc;
    }

    public String getTrangThaiSD() {
        return trangThaiSD;
    }

    public void setTrangThaiSD(String trangThaiSD) {
        this.trangThaiSD = trangThaiSD;
    }
    
    
}
