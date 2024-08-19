package model;

public class PenaltyModel {
    private String maTTV;
    private String DSPhat;
    private Float tongTienPhat;

    public PenaltyModel(String maTTV, String DSPhat, Float tongTienPhat) {
        this.maTTV = maTTV;
        this.DSPhat = DSPhat;
        this.tongTienPhat = tongTienPhat;
    }
    
    public String getMaTTV() {
        return maTTV;
    }

    public void setMaTTV(String maTTV) {
        this.maTTV = maTTV;
    }

    public String getDSPhat() {
        return DSPhat;
    }

    public void setDSPhat(String DSPhat) {
        this.DSPhat = DSPhat;
    }

    public Float getTongTienPhat() {
        return tongTienPhat;
    }

    public void setTongTienPhat(Float tongTienPhat) {
        this.tongTienPhat = tongTienPhat;
    }
    
    
}
