package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PenaltyListModel {
    private String tenLoi;
    private Integer soLuong;
    private Float tienPhat;
    private String trangThai;

    @JsonCreator
    public PenaltyListModel(
            @JsonProperty("tenLoi") String tenLoi,
            @JsonProperty("soLuong") Integer soLuong,
            @JsonProperty("tienPhat") Float tienPhat,
            @JsonProperty("trangThai") String trangThai
    ) {
        this.tenLoi = tenLoi;
        this.soLuong = soLuong;
        this.tienPhat = tienPhat;
        this.trangThai = trangThai;
    }

    public String getTenLoi() {
        return tenLoi;
    }

    public void setTenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Float getTienPhat() {
        return tienPhat;
    }

    public void setTienPhat(Float tienPhat) {
        this.tienPhat = tienPhat;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

     
}
