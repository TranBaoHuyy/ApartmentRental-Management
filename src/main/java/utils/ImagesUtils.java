package utils;

import java.awt.Image;

import javax.swing.ImageIcon;

import gui.ServiceInvoiceManage;

public class ImagesUtils {
	public static ImageIcon createResizedIcon(String imagePath, int width, int height) {
	    
        ImageIcon originalIcon = new ImageIcon(ServiceInvoiceManage.class.getResource(imagePath));
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
