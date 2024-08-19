package service;

import dao.RoomDao;
import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.RoomModel;
import utils.CheckNumeric;
import utils.CheckID;

public class RoomService {
    public RoomDao roomDao;
    public CheckNumeric checkNum = new CheckNumeric();
    public CheckID checkID = new CheckID();
    
    public RoomService() {
        roomDao = new RoomDao();
    }
    
    public ArrayList<String[]> handleGetData() {
        return roomDao.getData();
    }
    
    public ArrayList<String[]> handleGetRoomList() {
        return roomDao.getRoomList();
    }
    
    public void handleAddRoom(RoomModel room) throws IllegalArgumentException, IllegalAccessException{
        Boolean check = true;
        if(CheckID.checkMaTheTV(room.getMaTTV()) == false) {
            JOptionPane.showMessageDialog(null, "Mã TTV không phù hợp");   
            check = false;
        } else {
            Class<?> bookClass = room.getClass();
            Field[] fields = bookClass.getDeclaredFields(); 
            for (Field field : fields) {
                field.setAccessible(true); 
                Object value = field.get(room);
                if (value == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                    check = false;
                    break;
                }
            }   
        }
        if(check == true) {
            roomDao.addRoom(room);
        }
    }
    
    public void handleUpdateRoom (RoomModel room) throws IllegalArgumentException, IllegalAccessException{
        Boolean check = true;
        if(CheckID.checkMaTheTV(room.getMaTTV()) == false) {
            JOptionPane.showMessageDialog(null, "Mã TTV không phù hợp");   
            check = false;
        } else {
            Class<?> bookClass = room.getClass();
            Field[] fields = bookClass.getDeclaredFields(); 
            for (Field field : fields) {
                field.setAccessible(true); 
                Object value = field.get(room);
                if (value == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đủ thông tin");
                    check = false;
                    break;
                }
            }  
        }
        if(check == true) {
            roomDao.updateRoom(room);
        }
    }
    
    public void handleDeleteRoom(String maPhong, String maMuon) {
        roomDao.deleteRoom(maPhong, maMuon);
    }
    
    public ArrayList<String[]> handleSearchRoom(String maTheTV) {
        return roomDao.searchRoom(maTheTV);
    }
}
