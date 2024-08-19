package components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ActionButton extends JButton{
    private boolean mousePress;
    
    public ActionButton() {
        setContentAreaFilled(true);
        setBorder(new EmptyBorder(3, 3, 3, 3));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBackground(new Color(0, 0, 0, 0));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                mousePress = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePress = false;
            }
            
            
        });
    }
    
}
