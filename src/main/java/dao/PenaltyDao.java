package dao;

import com.mycompany.demo1.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import model.PenaltyModel;
import org.json.JSONArray;
import utils.RandomID;

public class PenaltyDao {
    public ArrayList<String[]> getData() {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM penalty";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String maTheTV = res.getString("maTTV");
                    String DSPhat = res.getString("DSPhat");
                    String tongTienPhat = String.valueOf(res.getFloat("tongTienPhat"));
                    String tbData[] = {stt, maTheTV, DSPhat, tongTienPhat};
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
            String sql = "SELECT * FROM card";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                while (res.next()) {   
                    String maTheTV = res.getString("maTheTV");
                    String loaiThe = res.getString("loaiThe");
                    String maDocGia = res.getString("maDocGia");
                    String ngayPhatHanh = res.getString("ngayPhatHanh");
                    String ngayHetHan = res.getString("ngayHetHan");
                    String soTienPhat = String.valueOf(res.getFloat("soTienPhat"));
                    String trangThai;
                    if(res.getBoolean("trangThai")) {
                        trangThai = "Còn hoạt động";
                    } else {
                        trangThai = "Không hoạt động";
                    }
                    String tbData[] = {maTheTV, loaiThe, maDocGia, ngayPhatHanh, ngayHetHan, trangThai, soTienPhat};
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
    
    public Map<String, Float> getPenalty(String nhomLoi) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        Map<String, Float> result = new HashMap<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT \"tenLoi\", \"tienPhat\" FROM \"penaltyBoard\" ");
            sql.append("WHERE \"nhomLoi\"= ? "); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, nhomLoi);
                res = stm.executeQuery();
                while (res.next()) {   
                    String tenLoi = res.getString("tenLoi");
                    Float tienPhat = res.getFloat("tienPhat");
                    result.put(tenLoi, tienPhat);
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
    
    public String getGroupPen(String tenLoi) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        String result = "";
        try {
            StringBuilder sql = new StringBuilder("SELECT \"nhomLoi\" FROM \"penaltyBoard\" ");
            sql.append("WHERE \"tenLoi\"= ? "); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, tenLoi);
                res = stm.executeQuery();
                while (res.next()) {   
                    String nhomLoi = res.getString("nhomLoi");
                    result = nhomLoi;
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
    
    public void addPenalty (PenaltyModel penalty) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder checkSql = new StringBuilder("SELECT * FROM card ");
            checkSql.append("WHERE \"maTheTV\"= ? "); 
            StringBuilder checkSql1 = new StringBuilder("SELECT * FROM penalty ");
            checkSql1.append("WHERE \"maTTV\"= ? "); 
            
            StringBuilder sql = new StringBuilder("INSERT INTO penalty ");
            sql.append("(\"maTTV\", \"DSPhat\", \"tongTienPhat\") ");
            sql.append("VALUES (?, CAST(? AS jsonb), ?)");
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(checkSql.toString());
                stm.setString(1, penalty.getMaTTV());
                res = stm.executeQuery();
                if(res.next()) {
                    stm = cnt.prepareStatement(checkSql1.toString());
                    stm.setString(1, penalty.getMaTTV());
                    res = stm.executeQuery();
                    if(!res.next()) {
                        stm = cnt.prepareStatement(sql.toString());
                        stm.setString(1, penalty.getMaTTV());
                        stm.setString(2, penalty.getDSPhat());
                        stm.setFloat(3, penalty.getTongTienPhat());
                        stm.executeUpdate();
                        
                        sql = new StringBuilder("UPDATE \"card\" SET ");
                        sql.append("\"soTienPhat\" = ? ");
                        sql.append("WHERE \"maTheTV\"= ?"); 
                        stm = cnt.prepareStatement(sql.toString());
                        stm.setFloat(1, penalty.getTongTienPhat());
                        stm.setString(2, penalty.getMaTTV());
                        stm.executeUpdate();  
                
                        JOptionPane.showMessageDialog(null, "Thêm thông tin xử phạt mới thành công!"); 
                    } else {
                        JOptionPane.showMessageDialog(null, "Đã có thông tin xử phạt! Từ chối thêm mới."); 
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Mã TTV không tồn tại!"); 
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Thêm thông tin xử phạt mới thất bại!"); 
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
    
    public void updatePenalty (PenaltyModel penalty, JSONArray list, ArrayList<String[]> oldListPenalty) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("UPDATE \"penalty\" SET ");
            sql.append("\"DSPhat\" = CAST(? AS jsonb), ");
            sql.append("\"tongTienPhat\" = ? ");
            sql.append("WHERE \"maTTV\"= ?"); 

            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                if(penalty.getDSPhat().equals("")) {
                    stm.setNull(1, java.sql.Types.OTHER);
                } else {
                    stm.setString(1, penalty.getDSPhat());
                }
                stm.setFloat(2, penalty.getTongTienPhat());
                stm.setString(3, penalty.getMaTTV());
                stm.executeUpdate();  
                
                sql = new StringBuilder("UPDATE \"card\" SET ");
                sql.append("\"soTienPhat\" = ? ");
                sql.append("WHERE \"maTheTV\"= ?"); 
                stm = cnt.prepareStatement(sql.toString());
                stm.setFloat(1, penalty.getTongTienPhat());
                stm.setString(2, penalty.getMaTTV());
                stm.executeUpdate();

                JOptionPane.showMessageDialog(null, "Sửa thông tin xử phạt thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Sửa thông tin xử phạt thất bại!"); 
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
    
    public void deletePenalty (String maTheTV) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("DELETE FROM penalty ");
            sql.append("WHERE \"maTTV\"= ?"); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, maTheTV);
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Xóa thông tin xử phạt thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Xóa thông tin xử phạt thất bại!"); 
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
    
    public ArrayList<String[]> searchPenalty(String maTheTV) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM penalty ");
            sql.append("WHERE \"maTTV\" LIKE ? "); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, "%" + maTheTV + "%");
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String theTV = res.getString("maTTV");
                    String DSPhat = res.getString("DSPhat");
                    String tongTienPhat = String.valueOf(res.getFloat("tongTienPhat"));
                    String tbData[] = {stt, theTV, DSPhat, tongTienPhat};
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
