package com.mycompany.demo1.views;

import components.AccountManagement;
import components.BHistoryManagement;
import components.BookManagement;
import components.CardManagement;
import components.PenaltyManagement;
import components.ReaderManagement;
import components.ReportManagement;
import components.RoomManagement;
import components.StaffManagement;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeAdmin extends javax.swing.JFrame {
    public String username;
    
    public HomeAdmin(String username, String role) {
        initComponents();
        this.username = username;
        setBackground(new Color(0, 0, 0, 0));
        panelMoving.setOpaque(false);
        bookMng_btn.setOpaque(false);
        reader_btn.setOpaque(false);
        card_btn.setOpaque(false);
        borrow_btn.setOpaque(false);
        report_btn.setOpaque(false);
        staff_btn.setOpaque(false);
        exit_btn.setOpaque(false);
        penalty_btn.setOpaque(false);
        room_btn.setOpaque(false);
        account_btn.setOpaque(false);
        
        ReportManagement form = null;
        try {
            form = new ReportManagement();
        } catch (SQLException ex) {
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.add(form).setVisible(true);
        mainPanel.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        colorPanel1 = new components.colorPanel();
        panelMoving = new javax.swing.JPanel();
        bookIcon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        bookMng_btn = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        reader_btn = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        borrow_btn = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        staff_btn = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        report_btn = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        exit_btn = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        account_btn = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        card_btn = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        room_btn = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        penalty_btn = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelMoving.setBackground(new java.awt.Color(74, 31, 61));

        bookIcon.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Pictures\\New folder (2)\\icons8-book-64.png")); // NOI18N

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("LOCAL LIBRARY");

        javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
        panelMoving.setLayout(panelMovingLayout);
        panelMovingLayout.setHorizontalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bookIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMovingLayout.setVerticalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bookIcon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(17, 17, 17))
        );

        bookMng_btn.setBackground(new java.awt.Color(74, 31, 61));
        bookMng_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bookMng_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleBookMng(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bookMng_btnMousePressed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Pictures\\New folder (2)\\icons8-book-28.png")); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Quản lý sách");

        javax.swing.GroupLayout bookMng_btnLayout = new javax.swing.GroupLayout(bookMng_btn);
        bookMng_btn.setLayout(bookMng_btnLayout);
        bookMng_btnLayout.setHorizontalGroup(
            bookMng_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookMng_btnLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bookMng_btnLayout.setVerticalGroup(
            bookMng_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookMng_btnLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(bookMng_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        reader_btn.setBackground(new java.awt.Color(74, 31, 61));
        reader_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reader_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleClickReader(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Pictures\\New folder (2)\\icons8-reader-28.png")); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Quản lý độc giả");

        javax.swing.GroupLayout reader_btnLayout = new javax.swing.GroupLayout(reader_btn);
        reader_btn.setLayout(reader_btnLayout);
        reader_btnLayout.setHorizontalGroup(
            reader_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reader_btnLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        reader_btnLayout.setVerticalGroup(
            reader_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reader_btnLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(reader_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        borrow_btn.setBackground(new java.awt.Color(74, 31, 61));
        borrow_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        borrow_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleBorrowClicked(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Pictures\\New folder (2)\\icons8-get-28.png")); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Quản lý mượn, trả");

        javax.swing.GroupLayout borrow_btnLayout = new javax.swing.GroupLayout(borrow_btn);
        borrow_btn.setLayout(borrow_btnLayout);
        borrow_btnLayout.setHorizontalGroup(
            borrow_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrow_btnLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        borrow_btnLayout.setVerticalGroup(
            borrow_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrow_btnLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(borrow_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        staff_btn.setBackground(new java.awt.Color(74, 31, 61));
        staff_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        staff_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleStaffClicked(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Pictures\\New folder (2)\\icons8-employee-28.png")); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Quản lý nhân viên");

        javax.swing.GroupLayout staff_btnLayout = new javax.swing.GroupLayout(staff_btn);
        staff_btn.setLayout(staff_btnLayout);
        staff_btnLayout.setHorizontalGroup(
            staff_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staff_btnLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        staff_btnLayout.setVerticalGroup(
            staff_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(staff_btnLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(staff_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        report_btn.setBackground(new java.awt.Color(74, 31, 61));
        report_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        report_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                report_btnMouseClicked(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Pictures\\New folder (2)\\icons8-report-28.png")); // NOI18N

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Thống kê, báo cáo");

        javax.swing.GroupLayout report_btnLayout = new javax.swing.GroupLayout(report_btn);
        report_btn.setLayout(report_btnLayout);
        report_btnLayout.setHorizontalGroup(
            report_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(report_btnLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        report_btnLayout.setVerticalGroup(
            report_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(report_btnLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(report_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        exit_btn.setBackground(new java.awt.Color(74, 31, 61));
        exit_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleExit(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Pictures\\New folder (2)\\icons8-exit-28.png")); // NOI18N

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Thoát");

        javax.swing.GroupLayout exit_btnLayout = new javax.swing.GroupLayout(exit_btn);
        exit_btn.setLayout(exit_btnLayout);
        exit_btnLayout.setHorizontalGroup(
            exit_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exit_btnLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        exit_btnLayout.setVerticalGroup(
            exit_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exit_btnLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(exit_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        account_btn.setBackground(new java.awt.Color(74, 31, 61));
        account_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        account_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                account_btnhandleStaffClicked(evt);
            }
        });

        jLabel18.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Pictures\\New folder (2)\\icons8-user-28.png")); // NOI18N

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Quản lý tài khoản");

        javax.swing.GroupLayout account_btnLayout = new javax.swing.GroupLayout(account_btn);
        account_btn.setLayout(account_btnLayout);
        account_btnLayout.setHorizontalGroup(
            account_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(account_btnLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        account_btnLayout.setVerticalGroup(
            account_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(account_btnLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(account_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        card_btn.setBackground(new java.awt.Color(74, 31, 61));
        card_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        card_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleCardClicked(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Pictures\\New folder (2)\\icons8-card-28.png")); // NOI18N

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Quản lý thẻ thư viện");

        javax.swing.GroupLayout card_btnLayout = new javax.swing.GroupLayout(card_btn);
        card_btn.setLayout(card_btnLayout);
        card_btnLayout.setHorizontalGroup(
            card_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card_btnLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        card_btnLayout.setVerticalGroup(
            card_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(card_btnLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(card_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        room_btn.setBackground(new java.awt.Color(74, 31, 61));
        room_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        room_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                room_btnhandleCardClicked(evt);
            }
        });

        jLabel20.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Pictures\\New folder (2)\\icons8-door-28.png")); // NOI18N

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Đăng ký phòng tự học");

        javax.swing.GroupLayout room_btnLayout = new javax.swing.GroupLayout(room_btn);
        room_btn.setLayout(room_btnLayout);
        room_btnLayout.setHorizontalGroup(
            room_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(room_btnLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        room_btnLayout.setVerticalGroup(
            room_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(room_btnLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(room_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel20))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        penalty_btn.setBackground(new java.awt.Color(74, 31, 61));
        penalty_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        penalty_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                penalty_btnhandlePenaltyClicked(evt);
            }
        });

        jLabel16.setIcon(new javax.swing.ImageIcon("C:\\Users\\admin\\Pictures\\New folder (2)\\icons8-wrong-28.png")); // NOI18N

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Quản lý xử phạt");

        javax.swing.GroupLayout penalty_btnLayout = new javax.swing.GroupLayout(penalty_btn);
        penalty_btn.setLayout(penalty_btnLayout);
        penalty_btnLayout.setHorizontalGroup(
            penalty_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(penalty_btnLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        penalty_btnLayout.setVerticalGroup(
            penalty_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(penalty_btnLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(penalty_btnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout colorPanel1Layout = new javax.swing.GroupLayout(colorPanel1);
        colorPanel1.setLayout(colorPanel1Layout);
        colorPanel1Layout.setHorizontalGroup(
            colorPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bookMng_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(reader_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(borrow_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(staff_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(account_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(card_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(colorPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
            .addComponent(room_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(report_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(penalty_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(exit_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        colorPanel1Layout.setVerticalGroup(
            colorPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(colorPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookMng_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reader_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(borrow_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(staff_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(account_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(card_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(room_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(penalty_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(report_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 828, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 687, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(colorPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel)
            .addComponent(colorPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void handleExit(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_handleExit
        System.exit(0);
    }//GEN-LAST:event_handleExit

    private void handleBookMng(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_handleBookMng
        BookManagement bookmng = null;
        try {
            bookmng = new BookManagement();
        } catch (SQLException ex) {
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.add(bookmng).setVisible(true);
        mainPanel.revalidate();
    }//GEN-LAST:event_handleBookMng

    private void bookMng_btnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookMng_btnMousePressed
        bookMng_btn.setBackground(new Color(255, 255, 255, 80));
        reader_btn.setOpaque(false);
        card_btn.setOpaque(false);
        borrow_btn.setOpaque(false);
        report_btn.setOpaque(false);
        staff_btn.setOpaque(false);
    }//GEN-LAST:event_bookMng_btnMousePressed

    private void handleClickReader(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_handleClickReader
        ReaderManagement form = null;
        try {
            form = new ReaderManagement();
        } catch (SQLException ex) {
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPanel.removeAll();
        mainPanel.add(form).setVisible(true);
        mainPanel.revalidate();
    }//GEN-LAST:event_handleClickReader

    private void handleBorrowClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_handleBorrowClicked
        BHistoryManagement form = null;
        try {
            form = new BHistoryManagement(username);
        } catch (SQLException ex) {
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.add(form).setVisible(true);
        mainPanel.revalidate();
    }//GEN-LAST:event_handleBorrowClicked

    private void handleStaffClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_handleStaffClicked
        StaffManagement form = null;
        try {
            form = new StaffManagement();
        } catch (SQLException ex) {
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.add(form).setVisible(true);
        mainPanel.revalidate();
    }//GEN-LAST:event_handleStaffClicked

    private void handleCardClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_handleCardClicked
        CardManagement form = null;
        try {
            form = new CardManagement(username);
        } catch (SQLException ex) {
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.add(form).setVisible(true);
        mainPanel.revalidate();
    }//GEN-LAST:event_handleCardClicked

    private void report_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_btnMouseClicked
        ReportManagement form = null;
        try {
            form = new ReportManagement();
        } catch (SQLException ex) {
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.add(form).setVisible(true);
        mainPanel.revalidate();
    }//GEN-LAST:event_report_btnMouseClicked

    private void penalty_btnhandlePenaltyClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_penalty_btnhandlePenaltyClicked
        PenaltyManagement form = null;
        try {
            form = new PenaltyManagement(username);
        } catch (SQLException ex) {
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.add(form).setVisible(true);
        mainPanel.revalidate();
    }//GEN-LAST:event_penalty_btnhandlePenaltyClicked

    private void account_btnhandleStaffClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_account_btnhandleStaffClicked
        AccountManagement form = null;
        try {
            form = new AccountManagement();
        } catch (SQLException ex) {
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.add(form).setVisible(true);
        mainPanel.revalidate();
    }//GEN-LAST:event_account_btnhandleStaffClicked

    private void room_btnhandleCardClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_room_btnhandleCardClicked
        RoomManagement form = null;
        try {
            form = new RoomManagement();
        } catch (SQLException ex) {
            Logger.getLogger(HomeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainPanel.removeAll();
        mainPanel.repaint();
        mainPanel.add(form).setVisible(true);
        mainPanel.revalidate();
    }//GEN-LAST:event_room_btnhandleCardClicked

    public static void main(String args[]) {
    
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String username = "";
                String role = "";
                new HomeAdmin(username, role).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel account_btn;
    private javax.swing.JLabel bookIcon;
    private javax.swing.JPanel bookMng_btn;
    private javax.swing.JPanel borrow_btn;
    private javax.swing.JPanel card_btn;
    private components.colorPanel colorPanel1;
    private javax.swing.JPanel exit_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JDesktopPane mainPanel;
    private javax.swing.JPanel panelMoving;
    private javax.swing.JPanel penalty_btn;
    private javax.swing.JPanel reader_btn;
    private javax.swing.JPanel report_btn;
    private javax.swing.JPanel room_btn;
    private javax.swing.JPanel staff_btn;
    // End of variables declaration//GEN-END:variables
}
