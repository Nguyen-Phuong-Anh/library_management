package service;

import dao.BHistoryDao;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.BHistoryModel;
import org.json.JSONArray;
import utils.CheckID;
import utils.CheckNull;
import utils.CheckNumeric;

public class BHistoryService {
    public BHistoryDao historyDao;
    public CheckNumeric checkNum = new CheckNumeric();
    public CheckID idcheck = new CheckID();
    
    public BHistoryService() {
        historyDao = new BHistoryDao();
    }
    
    public ArrayList<String[]> handleGetData(String nguoiThucHien) {
        return historyDao.getData(nguoiThucHien);
    }
    
    public ArrayList<String[]> handleGetDataSet() {
        return historyDao.getDataSet();
    }
    
    public void handleAddHistory(BHistoryModel history, JSONArray list, int tongSL) throws IllegalArgumentException, IllegalAccessException{
        Boolean check = true;
        if(checkNum.checkNumber(history.getSoLanGiaHan().toString()) == false) {
            JOptionPane.showMessageDialog(null, "Số lần gia hạn không đúng");   
            check = false;
        } else {
            if(idcheck.checkMaTheTV(history.getMaTheTV()) == false) {
                JOptionPane.showMessageDialog(null, "Số lần gia hạn không đúng");   
                check = false;
            }
            Class<?> bookClass = history.getClass();
            Field[] fields = bookClass.getDeclaredFields(); 
            for (Field field : fields) {
                field.setAccessible(true); 
                Object value = field.get(history);
                if (value != null) {
                    if (value instanceof Date) {
                        Date dateValue = (Date) value;
                        if (dateValue == null && !field.getName().equals("ngayTra")) {
                            JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                            check = false;
                            break;
                        }
                    } else if (value instanceof String && CheckNull.checkNull((String) value)) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                        check = false;
                        break;
                    }
                } else if (!field.getName().equals("ngayTra")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                    check = false;
                    break;
                }
            }   
        }
        if(check == true) {
            historyDao.addHistory(history, list, tongSL);
        }
    }
    
    public void handleUpdateHistory(BHistoryModel history, JSONArray list, ArrayList<String[]> oldListBook) throws IllegalArgumentException, IllegalAccessException{
        Boolean check = true;
        if(checkNum.checkNumber(history.getSoLanGiaHan().toString()) == false) {
            JOptionPane.showMessageDialog(null, "Số lần gia hạn không đúng");   
            check = false;
        } else  {
            Class<?> bookClass = history.getClass();
            Field[] fields = bookClass.getDeclaredFields(); 
            for (Field field : fields) {
                field.setAccessible(true); 
                Object value = field.get(history);
                if (value != null) {
                    if (value instanceof Date) {
                        Date dateValue = (Date) value;
                        if (dateValue == null && !field.getName().equals("ngayTra")) {
                            JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                            check = false;
                            break;
                        }
                    } else if (value instanceof String && CheckNull.checkNull((String) value)) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                        check = false;
                        break;
                    }
                } else if (!field.getName().equals("ngayTra")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                    check = false;
                    break;
                }   
            }
        }
        if(check == true) {
            historyDao.updateHistory(history, list, oldListBook);
        }
    }
    
    public void handleDeleteHistory(String maMuonTra) {
        historyDao.deleteHistory(maMuonTra);
    }
    
    public ArrayList<String[]> handleSearchHistory(String maTheTV) {
        return historyDao.searchHistory(maTheTV);
    }
    
    public ArrayList<String[]> handleSearchReaderHistory(String maTheTV) {
        return historyDao.searchReaderHistory(maTheTV);
    }
    
    public ArrayList<String[]> handleSearchHistoryOneBook(String maTheTV) {
        return historyDao.searchOneBookHistory(maTheTV);
    }
}
