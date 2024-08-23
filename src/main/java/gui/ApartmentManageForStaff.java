package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


import dao.ApartmentDAO;
import dao.ApartmentTypeDAO;
import utils.ButtonUtils;
import utils.ImagesUtils;
import utils.ShowMessage;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class ApartmentManageForStaff extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private static ApartmentManageForStaff instance;
    private JPanel panelToolBar;
    private JPanel panelContent;
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel panelFind;
	private JComboBox<String> comboBox;
    private JButton btnFind;
    
    private String selectedApartmentId;
    private JPanel panelFind_1;
    private JButton btnMakeContract;
    private JTextField textField;
    private JButton btnApartmentDetail;
    private JButton btnVacantApartment;
    private JButton btnExport;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ApartmentManageForStaff frame = ApartmentManageForStaff.getInstance();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    
    public String getSelectedApartmentId() {
		return selectedApartmentId;
	}


	private ApartmentManageForStaff() {
        super("Apartment Manage", false, false, false, false);
        setName("apartment");
        setBounds(100, 100, 950, 695);
        setBackground(new Color(238, 242, 243));
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        panelToolBar = new JPanel();
        getContentPane().add(panelToolBar, BorderLayout.NORTH);
        panelToolBar.setPreferredSize(new Dimension(getContentPane().getWidth(), 135));
        panelToolBar.setBackground(new Color(255, 255, 255));
        
        panelFind = new JPanel();
        panelFind.setBounds(651, 11, 273, 113);
        panelFind.setBackground(new Color(255, 255, 255));
        panelFind.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Find", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        
        
        comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Find by Apartment ID", "Find by Area Name", "Find by Status"}));
        comboBox.setBounds(20, 23, 150, 30);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        ImageIcon iconFind = ImagesUtils.createResizedIcon("/images/findButtonIcon.png", 45, 45);
        btnFind = new JButton(null, iconFind);
        btnFind.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		performSearch(e);
        	}
        });
        btnFind.setBounds(180, 23, 77, 77);
        btnFind.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFind.setMargin(new Insets(10, 0, 10, 0));
        btnFind.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFind.setFocusPainted(false);
        btnFind.setBorder(null);
        btnFind.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnFind);
        panelFind.setLayout(null);
        panelFind.add(comboBox);
        panelFind.add(btnFind);
  
        panelToolBar.setLayout(null);
        panelToolBar.add(panelFind);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(20, 70, 150, 30);
        panelFind.add(textField);
        
        panelFind_1 = new JPanel();
        panelFind_1.setLayout(null);
        panelFind_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Action Button", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panelFind_1.setBackground(Color.WHITE);
        panelFind_1.setBounds(10, 11, 631, 113);
        panelToolBar.add(panelFind_1);
        
        ImageIcon iconAdd = ImagesUtils.createResizedIcon("/images/contractButtonIcon.png", 45, 45);
        btnMakeContract = new JButton("Make Contract", iconAdd);
        btnMakeContract.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnMakeContractActionPerformed(e);
        	}
        });
        btnMakeContract.setBounds(326, 22, 130, 80);
        btnMakeContract.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnMakeContract.setMargin(new Insets(10, 0, 10, 0));
        btnMakeContract.setHorizontalTextPosition(SwingConstants.CENTER);
        btnMakeContract.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnMakeContract.setFocusPainted(false);
        btnMakeContract.setBorder(null);
        btnMakeContract.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnMakeContract);
        panelFind_1.add(btnMakeContract);
        
        ImageIcon iconDetail = ImagesUtils.createResizedIcon("/images/detailButtonIcon.png", 45, 45);
        btnApartmentDetail = new JButton("Apartment Detail", iconDetail);
        btnApartmentDetail.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnApartmentDetailActionPerformed(e);
        	}
        });
        btnApartmentDetail.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnApartmentDetail.setMargin(new Insets(10, 0, 10, 0));
        btnApartmentDetail.setHorizontalTextPosition(SwingConstants.CENTER);
        btnApartmentDetail.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnApartmentDetail.setFocusPainted(false);
        btnApartmentDetail.setBorder(null);
        btnApartmentDetail.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnApartmentDetail);
        btnApartmentDetail.setBounds(174, 22, 130, 80);
        panelFind_1.add(btnApartmentDetail);
        
        ImageIcon iconVacant = ImagesUtils.createResizedIcon("/images/vacantApartmentButtonIcon.png", 45, 45);
        btnVacantApartment = new JButton("Vacant Apartment", iconVacant);
        btnVacantApartment.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnVacantApartmentActionPerformed(e);
        	}
        });
        btnVacantApartment.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnVacantApartment.setMargin(new Insets(10, 0, 10, 0));
        btnVacantApartment.setHorizontalTextPosition(SwingConstants.CENTER);
        btnVacantApartment.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVacantApartment.setFocusPainted(false);
        btnVacantApartment.setBorder(null);
        btnVacantApartment.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnVacantApartment);
        btnVacantApartment.setBounds(22, 22, 130, 80);
        panelFind_1.add(btnVacantApartment);
        
        ImageIcon exportIcon = ImagesUtils.createResizedIcon("/images/exportButtonIcon.png", 45, 45);
        btnExport = new JButton("Export Data", exportIcon);
        btnExport.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnExportActionPerformed(e);
        	}
        });
        
        btnExport.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnExport.setMargin(new Insets(10, 0, 10, 0));
        btnExport.setHorizontalTextPosition(SwingConstants.CENTER);
        btnExport.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnExport.setFocusPainted(false);
        btnExport.setBorder(null);
        btnExport.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnExport);
        btnExport.setBounds(478, 22, 130, 80);
        panelFind_1.add(btnExport);
        
        panelContent = new JPanel();
        panelContent.setBackground(new Color(255, 255, 255));
        getContentPane().add(panelContent, BorderLayout.CENTER);
        
        scrollPane = new JScrollPane();
        GroupLayout gl_panelContent = new GroupLayout(panelContent);
        gl_panelContent.setHorizontalGroup(
        	gl_panelContent.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelContent.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_panelContent.setVerticalGroup(
        	gl_panelContent.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_panelContent.createSequentialGroup()
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
        			.addContainerGap())
        );
        
        table = new JTable();
        scrollPane.setViewportView(table);
        panelContent.setLayout(gl_panelContent);
        loadApartments();
    }

    public static ApartmentManageForStaff getInstance() {
        if (instance == null) {
            instance = new ApartmentManageForStaff();
        }
        return instance;
    }
    
    private void loadApartments() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Apartment ID");
        model.addColumn("Notes");
        model.addColumn("Area Name");
        model.addColumn("Household ID");
        model.addColumn("Type");
        model.addColumn("Price");
        model.addColumn("Status");

        ApartmentDAO apartmentDAO = new ApartmentDAO();
        ApartmentTypeDAO apartmentTypeDAO = new ApartmentTypeDAO();
        
        apartmentDAO.getAllApartments().forEach(apartment -> {
            String areaName = apartment.getAreaName();
            if (areaName == null || areaName.isEmpty()) {
                areaName = "N/A";
            }

            model.addRow(new Object[]{
                    apartment.getId(),
                    apartment.getNotes(),
                    areaName,
                    apartment.getHouseholdId() != null ? apartment.getHouseholdId() : "N/A",
                    apartmentTypeDAO.getApartmentTypeById(apartment.getTypeId()).getType(),
                    apartmentTypeDAO.getApartmentTypeById(apartment.getTypeId()).getPrice(),
                    apartment.getStatus()
            });
        });

        table.setModel(model);
        table.setRowHeight(30);
    }

    public void refreshApartments() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        ApartmentDAO apartmentDAO = new ApartmentDAO();
        ApartmentTypeDAO apartmentTypeDAO = new ApartmentTypeDAO();
        
        apartmentDAO.getAllApartments().forEach(apartment -> {
            String areaName = apartment.getAreaName();
            if (areaName == null || areaName.isEmpty()) {
                areaName = "N/A";
            }

            model.addRow(new Object[]{
                    apartment.getId(),
                    apartment.getNotes(),
                    areaName,
                    apartment.getHouseholdId() != null ? apartment.getHouseholdId() : "N/A",
                    apartmentTypeDAO.getApartmentTypeById(apartment.getTypeId()).getType(),
                    apartmentTypeDAO.getApartmentTypeById(apartment.getTypeId()).getPrice(),
                    apartment.getStatus()
            });
        });
    }
	protected void performSearch(ActionEvent e) {
		String selectedOption = comboBox.getSelectedItem().toString();
	    String searchText = textField.getText().trim();

	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	    table.setRowSorter(sorter);

	    RowFilter<Object, Object> rowFilter = null;
	    switch (selectedOption) {
	        case "Find by Apartment ID":
	            rowFilter = RowFilter.regexFilter(searchText, 0);
	            break;
	        case "Find by Area Name":
	            rowFilter = RowFilter.regexFilter("(?i)" + searchText, 2);
	            break;
	        case "Find by Status":
	            rowFilter = RowFilter.regexFilter("(?i)" + searchText, 6);
	            break;
	    }

	    sorter.setRowFilter(rowFilter);
	}
	protected void btnVacantApartmentActionPerformed(ActionEvent e) {
		refreshApartments();
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	    table.setRowSorter(sorter);
	    RowFilter<Object, Object> rowFilter = null;
	    rowFilter = RowFilter.regexFilter("Vacant", 6);
	    sorter.setRowFilter(rowFilter);
		
	}
	protected void btnApartmentDetailActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a row");
	    } else {
	    	selectedApartmentId = table.getValueAt(selectedRow, 0).toString();
	    	DetailApartment.getInstance().loadData();
			if(!DetailApartment.getInstance().isVisible()) {
				DetailApartment.getInstance().setVisible(true);
			}	    	
	    }
	}
	protected void btnMakeContractActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a Apartment");
	    } else if(table.getValueAt(selectedRow, 6).toString().equals("Vacant")) {
	    	selectedApartmentId = table.getValueAt(selectedRow, 0).toString();
	    	AddContractForStaff.getInstance().loadData();
			if(!AddContractForStaff.getInstance().isVisible()) {
				AddContractForStaff.getInstance().setVisible(true);
			}	    	
	    } else {
	    	ShowMessage.showWarningMessage("Warning", "Please Choose a vacant Apartment");
	    }
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

	        // Copy header
	        Row headerRow = sheet.createRow(0);
	        for (int col = 0; col < model.getColumnCount(); col++) {
	            Cell cell = headerRow.createCell(col);
	            cell.setCellValue(model.getColumnName(col));
	        }

	        // Copy selected rows
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

	        // Copy header
	        Row headerRow = sheet.createRow(0);
	        for (int col = 0; col < model.getColumnCount(); col++) {
	            Cell cell = headerRow.createCell(col);
	            cell.setCellValue(model.getColumnName(col));
	        }

	        // Copy all rows
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
