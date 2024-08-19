package dao;

import com.mycompany.demo1.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.CardModel;
import utils.RandomID;

public class CardDao {
    public ArrayList<String[]> getData() {
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
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
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
                    String tbData[] = {stt, maTheTV, loaiThe, maDocGia, ngayPhatHanh, ngayHetHan, trangThai, soTienPhat};
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
    
    public void addCard(CardModel card) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder checkSql = new StringBuilder("SELECT * FROM reader ");
            checkSql.append("WHERE \"maDocGia\"= ? "); 
            
            StringBuilder sql = new StringBuilder("INSERT INTO card ");
            sql.append("(\"maTheTV\", \"loaiThe\", \"maDocGia\", \"ngayPhatHanh\", \"ngayHetHan\", \"trangThai\", \"soTienPhat\",  \"nguoiTao\")");
            sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            
            cnt = DBConnection.getCon();
            if(cnt != null) {
                String cardID = randomID.randomCardID();
                Date date1 = new Date(card.getNgayPhatHanh().getTime());
                Date date2 = new Date(card.getNgayHetHan().getTime());
                stm = cnt.prepareStatement(checkSql.toString());
                stm.setString(1, card.getMaDocGia());
                res = stm.executeQuery();
                if(res.next()) {
                    if(res.getString("maTheTV") == null || res.getString("maTheTV").equals("")) {
                        stm = cnt.prepareStatement(sql.toString());
                        stm.setString(1, cardID);
                        stm.setString(2, card.getLoaiThe());
                        stm.setString(3, card.getMaDocGia());
                        stm.setDate(4, date1);
                        stm.setDate(5, date2);
                        stm.setBoolean(6, card.getTrangThai());
                        stm.setFloat(7, card.getSoTienPhat());
                        stm.setString(8, card.getNguoiTao());
                        stm.executeUpdate();

                        sql = new StringBuilder("UPDATE reader SET ");
                        sql.append("\"maTheTV\" = ? ");
                        sql.append("WHERE \"maDocGia\"= ?");
                        stm = cnt.prepareStatement(sql.toString());
                        stm.setString(1, cardID);
                        stm.setString(2, card.getMaDocGia());
                        stm.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Thêm thẻ thư viện mới thành công!"); 
                    } else {
                        JOptionPane.showMessageDialog(null, "Thẻ thư viện đã tồn tại!"); 
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Mã độc giả không tồn tại!"); 
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Thêm thẻ thư viện mới thất bại!"); 
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
    
    public void updateCard (CardModel card) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("UPDATE card SET ");
            sql.append("\"loaiThe\" = ?, ");
            sql.append("\"ngayPhatHanh\" = ?, ");
            sql.append("\"ngayHetHan\" = ?, ");
            sql.append("\"trangThai\" = ?, ");
            sql.append("\"soTienPhat\" = ? ");
            sql.append("WHERE \"maTheTV\"= ?"); 

            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                Date date1 = new Date(card.getNgayPhatHanh().getTime());
                Date date2 = new Date(card.getNgayHetHan().getTime());
                stm.setString(1, card.getLoaiThe());
                stm.setDate(2, date1);
                stm.setDate(3, date2);
                stm.setBoolean(4, card.getTrangThai());
                stm.setFloat(5, card.getSoTienPhat());
                stm.setString(6, card.getMaTheTV());
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Sửa thông tin thẻ thư viện thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Sửa thông tin thẻ thư viện thất bại!"); 
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
    
    public void deleteCard (String maTheTV) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        StringBuilder checkSql = new StringBuilder("SELECT * FROM \"borrowHis\" ");
        checkSql.append("WHERE \"maTheTV\"= ? AND \"trangThai\"= FALSE"); 
        
        try {
            StringBuilder sql = new StringBuilder("DELETE FROM card ");
            sql.append("WHERE \"maTheTV\"= ?"); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(checkSql.toString());
                stm.setString(1, maTheTV);
                res = stm.executeQuery();
                if(res.next()) {
                    JOptionPane.showMessageDialog(null, "Độc giả vẫn đang mượn sách và chưa trả!"); 
                } else {
                    stm = cnt.prepareStatement(sql.toString());
                    stm.setString(1, maTheTV);
                    stm.executeUpdate();
                    
                    sql = new StringBuilder("UPDATE reader SET ");
                    sql.append("\"maTheTV\" = ? ");
                    sql.append("WHERE \"maTheTV\"= ?");
                    stm = cnt.prepareStatement(sql.toString());
                    stm.setString(1, "");
                    stm.setString(2, maTheTV);
                    stm.executeUpdate();
                        
                    JOptionPane.showMessageDialog(null, "Xóa thẻ thư viện thành công!"); 
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Xóa thẻ thư viện thất bại!"); 
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
    
    public ArrayList<String[]> searchCard(String maTheTV) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM card ");
            sql.append("WHERE \"maTheTV\" LIKE ? "); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, "%" + maTheTV + "%");
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String theTV = res.getString("maTheTV");
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
                    String tbData[] = {stt, theTV, loaiThe, maDocGia, ngayPhatHanh, ngayHetHan, trangThai, soTienPhat};
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
