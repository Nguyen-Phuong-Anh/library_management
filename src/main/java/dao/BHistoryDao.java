package dao;

import com.mycompany.demo1.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.BHistoryModel;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.RandomID;

public class BHistoryDao {
    public ArrayList<String[]> getData(String nguoiThucHien) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM \"borrowHis\" ";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String maMuonTra = res.getString("maMuonTra");
                    String maTheTV = res.getString("maTheTV");
                    String jsonbAsString = res.getString("DSachSachMuon");
                    String ngayMuon = res.getString("ngayMuon");
                    String ngayHetHan = res.getString("ngayHetHan");
                    String ngayTra = res.getString("ngayTra");
                    String trangThai = "";
                    if(res.getBoolean("trangThai") == false) {
                        trangThai = "Chưa trả";
                    } else {
                        trangThai = "Đã trả";
                    }
                    String soLanGiaHan = String.valueOf(res.getInt("soLanGiaHan"));
                    
                    String tbData[] = {stt, maMuonTra, maTheTV, jsonbAsString, ngayMuon, ngayHetHan, ngayTra, trangThai, soLanGiaHan};
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
            String sql = "SELECT * FROM \"borrowHis\" ";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                while (res.next()) {   
                    String maMuonTra = res.getString("maMuonTra");
                    String maTheTV = res.getString("maTheTV");
                    String jsonbAsString = res.getString("DSachSachMuon");
                    String ngayMuon = res.getString("ngayMuon");
                    String ngayHetHan = res.getString("ngayHetHan");
                    String ngayTra = res.getString("ngayTra");
                    String trangThai = "";
                    if(res.getBoolean("trangThai") == false) {
                        trangThai = "Chưa trả";
                    } else {
                        trangThai = "Đã trả";
                    }
                    String soLanGiaHan = String.valueOf(res.getInt("soLanGiaHan"));
                    String ngayTao = res.getString("ngayTao");
                    String nguoiThucHien = res.getString("nguoiThucHien");
                    String tbData[] = {maMuonTra, maTheTV, jsonbAsString, ngayMuon, ngayHetHan, ngayTra, trangThai, soLanGiaHan, nguoiThucHien, ngayTao};
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
    
    public void addHistory(BHistoryModel history, JSONArray list, int tongSL) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("INSERT INTO \"borrowHis\"");
            sql.append("(\"maMuonTra\", \"maTheTV\", \"DSachSachMuon\", \"ngayMuon\", \"ngayHetHan\", \"ngayTra\", \"trangThai\", "
                    + "\"solangiahan\", \"nguoiThucHien\", \"ngayTao\")");
            sql.append("VALUES (?, ?, CAST(? AS jsonb), ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)");
            cnt = DBConnection.getCon();
            if(cnt != null) {
                String maMuonTra = randomID.randomBorrowID();
                Date ngayMuon = new Date(history.getNgayMuon().getTime());
                Date ngayHetHan = new Date(history.getNgayHetHan().getTime());
                Date ngayTra = null;
                if(history.getNgayTra() != null) {
                    ngayTra = new Date(history.getNgayTra().getTime());
                }
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, maMuonTra);
                stm.setString(2, history.getMaTheTV());
                stm.setString(3, history.getDSSachMuon());
                stm.setDate(4, ngayMuon);
                stm.setDate(5, ngayHetHan);
                if(ngayTra == null) {
                    stm.setNull(6, java.sql.Types.DATE);
                } else {
                    stm.setDate(6, ngayTra);
                }
                stm.setBoolean(7, history.getTrangThai());
                stm.setInt(8, history.getSoLanGiaHan());
                stm.setString(9, history.getNguoiThucHien());
                stm.executeUpdate();
                
                Integer sum = 0;
                for(int i = 0; i < list.length(); i++) {
                    JSONObject obj = list.getJSONObject(i);
                    sql = new StringBuilder("UPDATE \"book\" SET ");
                    sql.append("\"soLuongHienTai\" = \"soLuongHienTai\" - ? ");
                    sql.append("WHERE \"maSach\"= ?"); 
                    stm = cnt.prepareStatement(sql.toString());
                    stm.setInt(1, obj.getInt("soLuong"));
                    stm.setString(2, obj.getString("maSach"));
                    stm.executeUpdate();
                    
                    sum += obj.getInt("soLuong");
                }
                sql = new StringBuilder("UPDATE \"reader\" SET ");
                sql.append("\"soSachDaMuon\" = ? ");
                sql.append("WHERE \"maTheTV\"= ?"); 
                stm = cnt.prepareStatement(sql.toString());
                stm.setInt(1, sum);
                stm.setString(2, history.getMaTheTV());
                stm.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Thêm lịch sử mượn trả thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Thêm lịch sử mượn trả thất bại!"); 
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
    
    public void updateHistory (BHistoryModel history, JSONArray list, ArrayList<String[]> oldListBook) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("UPDATE \"borrowHis\" SET ");
            sql.append("\"DSachSachMuon\" = CAST(? AS jsonb), ");
            sql.append("\"ngayMuon\" = ?, ");
            sql.append("\"ngayHetHan\" = ?, ");
            sql.append("\"ngayTra\" = ?, ");
            sql.append("\"trangThai\" = ?, ");
            sql.append("\"solangiahan\" = ?, ");
            sql.append("\"ngayTao\" = CURRENT_TIMESTAMP ");
            sql.append("WHERE \"maMuonTra\"= ?"); 

            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                Date date = new Date(history.getNgayMuon().getTime());
                Date date1 = new Date(history.getNgayHetHan().getTime());
                Date date2 = null;
                if(history.getNgayTra() != null) {
                    date2 = new Date(history.getNgayTra().getTime());
                }
                stm.setString(1, history.getDSSachMuon());
                stm.setDate(2, date);
                stm.setDate(3, date1);
                if(date2 == null) stm.setNull(4, java.sql.Types.DATE);
                else
                    stm.setDate(4, date2);
                stm.setBoolean(5, history.getTrangThai());
                stm.setInt(6, history.getSoLanGiaHan());
                stm.setString(7, history.getMaMuonTra());
                stm.executeUpdate();
                
                Integer sum = 0;
                if(history.getTrangThai() == false) {
                    if(oldListBook.size() == list.length()) {
                        for(int i = 0; i < list.length(); i++) {
                            JSONObject obj = list.getJSONObject(i);
                            int newQuan = obj.getInt("soLuong") - Integer.parseInt(oldListBook.get(i)[2]);
                            sql = new StringBuilder("UPDATE \"book\" SET ");
                            sql.append("\"soLuongHienTai\" = \"soLuongHienTai\" - ? ");
                            sql.append("WHERE \"maSach\"= ?"); 
                            stm = cnt.prepareStatement(sql.toString());
                            stm.setInt(1, newQuan);
                            stm.setString(2, obj.getString("maSach"));
                            stm.executeUpdate();
                            sum += newQuan;
                        }
                    } else {
                        for(int i = 0; i < list.length(); i++) {
                            JSONObject obj = list.getJSONObject(i);
                            if(i < oldListBook.size()) {
                                int newQuan = obj.getInt("soLuong") - Integer.parseInt(oldListBook.get(i)[2]);
                                sql = new StringBuilder("UPDATE \"book\" SET ");
                                sql.append("\"soLuongHienTai\" = \"soLuongHienTai\" - ? ");
                                sql.append("WHERE \"maSach\"= ?"); 
                                stm = cnt.prepareStatement(sql.toString());
                                stm.setInt(1, newQuan);
                                stm.setString(2, obj.getString("maSach"));
                                stm.executeUpdate();
                                
                                sum += newQuan;
                            } else {
                                sql = new StringBuilder("UPDATE \"book\" SET ");
                                sql.append("\"soLuongHienTai\" = \"soLuongHienTai\" - ? ");
                                sql.append("WHERE \"maSach\"= ?"); 
                                stm = cnt.prepareStatement(sql.toString());
                                stm.setInt(1, obj.getInt("soLuong"));
                                stm.setString(2, obj.getString("maSach"));
                                stm.executeUpdate();
                                
                                sum += obj.getInt("soLuong");
                            }
                        }
                    }
                } else {
                    for(int i = 0; i < list.length(); i++) {
                        JSONObject obj = list.getJSONObject(i);
                        sql = new StringBuilder("UPDATE \"book\" SET ");
                        sql.append("\"soLuongHienTai\" = \"soLuongHienTai\" + ? ");
                        sql.append("WHERE \"maSach\"= ?"); 
                        stm = cnt.prepareStatement(sql.toString());
                        stm.setInt(1, obj.getInt("soLuong"));
                        stm.setString(2, obj.getString("maSach"));
                        stm.executeUpdate();
                        
                        sum -= obj.getInt("soLuong");
                    }
                }
                sql = new StringBuilder("UPDATE \"reader\" SET ");
                sql.append("\"soSachDaMuon\" = \"soSachDaMuon\" + ? ");
                sql.append("WHERE \"maTheTV\"= ?"); 
                stm = cnt.prepareStatement(sql.toString());
                stm.setInt(1, sum);
                stm.setString(2, history.getMaTheTV());
                stm.executeUpdate();  
                    
                JOptionPane.showMessageDialog(null, "Sửa thông tin mượn trả thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Sửa thông tin mượn trả thất bại!"); 
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
    
    public void deleteHistory(String maMuonTra) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        
        try {
            StringBuilder sql = new StringBuilder("DELETE FROM \"borrowHis\" ");
            sql.append("WHERE \"maMuonTra\"= ?"); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, maMuonTra);
                stm.executeUpdate();
                JOptionPane.showMessageDialog(null, "Xóa lịch sử mượn trả thành công!"); 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Xóa lịch sử mượn trả thất bại!"); 
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
    
    public ArrayList<String[]> searchHistory(String maThe) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM \"borrowHis\" ");
            sql.append("WHERE \"maTheTV\" LIKE ? "); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, "%" + maThe + "%");
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String maMuonTra = res.getString("maMuonTra");
                    String maTheTV = res.getString("maTheTV");
                    String jsonbAsString = res.getString("DSachSachMuon");
                    String ngayMuon = res.getString("ngayMuon");
                    String ngayHetHan = res.getString("ngayHetHan");
                    String ngayTra = res.getString("ngayTra");
                    String trangThai = "";
                    if(res.getBoolean("trangThai") == false) {
                        trangThai = "Chưa trả";
                    } else {
                        trangThai = "Đã trả";
                    }
                    String soLanGiaHan = String.valueOf(res.getInt("soLanGiaHan"));
                    
                    String tbData[] = {stt, maMuonTra, maTheTV, jsonbAsString, ngayMuon, ngayHetHan, ngayTra, trangThai, soLanGiaHan};
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
    
    public ArrayList<String[]> searchReaderHistory(String maThe) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM \"borrowHis\" ");
            sql.append("WHERE \"maTheTV\" = ? "); 
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql.toString());
                stm.setString(1, maThe);
                res = stm.executeQuery();
                while (res.next()) {   
                    String maMuonTra = res.getString("maMuonTra");
                    String jsonbAsString = res.getString("DSachSachMuon");
                    String ngayMuon = res.getString("ngayMuon");
                    String ngayHetHan = res.getString("ngayHetHan");
                    String ngayTra = res.getString("ngayTra");
                    String trangThai = "Chưa trả";
                    String soLanGiaHan = String.valueOf(res.getInt("soLanGiaHan"));
                    String nguoiThucHien = res.getString("nguoiThucHien");
                    
                    String tbData[] = {maMuonTra, jsonbAsString, ngayMuon, ngayHetHan, ngayTra, trangThai, soLanGiaHan, nguoiThucHien};
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
    
    public ArrayList<String[]> searchOneBookHistory(String maSach) {
        Connection cnt = null;
        ResultSet res = null;
        PreparedStatement stm = null;
        ArrayList<String[]> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM \"borrowHis\" WHERE \"DSachSachMuon\" @> '[{\"maSach\": \"" + maSach + "\"}]' AND \"trangThai\" = false";
            cnt = DBConnection.getCon();
            if(cnt != null) {
                stm = cnt.prepareStatement(sql);
                res = stm.executeQuery();
                Integer i = 0;
                while (res.next()) {   
                    String stt = String.valueOf(i);
                    String maTheTV = res.getString("maTheTV");
                    String ngayMuon = res.getString("ngayMuon");
                    String ngayHetHan = res.getString("ngayHetHan");
                    String jsonbAsString = res.getString("DSachSachMuon");
                    
                    String tbData[] = {stt, maTheTV, ngayMuon, ngayHetHan, jsonbAsString};
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
