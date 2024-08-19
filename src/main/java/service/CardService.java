package service;

import dao.CardDao;
import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.CardModel;
import utils.CheckNumeric;
import utils.CheckID;

public class CardService {
    public CardDao cardDao;
    public CheckNumeric checkNum = new CheckNumeric();
    public CheckID checkID = new CheckID();
    
    public CardService() {
        cardDao = new CardDao();
    }
    
    public ArrayList<String[]> handleGetData() {
        return cardDao.getData();
    }
    
    public ArrayList<String[]> handleGetDataSet() {
        return cardDao.getDataSet();
    }
    
    public void handleAddCard(CardModel card) throws IllegalArgumentException, IllegalAccessException{
        Boolean check = true;
        if(CheckID.checkMaDG(card.getMaDocGia()) == false) {
            JOptionPane.showMessageDialog(null, "Mã độc giả không phù hợp");   
            check = false;
        } else {
            
            Class<?> bookClass = card.getClass();
            Field[] fields = bookClass.getDeclaredFields(); 
            for (Field field : fields) {
                field.setAccessible(true); 
                Object value = field.get(card);
                if (value == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                    check = false;
                    break;
                }
            }   
        }
        if(check == true) {
            cardDao.addCard(card);
        }
    }
    
    public void handleUpdateCard(CardModel card) throws IllegalArgumentException, IllegalAccessException{
        Boolean check = true;
        if(CheckID.checkMaDG(card.getMaDocGia()) == false) {
            JOptionPane.showMessageDialog(null, "Mã độc giả không phù hợp");   
            check = false;
        } else {
            Class<?> bookClass = card.getClass();
            Field[] fields = bookClass.getDeclaredFields(); 
            for (Field field : fields) {
                field.setAccessible(true); 
                Object value = field.get(card);
                if (value == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                    check = false;
                    break;
                }
            }  
        }
        if(check == true) {
            cardDao.updateCard(card);
        }
    }
    
    public void handleDeleteCard(String maTheTV) {
        cardDao.deleteCard(maTheTV);
    }
    
    public ArrayList<String[]> handleSearchCard(String maTheTV) {
        return cardDao.searchCard(maTheTV);
    }
}
