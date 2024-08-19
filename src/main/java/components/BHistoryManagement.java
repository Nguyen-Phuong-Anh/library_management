package components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.BHistoryModel;
import model.BookListModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.BHistoryService;
import service.BookService;
import org.json.JSONObject;
import org.json.JSONArray;

public class BHistoryManagement extends javax.swing.JInternalFrame {
    private ArrayList<String[]> result = new ArrayList<>();
    public BHistoryService historyService;
    private final String username; 
    private ArrayList<String[]> listBook = new ArrayList<>();
    BookService bookService = new BookService();
    private ArrayList<String[]> oldListBook = new ArrayList<>();
    
    public BHistoryManagement(String username) throws SQLException {
        initComponents();
        this.username = username;
        this.historyService = new BHistoryService();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadData();
        getFilter();
        
        bHisTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int selectedRow = bHisTable.getSelectedRow();
            String maMuonTra = "";
            if (selectedRow != -1) {
                maMuonTra = (String) bHisTable.getValueAt(selectedRow, 1);
            }
        });
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onView(int row) {
                int i = bHisTable.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) bHisTable.getModel();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Boolean trangThai = false;
                if(model.getValueAt(i, 7).toString().equals("Đã trả")) { 
                    trangThai = true;
                }
                try {
                    Date date = dateFormat.parse(model.getValueAt(i, 4).toString());
                    Date date1 = dateFormat.parse(model.getValueAt(i, 5).toString());
                    Date date2 = null;
                    if(model.getValueAt(i, 6) != null) {
                        date2 = dateFormat.parse(model.getValueAt(i, 6).toString());
                    }
                    BHistoryModel history = new BHistoryModel("MaMuonSach", model.getValueAt(i, 2).toString(), 
                        model.getValueAt(i, 3).toString(), date, date1, date2,
                        trangThai, Integer.valueOf(model.getValueAt(i, 8).toString()), username);
                    BHisInfoForm form = new BHisInfoForm(history);
                    form.setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(BHistoryManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void onDelete(int row) {
                bHisTable.getCellEditor().stopCellEditing();
                DefaultTableModel model = (DefaultTableModel) bHisTable.getModel();
                if(model.getValueAt(row, 1).toString().equals("Đã trả")) {
                    if (row >= 0 && row < model.getRowCount()) {
                        historyService.handleDeleteHistory(model.getValueAt(row, 1).toString());
                        model.removeRow(row);
                        resetText();
                        add_btn.setEnabled(true);
                        update_btn.setEnabled(true);
                        addBook_btn.setEnabled(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Sách chưa được trả!"); 
                }
            }
        };
        
        bHisTable.getColumnModel().getColumn(9).setCellRenderer(new TableActionCellRender());
        bHisTable.getColumnModel().getColumn(9).setCellEditor(new TableActionCellEditor(event));
        
        addBook_dialog.setSize(592, 358);
        addBook_dialog.setLocationRelativeTo(this);
        addBook_dialog.setVisible(false);
    }    
    
    
    private static List<String[]> convertJsonb(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<BookListModel> bookList = objectMapper.readValue(jsonString, new TypeReference<List<BookListModel>>() {});
            
            List<String[]> resultList = new ArrayList<>();
            for(BookListModel list : bookList) {
                String[] arr = {
                    list.getMaSach(),
                    list.getTenSach(),
                    list.getSoLuong().toString()
                };
                resultList.add(arr);
            }
            return resultList;
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    
    public final void getFilter() {
        Map<String, ArrayList> map = bookService.handleGetBookFilter();
        for (Map.Entry<String, ArrayList> entry : map.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> arrayList = entry.getValue();
            
            if(key.equals("publisher")) {
                for (String item : arrayList) {
                    publisher_cbb.addItem(item);
                }
            } else if(key.equals("author")) {
                for (String item : arrayList) {
                    author_cbb.addItem(item);
                }
            } else {
                for (String item : arrayList) {
                    gernes_cbb.addItem(item);
                }
            }
        }
        publisher_cbb.setSelectedIndex(-1);
        author_cbb.setSelectedIndex(-1);
        gernes_cbb.setSelectedIndex(-1);
    }
    
    public final void loadData() throws SQLException {
        result = historyService.handleGetData(username);
        
        for (String[] array : result) {
            DefaultTableModel model = (DefaultTableModel) bHisTable.getModel();
            model.addRow(array);
        }
    }
    
    private void reloadTableData() {
        DefaultTableModel model = (DefaultTableModel) bHisTable.getModel();
        model.setRowCount(0);
        ArrayList<String[]> result = historyService.handleGetData(username);
        for (String[] array : result) {
            model.addRow(array);
        }
    }
    
    private void resetText() {
        maTV_txt.setText("");
        maTV_txt.setEditable(true);
        slGiaHan_num.setValue(0);
        ngayMuon_picker.setDate(null);
        ngayHetHan_picker.setDate(null);
        ngayTra_picker.setDate(null);
        daTra_btn.setEnabled(true);
        trangThai_group.clearSelection();
        DefaultTableModel tbmodel = (DefaultTableModel) bookList.getModel();
        tbmodel.setRowCount(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        trangThai_group = new javax.swing.ButtonGroup();
        addBook_dialog = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tieuDe_txt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        author_cbb = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        gernes_cbb = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        publisher_cbb = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        searchBtn1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        list_Book = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        soLuong_num = new javax.swing.JSpinner();
        addBtn = new javax.swing.JButton();
        clear_btn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bHisTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        maTV_txt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        add_btn = new javax.swing.JButton();
        update_btn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        ngayMuon_picker = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        ngayHetHan_picker = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        ngayTra_picker = new com.toedter.calendar.JDateChooser();
        daTra_btn = new javax.swing.JRadioButton();
        chuaTra_btn = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        slGiaHan_num = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        bookList = new javax.swing.JTable();
        addBook_btn = new javax.swing.JButton();
        search_TTV_txt = new javax.swing.JTextField();
        export_btn = new javax.swing.JButton();
        warning_txt = new javax.swing.JLabel();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Tiêu đề");

        tieuDe_txt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Tác giả");

        author_cbb.setEditable(true);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Thể loại");

        gernes_cbb.setEditable(true);

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("NXB");

        publisher_cbb.setEditable(true);

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel19.setText("TÌM KIẾM SÁCH");

        searchBtn1.setText("Tìm kiếm");
        searchBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchBtn1MouseClicked(evt);
            }
        });

        jScrollPane2.setViewportView(list_Book);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Số lượng");

        soLuong_num.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));

        addBtn.setText("Thêm");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        clear_btn.setText("Clear");
        clear_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(328, 328, 328)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(soLuong_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addBtn))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(author_cbb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tieuDe_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(gernes_cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(searchBtn1)
                                        .addGap(18, 18, 18)
                                        .addComponent(clear_btn))
                                    .addComponent(publisher_cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tieuDe_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(author_cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(gernes_cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(publisher_cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(soLuong_num, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(clear_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout addBook_dialogLayout = new javax.swing.GroupLayout(addBook_dialog.getContentPane());
        addBook_dialog.getContentPane().setLayout(addBook_dialogLayout);
        addBook_dialogLayout.setHorizontalGroup(
            addBook_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addBook_dialogLayout.setVerticalGroup(
            addBook_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(770, 624));

        bHisTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã mượn trả", "Mã thẻ TV", "DS Sách mượn", "Ngày mượn", "Ngày hết hạn", "Ngày trả", "Trạng thái", "Số lần gia hạn", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bHisTable.setAutoResizeMode(0);
        bHisTable.setRowHeight(40);
        bHisTable.setSelectionBackground(new java.awt.Color(174, 68, 90));
        bHisTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        bHisTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bHisTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bHisTable);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        maTV_txt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Mã thẻ TV");

        add_btn.setText("Add");
        add_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_btnhandleAddBook(evt);
            }
        });

        update_btn.setText("Update");
        update_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                update_btnhandleUpdateBook(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Ngày mượn");

        ngayMuon_picker.setDateFormatString("dd/ MM/ y");
        ngayMuon_picker.setPreferredSize(new java.awt.Dimension(88, 23));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setText("Ngày hết hạn");

        ngayHetHan_picker.setDateFormatString("dd/ MM/ y");
        ngayHetHan_picker.setPreferredSize(new java.awt.Dimension(88, 23));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Ngày trả");

        ngayTra_picker.setDateFormatString("dd/ MM/ y");
        ngayTra_picker.setPreferredSize(new java.awt.Dimension(88, 23));

        trangThai_group.add(daTra_btn);
        daTra_btn.setText("Đã trả");
        daTra_btn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                daTra_btnItemStateChanged(evt);
            }
        });

        trangThai_group.add(chuaTra_btn);
        chuaTra_btn.setText("Chưa trả");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("Trạng thái");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Số lần gia hạn");

        slGiaHan_num.setModel(new javax.swing.SpinnerNumberModel(0, 0, 5, 1));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("DS mượn");

        bookList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sách", "Tên sách", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(bookList);

        addBook_btn.setText("+");
        addBook_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleAddToBookList(evt);
            }
        });

        search_TTV_txt.setText("Mã thẻ thư viện");
        search_TTV_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search_TTV_txtKeyPressed(evt);
            }
        });

        export_btn.setText("Export");
        export_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                export_btnActionPerformed(evt);
            }
        });

        warning_txt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        warning_txt.setText("warning_label");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(daTra_btn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(chuaTra_btn))
                                            .addComponent(ngayMuon_picker, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(54, 54, 54)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(slGiaHan_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(ngayHetHan_picker, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(52, 52, 52)
                                                .addComponent(jLabel15)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ngayTra_picker, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(24, 24, 24)
                                        .addComponent(maTV_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 51, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(warning_txt)
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(addBook_btn)
                                                .addGap(30, 30, 30))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(add_btn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(update_btn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(export_btn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(search_TTV_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maTV_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(ngayMuon_picker, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ngayHetHan_picker, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel14))))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(jLabel15))
                        .addComponent(ngayTra_picker, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(daTra_btn)
                            .addComponent(chuaTra_btn)
                            .addComponent(jLabel16)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(slGiaHan_num, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(search_TTV_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addBook_btn))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(warning_txt)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(add_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(export_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 797, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void add_btnhandleAddBook(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_btnhandleAddBook
        Boolean trangThai = false;
        if(daTra_btn.isSelected()) {
            trangThai = true;
        } else if(!chuaTra_btn.isSelected()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn trạng thái!"); 
            return;
        }
        
        JSONArray list = new JSONArray();
        DefaultTableModel tbmodel = (DefaultTableModel) bookList.getModel();
        if(tbmodel.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sách cần mượn!"); 
            return;
        }
        int tongSL = 0;
        for(int i = 0; i < tbmodel.getRowCount(); i++) {
            JSONObject book = new JSONObject();
            book.put("maSach", tbmodel.getValueAt(i, 0));
            book.put("tenSach", tbmodel.getValueAt(i, 1));
            book.put("soLuong", tbmodel.getValueAt(i, 2));
            tongSL += Integer.valueOf(tbmodel.getValueAt(i, 2).toString());
            list.put(book);
        }
        
        String bookListString = list.toString();

        try {
            BHistoryModel history = new BHistoryModel("maMuonTra", maTV_txt.getText(), bookListString, ngayMuon_picker.getDate(), ngayHetHan_picker.getDate(), 
                    ngayTra_picker.getDate(), trangThai, Integer.valueOf(slGiaHan_num.getValue().toString()), username);
            
            historyService.handleAddHistory(history, list, tongSL);
            reloadTableData();
            resetText();
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(BHistoryManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_add_btnhandleAddBook

    private void update_btnhandleUpdateBook(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_btnhandleUpdateBook
        int row = bHisTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) bHisTable.getModel();
        Boolean trangThai = false;
        if(daTra_btn.isSelected()) {
            trangThai = true;
        }
        
        JSONArray list = new JSONArray();
        DefaultTableModel tbmodel = (DefaultTableModel) bookList.getModel();
        if(tbmodel.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sách cần mượn!"); 
            return;
        }
        for(int i = 0; i < tbmodel.getRowCount(); i++) {
            JSONObject book = new JSONObject();
            book.put("maSach", tbmodel.getValueAt(i, 0));
            book.put("tenSach", tbmodel.getValueAt(i, 1));
            book.put("soLuong", tbmodel.getValueAt(i, 2));
            list.put(book);
        }
        
        String bookListString = list.toString();
        
        BHistoryModel history = new BHistoryModel(model.getValueAt(row, 1).toString(), maTV_txt.getText(), bookListString, ngayMuon_picker.getDate(), 
                ngayHetHan_picker.getDate(), ngayTra_picker.getDate(), trangThai, Integer.valueOf(slGiaHan_num.getValue().toString()), username);
        
        try {
            historyService.handleUpdateHistory(history, list, oldListBook);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(BookManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        reloadTableData();
        resetText();
    }//GEN-LAST:event_update_btnhandleUpdateBook

    private void handleAddToBookList(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_handleAddToBookList
        addBook_dialog.setVisible(true);
    }//GEN-LAST:event_handleAddToBookList

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        int index = list_Book.getSelectedIndex();
        if(index < 0) {
            JOptionPane.showMessageDialog(this, "Chưa có sách!");
            return;
        }
        try {
            int quantityValue = Integer.parseInt(soLuong_num.getValue().toString());
            int newQuantity = Integer.parseInt(listBook.get(index)[2]) - quantityValue;
            DefaultTableModel model = (DefaultTableModel) bookList.getModel();
            
            if(newQuantity > 0) {
                String[] row = {listBook.get(index)[0], listBook.get(index)[1], soLuong_num.getValue().toString()};

                Boolean existed = false;
                for(int i = 0; i < model.getRowCount(); i++) {
                    if(Integer.parseInt(model.getValueAt(i, 2).toString()) == 5) {
                        JOptionPane.showMessageDialog(this, "Số lượng được mượn đối với sách đạt tối đa!");
                        addBook_dialog.setVisible(false);
                        return;
                    }
                    Object maSach = model.getValueAt(i, 0);
                    if(maSach.equals(listBook.get(index)[0])) {
                        int newQuan = quantityValue + Integer.parseInt(model.getValueAt(i, 2).toString());
                        model.setValueAt(newQuan, i, 2);
                        existed = true;
                        break;
                    }
                }
                if(!existed) {
                   model.addRow(row);
                }
                listBook.get(index)[2] = String.valueOf(newQuantity);
                daTra_btn.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Sách để mượn đã hết!");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xử lý số lượng!");
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void searchBtn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchBtn1MouseClicked
        Map<String, String> map = new HashMap<>();
        map.put("tieuDe", tieuDe_txt.getText());
        if(author_cbb.getSelectedItem() != null) {
            map.put("tacGia", author_cbb.getSelectedItem().toString());   
        } else {
            map.put("tacGia", "");
        }
        if(gernes_cbb.getSelectedItem() != null) {
            map.put("theLoai", gernes_cbb.getSelectedItem().toString());
        } else {
            map.put("theLoai", "");
        }
        if(publisher_cbb.getSelectedItem() != null) {
            map.put("NXB", publisher_cbb.getSelectedItem().toString());
        } else {
            map.put("NXB", "");
        }
        
        listBook = bookService.handleSearchBookForAdding(map);
        DefaultListModel<String> model = new DefaultListModel<>();
        model.clear();
        for(String[] item : listBook) {
            model.addElement(item[1]);
        }
        list_Book.setModel(model);
    }//GEN-LAST:event_searchBtn1MouseClicked

    private void clear_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_btnActionPerformed
        author_cbb.setSelectedIndex(-1);
        gernes_cbb.setSelectedIndex(-1);
        publisher_cbb.setSelectedIndex(-1);
        tieuDe_txt.setText("");    
        list_Book.removeAll();
        DefaultListModel model = (DefaultListModel) list_Book.getModel();
        model.clear();
    }//GEN-LAST:event_clear_btnActionPerformed

    private void search_TTV_txtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_TTV_txtKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String maTheTV = search_TTV_txt.getText();
            DefaultTableModel model = (DefaultTableModel) bHisTable.getModel();
            model.setRowCount(0);
            ArrayList<String[]> result = historyService.handleSearchHistory(maTheTV);
            for (String[] array : result) {
                model.addRow(array);
            }
        }
    }//GEN-LAST:event_search_TTV_txtKeyPressed

    private void daTra_btnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_daTra_btnItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED) {
            addBook_btn.setEnabled(false);
            add_btn.setEnabled(false);
        } else {
            add_btn.setEnabled(false);
            addBook_btn.setEnabled(true);
        }
    }//GEN-LAST:event_daTra_btnItemStateChanged

    private static CellStyle createStyleForHeader(XSSFSheet sheet) {
        // Create font
        XSSFFont font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 12); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.TOP);
        cellStyle.setFillForegroundColor(IndexedColors.DARK_GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setWrapText(true);
        return cellStyle;
    }
    
    private void ExportExcel(ArrayList<String[]> result){
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("book");

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 0);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH MƯỢN TRẢ");
            
            CellRangeAddress mergedRegion = new CellRangeAddress(0, 0, 0, 9);
            
            spreadsheet.addMergedRegion(mergedRegion);
            
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setFontHeightInPoints((short) 14);
            font.setBold(true);
            style.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            cell.setCellStyle(style);

            //Tạo dòng tiêu đều của bảng
            // create CellStyle
            CellStyle cellStyle_Head = createStyleForHeader(spreadsheet);
            row = spreadsheet.createRow((short) 1);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("STT");
            
            cell = row.createCell(1, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã mượn trả");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã thẻ TV");
            
            cell = row.createCell(3, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("DS Mượn");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Ngày mượn");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Ngày hết hạn");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Ngày trả");
            
            cell = row.createCell(7, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Trạng thái");
            
            cell = row.createCell(8, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Số lần gia hạn");
            
            cell = row.createCell(9, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Người thực hiện");
            
            cell = row.createCell(10, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Ngày tạo");

            //Đinh dạng Tạo đường kẻ cho ô chứa dữ liệu
            CellStyle cellStyle_data = spreadsheet.getWorkbook().createCellStyle();
            cellStyle_data.setBorderLeft(BorderStyle.THIN);
            cellStyle_data.setBorderRight(BorderStyle.THIN);
            cellStyle_data.setBorderBottom(BorderStyle.THIN);

            for(int i = 0; i < result.size(); i++) {
                int j = 0;
                row = spreadsheet.createRow((short) 2 + i);
                row.setHeight((short) 400);

                cell = row.createCell(0);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(i + 1);

                cell = row.createCell(1);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(result.get(i)[j]);

                cell = row.createCell(2);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(result.get(i)[j + 1]);
               
                cell=row.createCell(3);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(result.get(i)[j + 2]);
                
                cell=row.createCell(4);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(result.get(i)[j + 3]);
                
                cell=row.createCell(5);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(result.get(i)[j + 4]);
                
                cell=row.createCell(6);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(result.get(i)[j + 5]);
                
                cell=row.createCell(7);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(result.get(i)[j + 6]);
                
                cell=row.createCell(8);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(result.get(i)[j + 7]);
                
                cell=row.createCell(9);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(result.get(i)[j + 8]);
                
                cell=row.createCell(10);
                cell.setCellStyle(cellStyle_data);
                cell.setCellValue(result.get(i)[j + 9]);

            }
            //Hiệu chỉnh độ rộng của cột
            for (int col = 0; col < 9; col++) {
                spreadsheet.autoSizeColumn(col);
            }

            File f = new File("D:\\xuatfile\\BHistory.xlsx");
            FileOutputStream out = new FileOutputStream(f);
            workbook.write(out);
            out.close();
           
            JOptionPane.showMessageDialog(null, "Xuất thành file excel thành công");  
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }  
    
    private void export_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_export_btnActionPerformed
        ArrayList<String[]> result = historyService.handleGetDataSet();
        ExportExcel(result);
    }//GEN-LAST:event_export_btnActionPerformed

    private void bHisTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bHisTableMouseClicked
        resetText();
        int i = bHisTable.getSelectedRow();
        if(i != -1) {
            DefaultTableModel model = (DefaultTableModel) bHisTable.getModel();
            maTV_txt.setText(model.getValueAt(i, 2).toString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = dateFormat.parse(model.getValueAt(i, 4).toString());
                ngayMuon_picker.setDate(date);
                Date date1 = dateFormat.parse(model.getValueAt(i, 5).toString());
                ngayHetHan_picker.setDate(date1);
                if(model.getValueAt(i, 6) != null) {
                    Date date2 = dateFormat.parse(model.getValueAt(i, 6).toString());
                    ngayTra_picker.setDate(date2);
                }
            } catch (ParseException ex) {
                Logger.getLogger(BHistoryManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
            String trangThai = model.getValueAt(i, 7).toString();
            if(trangThai.equals("Đã trả")) {
                add_btn.setEnabled(false);
                update_btn.setEnabled(false);
                daTra_btn.setSelected(true);
            } else {
                add_btn.setEnabled(false);
                chuaTra_btn.setSelected(true);
            }
            if(model.getValueAt(i, 8) != null) {
                slGiaHan_num.setValue(Integer.valueOf(model.getValueAt(i, 8).toString()));
            } else {
                slGiaHan_num.setValue(0);
            }

            ArrayList<String[]> arr = (ArrayList<String[]>) convertJsonb(model.getValueAt(i, 3).toString());
            DefaultTableModel model1 = (DefaultTableModel) bookList.getModel();
            model1.setRowCount(0);
            for(String[] item : arr) {
                model1 = (DefaultTableModel) bookList.getModel();
                model1.addRow(item);
            }

            oldListBook = arr;
            maTV_txt.setEditable(false);
        }
    }//GEN-LAST:event_bHisTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBook_btn;
    private javax.swing.JDialog addBook_dialog;
    private javax.swing.JButton addBtn;
    private javax.swing.JButton add_btn;
    private javax.swing.JComboBox<String> author_cbb;
    private javax.swing.JTable bHisTable;
    private javax.swing.JTable bookList;
    private javax.swing.JRadioButton chuaTra_btn;
    private javax.swing.JButton clear_btn;
    private javax.swing.JRadioButton daTra_btn;
    private javax.swing.JButton export_btn;
    private javax.swing.JComboBox<String> gernes_cbb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> list_Book;
    private javax.swing.JTextField maTV_txt;
    private com.toedter.calendar.JDateChooser ngayHetHan_picker;
    private com.toedter.calendar.JDateChooser ngayMuon_picker;
    private com.toedter.calendar.JDateChooser ngayTra_picker;
    private javax.swing.JComboBox<String> publisher_cbb;
    private javax.swing.JButton searchBtn1;
    private javax.swing.JTextField search_TTV_txt;
    private javax.swing.JSpinner slGiaHan_num;
    private javax.swing.JSpinner soLuong_num;
    private javax.swing.JTextField tieuDe_txt;
    private javax.swing.ButtonGroup trangThai_group;
    private javax.swing.JButton update_btn;
    private javax.swing.JLabel warning_txt;
    // End of variables declaration//GEN-END:variables
}
