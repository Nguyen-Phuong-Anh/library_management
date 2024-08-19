package dao;

import com.mycompany.demo1.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.RoomModel;
import utils.RandomID;

public class RoomDao {
    public ArrayList<String[]> getData() {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM \"bookRoom\"";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String maMuon = res.getString("maMuon");
                    String maPhong = res.getString("maPhong");
                    String maTTV = res.getString("maTTV");
                    String ngayMuon = res.getString("ngayMuon");
                    String tgBatDau = res.getString("tgBatDau");
                    String tgKetThuc = res.getString("tgKetThuc");
                    String trangThaiSD = res.getString("trangThaiSD");
                    String tbData[] = {stt, maMuon, maPhong, maTTV, ngayMuon, tgBatDau, tgKetThuc, trangThaiSD};
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
    
    public ArrayList<String[]> getRoomList() {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM \"room\"";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String maPhong = res.getString("maPhong");
                    String tenPhong = res.getString("tenPhong");
                    String viTri = res.getString("viTri");
                    String sucChua = res.getString("sucChua");
                    String trangThai;
                    if(res.getBoolean("trangThai")) {
                        trangThai = "Còn trống";
                    } else {
                        trangThai = "Đã đặt";
                    }
                    String tbData[] = {stt, maPhong, tenPhong, viTri, sucChua, trangThai};
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
    
    public void addRoom(RoomModel room) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder checkSql = new StringBuilder("SELECT * FROM card ");
            checkSql.append("WHERE \"maTheTV\"= ? "); 
            
            StringBuilder sql = new StringBuilder("INSERT INTO \"bookRoom\" ");
            sql.append("(\"maMuon\", \"maPhong\", \"maTTV\", \"ngayMuon\", \"tgBatDau\", \"tgKetThuc\", \"trangThaiSD\")");
            sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");
            
            cnt = DBConnection.getCon();
            if(cnt != null) {
                String roomID = randomID.randomBookRoomID();
                Date date1 = new Date(room.getNgayMuon().getTime());
                LocalTime localTime = LocalTime.parse(room.getTgBatDau());
                Time time1 = Time.valueOf(localTime);
                LocalTime localTime2 = LocalTime.parse(room.getTgKetThuc());
                Time time2 = Time.valueOf(localTime2);
                stm = cnt.prepareStatement(checkSql.toString());
                stm.setString(1, room.getMaTTV());
                res = stm.executeQuery();
                if(res.next()) {
                    stm = cnt.prepareStatement(sql.toString());
                    stm.setString(1, roomID);
                    stm.setString(2, room.getMaPhong());
                    stm.setString(3, room.getMaTTV());
                    stm.setDate(4, date1);
                    stm.setTime(5, time1);
                    stm.setTime(6, time2);
                    stm.setString(7, room.getTrangThaiSD());
                    stm.executeUpdate();

                    sql = new StringBuilder("UPDATE room SET ");
                    sql.append("\"trangThai\" = false ");
                    sql.append("WHERE \"maPhong\"= ?");
                    stm = cnt.prepareStatement(sql.toString());
                    stm.setString(1, room.getMaPhong());
                    stm.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Thêm thông tin mượn phòng thành công!"); 
                } else {
                    JOptionPane.showMessageDialog(null, "Mã TTV không tồn tại!"); 
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Thêm thông tin mượn phòng mới thất bại!"); 
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
    
    public void updateRoom (RoomModel room) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("UPDATE \"bookRoom\" SET ");
            sql.append("\"maTTV\" = ?, ");
            sql.append("\"ngayMuon\" = ?, ");
            sql.append("\"tgBatDau\" = ?, ");
            sql.append("\"tgKetThuc\" = ?, ");
            sql.append("\"trangThaiSD\" = ? ");
            sql.append("WHERE \"maMuon\"= ?"); 
            Date date1 = new Date(room.getNgayMuon().getTime());
            LocalTime localTime = LocalTime.parse(room.getTgBatDau());
            Time time1 = Time.valueOf(localTime);
            LocalTime localTime2 = LocalTime.parse(room.getTgKetThuc());
            Time time2 = Time.valueOf(localTime2);
            
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, room.getMaTTV());
                stm.setDate(2, date1);
                stm.setTime(3, time1);
                stm.setTime(4, time2);
                stm.setString(5, room.getTrangThaiSD());
                stm.setString(6, room.getMaMuon());
                stm.executeUpdate();
                
                if(room.getTrangThaiSD().equals("Đã trả")) {
                    sql = new StringBuilder("UPDATE room SET ");
                    sql.append("\"trangThai\" = true ");
                    sql.append("WHERE \"maPhong\"= ?");
                    stm = cnt.prepareStatement(sql.toString());
                    stm.setString(1, room.getMaPhong());
                    stm.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Sửa thông tin mượn phòng thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Sửa thông tin mượn phòng thất bại!"); 
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
    
    public void deleteRoom (String maPhong, String maMuon) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        StringBuilder checkSql = new StringBuilder("SELECT * FROM \"room\" ");
        checkSql.append("WHERE \"maPhong\"= ? AND \"trangThai\"= false"); 
        
        try {
            StringBuilder sql = new StringBuilder("DELETE FROM \"bookRoom\" ");
            sql.append("WHERE \"maMuon\"= ?"); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(checkSql.toString());
                stm.setString(1, maPhong);
                res = stm.executeQuery();
                if(res.next()) {
                    JOptionPane.showMessageDialog(null, "Phòng đang có người mượn và chưa trả!"); 
                } else {
                    stm = cnt.prepareStatement(sql.toString());
                    stm.setString(1, maMuon);
                    stm.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Xóa thông tin mượn phòng thành công!"); 
                }
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Xóa thông tin mượn phòng thất bại!"); 
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
    
    public ArrayList<String[]> searchRoom(String maTheTV) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM \"bookRoom\" ");
            sql.append("WHERE \"maTTV\" LIKE ? "); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, "%" + maTheTV + "%");
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String maMuon = res.getString("maMuon");
                    String maPhong = res.getString("maPhong");
                    String maTTV = res.getString("maTTV");
                    String ngayMuon = res.getString("ngayMuon");
                    String tgBatDau = res.getString("tgBatDau");
                    String tgKetThuc = res.getString("tgKetThuc");
                    String trangThaiSD = res.getString("trangThaiSD");
                    String tbData[] = {stt, maMuon, maPhong, maTTV, ngayMuon, tgBatDau, tgKetThuc, trangThaiSD};
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
