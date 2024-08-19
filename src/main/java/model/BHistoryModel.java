package model;

import java.util.Date;

public class BHistoryModel {
    private String maMuonTra;
    private String maTheTV;
    private String DSSachMuon;
    private Date ngayMuon;
    private Date ngayHetHan;
    private Date ngayTra;
    private Boolean trangThai;
    private Integer soLanGiaHan;
    private String nguoiThucHien;

    public BHistoryModel() {
    }

    public BHistoryModel(String maMuonTra, String maTheTV, String DSSachMuon, Date ngayMuon, Date ngayHetHan, Date ngayTra, Boolean trangThai, Integer soLanGiaHan, String nguoiThucHien) {
        this.maMuonTra = maMuonTra;
        this.maTheTV = maTheTV;
        this.DSSachMuon = DSSachMuon;
        this.ngayMuon = ngayMuon;
        this.ngayHetHan = ngayHetHan;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
        this.soLanGiaHan = soLanGiaHan;
        this.nguoiThucHien = nguoiThucHien;
    }

    public String getMaMuonTra() {
        return maMuonTra;
    }

    public void setMaMuonTra(String maMuonTra) {
        this.maMuonTra = maMuonTra;
    }

    public String getMaTheTV() {
        return maTheTV;
    }

    public void setMaTheTV(String maTheTV) {
        this.maTheTV = maTheTV;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public String getDSSachMuon() {
        return DSSachMuon;
    }

    public void setDSSachMuon(String DSSachMuon) {
        this.DSSachMuon = DSSachMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getSoLanGiaHan() {
        return soLanGiaHan;
    }

    public void setSoLanGiaHan(Integer soLanGiaHan) {
        this.soLanGiaHan = soLanGiaHan;
    }

    public String getNguoiThucHien() {
        return nguoiThucHien;
    }

    public void setNguoiThucHien(String nguoiThucHien) {
        this.nguoiThucHien = nguoiThucHien;
    }
    
    
}
