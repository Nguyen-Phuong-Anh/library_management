package dao;

import com.mycompany.demo1.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ReaderModel;
import utils.RandomID;

public class ReaderDao {
    public ArrayList<String[]> getData() {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM reader";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String maDocGia = res.getString("maDocGia");
                    String hoTen = res.getString("hoTen");
                    String ngaySinh = res.getString("ngaySinh");
                    String diaChi = res.getString("diaChi");
                    String soDienThoai = res.getString("soDienThoai");
                    String soCCCD = res.getString("soCCCD");
                    String maTheTV = res.getString("maTheTV");
                    String soSachDaMuon = String.valueOf(res.getInt("soSachDaMuon"));
                    
                    String tbData[] = {stt, maDocGia, maTheTV, hoTen, ngaySinh, diaChi, soDienThoai, soCCCD, soSachDaMuon};
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
            String sql = "SELECT * FROM reader";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String maDocGia = res.getString("maDocGia");
                    String hoTen = res.getString("hoTen");
                    String ngaySinh = res.getString("ngaySinh");
                    String diaChi = res.getString("diaChi");
                    String soDienThoai = res.getString("soDienThoai");
                    String soCCCD = res.getString("soCCCD");
                    String maTheTV = res.getString("maTheTV");
                    String soSachDaMuon = String.valueOf(res.getInt("soSachDaMuon"));
                    
                    String tbData[] = {maDocGia, maTheTV, hoTen, ngaySinh, diaChi, soDienThoai, soCCCD, soSachDaMuon};
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
    
    public void addReader(ReaderModel reader) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("INSERT INTO reader ");
            sql.append("(\"maDocGia\", \"hoTen\", \"ngaySinh\", \"diaChi\", \"soDienThoai\", \"soCCCD\", \"maTheTV\", \"soSachDaMuon\")");
            sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            cnt = DBConnection.getCon();
            if(cnt != null) {
                String readerID = randomID.randomReaderID();
                Date dob = new Date(reader.getNgaySinh().getTime());
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, readerID);
                stm.setString(2, reader.getHoTen());
                stm.setDate(3, dob);
                stm.setString(4, reader.getDiaChi());
                stm.setString(5, reader.getSoDienThoai());
                stm.setString(6, reader.getSoCCCD());
                stm.setString(7, "");
                stm.setInt(8, Integer.parseInt(reader.getSoSachDaMuon()));
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Thêm độc giả mới thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Thêm độc giả mới thất bại!"); 
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
    
    public void updateReader (ReaderModel reader) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("UPDATE reader SET ");
            sql.append("\"hoTen\" = ?, ");
            sql.append("\"ngaySinh\" = ?, ");
            sql.append("\"diaChi\" = ?, ");
            sql.append("\"soDienThoai\" = ?, ");
            sql.append("\"soCCCD\" = ?, ");
            sql.append("\"soSachDaMuon\" = ? ");
            sql.append("WHERE \"maDocGia\"= ?"); 

            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                Date dob = new Date(reader.getNgaySinh().getTime());
                stm.setString(1, reader.getHoTen());
                stm.setDate(2, dob);
                stm.setString(3, reader.getDiaChi());
                stm.setString(4, reader.getSoDienThoai());
                stm.setString(5, reader.getSoCCCD());
                stm.setInt(6, Integer.parseInt(reader.getSoSachDaMuon()));
                stm.setString(7, reader.getMaDocGia());
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Sửa thông tin độc giả thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Sửa thông tin độc giả thất bại!"); 
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
    
    public void deleteReader(String maDocGia) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("DELETE FROM reader ");
            sql.append("WHERE \"maDocGia\"= ?"); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, maDocGia);
                stm.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Xóa độc giả thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Xóa độc giả thất bại!"); 
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
    
    public ArrayList<String[]> searchReader(ReaderModel reader) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM reader ");
            sql.append("WHERE \"maTheTV\" LIKE ? "); 
            sql.append("AND \"hoTen\" LIKE ? "); 
            sql.append("AND \"soDienThoai\" LIKE ? "); 
            sql.append("AND \"soCCCD\" LIKE ? "); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, "%" + reader.getMaTheTV()+ "%");
                stm.setString(2, "%" + reader.getHoTen()+ "%");
                stm.setString(3, "%" + reader.getSoDienThoai()+ "%");
                stm.setString(4, "%" + reader.getSoCCCD()+ "%");
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String maDocGia = res.getString("maDocGia");
                    String hoTen = res.getString("hoTen");
                    String ngaySinh = res.getString("ngaySinh");
                    String diaChi = res.getString("diaChi");
                    String soDienThoai = res.getString("soDienThoai");
                    String soCCCD = res.getString("soCCCD");
                    String maTheTV = res.getString("maTheTV");
                    String soSachDaMuon = String.valueOf(res.getInt("soSachDaMuon"));
                    
                    String tbData[] = {stt, maDocGia, maTheTV, hoTen, ngaySinh, diaChi, soDienThoai, soCCCD, soSachDaMuon};
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
    
    public ArrayList<String[]> searchOneReader(String maTTV) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM reader ");
            sql.append("WHERE \"maTheTV\" = ? "); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, maTTV);
                res = stm.executeQuery();
                while (res.next()) {   
                    String maDocGia = res.getString("maDocGia");
                    String hoTen = res.getString("hoTen");
                    String ngaySinh = res.getString("ngaySinh");
                    String diaChi = res.getString("diaChi");
                    String soDienThoai = res.getString("soDienThoai");
                    String soCCCD = res.getString("soCCCD");
                    String maTheTV = res.getString("maTheTV");
                    String soSachDaMuon = String.valueOf(res.getInt("soSachDaMuon"));
                    
                    String tbData[] = {maDocGia, maTheTV, hoTen, ngaySinh, diaChi, soDienThoai, soCCCD, soSachDaMuon};
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
}
