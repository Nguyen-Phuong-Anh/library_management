package components;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.RoomModel;
import service.RoomService;

public class RoomManagement extends javax.swing.JInternalFrame {
    private ArrayList<String[]> result = new ArrayList<>();
    private ArrayList<String[]> roomList = new ArrayList<>();
    public RoomService roomService;
    
    public RoomManagement() throws SQLException {
        initComponents();
        this.roomService = new RoomService();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadData();
        viTri_txt.setText("");
        sucChua_txt.setText("");
    }
    
    public final void loadData() throws SQLException {
        //get data
        result = roomService.handleGetData();
        for (String[] array : result) {
            DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
            model.addRow(array);
        }
        loadRoom();
    }
    
    private void reloadTableData() {
        DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
        model.setRowCount(0);

        ArrayList<String[]> result = roomService.handleGetData();
        for (String[] array : result) {
            model.addRow(array);
        }

        roomTable.repaint(); 
        loadRoom();
    }
    
    private void resetText() {
        tenPhong_cbb.setSelectedIndex(-1);
        viTri_txt.setText("");
        sucChua_txt.setText("");
        roomState_txt.setText("");
        maTTV_txt.setText("");
        ngayMuon_picker.setDate(null);
        trangThai_group.clearSelection();
        gioBD_selector.setSelectedIndex(-1);
        gioKT_selector.setSelectedIndex(-1);
        add_btn.setEnabled(true);
        tenPhong_cbb.setEnabled(true);
    }
    
    private void loadRoom() {
        roomList = roomService.handleGetRoomList();
        tenPhong_cbb.removeAllItems();
        for(String[] arr : roomList) {
            tenPhong_cbb.addItem(arr[2]);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        trangThai_group = new javax.swing.ButtonGroup();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        roomTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        add_btn = new javax.swing.JButton();
        update_btn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        ngayMuon_picker = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        daDat_btn = new javax.swing.JRadioButton();
        dangSD_btn = new javax.swing.JRadioButton();
        search_TTV_txt = new javax.swing.JTextField();
        delete_btn = new javax.swing.JButton();
        tenPhong_cbb = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        viTri_txt = new javax.swing.JLabel();
        sucChua_txt = new javax.swing.JLabel();
        daTra_btn = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        gioBD_selector = new javax.swing.JComboBox<>();
        gioKT_selector = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        maTTV_txt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        roomState_txt = new javax.swing.JLabel();

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(770, 624));

        roomTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã mượn", "Mã phòng", "Mã TTV", "Ngày mượn", "TG bắt đầu", "TG kết thúc", "TT sử dụng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        roomTable.setRowHeight(40);
        roomTable.setSelectionBackground(new java.awt.Color(174, 68, 90));
        roomTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        roomTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(roomTable);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

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

        ngayMuon_picker.setAutoscrolls(true);
        ngayMuon_picker.setDateFormatString("dd/ MM/ y");
        ngayMuon_picker.setPreferredSize(new java.awt.Dimension(88, 23));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Tên phòng");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("Trạng thái SD");

        trangThai_group.add(daDat_btn);
        daDat_btn.setText("Đã đặt");

        trangThai_group.add(dangSD_btn);
        dangSD_btn.setText("Đang sử dụng");

        search_TTV_txt.setText("Mã thẻ thư viện");
        search_TTV_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                search_TTV_txtKeyPressed(evt);
            }
        });

        delete_btn.setText("Delete");
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });

        tenPhong_cbb.setEditable(true);
        tenPhong_cbb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tenPhong_cbbItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Vị trí");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Sức chứa");

        viTri_txt.setText("jLabel1");

        sucChua_txt.setText("jLabel1");

        trangThai_group.add(daTra_btn);
        daTra_btn.setText("Đã trả");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("Giờ bắt đầu");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setText("Giờ kết thúc");

        gioBD_selector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00" }));

        gioKT_selector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00" }));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setText("Mã TTV");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setText("Trạng thái");

        roomState_txt.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel5)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tenPhong_cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(viTri_txt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(roomState_txt)
                                .addGap(40, 40, 40))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel19)
                                .addComponent(jLabel17)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(gioBD_selector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel18)
                                .addGap(16, 16, 16)
                                .addComponent(gioKT_selector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ngayMuon_picker, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                    .addComponent(maTTV_txt))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(360, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(sucChua_txt))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGap(72, 72, 72)
                                        .addComponent(search_TTV_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(add_btn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(update_btn)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(delete_btn)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(daDat_btn)
                                    .addGap(18, 18, 18)
                                    .addComponent(dangSD_btn)
                                    .addGap(18, 18, 18)
                                    .addComponent(daTra_btn))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tenPhong_cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(roomState_txt)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(sucChua_txt))))
                            .addComponent(viTri_txt))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ngayMuon_picker, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(daDat_btn)
                        .addComponent(dangSD_btn)
                        .addComponent(daTra_btn)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(gioBD_selector, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(gioKT_selector, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(maTTV_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_btnhandleAddBook(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_btnhandleAddBook
        if(!roomState_txt.getText().equals("Còn trống")) {
            JOptionPane.showMessageDialog(null, "Phòng đã được sử dụng!"); 
            return;
        }
        try {
            Integer i = tenPhong_cbb.getSelectedIndex();
            String maPhong = roomList.get(i)[1];
            
            String trangThai = "";
            if(daDat_btn.isSelected()) {
                trangThai = "Đã đặt";
            } else if (dangSD_btn.isSelected()) {
                trangThai = "Đang sử dụng";
            } else if (daTra_btn.isSelected()) {
                trangThai = "Đã trả";
            } else {
                JOptionPane.showMessageDialog(null, "Cần chọn trạng thái!"); 
                return;
            }
            RoomModel room = new RoomModel("", maPhong, maTTV_txt.getText(), ngayMuon_picker.getDate(), gioBD_selector.getSelectedItem().toString(), gioKT_selector.getSelectedItem().toString(), trangThai);
            roomService.handleAddRoom(room);
            reloadTableData();
            resetText();
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(RoomManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_add_btnhandleAddBook

    private void update_btnhandleUpdateBook(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_update_btnhandleUpdateBook
        int i = roomTable.getSelectedRow();
        Integer index = tenPhong_cbb.getSelectedIndex();
        String maPhong = roomList.get(index)[1];
        DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
        String trangThai = "";
        if(daDat_btn.isSelected()) {
            trangThai = "Đã đặt";
        } else if (dangSD_btn.isSelected()) {
            trangThai = "Đang sử dụng";
        } else if (daTra_btn.isSelected()) {
            trangThai = "Đã trả";
        }
        
        RoomModel room = new RoomModel(model.getValueAt(i, 1).toString(), maPhong, maTTV_txt.getText(), ngayMuon_picker.getDate(), 
                gioBD_selector.getSelectedItem().toString(), gioKT_selector.getSelectedItem().toString(), trangThai);
        try {
            roomService.handleUpdateRoom(room);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(BookManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        reloadTableData();
        resetText();
    }//GEN-LAST:event_update_btnhandleUpdateBook

    private void roomTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomTableMouseClicked
        int i = roomTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
        if(model.getValueAt(i, 7).toString().equals("Đã đặt")) {
            daDat_btn.setSelected(true);
        } else if(model.getValueAt(i, 7).toString().equals("Đang sử dụng")){
            dangSD_btn.setSelected(true);
        } else {
            daTra_btn.setSelected(true);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(model.getValueAt(i, 4).toString());
            ngayMuon_picker.setDate(date);
        } catch (ParseException ex) {
            Logger.getLogger(RoomManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        int index = 0;
        for(int a = 0; a < roomList.size(); a++) {
            if(roomList.get(a)[1].equals(model.getValueAt(i, 2).toString())) {
                index = a;
                break;
            }
        }
        tenPhong_cbb.setSelectedIndex(index);
        tenPhong_cbb.setEnabled(false);
        viTri_txt.setText(roomList.get(index)[3]);
        sucChua_txt.setText(roomList.get(index)[4]);
        roomState_txt.setText(roomList.get(index)[5]);
        String[] parts = model.getValueAt(i, 5).toString().split(":");
        String hour = parts[0];
        String minute = parts[1];
        String newTimeString = hour + ":" + minute;
        String[] parts1 = model.getValueAt(i, 6).toString().split(":");
        String hour1 = parts1[0];
        String minute1 = parts1[1];
        String newTimeString1 = hour1 + ":" + minute1;
        gioBD_selector.setSelectedItem(newTimeString);
        gioKT_selector.setSelectedItem(newTimeString1);
        maTTV_txt.setText(model.getValueAt(i, 3).toString());
        add_btn.setEnabled(false);
    }//GEN-LAST:event_roomTableMouseClicked

    private void search_TTV_txtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_TTV_txtKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String maTheTV = search_TTV_txt.getText();
            DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
            model.setRowCount(0);
            ArrayList<String[]> result = roomService.handleSearchRoom(maTheTV);
            for (String[] array : result) {
                model.addRow(array);
            }
        }
    }//GEN-LAST:event_search_TTV_txtKeyPressed
    
    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnActionPerformed
        DefaultTableModel model = (DefaultTableModel) roomTable.getModel();
        int row = roomTable.getSelectedRow();
        if (row >= 0 && row < model.getRowCount()) {
            roomService.handleDeleteRoom(model.getValueAt(row, 2).toString(), model.getValueAt(row, 1).toString());
            reloadTableData();
            resetText();
        }
    }//GEN-LAST:event_delete_btnActionPerformed

    private void tenPhong_cbbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tenPhong_cbbItemStateChanged
        int i = tenPhong_cbb.getSelectedIndex();
        if(i >= 0) {
            viTri_txt.setText(roomList.get(i)[3]);
            sucChua_txt.setText(roomList.get(i)[4]);
            roomState_txt.setText(roomList.get(i)[5]);
        }
    }//GEN-LAST:event_tenPhong_cbbItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_btn;
    private javax.swing.JRadioButton daDat_btn;
    private javax.swing.JRadioButton daTra_btn;
    private javax.swing.JRadioButton dangSD_btn;
    private javax.swing.JButton delete_btn;
    private javax.swing.JComboBox<String> gioBD_selector;
    private javax.swing.JComboBox<String> gioKT_selector;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField maTTV_txt;
    private com.toedter.calendar.JDateChooser ngayMuon_picker;
    private javax.swing.JLabel roomState_txt;
    private javax.swing.JTable roomTable;
    private javax.swing.JTextField search_TTV_txt;
    private javax.swing.JLabel sucChua_txt;
    private javax.swing.JComboBox<String> tenPhong_cbb;
    private javax.swing.ButtonGroup trangThai_group;
    private javax.swing.JButton update_btn;
    private javax.swing.JLabel viTri_txt;
    // End of variables declaration//GEN-END:variables
}
