package service;

import dao.ReaderDao;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import model.ReaderModel;
import utils.CheckNull;
import utils.CheckNumeric;

public class ReaderService {
    public ReaderDao readerDao;
    public CheckNumeric checkNum = new CheckNumeric();
    
    public ReaderService() {
        readerDao = new ReaderDao();
    }
    
    public ArrayList<String[]> handleGetData() {
        return readerDao.getData();
    }
    
    public ArrayList<String[]> handleGetDataSet() {
        return readerDao.getDataSet();
    }
    
    public void handleAddReader(ReaderModel reader) throws IllegalArgumentException, IllegalAccessException{
        Boolean check = true;
        if(checkNum.checkSDT(reader.getSoDienThoai()) == false) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không đúng");   
            check = false;
        } else {
            if(checkNum.checkCCCD(reader.getSoCCCD()) == false) {
                JOptionPane.showMessageDialog(null, "Số CCCD không đúng");   
                check = false;
            } else  {
                if(checkNum.checkNumber(reader.getSoSachDaMuon()) == false && reader.getSoSachDaMuon().equals(0)) {
                    JOptionPane.showMessageDialog(null, "Số sách không hợp lệ");   
                    check = false;
                } else {
                    Class<?> bookClass = reader.getClass();
                    Field[] fields = bookClass.getDeclaredFields(); 
                    for (Field field : fields) {
                        field.setAccessible(true); 
                        Object value = field.get(reader);
                        if (value != null) {
                            if (value instanceof Date) {
                                Date dateValue = (Date) value;
                                if (dateValue == null) {
                                    JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                                    check = false;
                                    break;
                                }
                            } else if (value instanceof String && CheckNull.checkNull((String) value)) {
                                JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                                check = false;
                                break;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                            check = false;
                            break;
                        }
                    }   
                }
            }
        }
        if(check == true) {
            readerDao.addReader(reader);
        }
    }
    
    public void handleUpdateReader(ReaderModel reader) throws IllegalArgumentException, IllegalAccessException{
        Boolean check = true;
        if(checkNum.checkSDT(reader.getSoDienThoai()) == false) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không đúng");   
            check = false;
        } else {
            if(checkNum.checkCCCD(reader.getSoCCCD()) == false) {
                JOptionPane.showMessageDialog(null, "Số CCCD không đúng");   
                check = false;
            } else  {
                if(checkNum.checkNumber(reader.getSoSachDaMuon()) == false && reader.getSoSachDaMuon().equals(0)) {
                    JOptionPane.showMessageDialog(null, "Số sách không hợp lệ");   
                    check = false;
                } else {
                    Class<?> bookClass = reader.getClass();
                    Field[] fields = bookClass.getDeclaredFields(); 
                    for (Field field : fields) {
                        field.setAccessible(true); 
                        Object value = field.get(reader);
                        if (!(value instanceof Date)) {
                            if(CheckNull.checkNull((String) value)) {
                                JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                                check = false;
                                break;
                            }  
                        } 
                    }   
                }
            }
        }
        if(check == true) {
            readerDao.updateReader(reader);
        }
    }
    
    public void handleDeleteReader(String maDocGia) {
        readerDao.deleteReader(maDocGia);
    }
    
    public ArrayList<String[]> handleSearchReader(ReaderModel reader) {
        return readerDao.searchReader(reader);
    }
    
    public ArrayList<String[]> handleSearchOneReader(String maTTV) {
        return readerDao.searchOneReader(maTTV);
    }
}
