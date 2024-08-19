package components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.PenaltyListModel;
import model.PenaltyModel;
import org.json.JSONArray;
import org.json.JSONObject;
import service.PenaltyService;

public class PenaltyManagement extends javax.swing.JInternalFrame {
    private ArrayList<String[]> result = new ArrayList<>();
    public PenaltyService penaltyService;
    private final String username;
    private ArrayList<String[]> oldListPenalty = new ArrayList<>();
    private Map<String, Float> penaltyData = new HashMap<>();
    Float oldTienPhat;
    
    public PenaltyManagement(String username) throws SQLException {
        initComponents();
        this.username = username;
        this.penaltyService = new PenaltyService();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadData();
        
        penaltyTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            int selectedRow = penaltyTable.getSelectedRow();
            String mathethuvien = "";
            if (selectedRow != -1) {
                mathethuvien = (String) penaltyTable.getValueAt(selectedRow, 1);
            }
        });
        
        DefaultTableModel listModel = (DefaultTableModel) penaltyList.getModel();
        listModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                Float tongTienPhat = Float.valueOf(0);
                for (int i = 0; i < penaltyList.getRowCount(); i++) {
                    if(penaltyList.getValueAt(i, 3).toString().equals("Chưa nộp")) {
                        tongTienPhat += Float.valueOf(penaltyList.getValueAt(i, 2).toString());   
                    }
                }
                tongTienPhat_txt.setText(tongTienPhat.toString());
            }
        });
        
        update_btn1.setEnabled(false);
    }
    
    public final void loadData() throws SQLException {
        //get data
        result = penaltyService.handleGetData();
        for (String[] array : result) {
            DefaultTableModel model = (DefaultTableModel) penaltyTable.getModel();
            model.addRow(array);
        }
    }
    
    private void reloadTableData() {
        DefaultTableModel model = (DefaultTableModel) penaltyTable.getModel();
        model.setRowCount(0);

        ArrayList<String[]> result = penaltyService.handleGetData();
        for (String[] array : result) {
            model.addRow(array);
        }

        penaltyTable.repaint(); 
    }
    
    private void resetText() {
        maTTV_txt.setText("");
        tongTienPhat_txt.setText("");
        tienPhat_txt.setText("");
        DefaultTableModel model = (DefaultTableModel) penaltyList.getModel();
        model.setRowCount(0);
        penaltyGrp_cbb.setSelectedIndex(-1);
        penaltyName_cb.removeAllItems();
        trangThai_grp.clearSelection();
        tongTienPhat_txt.setText("");
        soLuong_num.setValue(1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        trangThai_grp = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        penaltyTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        tongTienPhat_txt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        add_btn = new javax.swing.JButton();
        update_btn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        maTTV_txt = new javax.swing.JTextField();
        search_TTV_txt = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        penaltyList = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        penaltyGrp_cbb = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        penaltyName_cb = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        soLuong_num = new javax.swing.JSpinner();
        addBtn1 = new javax.swing.JButton();
        delete_btn1 = new javax.swing.JButton();
        tienPhat_txt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        update_btn1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        daNop_btn = new javax.swing.JRadioButton();
        chuaNop_btn = new javax.swing.JRadioButton();
        delete_btn = new javax.swing.JButton();

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(770, 624));

        penaltyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã thẻ TV", "DS Lỗi", "Số tiền phạt"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        penaltyTable.setRowHeight(40);
        penaltyTable.setSelectionBackground(new java.awt.Color(174, 68, 90));
        penaltyTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        penaltyTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                penaltyTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(penaltyTable);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        tongTienPhat_txt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tongTienPhat_txt.setText("0");
        tongTienPhat_txt.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Tổng tiền phạt");

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

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Mã TTV");

        maTTV_txt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        search_TTV_txt.setText("Mã thẻ thư viện");
        search_TTV_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search_TTV_txtKeyPressed(evt);
            }
        });

        penaltyList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lỗi", "Số lượng", "Tiền phạt", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        penaltyList.setSelectionBackground(new java.awt.Color(174, 68, 90));
        penaltyList.setSelectionForeground(new java.awt.Color(255, 255, 255));
        penaltyList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                penaltyListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(penaltyList);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("DS phạt");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Nhóm lỗi");

        penaltyGrp_cbb.setEditable(true);
        penaltyGrp_cbb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Vi phạm--", "Vi phạm về mượn/trả sách", "Vi phạm về thẻ thành viên", "Vi phạm về bảo quản sách", "Vi phạm về sử dụng cơ sở vật chất của thư viện" }));
        penaltyGrp_cbb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                penaltyGrp_cbbItemStateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Lỗi xử phạt");

        penaltyName_cb.setEditable(true);
        penaltyName_cb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                penaltyName_cbItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("Số lượng");

        soLuong_num.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));
        soLuong_num.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                soLuong_numStateChanged(evt);
            }
        });

        addBtn1.setText("+");
        addBtn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPenaltytoListClicked(evt);
            }
        });

        delete_btn1.setText("-");
        delete_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btn1ActionPerformed(evt);
            }
        });

        tienPhat_txt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setText("Tiền phạt");

        update_btn1.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Pictures\\New folder (2)\\icons8-tick-12.png")); // NOI18N
        update_btn1.setToolTipText("");
        update_btn1.setMaximumSize(new java.awt.Dimension(23, 23));
        update_btn1.setMinimumSize(new java.awt.Dimension(23, 23));
        update_btn1.setPreferredSize(new java.awt.Dimension(23, 23));
        update_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_btn1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setText("Trạng thái");

        trangThai_grp.add(daNop_btn);
        daNop_btn.setText("Đã nộp");

        trangThai_grp.add(chuaNop_btn);
        chuaNop_btn.setText("Chưa nộp");

        delete_btn.setText("Delete");
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(maTTV_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(delete_btn1)
                            .addComponent(jLabel6))
                        .addGap(215, 215, 215)))
                .addGap(197, 197, 197)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(penaltyName_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(penaltyGrp_cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tienPhat_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(daNop_btn)
                        .addGap(18, 18, 18)
                        .addComponent(chuaNop_btn)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tongTienPhat_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(add_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(update_btn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delete_btn))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(soLuong_num, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(update_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(search_TTV_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(maTTV_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(delete_btn1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tongTienPhat_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(penaltyGrp_cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(penaltyName_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tienPhat_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(daNop_btn)
                            .addComponent(chuaNop_btn))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(update_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(soLuong_num, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11))
                            .addComponent(addBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(search_TTV_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(12, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_btnhandleAddBook(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_btnhandleAddBook
        try {
            if(penaltyGrp_cbb.getSelectedItem() != null && penaltyName_cb.getSelectedItem() != null) {
                JSONArray list = new JSONArray();
                DefaultTableModel tbmodel = (DefaultTableModel) penaltyList.getModel();
                if(tbmodel.getRowCount() <= 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn lỗi xử phạt!"); 
                    return;
                }
                for(int i = 0; i < tbmodel.getRowCount(); i++) {
                    JSONObject book = new JSONObject();
                    book.put("tenLoi", tbmodel.getValueAt(i, 0));
                    book.put("soLuong", tbmodel.getValueAt(i, 1));
                    book.put("tienPhat", tbmodel.getValueAt(i, 2));
                    book.put("trangThai", tbmodel.getValueAt(i, 3));
                    list.put(book);
                }

                String penaltyListString = list.toString();
                
                PenaltyModel penalty = new PenaltyModel(maTTV_txt.getText(), penaltyListString, Float.valueOf(tongTienPhat_txt.getText()));
                penaltyService.handleAddPenalty(penalty);
                reloadTableData();
                resetText();
            }
            else {
                JOptionPane.showMessageDialog(null, "Vui lòng điền thông tin cần thiết!"); 
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(PenaltyManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_add_btnhandleAddBook

    private void update_btnhandleUpdateBook(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_btnhandleUpdateBook
        int row = penaltyTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) penaltyTable.getModel();
        
        JSONArray list = new JSONArray();
        String penaltyListString = "";
        DefaultTableModel tbmodel = (DefaultTableModel) penaltyList.getModel();
            if(tbmodel.getRowCount() > 0) {
                for(int i = 0; i < tbmodel.getRowCount(); i++) {
                JSONObject book = new JSONObject();
                book.put("tenLoi", tbmodel.getValueAt(i, 0));
                book.put("soLuong", tbmodel.getValueAt(i, 1));
                book.put("tienPhat", tbmodel.getValueAt(i, 2));
                book.put("trangThai", tbmodel.getValueAt(i, 3));
                list.put(book);
            }
            penaltyListString = list.toString();
        }
     
        PenaltyModel penalty = new PenaltyModel(model.getValueAt(row, 1).toString(), penaltyListString, 
                Float.valueOf(tongTienPhat_txt.getText()));

        try {
            penaltyService.handleUpdatePenalty(penalty, list, oldListPenalty);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(BookManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        reloadTableData();
        resetText();
        add_btn.setEnabled(true);
        addBtn1.setEnabled(true);
        update_btn1.setEnabled(false);
    }//GEN-LAST:event_update_btnhandleUpdateBook

    private static List<String[]> convertJsonb(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<PenaltyListModel> bookList = objectMapper.readValue(jsonString, new TypeReference<List<PenaltyListModel>>() {});
            List<String[]> resultList = new ArrayList<>();
            for(PenaltyListModel list : bookList) {
                String[] arr = {
                    list.getTenLoi(),
                    list.getSoLuong().toString(),
                    list.getTienPhat().toString(),
                    list.getTrangThai()
                    
                };
                resultList.add(arr);
            }
            return resultList;
        } catch (JsonProcessingException e) {
            return null;
        }
    }
    
    private void penaltyTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penaltyTableMouseClicked
        int i = penaltyTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) penaltyTable.getModel();
        maTTV_txt.setText(model.getValueAt(i, 1).toString());
        tongTienPhat_txt.setText(model.getValueAt(i, 3).toString());
        DefaultTableModel model1 = (DefaultTableModel) penaltyList.getModel();
        model1.setRowCount(0);
        if(model.getValueAt(i, 2) != null) {
            ArrayList<String[]> arr = (ArrayList<String[]>) convertJsonb(model.getValueAt(i, 2).toString());
            for(String[] item : arr) {
                model1 = (DefaultTableModel) penaltyList.getModel();
                model1.addRow(item);
            }

            oldListPenalty = arr;
        } 
    }//GEN-LAST:event_penaltyTableMouseClicked

    private void search_TTV_txtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_TTV_txtKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String maTheTV = search_TTV_txt.getText();
            DefaultTableModel model = (DefaultTableModel) penaltyTable.getModel();
            model.setRowCount(0);
            ArrayList<String[]> result = penaltyService.handleSearchPenalty(maTheTV);
            for (String[] array : result) {
                model.addRow(array);
            }
        }
    }//GEN-LAST:event_search_TTV_txtKeyPressed

    
    private void delete_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btn1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) penaltyList.getModel();
        int i = penaltyList.getSelectedRow();
        model.removeRow(i);
    }//GEN-LAST:event_delete_btn1ActionPerformed

    private void penaltyGrp_cbbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_penaltyGrp_cbbItemStateChanged
        if(penaltyGrp_cbb.getSelectedItem() != null) {
            String nhomLoi = penaltyGrp_cbb.getSelectedItem().toString();
            penaltyData = penaltyService.handleGetPenalty(nhomLoi);
            penaltyName_cb.removeAllItems();
            for (Map.Entry<String, Float> entry : penaltyData.entrySet()) {
                penaltyName_cb.addItem(entry.getKey());
            }
        }   
    }//GEN-LAST:event_penaltyGrp_cbbItemStateChanged

    private void penaltyName_cbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_penaltyName_cbItemStateChanged
        if(penaltyName_cb.getSelectedItem() != null) {
            Float tienPhat = penaltyData.get(penaltyName_cb.getSelectedItem().toString());
            tienPhat_txt.setText(tienPhat.toString());
            oldTienPhat = tienPhat;
        }
    }//GEN-LAST:event_penaltyName_cbItemStateChanged

    private void soLuong_numStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_soLuong_numStateChanged
        Float newTienPhat = oldTienPhat * (int) soLuong_num.getValue();
        tienPhat_txt.setText(newTienPhat.toString());
    }//GEN-LAST:event_soLuong_numStateChanged

    private void addPenaltytoListClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPenaltytoListClicked
        DefaultTableModel model = (DefaultTableModel) penaltyList.getModel();
        String tenLoi = penaltyName_cb.getSelectedItem().toString();
        String trangThai;
        if(daNop_btn.isSelected()) {
            trangThai = "Đã nộp";
        } else if(chuaNop_btn.isSelected()) {
            trangThai = "Chưa nộp";
        } else {
            JOptionPane.showMessageDialog(null, "Cần chọn trạng thái!"); 
            return;
        }
        String tienPhat = tienPhat_txt.getText();
        String soLuong = String.valueOf(soLuong_num.getValue());
        Integer existedRow = null;
        for (int i = 0; i < penaltyList.getRowCount(); i++) {
            if(penaltyList.getValueAt(i, 0).equals(tenLoi) && penaltyList.getValueAt(i, 3).equals(trangThai)) {
                existedRow = i;
                break;
            }
        }
        if(existedRow != null) {
            Float oldTP = Float.valueOf(penaltyList.getValueAt(existedRow, 2).toString());
            String newTP = String.valueOf(oldTP + Float.valueOf(tienPhat));
            Integer oldSL = Integer.valueOf(penaltyList.getValueAt(existedRow, 1).toString());
            String newSL = String.valueOf(oldSL + Integer.valueOf(soLuong));
            model.setValueAt(newTP, existedRow, 2);
            model.setValueAt(newSL, existedRow, 1);
        } else {
            String row[] = {tenLoi, soLuong, tienPhat, trangThai};
            model.addRow(row);
        }
    }//GEN-LAST:event_addPenaltytoListClicked

    private void penaltyListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penaltyListMouseClicked
        int i = penaltyList.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) penaltyList.getModel();
        String nhomLoi = penaltyService.handleGetGroupPen(model.getValueAt(i, 0).toString());
        penaltyGrp_cbb.setSelectedItem(nhomLoi);
        if(model.getValueAt(i, 3).toString().equals("Đã nộp")) {
            daNop_btn.setSelected(true);
        } else {
            chuaNop_btn.setSelected(true);
        }
        soLuong_num.setValue(Integer.valueOf(model.getValueAt(i, 1).toString()));
        update_btn1.setEnabled(true);
    }//GEN-LAST:event_penaltyListMouseClicked

    private void update_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btn1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) penaltyList.getModel();
        int i = penaltyList.getSelectedRow();
        
        model.setValueAt(penaltyName_cb.getSelectedItem().toString(), i, 0);
        model.setValueAt(soLuong_num.getValue().toString(), i, 1);
        model.setValueAt(tienPhat_txt.getText(), i, 2);
        String trangThai = "";
        if(daNop_btn.isSelected()) {
            trangThai = "Đã nộp";
        } else if(chuaNop_btn.isSelected()) {
            trangThai = "Chưa nộp";
        } 
        model.setValueAt(trangThai, i, 3);
    }//GEN-LAST:event_update_btn1ActionPerformed

    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnActionPerformed
        DefaultTableModel model = (DefaultTableModel) penaltyTable.getModel();
        int i = penaltyTable.getSelectedRow();
        Boolean allow = true;
        for(int a = 0; a < penaltyList.getRowCount(); a++) {
            if(penaltyList.getValueAt(a, 3).equals("Chưa nộp")) {
                JOptionPane.showMessageDialog(null, "Còn khoản phạt chưa nộp!"); 
                allow = false;
                break;
            }
        }
        if(allow) {
            penaltyService.handleDeletePenalty(model.getValueAt(i, 1).toString());
            reloadTableData();
            resetText();
        }

    }//GEN-LAST:event_delete_btnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn1;
    private javax.swing.JButton add_btn;
    private javax.swing.JRadioButton chuaNop_btn;
    private javax.swing.JRadioButton daNop_btn;
    private javax.swing.JButton delete_btn;
    private javax.swing.JButton delete_btn1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField maTTV_txt;
    private javax.swing.JComboBox<String> penaltyGrp_cbb;
    private javax.swing.JTable penaltyList;
    private javax.swing.JComboBox<String> penaltyName_cb;
    private javax.swing.JTable penaltyTable;
    private javax.swing.JTextField search_TTV_txt;
    private javax.swing.JSpinner soLuong_num;
    private javax.swing.JTextField tienPhat_txt;
    private javax.swing.JTextField tongTienPhat_txt;
    private javax.swing.ButtonGroup trangThai_grp;
    private javax.swing.JButton update_btn;
    private javax.swing.JButton update_btn1;
    // End of variables declaration//GEN-END:variables
}
