package components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Color;
import java.text.ParseException;
import javax.swing.JFrame;
import model.ReaderModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.BookListModel;

public class ReaderInfoForm extends javax.swing.JFrame {
    private ReaderModel reader;
    
    public ReaderInfoForm(ReaderModel reader, ArrayList<String[]> readerHis) throws ParseException {
        this.reader = reader;
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        hoTen_txt.setText(reader.getHoTen());
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dobWithTime = inputFormat.parse(reader.getNgaySinh().toString());
        String dobString = outputFormat.format(dobWithTime);
        ngaySinh_picker.setText(dobString);
        diaChi_txt.setText(reader.getDiaChi());
        sdt_txt.setText(reader.getSoDienThoai());
        cccd_txt.setText(reader.getSoCCCD());
        slSach_num.setText(String.valueOf(reader.getSoSachDaMuon()));
        
        if(readerHis != null) {
            for(String[] arr : readerHis) {
                DefaultTableModel model = (DefaultTableModel) readerHis_tb.getModel();
                model.addRow(arr);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        hoTen_txt = new javax.swing.JLabel();
        ngaySinh_picker = new javax.swing.JLabel();
        diaChi_txt = new javax.swing.JLabel();
        sdt_txt = new javax.swing.JLabel();
        cccd_txt = new javax.swing.JLabel();
        slSach_num = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        readerHis_tb = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        bookList = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Địa chỉ");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Họ và tên");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Ngày sinh");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Sách đã mượn");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("CCCD");

        jLabel9.setFont(new java.awt.Font("Showcard Gothic", 0, 24)); // NOI18N
        jLabel9.setText("READER INFORMATION");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("SDT");

        hoTen_txt.setText("jLabel10");

        ngaySinh_picker.setText("jLabel10");

        diaChi_txt.setText("jLabel10");

        sdt_txt.setText("jLabel10");

        cccd_txt.setText("jLabel10");

        slSach_num.setText("jLabel10");

        readerHis_tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã mượn trả", "DS Mượn", "Ngày mượn", "Ngày hết hạn", "Ngày trả", "Trạng thái", "Số lần gia hạn", "Người thực hiện"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
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
        readerHis_tb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                readerHis_tbMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(readerHis_tb);

        jLabel10.setFont(new java.awt.Font("Showcard Gothic", 0, 24)); // NOI18N
        jLabel10.setText("BORROWING HISTORY");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cccd_txt)
                            .addComponent(sdt_txt)
                            .addComponent(diaChi_txt)
                            .addComponent(ngaySinh_picker)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(hoTen_txt)
                                .addGap(71, 71, 71)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(slSach_num))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(65, 65, 65))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(hoTen_txt)
                            .addComponent(jLabel6)
                            .addComponent(slSach_num))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(ngaySinh_picker))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(diaChi_txt))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(sdt_txt))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cccd_txt))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
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

    private void readerHis_tbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_readerHis_tbMouseClicked
        DefaultTableModel model = (DefaultTableModel) readerHis_tb.getModel();
        int i = readerHis_tb.getSelectedRow();
        if(i >= 0) {
            ArrayList<String[]> arr = (ArrayList<String[]>) convertJsonb(model.getValueAt(i, 1).toString());
            DefaultTableModel model1 = (DefaultTableModel) bookList.getModel();
            model1.setRowCount(0);
            for(String[] item : arr) {
                model1 = (DefaultTableModel) bookList.getModel();
                model1.addRow(item);
            }
        }
    }//GEN-LAST:event_readerHis_tbMouseClicked


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReaderModel reader = new ReaderModel();
                ReaderInfoForm form;
                ArrayList<String[]> readerHis = null;
                try {
                    form = new ReaderInfoForm(reader, readerHis);
                    form.getContentPane().setBackground(Color.WHITE);
                    form.show();
                } catch (ParseException ex) {
                    Logger.getLogger(ReaderInfoForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bookList;
    private javax.swing.JLabel cccd_txt;
    private javax.swing.JLabel diaChi_txt;
    private javax.swing.JLabel hoTen_txt;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel ngaySinh_picker;
    private javax.swing.JTable readerHis_tb;
    private javax.swing.JLabel sdt_txt;
    private javax.swing.JLabel slSach_num;
    // End of variables declaration//GEN-END:variables
}
