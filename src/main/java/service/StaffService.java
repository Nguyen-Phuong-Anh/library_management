package service;

import dao.StaffDao;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.StaffModel;
import utils.CheckNull;
import utils.CheckNumeric;

public class StaffService {
    public StaffDao staffDao;
    public CheckNumeric checkNum = new CheckNumeric();
    
    public StaffService() {
        staffDao = new StaffDao();
    }
    
    public ArrayList<String[]> handleGetData() {
        return staffDao.getData();
    }
    
    public ArrayList<String[]> handleGetDataSet() {
        return staffDao.getDataSet();
    }
    
    public void handleAddStaff(StaffModel staff) throws IllegalArgumentException, IllegalAccessException{
        Boolean check = true;
        if(checkNum.checkSDT(staff.getSoDienThoai()) == false) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không đúng");   
            check = false;
        } else {
            Class<?> bookClass = staff.getClass();
            Field[] fields = bookClass.getDeclaredFields(); 
            for (Field field : fields) {
                field.setAccessible(true); 
                Object value = field.get(staff);
                if (value != null) {
                    if (value instanceof java.util.Date) {
                        java.util.Date dateValue = (java.util.Date) value;
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
        if(check == true) {
            staffDao.addStaff(staff);
        }
    }
    
    public void handleUpdateStaff(StaffModel staff) throws IllegalArgumentException, IllegalAccessException{
        Boolean check = true;
        if(checkNum.checkSDT(staff.getSoDienThoai()) == false) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không đúng");   
            check = false;
        } else {
            Class<?> bookClass = staff.getClass();
            Field[] fields = bookClass.getDeclaredFields(); 
            for (Field field : fields) {
                field.setAccessible(true); 
                Object value = field.get(staff);
                if (value == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                    check = false;
                    break;
                }
            }  
        }
        if(check == true) {
            staffDao.updateStaff(staff);
        }
    }
    
    public void handleDeleteStaff(String maNhanVien) {
        staffDao.deleteStaff(maNhanVien);
    }
    
    public ArrayList<String[]> handleSearchStaff(StaffModel staff) {
        return staffDao.searchStaff(staff);
    }
}
