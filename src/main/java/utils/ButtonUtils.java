package utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ButtonUtils {
	public static void setupButtonForJIFrame(JButton button) {
	    button.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseEntered(MouseEvent e) {
	            button.setBackground(new Color(240, 240, 240));
	            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	            
	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	            button.setBackground(new Color(255, 255, 255));
	            button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	        }
	    });
	    
	    button.setFocusPainted(false);	    
	    button.setBackground(new Color(255, 255, 255));
	    button.setBorder(null);
	    button.setFont(new Font("Tahoma", Font.BOLD, 14));
	    button.setVerticalTextPosition(SwingConstants.BOTTOM);
	    button.setHorizontalTextPosition(SwingConstants.CENTER);
	    button.setMargin(new Insets(10, 0, 10, 0));
	}
	
	public static void setupNormalButton(JButton button, Color color, Color hoverColor) {
	    button.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseEntered(MouseEvent e) {
	            //button.setBackground(new Color(131, 209, 51));
	            button.setBackground(hoverColor);
	            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	            //button.setBackground(new Color(159, 216, 96));
	            button.setBackground(color);
	            button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	        }
	    });

	    button.setFont(new Font("Tahoma", Font.BOLD, 14));
	    button.setForeground(new Color(255, 255, 255));
	    button.setFocusPainted(false);
	    button.setBorderPainted(false);
	    button.setBorder(new EmptyBorder(8, 15, 8, 15));
	    button.setBackground(color);
        //btnAdd.setBackground(new Color(159, 216, 96));
	}
}
