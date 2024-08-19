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
import model.BookModel;
import static utils.RandomID.randomBookID;

public class BookDao {
    public ArrayList<String[]> getData() {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM book";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String maSach = res.getString("maSach");
                    String tieuDe = res.getString("tieuDe");
                    String tenTacGia = res.getString("tenTacGia");
                    String tenTheLoai = res.getString("tenTheLoai");
                    String soLuong = String.valueOf(res.getInt("soLuong"));
                    String soLuongHienTai = String.valueOf(res.getInt("soLuongHienTai"));
                    String giaTien = String.valueOf(res.getFloat("giaTien"));
                    String nxb = res.getString("tenNXB");
                    
                    String tbData[] = {stt, maSach, tieuDe, tenTacGia, tenTheLoai, soLuong, soLuongHienTai, giaTien, nxb};
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
    
    public ArrayList<String[]> getDataSet() {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM book";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                while (res.next()) {   
                    String maSach = res.getString("maSach");
                    String tieuDe = res.getString("tieuDe");
                    String tenTacGia = res.getString("tenTacGia");
                    String tenTheLoai = res.getString("tenTheLoai");
                    String soLuong = String.valueOf(res.getInt("soLuong"));
                    String soLuongHienTai = String.valueOf(res.getInt("soLuongHienTai"));
                    String giaTien = String.valueOf(res.getFloat("giaTien"));
                    String nxb = res.getString("tenNXB");
                    String ngayTao = res.getString("ngayTao");
                    
                    String tbData[] = {maSach, tieuDe, tenTacGia, tenTheLoai, soLuong, soLuongHienTai, giaTien, nxb, ngayTao};
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
    
    public void addBook(BookModel book) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("INSERT INTO book ");
            sql.append("(\"maSach\", \"tieuDe\", \"tenTacGia\", \"tenTheLoai\", \"soLuong\", \"soLuongHienTai\", \"giaTien\", \"tenNXB\", \"ngayTao\")");
            sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)");
            cnt = DBConnection.getCon();
            if(cnt != null) {
                String bookID = randomBookID();
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, bookID);
                stm.setString(2, book.getTieuDe());
                stm.setString(3, book.getTenTacGia());
                stm.setString(4, book.getTenTheLoai());
                stm.setInt(5, Integer.parseInt(book.getSoLuong()));
                stm.setInt(6, Integer.parseInt(book.getSoLuongHienTai()));
                stm.setFloat(7, Float.parseFloat(book.getGiaTien()));
                stm.setString(8, book.getTenNXB());
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Thêm sách mới thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Thêm sách mới thất bại!"); 
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
    
    public void updateBook (BookModel book) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("UPDATE book SET ");
            sql.append("\"tieuDe\" = ?, ");
            sql.append("\"tenTacGia\" = ?, ");
            sql.append("\"tenTheLoai\" = ?, ");
            sql.append("\"soLuong\" = ?, ");
            sql.append("\"soLuongHienTai\" = ?, ");
            sql.append("\"giaTien\" = ?, ");
            sql.append("\"tenNXB\" = ?, ");
            sql.append("\"ngayTao\" = CURRENT_TIMESTAMP ");
            sql.append("WHERE \"maSach\"= ?"); 

            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, book.getTieuDe());
                stm.setString(2, book.getTenTacGia());
                stm.setString(3, book.getTenTheLoai());
                stm.setInt(4, Integer.parseInt(book.getSoLuong()));
                stm.setInt(5, Integer.parseInt(book.getSoLuongHienTai()));
                stm.setFloat(6, Float.parseFloat(book.getGiaTien()));
                stm.setString(7, book.getTenNXB());
                stm.setString(8, book.getMaSach());
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Sửa thông tin thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Sửa thông tin thất bại!"); 
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
    
    public void deleteBook(String maSach) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("DELETE FROM book ");
            sql.append("WHERE \"maSach\"= ?"); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, maSach);
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Xóa sách thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Xóa sách thất bại!"); 
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
    
    public ArrayList<String[]> searchBook(BookModel book) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM book ");
            sql.append("WHERE \"tieuDe\" LIKE ? "); 
            sql.append("AND \"tenTacGia\" LIKE ? "); 
            sql.append("AND \"tenNXB\" LIKE ? "); 
            sql.append("AND \"tenTheLoai\" LIKE ? "); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, "%" + book.getTieuDe() + "%");
                stm.setString(2, "%" + book.getTenTacGia() + "%");
                stm.setString(3, "%" + book.getTenNXB()+ "%");
                stm.setString(4, "%" + book.getTenTheLoai()+ "%");
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String maSach = res.getString("maSach");
                    String tieuDe = res.getString("tieuDe");
                    String tenTacGia = res.getString("tenTacGia");
                    String tenTheLoai = res.getString("tenTheLoai");
                    String soLuong = String.valueOf(res.getInt("soLuong"));
                    String soLuongHienTai = String.valueOf(res.getInt("soLuongHienTai"));
                    String giaTien = String.valueOf(res.getFloat("giaTien"));
                    String nxb = res.getString("tenNXB");
                    
                    String tbData[] = {stt, maSach, tieuDe, tenTacGia, tenTheLoai, soLuong, soLuongHienTai, giaTien, nxb};
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
    
    public Map<String, ArrayList> getBookFilter() {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        Map<String, ArrayList> map = new HashMap<>();
        ArrayList<String> author = new ArrayList<>();
        ArrayList<String> publisher = new ArrayList<>();
        ArrayList<String> gernes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM author";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String tenTacGia = res.getString("tenTacGia");
                    author.add(tenTacGia);
                    i++;
                }
            }
            map.put("author", author);
            
            sql = "SELECT * FROM publisher";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String tenNXB = res.getString("tenNXB");
                    publisher.add(tenNXB);
                    i++;
                }
            }
            map.put("publisher", publisher);
            
            sql = "SELECT * FROM gernes";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String tenTheLoai = res.getString("tenTheLoai");
                    gernes.add(tenTheLoai);
                    i++;
                }
            }
            map.put("gernes", gernes);
            
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
        return map;
    }
    
    public ArrayList<String[]> searchBookForAdding(Map<String, String> map) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM book ");
            sql.append("WHERE \"tieuDe\" LIKE ? "); 
            sql.append("AND \"tenTacGia\" LIKE ? "); 
            sql.append("AND \"tenNXB\" LIKE ? "); 
            sql.append("AND \"tenTheLoai\" LIKE ? "); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, "%" + map.get("tieuDe") + "%");
                stm.setString(2, "%" + map.get("tacGia") + "%");
                stm.setString(3, "%" + map.get("NXB") + "%");
                stm.setString(4, "%" + map.get("theLoai") + "%");
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String maSach = res.getString("maSach");
                    String tieuDe = res.getString("tieuDe");
                    String soLuongHienTai = String.valueOf(res.getInt("soLuongHienTai"));
                    
                    String tbData[] = {maSach, tieuDe, soLuongHienTai};
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


