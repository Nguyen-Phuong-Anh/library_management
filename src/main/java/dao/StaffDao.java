package dao;

import com.mycompany.demo1.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.StaffModel;
import utils.RandomID;

public class StaffDao {
    public ArrayList<String[]> getData() {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM staff";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String maNhanVien = res.getString("maNhanVien");
                    String hoTen = res.getString("hoTen");
                    String ngaySinh = res.getString("ngaySinh");
                    String diaChi = res.getString("diaChi");
                    String soDienThoai = res.getString("soDienThoai");
                    String role = res.getString("chucVu");
                    
                    String tbData[] = {stt, maNhanVien, hoTen, ngaySinh, diaChi, soDienThoai, role};
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
    public RandomID randomID = new RandomID();
    
    public ArrayList<String[]> getDataSet() {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM staff";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                while (res.next()) {   
                    String maNhanVien = res.getString("maNhanVien");
                    String hoTen = res.getString("hoTen");
                    String ngaySinh = res.getString("ngaySinh");
                    String diaChi = res.getString("diaChi");
                    String soDienThoai = res.getString("soDienThoai");
                    String role = res.getString("chucVu");
                    
                    String tbData[] = {maNhanVien, hoTen, ngaySinh, diaChi, soDienThoai, role};
                    result.add(tbData);
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
    
    public void addStaff(StaffModel staff) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("INSERT INTO staff ");
            sql.append("(\"maNhanVien\", \"hoTen\", \"ngaySinh\", \"diaChi\", \"soDienThoai\", \"chucVu\")");
            sql.append("VALUES (?, ?, ?, ?, ?, ?)");
            cnt = DBConnection.getCon();
            if(cnt != null) {
                String staffID = randomID.randomStaff();
                Date dob = new Date(staff.getNgaySinh().getTime());
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, staffID);
                stm.setString(2, staff.getHoTen());
                stm.setDate(3, dob);
                stm.setString(4, staff.getDiaChi());
                stm.setString(5, staff.getSoDienThoai());
                stm.setString(6, staff.getRole());
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Thêm nhân viên mới thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Thêm nhân viên mới thất bại!"); 
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
    
    public void updateStaff (StaffModel staff) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("UPDATE staff SET ");
            sql.append("\"hoTen\" = ?, ");
            sql.append("\"ngaySinh\" = ?, ");
            sql.append("\"diaChi\" = ?, ");
            sql.append("\"soDienThoai\" = ?, ");
            sql.append("\"chucVu\" = ? ");
            sql.append("WHERE \"maNhanVien\"= ?"); 

            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                Date dob = new Date(staff.getNgaySinh().getTime());
                stm.setString(1, staff.getHoTen());
                stm.setDate(2, dob);
                stm.setString(3, staff.getDiaChi());
                stm.setString(4, staff.getSoDienThoai());
                stm.setString(5, staff.getRole());
                stm.setString(6, staff.getMaNhanVien());
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Sửa thông tin nhân viên thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Sửa thông tin nhân viên thất bại!"); 
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
    
    public void deleteStaff(String maNhanVien) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("DELETE FROM staff ");
            sql.append("WHERE \"maNhanVien\"= ?"); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, maNhanVien);
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Xóa nhân viên thất bại!"); 
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
    
    public ArrayList<String[]> searchStaff(StaffModel staff) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM staff ");
            sql.append("AND \"maNhanVien\" LIKE ? "); 
            sql.append("AND \"hoTen\" LIKE ? "); 
            sql.append("AND \"soDienThoai\" LIKE ? "); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, "%" + staff.getMaNhanVien()+ "%");
                stm.setString(2, "%" + staff.getHoTen()+ "%");
                stm.setString(3, "%" + staff.getSoDienThoai()+ "%");
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String maNhanVien = res.getString("maNhanVien");
                    String hoTen = res.getString("hoTen");
                    String ngaySinh = res.getString("ngaySinh");
                    String diaChi = res.getString("diaChi");
                    String soDienThoai = res.getString("soDienThoai");
                    String role = res.getString("role");
                    
                    String tbData[] = {stt, maNhanVien, hoTen, ngaySinh, diaChi, soDienThoai, role};
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
