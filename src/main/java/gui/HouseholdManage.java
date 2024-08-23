package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import dao.HouseholdDAO;
import entity.Household;
import utils.ButtonUtils;
import utils.ImagesUtils;
import utils.ShowMessage;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class HouseholdManage extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private static HouseholdManage instance;
    private JPanel panelToolBar;
    private JPanel panelContent;
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel panelButton;
    private JPanel panelFind;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnExport;
	private JComboBox<String> comboBox;
    private JTextField textField;
    private JButton btnFind;
    
    private String selectedHouseholdId;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                HouseholdManage frame = HouseholdManage.getInstance();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public String getSelectedHouseholdId() {
		return selectedHouseholdId;
	}

	private HouseholdManage() {
        super("Household Manage", false, false, false, false);
        setBounds(100, 100, 950, 695);
        setBackground(new Color(238, 242, 243));
        getContentPane().setLayout(null);
        
        panelToolBar = new JPanel();
        panelToolBar.setBounds(0, 0, 934, 146);
        getContentPane().add(panelToolBar);
        panelToolBar.setPreferredSize(new Dimension(getContentPane().getWidth(), 135));
        panelToolBar.setBackground(new Color(255, 255, 255));
        
        panelButton = new JPanel();
        panelButton.setBounds(10, 11, 296, 124);
        panelButton.setBackground(new Color(255, 255, 255));
        panelButton.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Function", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        
        panelFind = new JPanel();
        panelFind.setBounds(316, 11, 306, 124);
        panelFind.setBackground(new Color(255, 255, 255));
        panelFind.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Find", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        
        comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Find by ID number", "Find by Name", "Find by Phone"}));
        comboBox.setBounds(15, 25, 200, 30);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        textField = new JTextField();
        textField.setBounds(15, 75, 200, 30);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textField.setColumns(10);
        
        ImageIcon iconFind = ImagesUtils.createResizedIcon("/images/findButtonIcon.png", 45, 45);
        btnFind = new JButton(null, iconFind);
        btnFind.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnFindActionPerformed(e);
        	}
        });
        btnFind.setBounds(225, 25, 67, 80);
        btnFind.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFind.setMargin(new Insets(10, 0, 10, 0));
        btnFind.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFind.setFocusPainted(false);
        btnFind.setBorder(null);
        btnFind.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnFind);
        

        
        ImageIcon iconAdd = ImagesUtils.createResizedIcon("/images/addButtonIcon.png", 45, 45);
        btnAdd = new JButton("Add", iconAdd);
        btnAdd.setBounds(8, 28, 63, 87);
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAddActionPerformed(e);
        	}
        });
        btnAdd.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnAdd.setMargin(new Insets(10, 0, 10, 0));
        btnAdd.setHorizontalTextPosition(SwingConstants.CENTER);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAdd.setFocusPainted(false);
        btnAdd.setBorder(null);
        btnAdd.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnAdd);
        
        ImageIcon iconDelete = ImagesUtils.createResizedIcon("/images/deleteButtonIcon.png", 45, 45);
        btnDelete = new JButton("Delete", iconDelete);
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnDeleteActionPerformed(e);
        	}
        });
        btnDelete.setBounds(79, 27, 63, 88);
        btnDelete.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnDelete.setMargin(new Insets(10, 0, 10, 0));
        btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnDelete.setFocusPainted(false);
        btnDelete.setBorder(null);
        btnDelete.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnDelete);
        
        ImageIcon iconUpdate = ImagesUtils.createResizedIcon("/images/updateButtonIcon.png", 45, 45);
        btnUpdate = new JButton("Update", iconUpdate);
        btnUpdate.setBounds(150, 27, 63, 88);
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnUpdateActionPerformed(e);
        	}
        });
        btnUpdate.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnUpdate.setMargin(new Insets(10, 0, 10, 0));
        btnUpdate.setHorizontalTextPosition(SwingConstants.CENTER);
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnUpdate.setFocusPainted(false);
        btnUpdate.setBorder(null);
        btnUpdate.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnUpdate);
        
        panelToolBar.setLayout(null);
        panelButton.setLayout(null);
        panelButton.add(btnAdd);
        panelButton.add(btnDelete);
        panelButton.add(btnUpdate);
        panelToolBar.add(panelButton);
        
        ImageIcon iconExport = ImagesUtils.createResizedIcon("/images/exportButtonIcon.png", 45, 45);
        btnExport = new JButton("Export", iconExport);
        btnExport.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnExportActionPerformed(e);
        	}
        });
        btnExport.setBounds(221, 27, 63, 88);
        panelButton.add(btnExport);
        btnExport.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnExport.setMargin(new Insets(10, 0, 10, 0));
        btnExport.setHorizontalTextPosition(SwingConstants.CENTER);
        btnExport.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnExport.setFocusPainted(false);
        btnExport.setBorder(null);
        btnExport.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnExport);
        
        panelToolBar.add(panelFind);
        panelFind.setLayout(null);
        panelFind.add(textField);
        panelFind.add(comboBox);
        panelFind.add(btnFind);
        
        panelContent = new JPanel();
        panelContent.setBounds(0, 135, 934, 525);
        panelContent.setBackground(new Color(255, 255, 255));
        getContentPane().add(panelContent);
        
        scrollPane = new JScrollPane();
        GroupLayout gl_panelContent = new GroupLayout(panelContent);
        gl_panelContent.setHorizontalGroup(
        	gl_panelContent.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelContent.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_panelContent.setVerticalGroup(
        	gl_panelContent.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_panelContent.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        			.addContainerGap())
        );
        
        table = new JTable();
        scrollPane.setViewportView(table);
        panelContent.setLayout(gl_panelContent);
        loadHouseholds();
    }

    public static HouseholdManage getInstance() {
        if (instance == null) {
            instance = new HouseholdManage();
        }
        return instance;
    }
    
    private void loadHouseholds() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Number");
        model.addColumn("Household Head Name");
        model.addColumn("Phone Number");
        model.addColumn("Date of Birth");
        model.addColumn("Email");
        model.addColumn("Image");
        model.addColumn("Gender");
        model.addColumn("Member Quantity");

        HouseholdDAO householdDAO = new HouseholdDAO();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        householdDAO.getAllHouseholds().forEach(household -> {
            String formattedDateOfBirth = (household.getDateOfBirth() != null)
                    ? dateFormat.format(household.getDateOfBirth())
                    : "N/A";

            model.addRow(new Object[]{
                    household.getIdNumber(),
                    household.getHouseholdHeadName(),
                    household.getPhoneNumber(),
                    formattedDateOfBirth,
                    household.getEmail(),
                    household.getImage(),
                    household.getGender(),
                    household.getMemberQuantity()
            });
        });

        table.setModel(model);
        TableColumn imageColumn = table.getColumnModel().getColumn(5);
        imageColumn.setCellRenderer(new ImageRenderer());
        table.setRowHeight(90);
    }
    
    @SuppressWarnings("serial")
	private static class ImageRenderer extends DefaultTableCellRenderer {
    	private int size = 80;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String imagePath = (String) value;

            if (imagePath != null && !imagePath.isEmpty()) {
                ImageIcon icon = new ImageIcon(imagePath);
                Image image = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);

                JLabel label = new JLabel();
                label.setIcon(new ImageIcon(image));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                table.setRowHeight(row, size+10);
                return label;
            } else {
                return c;
            }
        }
    }

    public void refreshHouseholds() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        HouseholdDAO householdDAO = new HouseholdDAO();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        householdDAO.getAllHouseholds().forEach(household -> {
            String formattedDateOfBirth = (household.getDateOfBirth() != null)
                    ? dateFormat.format(household.getDateOfBirth())
                    : "N/A";

            model.addRow(new Object[]{
                    household.getIdNumber(),
                    household.getHouseholdHeadName(),
                    household.getPhoneNumber(),
                    formattedDateOfBirth,
                    household.getEmail(),
                    household.getImage(),
                    household.getGender(),
                    household.getMemberQuantity()
            });
        });
    }

	protected void btnAddActionPerformed(ActionEvent e) {
		if (!AddHousehold.getInstance().isVisible()) {
			AddHousehold.getInstance().setVisible(true);
	    }
	}
	protected void btnUpdateActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a row");
	    } else {
	    	selectedHouseholdId = table.getValueAt(selectedRow, 0).toString();
	    	UpdateHousehold.getInstance().loadData();
			if(!UpdateHousehold.getInstance().isVisible()) {
				UpdateHousehold.getInstance().setVisible(true);
				//UpdateHousehold.getInstance().clearTextField();
			}
	    }
	}
	protected void btnDeleteActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
	    if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a row for Delete");
	    } else {
	        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Household ?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

	        if (option == JOptionPane.YES_OPTION) {
	            var householdDAO = new HouseholdDAO();
	            String householdId = table.getValueAt(selectedRow, 0).toString();

	            Household household = householdDAO.getHouseholdById(householdId);
	            String imagePath = household.getImage();

	            try {
	                boolean isSuccess = householdDAO.deleteHousehold(householdId);
	                refreshHouseholds();

	                if (isSuccess) {
	                    
	                    deleteImage(imagePath);

	                    ShowMessage.showMessage("Message", "Household deleted successfully");
	                } else {
	                    ShowMessage.showErrorMessage("Error", "Cannot delete Household, some error happened!");
	                }
	            } catch (SQLIntegrityConstraintViolationException ex) {
	                ShowMessage.showErrorMessage("Error", "Cannot delete Household. This Staff is referenced in another table.");
	            }
	        }
	    }
	}
	
	private void deleteImage(String imagePath) {
	    if (imagePath != null && !imagePath.isEmpty()) {
	        try {
	            File file = new File(imagePath);
	            if (file.exists()) {
	                if (file.delete()) {
	                    System.out.println("Image deleted successfully");
	                } else {
	                    System.out.println("Failed to delete image");
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	protected void btnFindActionPerformed(ActionEvent e) {
		String selectedOption = comboBox.getSelectedItem().toString();
        String searchText = textField.getText().trim();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        switch (selectedOption) {
            case "Find by ID number":
                filters.add(RowFilter.regexFilter("(?i)" + searchText, 0));
                break;
            case "Find by Name":
                filters.add(RowFilter.regexFilter("(?i)" + searchText, 1));
                break;
            case "Find by Phone":
                filters.add(RowFilter.regexFilter("(?i)" + searchText, 2));
                break;
        }

        RowFilter<Object, Object> rowFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(rowFilter);
       
	}

	protected void btnExportActionPerformed(ActionEvent e) {
		RowSorter<? extends TableModel> rowSorter = table.getRowSorter();
	    
	    if (rowSorter != null && rowSorter.getViewRowCount() > 0) {
	        exportFilteredData();
	    } else {
	        exportAllData();
	    }
	}
	
	@SuppressWarnings("resource")
	private void exportFilteredData() {
	    try {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();
	        RowSorter<? extends TableModel> rowSorter = table.getRowSorter();

	        int[] selectedRows = new int[rowSorter.getViewRowCount()];
	        for (int i = 0; i < rowSorter.getViewRowCount(); i++) {
	            int modelRow = table.convertRowIndexToModel(i);
	            selectedRows[i] = modelRow;
	        }

	        Workbook workbook = new HSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Filtered Data");

	        Row headerRow = sheet.createRow(0);
	        for (int col = 0; col < model.getColumnCount(); col++) {
	            Cell cell = headerRow.createCell(col);
	            cell.setCellValue(model.getColumnName(col));
	        }

	        for (int i = 0; i < selectedRows.length; i++) {
	            int rowIndex = selectedRows[i];
	            Row dataRow = sheet.createRow(i + 1);
	            for (int col = 0; col < model.getColumnCount(); col++) {
	                Cell cell = dataRow.createCell(col);
	                cell.setCellValue(model.getValueAt(rowIndex, col).toString());
	            }
	        }

	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Save As");
	        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files (*.xls, *.xlsx)", "xls", "xlsx"));

	        int userSelection = fileChooser.showSaveDialog(this);

	        if (userSelection == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fileChooser.getSelectedFile();
	            String filePath = selectedFile.getAbsolutePath();

	            if (!filePath.toLowerCase().endsWith(".xls") && !filePath.toLowerCase().endsWith(".xlsx")) {
	                filePath += ".xlsx";
	            }

	            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
	                workbook.write(outputStream);
	                ShowMessage.showMessage("Export Success", "Filtered data exported to " + filePath);
	            }
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        ShowMessage.showErrorMessage("Export Error", "An error occurred during export.");
	    }
	}


	@SuppressWarnings("resource")
	private void exportAllData() {
	    try {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();

	        Workbook workbook = new HSSFWorkbook();
	        Sheet sheet = workbook.createSheet("All Data");

	        Row headerRow = sheet.createRow(0);
	        for (int col = 0; col < model.getColumnCount(); col++) {
	            Cell cell = headerRow.createCell(col);
	            cell.setCellValue(model.getColumnName(col));
	        }

	        for (int i = 0; i < model.getRowCount(); i++) {
	            Row dataRow = sheet.createRow(i + 1);
	            for (int col = 0; col < model.getColumnCount(); col++) {
	                Cell cell = dataRow.createCell(col);
	                cell.setCellValue(model.getValueAt(i, col).toString());
	            }
	        }

	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Save As");
	        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files (*.xls, *.xlsx)", "xls", "xlsx"));

	        int userSelection = fileChooser.showSaveDialog(this);

	        if (userSelection == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fileChooser.getSelectedFile();
	            String filePath = selectedFile.getAbsolutePath();

	            if (!filePath.toLowerCase().endsWith(".xls") && !filePath.toLowerCase().endsWith(".xlsx")) {
	                filePath += ".xlsx";
	            }

	            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
	                workbook.write(outputStream);
	                ShowMessage.showMessage("Export Success", "All data exported to " + filePath);
	            }
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        ShowMessage.showErrorMessage("Export Error", "An error occurred during export.");
	    }
	}
}
