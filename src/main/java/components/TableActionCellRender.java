package components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableActionCellRender extends DefaultTableCellRenderer{
    Font font = new Font("Times New Roman", Font.PLAIN, 14);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        setFont(font);
        setVerticalAlignment(CENTER);
        setHorizontalAlignment(CENTER);
        ActionPanel action = new ActionPanel();
        if(isSelected == false && row % 2 == 0) {
            action.setBackground(Color.white);
        } else {
            action.setBackground(com.getBackground());
        }
        return action;
    }
    
}
