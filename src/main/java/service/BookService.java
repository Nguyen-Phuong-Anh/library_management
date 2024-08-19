package service;

import dao.BookDao;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;
import model.BookModel;
import utils.CheckNull;
import static utils.CheckNumeric.CheckNumeric;

public class BookService {
    public BookDao bookDao;

    public BookService() {
        this.bookDao = new BookDao();
    }
    
    public ArrayList<String[]> handleGetData() {
        return bookDao.getData();
    }
    
    public ArrayList<String[]> handleGetDataSet() {
        return bookDao.getDataSet();
    }
    
    public void handleAddBook(BookModel book) throws IllegalArgumentException, IllegalAccessException{
        String giaTien = book.getGiaTien();
        Boolean check = true;
        
        if(!CheckNumeric(giaTien)) {
            JOptionPane.showMessageDialog(null, "Giá tiền phải là một con số");   
            check = false;
        } else {
            Class<?> bookClass = book.getClass();
            Field[] fields = bookClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); 
                Object value = field.get(book);
                if(CheckNull.checkNull((String) value)) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");   
                    check = false;
                    break;
                }
            }
        }
        if(check == true) {
            bookDao.addBook(book);
        }
    }
    
    public void handleUpdateBook(BookModel book) throws IllegalArgumentException, IllegalAccessException{
        String giaTien = book.getGiaTien();
        Boolean check = true;
        
        if(!CheckNumeric(giaTien)) {
            JOptionPane.showMessageDialog(null, "Giá tiền phải là một con số");   
            check = false;
        } else {
            Class<?> bookClass = book.getClass();
            Field[] fields = bookClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); 
                Object value = field.get(book);
                if(CheckNull.checkNull((String) value)) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");   
                    check = false;
                    break;
                }
            }
        }
        if(check == true) {
            bookDao.updateBook(book);
        }
    }
    
    public void handleDeleteBook(String maSach) {
        bookDao.deleteBook(maSach);
    }
    
    public ArrayList<String[]> handleSearchBook(BookModel book) {
        return bookDao.searchBook(book);
    }
    
    public Map<String, ArrayList> handleGetBookFilter() {
        return bookDao.getBookFilter();
    }
    
    public ArrayList<String[]> handleSearchBookForAdding(Map<String, String> map) {
        return bookDao.searchBookForAdding(map);
    }
}
