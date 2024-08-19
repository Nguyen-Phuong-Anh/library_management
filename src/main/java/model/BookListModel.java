package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookListModel {
    private String maSach;
    private String tenSach;
    private Integer soLuong;

    @JsonCreator
    public BookListModel(
            @JsonProperty("maSach") String maSach,
            @JsonProperty("tenSach") String tenSach,
            @JsonProperty("soLuong") Integer soLuong
    ) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.soLuong = soLuong;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }
   
   
}
