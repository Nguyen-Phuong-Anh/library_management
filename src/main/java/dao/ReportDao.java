package dao;

import com.mycompany.demo1.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportDao {
    public ArrayList<String[]> getBook() {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        
        try {
            String sql = "SELECT \"tenTheLoai\", SUM(\"soLuong\") AS \"soLuong\" FROM book GROUP BY \"tenTheLoai\"";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                while (res.next()) {   
                    String tenTheLoai = res.getString("tenTheLoai");
                    String soLuongHienTai = String.valueOf(res.getInt("soLuong"));
                    
                    String tbData[] = {tenTheLoai, soLuongHienTai};
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
    
    public ArrayList<String[]> getReader() {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        
        try {
            String sql = "SELECT TO_CHAR(\"ngayPhatHanh\", 'YYYY-MM') AS thangPhatHanh, COUNT(*) AS soLuong FROM card GROUP BY TO_CHAR(\"ngayPhatHanh\", 'YYYY-MM') ORDER BY thangPhatHanh";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                while (res.next()) {   
                    String tenTheLoai = res.getString("thangPhatHanh");
                    String soLuongHienTai = String.valueOf(res.getInt("soLuong"));
                    
                    String tbData[] = {tenTheLoai, soLuongHienTai};
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
    
    public ArrayList<String[]> getBorrowedBook() {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        
        try {
            String sql = "SELECT \"maSach\", \"tieuDe\", \"soLuong\", \"soLuongHienTai\" "
                    + "FROM \"book\" WHERE \"soLuongHienTai\" < \"soLuong\"";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                int i = 0;
                while (res.next()) {
                    String stt = String.valueOf(i);
                    String maSach = res.getString("maSach");
                    String tieuDe = res.getString("tieuDe");
                    Integer soLuong = res.getInt("soLuong");
                    Integer soLuongHienTai = res.getInt("soLuongHienTai");
                    String sl = String.valueOf(soLuong - soLuongHienTai);
                    
                    String tbData[] = {stt, maSach, tieuDe, sl};
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
    
    public ArrayList<String[]> getBHis() {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        
        try {
            String sql = "SELECT \"maTheTV\", " +
             "COUNT(CASE WHEN \"trangThai\" = false THEN 1 END) AS soLuongChuaTra " +
             "FROM " +
             "\"borrowHis\" " +
             "GROUP BY \"maTheTV\"";

            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                while (res.next()) {   
                    String soLuongChuaTra = String.valueOf(res.getInt("soLuongChuaTra"));
                    String maTheTV = res.getString("maTheTV");
                    String tbData[] = {maTheTV, soLuongChuaTra};
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
    
    public ArrayList<String[]> getBorrowedList() {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        
        try {
            String sql = "SELECT \"DSachSachMuon\" FROM \"borrowHis\"";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                while (res.next()) {   
                    String DSachSachMuon = res.getString("DSachSachMuon");
                    
                    String tbData[] = {DSachSachMuon};
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
