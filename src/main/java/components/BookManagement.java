package components;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.BookModel;
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
import service.BookService;

public class BookManagement extends javax.swing.JInternalFrame {
    public BookService bookService;
    
    public BookManagement() throws SQLException {
        initComponents();
        this.bookService = new BookService();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadData();
        getFilter();
        
        bookTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int selectedRow = bookTable.getSelectedRow();
            String masach = "";
            if (selectedRow != -1) {
                masach = (String) bookTable.getValueAt(selectedRow, 1);
            }
        });
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onView(int row) {
                int i = bookTable.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
                BookModel book = new BookModel(model.getValueAt(i, 1).toString(), model.getValueAt(i, 2).toString(), model.getValueAt(i, 3).toString(), 
                        model.getValueAt(i, 4).toString(), 
                model.getValueAt(i, 5).toString(), model.getValueAt(i, 6).toString(), model.getValueAt(i, 7).toString(), model.getValueAt(i, 8).toString());
                BookInfoForm form = new BookInfoForm(book);
                form.setVisible(true);
            }

            @Override
            public void onDelete(int row) {
                if(bookTable.isEditing()) {
                    bookTable.getCellEditor().stopCellEditing();
                    int i = bookTable.getSelectedRow();
                    DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
                    bookService.handleDeleteBook(model.getValueAt(i, 1).toString());
                    reloadTableData(); 
                    resetText();
                }
            }
        };
        
        bookTable.getColumnModel().getColumn(9).setCellRenderer(new TableActionCellRender());
        bookTable.getColumnModel().getColumn(9).setCellEditor(new TableActionCellEditor(event));
        
        search_Dialog.setSize(575, 289);
        search_Dialog.setLocationRelativeTo(this);
        search_Dialog.setVisible(false);
    }
    
    public final void loadData() throws SQLException {
        //get data
        ArrayList<String[]> result = bookService.handleGetData();
        for (String[] array : result) {
            DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
            model.addRow(array);
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
                    publisher_cbb1.addItem(item);
                }
            } else if(key.equals("author")) {
                for (String item : arrayList) {
                    author_cbb.addItem(item);
                    author_cbb1.addItem(item);
                }
            } else {
                for (String item : arrayList) {
                    gernes_cbb.addItem(item);
                    gernes_cbb1.addItem(item);
                }
            }
        }
        publisher_cbb.setSelectedIndex(-1);
        publisher_cbb1.setSelectedIndex(-1);
        author_cbb.setSelectedIndex(-1);
        author_cbb1.setSelectedIndex(-1);
        gernes_cbb.setSelectedIndex(-1);
        gernes_cbb1.setSelectedIndex(-1);
    }
    
    private void reloadTableData() {
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.setRowCount(0);

        ArrayList<String[]> result = bookService.handleGetData();
        for (String[] array : result) {
            model.addRow(array);
        }
    }
    
    private void resetText() {
        giaTien_txt.setText("");
        publisher_cbb.setSelectedIndex(-1);
        author_cbb.setSelectedIndex(-1);
        gernes_cbb.setSelectedIndex(-1);
        tieuDe_txt.setText("");
        soLuong_num.setValue(0);
        SLht_num.setValue(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        search_Dialog = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        tieuDe_txt1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        searchBtn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        gernes_cbb1 = new javax.swing.JComboBox<>();
        author_cbb1 = new javax.swing.JComboBox<>();
        publisher_cbb1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        tieuDe_txt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        soLuong_num = new javax.swing.JSpinner();
        SLht_num = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        giaTien_txt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        add_btn = new javax.swing.JButton();
        update_btn = new javax.swing.JButton();
        publisher_cbb = new javax.swing.JComboBox<>();
        author_cbb = new javax.swing.JComboBox<>();
        gernes_cbb = new javax.swing.JComboBox<>();
        search_btn = new javax.swing.JButton();
        export_btn = new javax.swing.JButton();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Tiêu đề");

        tieuDe_txt1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Tác giả");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("NXB");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel11.setText("TÌM KIẾM");

        searchBtn.setText("Tìm kiếm");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Thể loại");

        gernes_cbb1.setEditable(true);

        author_cbb1.setEditable(true);

        publisher_cbb1.setEditable(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(tieuDe_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gernes_cbb1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel11))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(author_cbb1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(46, 46, 46)
                                .addComponent(jLabel10)))
                        .addGap(18, 18, 18)
                        .addComponent(publisher_cbb1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(searchBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel11)
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tieuDe_txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(gernes_cbb1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(author_cbb1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(publisher_cbb1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout search_DialogLayout = new javax.swing.GroupLayout(search_Dialog.getContentPane());
        search_Dialog.getContentPane().setLayout(search_DialogLayout);
        search_DialogLayout.setHorizontalGroup(
            search_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(search_DialogLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        search_DialogLayout.setVerticalGroup(
            search_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(770, 624));

        bookTable.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        bookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sách", "Tiêu đề", "Tác giả", "Thể loại", "Số lượng", "SL đang có", "Giá tiền", "NXB", ""
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
        bookTable.setRowHeight(40);
        bookTable.setSelectionBackground(new java.awt.Color(174, 68, 90));
        bookTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        bookTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bookTable);
        if (bookTable.getColumnModel().getColumnCount() > 0) {
            bookTable.getColumnModel().getColumn(9).setResizable(false);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tieuDe_txt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Tiêu đề");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Tác giả");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Thể loại");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Số lượng");

        soLuong_num.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        SLht_num.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("SL hiện tại");

        giaTien_txt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Giá tiền");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("NXB");

        add_btn.setText("Add");
        add_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleAddBook(evt);
            }
        });

        update_btn.setText("Update");
        update_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleUpdateBook(evt);
            }
        });

        publisher_cbb.setEditable(true);

        author_cbb.setEditable(true);

        gernes_cbb.setEditable(true);

        search_btn.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Pictures\\New folder (2)\\icons8-search-25.png")); // NOI18N
        search_btn.setBorder(null);
        search_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_btnhandleUpdateBook(evt);
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
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tieuDe_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                                        .addComponent(jLabel6))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(author_cbb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(97, 97, 97)
                                        .addComponent(jLabel7)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(giaTien_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(publisher_cbb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(gernes_cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 339, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(soLuong_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(SLht_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(add_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(update_btn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(export_btn)
                                .addGap(36, 36, 36)))
                        .addComponent(search_btn))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tieuDe_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(author_cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(gernes_cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(giaTien_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(publisher_cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(soLuong_num, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 19, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SLht_num, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(search_btn)
                            .addComponent(add_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(update_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(export_btn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void handleAddBook(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_handleAddBook
        BookModel book = new BookModel("bookID", tieuDe_txt.getText(), author_cbb.getSelectedItem().toString(), gernes_cbb.getSelectedItem().toString(), 
        String.valueOf(soLuong_num.getValue()), String.valueOf(SLht_num.getValue()), giaTien_txt.getText(), publisher_cbb.getSelectedItem().toString());
        
        try {
            bookService.handleAddBook(book);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(BookManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        reloadTableData();
        resetText();
    }//GEN-LAST:event_handleAddBook

    private void bookTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookTableMouseClicked
        int i = bookTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        tieuDe_txt.setText(model.getValueAt(i, 2).toString());
        author_cbb.setSelectedItem(model.getValueAt(i, 3).toString());
        gernes_cbb.setSelectedItem(model.getValueAt(i, 4).toString());
        soLuong_num.setValue(Integer.valueOf(model.getValueAt(i, 5).toString()));
        SLht_num.setValue(Integer.valueOf(model.getValueAt(i, 6).toString()));
        giaTien_txt.setText(model.getValueAt(i, 7).toString());
        publisher_cbb.setSelectedItem(model.getValueAt(i, 8).toString());
    }//GEN-LAST:event_bookTableMouseClicked

    private void handleUpdateBook(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_handleUpdateBook
        int i = bookTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        
        BookModel book = new BookModel(model.getValueAt(i, 1).toString(), tieuDe_txt.getText(), author_cbb.getSelectedItem().toString(), gernes_cbb.getSelectedItem().toString(), 
        String.valueOf(soLuong_num.getValue()), String.valueOf(SLht_num.getValue()), giaTien_txt.getText(), publisher_cbb.getSelectedItem().toString());
        
        try {
            bookService.handleUpdateBook(book);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(BookManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        reloadTableData();
        resetText();
    }//GEN-LAST:event_handleUpdateBook

    private void search_btnhandleUpdateBook(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_btnhandleUpdateBook
        search_Dialog.setVisible(true);
    }//GEN-LAST:event_search_btnhandleUpdateBook

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        BookModel book = new BookModel();
        book.setTieuDe(tieuDe_txt1.getText());
        if(author_cbb1.getSelectedIndex() != -1) { 
            book.setTenTacGia(author_cbb1.getSelectedItem().toString());
        } else {
            book.setTenTacGia("");
        }
        if(publisher_cbb1.getSelectedIndex() != -1) { 
            book.setTenNXB(publisher_cbb1.getSelectedItem().toString());
        } else {
            book.setTenNXB("");
        }
        if(gernes_cbb1.getSelectedIndex() != -1) { 
            book.setTenTheLoai(gernes_cbb1.getSelectedItem().toString());
        } else {
            book.setTenTheLoai("");
        }
        DefaultTableModel model = (DefaultTableModel) bookTable.getModel();
        model.setRowCount(0);
        ArrayList<String[]> result = bookService.handleSearchBook(book);
        for (String[] array : result) {
            model.addRow(array);
        }
        search_Dialog.setVisible(false);
    }//GEN-LAST:event_searchBtnActionPerformed

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
            cell.setCellValue("DANH SÁCH SÁCH");
            
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
            cell.setCellValue("Mã sách");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Tiêu đề");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Tên tác giả");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Tên thể loại");
            
            cell = row.createCell(5, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Số lượng");
            
            cell = row.createCell(6, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Số lượng hiện tại");
            
            cell = row.createCell(7, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Giá tiền");
            
            cell = row.createCell(8, CellType.STRING);
            cell.setCellStyle(cellStyle_Head);
            cell.setCellValue("Tên NXB");
            
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

            File f = new File("D:\\xuatfile\\Books.xlsx");
            FileOutputStream out = new FileOutputStream(f);
            workbook.write(out);
            out.close();
           
            JOptionPane.showMessageDialog(null, "Xuất thành file excel thành công");  
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }   
    
    private void export_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_export_btnActionPerformed
        ArrayList<String[]> result = bookService.handleGetDataSet();
        ExportExcel(result);
    }//GEN-LAST:event_export_btnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner SLht_num;
    private javax.swing.JButton add_btn;
    private javax.swing.JComboBox<String> author_cbb;
    private javax.swing.JComboBox<String> author_cbb1;
    private javax.swing.JTable bookTable;
    private javax.swing.JButton export_btn;
    private javax.swing.JComboBox<String> gernes_cbb;
    private javax.swing.JComboBox<String> gernes_cbb1;
    private javax.swing.JTextField giaTien_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> publisher_cbb;
    private javax.swing.JComboBox<String> publisher_cbb1;
    private javax.swing.JButton searchBtn;
    private javax.swing.JDialog search_Dialog;
    private javax.swing.JButton search_btn;
    private javax.swing.JSpinner soLuong_num;
    private javax.swing.JTextField tieuDe_txt;
    private javax.swing.JTextField tieuDe_txt1;
    private javax.swing.JButton update_btn;
    // End of variables declaration//GEN-END:variables
}

