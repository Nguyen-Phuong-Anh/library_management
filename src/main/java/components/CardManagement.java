package components;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.CardModel;
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
import service.CardService;

public class CardManagement extends javax.swing.JInternalFrame {
    private ArrayList<String[]> result = new ArrayList<>();
    public CardService cardService;
    private final String username;
    
    public CardManagement(String username) throws SQLException {
        initComponents();
        this.username = username;
        this.cardService = new CardService();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadData();
        
        cardTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int selectedRow = cardTable.getSelectedRow();
            String mathethuvien = "";
            if (selectedRow != -1) {
                mathethuvien = (String) cardTable.getValueAt(selectedRow, 1);
            }
        });
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onView(int row) {
                int i = cardTable.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) cardTable.getModel();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Boolean trangThai = false;
                if(model.getValueAt(i, 6).toString().equals("Còn hoạt động")) { 
                    trangThai = true;
                }
                
                try {
                    Date date1 = dateFormat.parse(model.getValueAt(i, 4).toString());
                    Date date2 = dateFormat.parse(model.getValueAt(i, 5).toString());
                    CardModel card = new CardModel(model.getValueAt(i, 1).toString(), model.getValueAt(i, 2).toString(), 
                            model.getValueAt(i, 3).toString(), date1, date2, trangThai, Float.valueOf(model.getValueAt(i, 7).toString()), username);
                    CardInfoForm form = new CardInfoForm(card);
                    form.setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(CardManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void onDelete(int row) {
                if(cardTable.isEditing()) {
                    cardTable.getCellEditor().stopCellEditing();
                    int i = cardTable.getSelectedRow();
                    DefaultTableModel model = (DefaultTableModel) cardTable.getModel();
                    cardService.handleDeleteCard(model.getValueAt(i, 1).toString());
                    reloadTableData(); 
                    resetText();
                }
            }
        };
        
        cardTable.getColumnModel().getColumn(8).setCellRenderer(new TableActionCellRender());
        cardTable.getColumnModel().getColumn(8).setCellEditor(new TableActionCellEditor(event));
        
    }
    
    public final void loadData() throws SQLException {
        //get data
        result = cardService.handleGetData();
        for (String[] array : result) {
            DefaultTableModel model = (DefaultTableModel) cardTable.getModel();
            model.addRow(array);
        }
    }
    
    private void reloadTableData() {
        DefaultTableModel model = (DefaultTableModel) cardTable.getModel();
        model.setRowCount(0);

        ArrayList<String[]> result = cardService.handleGetData();
        for (String[] array : result) {
            model.addRow(array);
        }

        cardTable.repaint(); 
    }
    
    private void resetText() {
        maDG_txt.setText("");
        tienPhat_txt.setText("");
        ngayPH_picker.setDate(null);
        ngayHH_picker.setDate(null);
        type_cbb.setSelectedIndex(-1);
        conHD_btn.setSelected(false);
        koHD_btn.setSelected(false);
        add_btn.setEnabled(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hd_group = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cardTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        tienPhat_txt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        add_btn = new javax.swing.JButton();
        update_btn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        ngayPH_picker = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        type_cbb = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        ngayHH_picker = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        maDG_txt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        conHD_btn = new javax.swing.JRadioButton();
        koHD_btn = new javax.swing.JRadioButton();
        search_TTV_txt = new javax.swing.JTextField();
        export_btn = new javax.swing.JButton();

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(770, 624));

        cardTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã thẻ TV", "Loại thẻ", "Mã độc giả", "Ngày phát hành", "Ngày hết hạn", "Trạng thái", "Số tiền phạt", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cardTable.setRowHeight(40);
        cardTable.setSelectionBackground(new java.awt.Color(174, 68, 90));
        cardTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        cardTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(cardTable);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tienPhat_txt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Số tiền phạt");

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
        jLabel8.setText("Ngày phát hành");

        ngayPH_picker.setAutoscrolls(true);
        ngayPH_picker.setDateFormatString("dd/ MM/ y");
        ngayPH_picker.setPreferredSize(new java.awt.Dimension(88, 23));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Loại thẻ");

        type_cbb.setEditable(true);
        type_cbb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Learner", "Normal", "VIP" }));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Ngày hết hạn");

        ngayHH_picker.setDateFormatString("dd/ MM/ y");
        ngayHH_picker.setPreferredSize(new java.awt.Dimension(88, 23));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Mã độc giả");

        maDG_txt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("Trạng thái");

        hd_group.add(conHD_btn);
        conHD_btn.setText("Còn hoạt động");

        hd_group.add(koHD_btn);
        koHD_btn.setText("Không hoạt động");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addComponent(jLabel2))
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ngayPH_picker, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tienPhat_txt, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(type_cbb, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(maDG_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                        .addComponent(add_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(update_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(export_btn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ngayHH_picker, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(conHD_btn)
                                .addGap(18, 18, 18)
                                .addComponent(koHD_btn)))
                        .addContainerGap(52, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(search_TTV_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maDG_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ngayPH_picker, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ngayHH_picker, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(34, 34, 34)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(type_cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(conHD_btn)
                    .addComponent(koHD_btn)
                    .addComponent(jLabel16))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tienPhat_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(add_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(export_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(8, 8, 8)
                .addComponent(search_TTV_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_btnhandleAddBook(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_btnhandleAddBook
        Boolean trangThai = false;
        if(conHD_btn.isSelected()) {
            trangThai = true;
        } else if(!koHD_btn.isSelected()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn trạng thái!"); 
            return;
        }
        
        try {
            if(type_cbb.getSelectedItem() != null) {
                CardModel card = new CardModel("cardID", type_cbb.getSelectedItem().toString(), maDG_txt.getText(), ngayPH_picker.getDate(), 
                        ngayHH_picker.getDate(), trangThai, Float.valueOf(tienPhat_txt.getText()), username);
                cardService.handleAddCard(card);
                reloadTableData();
                resetText();
            }
            else {
                CardModel card = new CardModel("cardID", null, maDG_txt.getText(), ngayPH_picker.getDate(), 
                        ngayHH_picker.getDate(), trangThai, Float.valueOf(tienPhat_txt.getText()), username);
                cardService.handleAddCard(card);
                reloadTableData();
                resetText();
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(CardManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_add_btnhandleAddBook

    private void update_btnhandleUpdateBook(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_btnhandleUpdateBook
        int i = cardTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) cardTable.getModel();
        Boolean trangThai = false;
        if(conHD_btn.isSelected()) {
            trangThai = true;
        } else if(!koHD_btn.isSelected()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn trạng thái!"); 
            return;
        }
        CardModel card = new CardModel(model.getValueAt(i, 1).toString(), type_cbb.getSelectedItem().toString(), maDG_txt.getText(), ngayPH_picker.getDate(), 
                        ngayHH_picker.getDate(), trangThai, Float.valueOf(tienPhat_txt.getText()), "nguoitao");

        try {
            cardService.handleUpdateCard(card);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(BookManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        reloadTableData();
        resetText();
    }//GEN-LAST:event_update_btnhandleUpdateBook

    private void cardTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardTableMouseClicked
        int i = cardTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) cardTable.getModel();
        maDG_txt.setText(model.getValueAt(i, 3).toString());
        if(model.getValueAt(i, 6).toString().equals("Còn hoạt động")) {
            conHD_btn.setSelected(true);
        } else {
            koHD_btn.setSelected(true);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(model.getValueAt(i, 4).toString());
            ngayPH_picker.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(CardManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Date date = dateFormat.parse(model.getValueAt(i, 5).toString());
            ngayHH_picker.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(CardManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        tienPhat_txt.setText(model.getValueAt(i, 7).toString());
        type_cbb.setSelectedItem(model.getValueAt(i, 2).toString());
        add_btn.setEnabled(false);
    }//GEN-LAST:event_cardTableMouseClicked

    private void search_TTV_txtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_TTV_txtKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String maTheTV = search_TTV_txt.getText();
            DefaultTableModel model = (DefaultTableModel) cardTable.getModel();
            model.setRowCount(0);
            ArrayList<String[]> result = cardService.handleSearchCard(maTheTV);
            for (String[] array : result) {
                model.addRow(array);
            }
        }
    }//GEN-LAST:event_search_TTV_txtKeyPressed

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
            XSSFSheet spreadsheet = workbook.createSheet("card");

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 0);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("DANH SÁCH THẺ THƯ VIỆN");
            
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
            cell.setCellValue("Mã thẻ TV");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Loại thẻ");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Mã độc giả");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Ngày phát hành");
            
            cell = row.createCell(5, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Ngày hết hạn");
            
            cell = row.createCell(6, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Trạng thái");
            
            cell = row.createCell(7, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Số tiền phạt");
            
            cell = row.createCell(8, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Người tạo");
            
            cell = row.createCell(9, CellType.STRING);
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

            }
            //Hiệu chỉnh độ rộng của cột
            for (int col = 0; col < 9; col++) {
                spreadsheet.autoSizeColumn(col);
            }

            File f = new File("D:\\xuatfile\\Card.xlsx");
            FileOutputStream out = new FileOutputStream(f);
            workbook.write(out);
            out.close();
           
            JOptionPane.showMessageDialog(null, "Xuất thành file excel thành công");  
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }   
    
    private void export_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_export_btnActionPerformed
        ArrayList<String[]> result = cardService.handleGetDataSet();
        ExportExcel(result);
    }//GEN-LAST:event_export_btnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_btn;
    private javax.swing.JTable cardTable;
    private javax.swing.JRadioButton conHD_btn;
    private javax.swing.JButton export_btn;
    private javax.swing.ButtonGroup hd_group;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton koHD_btn;
    private javax.swing.JTextField maDG_txt;
    private com.toedter.calendar.JDateChooser ngayHH_picker;
    private com.toedter.calendar.JDateChooser ngayPH_picker;
    private javax.swing.JTextField search_TTV_txt;
    private javax.swing.JTextField tienPhat_txt;
    private javax.swing.JComboBox<String> type_cbb;
    private javax.swing.JButton update_btn;
    // End of variables declaration//GEN-END:variables
}
