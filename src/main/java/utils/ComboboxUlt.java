package utils;

import javax.swing.JComboBox;

public class ComboboxUlt {
	public static void showWarning(@SuppressWarnings("rawtypes") JComboBox comboBox, String mess) {
		if(comboBox.getSelectedIndex() == 0) {
			ShowMessage.showWarningMessage("Warning", mess);
			return;
		}
	}
}
