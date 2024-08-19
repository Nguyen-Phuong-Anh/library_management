package service;

import dao.AccountDao;
import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.AccountModel;
import utils.CheckNumeric;
import utils.CheckID;

public class AccountService {
    public AccountDao accountDao;
    public CheckNumeric checkNum = new CheckNumeric();
    public CheckID checkID = new CheckID();
    
    public AccountService() {
        accountDao = new AccountDao();
    }
    
    public ArrayList<String[]> handleGetData() {
        return accountDao.getData();
    }
    
    public void handleAddAccount(AccountModel account) throws IllegalArgumentException, IllegalAccessException{
        Boolean check = true;
        if(CheckID.checkMaNV(account.getMaNV()) == false) {
            JOptionPane.showMessageDialog(null, "Mã NV không phù hợp");   
            check = false;
        } else {
            Class<?> bookClass = account.getClass();
            Field[] fields = bookClass.getDeclaredFields(); 
            for (Field field : fields) {
                field.setAccessible(true); 
                Object value = field.get(account);
                if (value == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                    check = false;
                    break;
                }
            }   
        }
        if(check == true) {
            accountDao.addAccount(account);
        }
    }
    
    public void handleUpdateAccount(AccountModel account) throws IllegalArgumentException, IllegalAccessException{
        Boolean check = true;
        if(CheckID.checkMaNV(account.getMaNV()) == false) {
            JOptionPane.showMessageDialog(null, "Mã NV không phù hợp");   
            check = false;
        } else {
            Class<?> bookClass = account.getClass();
            Field[] fields = bookClass.getDeclaredFields(); 
            for (Field field : fields) {
                field.setAccessible(true); 
                Object value = field.get(account);
                if (value == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                    check = false;
                    break;
                }
            }  
        }
        if(check == true) {
            accountDao.updateAccount(account);
        }
    }
    
    public void handleDeleteAccount(String maNV) {
        accountDao.deleteAccount(maNV);
    }
    
    public ArrayList<String[]> handleSearchAccount(String maNV) {
        return accountDao.searchAccount(maNV);
    }
}
