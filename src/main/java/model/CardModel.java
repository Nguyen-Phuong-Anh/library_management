package model;

import java.util.Date;

public class CardModel {
    private String maTheTV;
    private String loaiThe;
    private String maDocGia;
    private Date ngayPhatHanh;
    private Date ngayHetHan;
    private Boolean trangThai;
    private Float soTienPhat;
    private String nguoiTao;

    public CardModel() {
    }

    public CardModel(String maTheTV, String loaiThe, String maDocGia, Date ngayPhatHanh, Date ngayHetHan, Boolean trangThai, Float soTienPhat, String nguoiTao) {
        this.maTheTV = maTheTV;
        this.loaiThe = loaiThe;
        this.maDocGia = maDocGia;
        this.ngayPhatHanh = ngayPhatHanh;
        this.ngayHetHan = ngayHetHan;
        this.trangThai = trangThai;
        this.soTienPhat = soTienPhat;
        this.nguoiTao = nguoiTao;
    }

    public String getMaTheTV() {
        return maTheTV;
    }

    public void setMaTheTV(String maTheTV) {
        this.maTheTV = maTheTV;
    }

    public String getLoaiThe() {
        return loaiThe;
    }

    public void setLoaiThe(String loaiThe) {
        this.loaiThe = loaiThe;
    }

    public String getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public Date getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(Date ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Float getSoTienPhat() {
        return soTienPhat;
    }

    public void setSoTienPhat(Float soTienPhat) {
        this.soTienPhat = soTienPhat;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }
    
}
