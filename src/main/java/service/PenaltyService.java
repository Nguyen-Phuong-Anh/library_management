package service;

import dao.PenaltyDao;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;
import model.PenaltyModel;
import org.json.JSONArray;
import utils.CheckNumeric;
import utils.CheckID;

public class PenaltyService {
    public PenaltyDao penaltyDao;
    public CheckNumeric checkNum = new CheckNumeric();
    public CheckID checkID = new CheckID();
    
    public PenaltyService() {
        penaltyDao = new PenaltyDao();
    }
    
    public ArrayList<String[]> handleGetData() {
        return penaltyDao.getData();
    }
    
    public ArrayList<String[]> handleGetDataSet() {
        return penaltyDao.getDataSet();
    }
    
    public Map<String, Float> handleGetPenalty(String nhomLoi) {
        return penaltyDao.getPenalty(nhomLoi);
    }
    
    public String handleGetGroupPen(String tenLoi) {
        return penaltyDao.getGroupPen(tenLoi);
    }
    
    public void handleAddPenalty(PenaltyModel penalty) throws IllegalArgumentException, IllegalAccessException{
        Boolean check = true;
        if(CheckID.checkMaTheTV(penalty.getMaTTV()) == false) {
            JOptionPane.showMessageDialog(null, "Mã TTV không phù hợp");   
            check = false;
        } else {
            Class<?> bookClass = penalty.getClass();
            Field[] fields = bookClass.getDeclaredFields(); 
            for (Field field : fields) {
                field.setAccessible(true); 
                Object value = field.get(penalty);
                if (value == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                    check = false;
                    break;
                }
            }   
        }
        if(check == true) {
            penaltyDao.addPenalty(penalty);
        }
    }
    
    public void handleUpdatePenalty(PenaltyModel penalty, JSONArray list, ArrayList<String[]> oldListPenalty) throws IllegalArgumentException, IllegalAccessException{
        Boolean check = true;
        Class<?> bookClass = penalty.getClass();
        Field[] fields = bookClass.getDeclaredFields(); 
        for (Field field : fields) {
            field.setAccessible(true); 
            Object value = field.get(penalty);
            if (value == null) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                check = false;
                break;
            }
        }
        if(check == true) {
            penaltyDao.updatePenalty(penalty, list, oldListPenalty);
        }
    }
    
    public void handleDeletePenalty (String maTheTV) {
        penaltyDao.deletePenalty(maTheTV);
    }
    
    public ArrayList<String[]> handleSearchPenalty (String maTheTV) {
        return penaltyDao.searchPenalty(maTheTV);
    }
}
