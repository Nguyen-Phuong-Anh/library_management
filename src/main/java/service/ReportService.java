package service;

import dao.ReportDao;
import java.util.ArrayList;

public class ReportService {
    public ReportDao reportDao;

    public ReportService() {
        reportDao = new ReportDao();
    }
    
    public ArrayList<String[]> handleGetBook() {
        return reportDao.getBook();
    }
    
    public ArrayList<String[]> handleGetReader() {
        return reportDao.getReader();
    }
    
    public ArrayList<String[]> handleGetBorrowedList () {
        return reportDao.getBorrowedList();
    }
    
    public ArrayList<String[]> handleGetBHis() {
        return reportDao.getBHis();
    }
    
    public ArrayList<String[]> handleGetBorrowedBook() {
        return reportDao.getBorrowedBook();
    }
    
}
