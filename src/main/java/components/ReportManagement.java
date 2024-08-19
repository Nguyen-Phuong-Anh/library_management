package components;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.BookListModel;
import model.ReaderModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import service.BHistoryService;
import service.ReaderService;
import service.ReportService;
import java.awt.event.ItemEvent;

public final class ReportManagement extends javax.swing.JInternalFrame {
    private ArrayList<String[]> result = new ArrayList<>();
    public ReportService reportService;
    public ReaderService readerService;
    public BHistoryService historyService;
    
    public ReportManagement() throws SQLException {
        initComponents();
        this.reportService = new ReportService();
        this.readerService = new ReaderService();
        this.historyService = new BHistoryService();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        showPieChart();
        showLineChart();
        showBarChart();
        borrowedList();
        handleBorrowBook();
        
        bookBorrowedDetail_dialog.setSize(592, 358);
        bookBorrowedDetail_dialog.setLocationRelativeTo(this);
        bookBorrowedDetail_dialog.setVisible(false);
    }
    
    public void showPieChart() {
        DefaultPieDataset barDataset = new DefaultPieDataset();
        ArrayList<String[]> result = reportService.handleGetBook();
        for (String[] array : result) {
            barDataset.setValue(array[0], Double.valueOf(array[1]));
        }
        
        JFreeChart pieChart = ChartFactory.createPieChart("Thể loại", barDataset, true, true, false);
        PiePlot piePlot = (PiePlot) pieChart.getPlot();
        Color[] customColors = new Color[] {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.PINK, Color.GRAY}; 
        for (int i = 0; i < result.size(); i++) {
            piePlot.setSectionPaint(result.get(i)[0], customColors[i % customColors.length]);
        }
        piePlot.setBackgroundPaint(Color.WHITE);
        ChartPanel barChartPanel = new ChartPanel(pieChart);
        piePanel.removeAll();
        piePanel.setLayout(new BorderLayout());
        piePanel.setPreferredSize(new Dimension(256, 210));
        piePanel.add(barChartPanel, BorderLayout.CENTER);
        piePanel.validate();
    }
    
    public void showLineChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<String[]> result = reportService.handleGetReader();
        for (String[] array : result) {
            dataset.setValue(Integer.parseInt(array[1]), "Số lượng", array[0]);
        }
        
        JFreeChart lineChart = ChartFactory.createLineChart("Độc giả đăng ký", "Tháng", "Số lượng", dataset, 
                PlotOrientation.VERTICAL, false, true, false);
        
        CategoryPlot lineCategoryPlot = lineChart.getCategoryPlot();
        lineCategoryPlot.setBackgroundPaint(Color.WHITE);
        
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        lineRenderer.setSeriesPaint(0, Color.RED);
        
        ChartPanel lineChartPanel = new ChartPanel(lineChart);
        linePanel.removeAll();
        linePanel.add(lineChartPanel, BorderLayout.CENTER);
        linePanel.validate();
    }

    public void showBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<String[]> result = reportService.handleGetBook();
        for (String[] array : result) {
            dataset.setValue(Integer.parseInt(array[1]), "Số lượng", array[0]);
        }
        
        JFreeChart barChart = ChartFactory.createBarChart("Số lượng sách", "Thể loại", "Số lượng", dataset, 
                PlotOrientation.VERTICAL, true, true, true);
        
        CategoryPlot barCategoryPlot = barChart.getCategoryPlot();
        barCategoryPlot.setBackgroundPaint(Color.WHITE);
        
        BarRenderer renderer = (BarRenderer) barCategoryPlot.getRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        
        ChartPanel barChartPanel = new ChartPanel(barChart);
        barPanel.removeAll();
        barPanel.add(barChartPanel, BorderLayout.CENTER);
        barPanel.validate();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bookBorrowedDetail_dialog = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DSMuon_tb = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        piePanel = new javax.swing.JPanel();
        linePanel = new javax.swing.JPanel();
        barPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        slMuon_txt = new javax.swing.JLabel();
        DGMuon_txt = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        maTTV_cb = new javax.swing.JComboBox<>();

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        DSMuon_tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã sách", "Tên sách", "Số lượng mượn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(DSMuon_tb);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("DANH SÁCH MƯỢN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout bookBorrowedDetail_dialogLayout = new javax.swing.GroupLayout(bookBorrowedDetail_dialog.getContentPane());
        bookBorrowedDetail_dialog.getContentPane().setLayout(bookBorrowedDetail_dialogLayout);
        bookBorrowedDetail_dialogLayout.setHorizontalGroup(
            bookBorrowedDetail_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookBorrowedDetail_dialogLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        bookBorrowedDetail_dialogLayout.setVerticalGroup(
            bookBorrowedDetail_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookBorrowedDetail_dialogLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(770, 624));

        piePanel.setBackground(new java.awt.Color(204, 204, 204));
        piePanel.setLayout(new java.awt.BorderLayout());

        linePanel.setBackground(new java.awt.Color(204, 204, 204));
        linePanel.setLayout(new java.awt.BorderLayout());

        barPanel.setBackground(new java.awt.Color(204, 204, 204));
        barPanel.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Tình trạng mượn trả sách");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Số lượng sách được mượn");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Số lượng độc giả chưa trả");

        slMuon_txt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        slMuon_txt.setText("jLabel5");

        DGMuon_txt.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        DGMuon_txt.setText("jLabel5");

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("...");

        maTTV_cb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                maTTV_cbItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(piePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(linePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DGMuon_txt))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(slMuon_txt))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(maTTV_cb, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(linePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(piePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(slMuon_txt)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(DGMuon_txt)
                    .addComponent(jButton2)
                    .addComponent(maTTV_cb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 615, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        bookBorrowedDetail_dialog.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void maTTV_cbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_maTTV_cbItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            if(!maTTV_cb.getSelectedItem().toString().equals("---Chọn mã TTV---")) {
                try {
                    ArrayList<String[]> result = readerService.handleSearchOneReader(maTTV_cb.getSelectedItem().toString());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date ngaySinh = null;
                    try {
                        ngaySinh = dateFormat.parse(result.get(0)[3]);
                    } catch (ParseException ex) {
                        Logger.getLogger(ReportManagement.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ReaderModel reader = new ReaderModel(result.get(0)[0], result.get(0)[1], result.get(0)[2], ngaySinh, result.get(0)[4], result.get(0)[5], result.get(0)[6], result.get(0)[7]);
                    ArrayList<String[]> readerHis = null;
                    readerHis = historyService.handleSearchReaderHistory(maTTV_cb.getSelectedItem().toString());
                    ReaderInfoForm form = new ReaderInfoForm(reader, readerHis);
                    form.setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(ReportManagement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }//GEN-LAST:event_maTTV_cbItemStateChanged

    public void borrowedList() {
        ArrayList<String[]> result = reportService.handleGetBorrowedList();
        int totalQuantity = 0;
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
            for (String[] jsonStrings : result) {
                List<BookListModel> bookList = objectMapper.readValue(jsonStrings[0], new TypeReference<List<BookListModel>>() {});
                for(BookListModel list : bookList) {
                    totalQuantity += list.getSoLuong();
                }
            }
            
            slMuon_txt.setText(String.valueOf(totalQuantity));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //borrow list
        ArrayList<String[]> list = reportService.handleGetBorrowedBook();
        for (String[] array : list) {
            DefaultTableModel model = (DefaultTableModel) DSMuon_tb.getModel();
            model.addRow(array);
        }
    }
    
    public void handleBorrowBook() {
        ArrayList<String[]> result = reportService.handleGetBHis();
        Integer sl1 = 0;
        maTTV_cb.addItem("---Chọn mã TTV---");
        for(String[] arr : result) {
            if(Integer.parseInt(arr[1]) != 0) {
                sl1++;
                maTTV_cb.addItem(arr[0]);
            }
        }
        DGMuon_txt.setText(String.valueOf(sl1));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DGMuon_txt;
    private javax.swing.JTable DSMuon_tb;
    private javax.swing.JPanel barPanel;
    private javax.swing.JDialog bookBorrowedDetail_dialog;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel linePanel;
    private javax.swing.JComboBox<String> maTTV_cb;
    private javax.swing.JPanel piePanel;
    private javax.swing.JLabel slMuon_txt;
    // End of variables declaration//GEN-END:variables
}
