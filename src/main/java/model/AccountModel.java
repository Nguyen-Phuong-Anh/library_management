package model;

/**
 *
 * @author admin
 */
public class AccountModel {
    private String username;
    private String password;
    private String role;
    private String maNV;

    public AccountModel(String username, String password, String role, String maNV) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.maNV = maNV;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
    
    
}
