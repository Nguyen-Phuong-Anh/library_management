package dao;

import com.mycompany.demo1.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.AccountModel;

public class AccountDao {
    public ArrayList<String[]> getData() {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            String sql = "SELECT * " +
             "FROM \"accounts\" AS a " +
             "JOIN \"staff\" AS s ON a.\"maNhanVien\" = s.\"maNhanVien\"";

            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String maNhanVien = res.getString("maNhanVien");
                    String username = res.getString("username");
                    String password = res.getString("password");
                    String role = res.getString("role");
                    String hoTen = res.getString("hoTen");
                    String diaChi = res.getString("diaChi");
                    String soDienThoai = res.getString("soDienThoai");
                    
                    String tbData[] = {stt, maNhanVien, username, password, role, hoTen, diaChi, soDienThoai};
                    result.add(tbData);
                    i++;
                }
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(cnt != null) {
                    cnt.close();
                }
                if(stm != null) {
                    stm.close();
                }
                if(res != null) {
                    res.close();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());

            }
        }
        return result;
    }
    
    public void addAccount(AccountModel account) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder checkSql = new StringBuilder("SELECT * FROM accounts ");
            checkSql.append("WHERE \"maNhanVien\"= ? "); 
            
            StringBuilder sql = new StringBuilder("INSERT INTO accounts ");
            sql.append("(\"username\", \"password\", \"role\", \"maNhanVien\")");
            sql.append("VALUES (?, ?, ?, ?)");
            
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(checkSql.toString());
                stm.setString(1, account.getMaNV());
                res = stm.executeQuery();
                if(!res.next()) {
                    stm = cnt.prepareStatement(sql.toString());
                    stm.setString(1, account.getUsername());
                    stm.setString(2, account.getPassword());
                    stm.setString(3, account.getRole());
                    stm.setString(4, account.getMaNV());
                    stm.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Thêm tài khoản cho nhân viên thành công!"); 
                } else {
                    JOptionPane.showMessageDialog(null, "Tài khoản cho nhân viên này đã tồn tại!"); 
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Thêm tài khoản cho nhân viên thất bại!"); 
        } finally {
            try {
                if(cnt != null) {
                    cnt.close();
                }
                if(stm != null) {
                    stm.close();
                }
                if(res != null) {
                    res.close();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());

            }
        }
    }
    
    public void updateAccount (AccountModel account) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("UPDATE accounts SET ");
            sql.append("\"username\" = ?, ");
            sql.append("\"password\" = ?, ");
            sql.append("\"role\" = ? ");
            sql.append("WHERE \"maNhanVien\"= ?"); 

            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getRole());
                stm.setString(4, account.getMaNV());
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Sửa thông tin tài khoản thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Sửa thông tin tài khoản thất bại!"); 
        } finally {
            try {
                if(cnt != null) {
                    cnt.close();
                }
                if(stm != null) {
                    stm.close();
                }
                if(res != null) {
                    res.close();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());

            }
        }
    }
    
    public void deleteAccount (String maNV) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("DELETE FROM accounts ");
            sql.append("WHERE \"maNhanVien\"= ?"); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, maNV);
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Xóa tài khoản thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Xóa tài khoản thất bại!"); 
        } finally {
            try {
                if(cnt != null) {
                    cnt.close();
                }
                if(stm != null) {
                    stm.close();
                }
                if(res != null) {
                    res.close();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());

            }
        }
    }
    
    public ArrayList<String[]> searchAccount(String maNV) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT accounts.*, staff.* FROM accounts ");
            sql.append("INNER JOIN staff ON accounts.\"maNhanVien\" = staff.\"maNhanVien\" ");
            sql.append("WHERE accounts.\"maNhanVien\" LIKE ? ");

            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, "%" + maNV + "%");
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String maNhanVien = res.getString("maNhanVien");
                    String username = res.getString("username");
                    String password = res.getString("password");
                    String role = res.getString("role");
                    String hoTen = res.getString("hoTen");
                    String diaChi = res.getString("diaChi");
                    String soDienThoai = res.getString("soDienThoai");
                    
                    String tbData[] = {stt, maNhanVien, username, password, role, hoTen, diaChi, soDienThoai};
                    result.add(tbData);
                    i++;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if(cnt != null) {
                    cnt.close();
                }
                if(stm != null) {
                    stm.close();
                }
                if(res != null) {
                    res.close();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());

            }
        }
        return result;
    }
}
