package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
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

import dao.ContractDAO;
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

public class ContractManage extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private static ContractManage instance;
    private JPanel panelToolBar;
    private JPanel panelContent;
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel panelButton;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnExport;
    
    private String selectedContractId;
    private JPanel panelFind;
    private JComboBox<String> comboBox;
    private JButton btnFind;
    private JTextField textField;;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ContractManage frame = ContractManage.getInstance();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    
    public String getSelectedContractId() {
		return selectedContractId;
	}

	private ContractManage() {
        super("Contract Manage", false, false, false, false);
        setBounds(100, 100, 950, 695);
        setBackground(new Color(238, 242, 243));
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        panelToolBar = new JPanel();
        getContentPane().add(panelToolBar, BorderLayout.NORTH);
        panelToolBar.setPreferredSize(new Dimension(getContentPane().getWidth(), 135));
        panelToolBar.setBackground(new Color(255, 255, 255));
        
        panelButton = new JPanel();
        panelButton.setBackground(new Color(255, 255, 255));
        panelButton.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Function", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        
        panelFind = new JPanel();
        panelFind.setLayout(null);
        panelFind.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Find By", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panelFind.setBackground(Color.WHITE);
        
        comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Household ID Number", "Apartment ID", "Contract Number", "Status"}));
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
        comboBox.setBounds(20, 23, 150, 30);
        panelFind.add(comboBox);
        
        ImageIcon iconFind = ImagesUtils.createResizedIcon("/images/findButtonIcon.png", 45, 45);
        btnFind = new JButton("Find", iconFind);
        btnFind.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnFindActionPerformed(e);
        	}
        });
        btnFind.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFind.setMargin(new Insets(10, 0, 10, 0));
        btnFind.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFind.setFocusPainted(false);
        btnFind.setBorder(null);
        btnFind.setBackground(Color.WHITE);
        btnFind.setBounds(180, 23, 77, 77);
        ButtonUtils.setupButtonForJIFrame(btnFind);
        panelFind.add(btnFind);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(20, 70, 150, 30);
        panelFind.add(textField);
        
        
        GroupLayout gl_panelToolBar = new GroupLayout(panelToolBar);
        gl_panelToolBar.setHorizontalGroup(
        	gl_panelToolBar.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelToolBar.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(panelButton, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(panelFind, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(349, Short.MAX_VALUE))
        );
        gl_panelToolBar.setVerticalGroup(
        	gl_panelToolBar.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_panelToolBar.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panelToolBar.createParallelGroup(Alignment.TRAILING)
        				.addComponent(panelFind, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
        				.addComponent(panelButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addContainerGap())
        );
        
        
        

        

        
        ImageIcon iconAdd = ImagesUtils.createResizedIcon("/images/addButtonIcon.png", 45, 45);
        btnAdd = new JButton("Add", iconAdd);
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
        
        ImageIcon iconExport = ImagesUtils.createResizedIcon("/images/exportButtonIcon.png", 45, 45);
        btnExport = new JButton("Export", iconExport);
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
        
        GroupLayout gl_panelButton = new GroupLayout(panelButton);
        gl_panelButton.setHorizontalGroup(
        	gl_panelButton.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelButton.createSequentialGroup()
        			.addGap(6)
        			.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panelButton.setVerticalGroup(
        	gl_panelButton.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panelButton.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panelButton.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
        				.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
        				.addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
        				.addComponent(btnExport, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
        );
        panelButton.setLayout(gl_panelButton);
        panelToolBar.setLayout(gl_panelToolBar);
        
        panelContent = new JPanel();
        panelContent.setBackground(new Color(255, 255, 255));
        getContentPane().add(panelContent, BorderLayout.CENTER);
        
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
        loadContracts();
    }

    public static ContractManage getInstance() {
        if (instance == null) {
            instance = new ContractManage();
        }
        return instance;
    }
    
    private void loadContracts() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Contract Number");
        model.addColumn("Start Date");
        model.addColumn("End Date");
        model.addColumn("Apartment ID");
        model.addColumn("Staff ID");
        model.addColumn("Household ID Number");
        model.addColumn("Status");

        ContractDAO contractDAO = new ContractDAO();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        contractDAO.getAllContracts().forEach(contract -> {
            String formattedStartDate = dateFormat.format(contract.getStartDate());
            String formattedEndDate = (contract.getEndDate() != null)
                    ? dateFormat.format(contract.getEndDate())
                    : "N/A";

            model.addRow(new Object[]{
                    contract.getId(),
                    contract.getContractNumber(),
                    formattedStartDate,
                    formattedEndDate,
                    contract.getApartmentId(),
                    contract.getStaffId(),
                    contract.getHouseholdIdNumber(),
                    contract.isStatus() ? "Validated" : "Expired"
            });
        });

        table.setModel(model);
        table.setRowHeight(30);
    }

    public void refreshContracts() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        ContractDAO contractDAO = new ContractDAO();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        contractDAO.getAllContracts().forEach(contract -> {
            String formattedStartDate = dateFormat.format(contract.getStartDate());
            String formattedEndDate = (contract.getEndDate() != null)
                    ? dateFormat.format(contract.getEndDate())
                    : "N/A";

            model.addRow(new Object[]{
                    contract.getId(),
                    contract.getContractNumber(),
                    formattedStartDate,
                    formattedEndDate,
                    contract.getApartmentId(),
                    contract.getStaffId(),
                    contract.getHouseholdIdNumber(),
                    contract.isStatus() ? "Validated" : "Expired"
            });
        });
    }

	protected void btnAddActionPerformed(ActionEvent e) {
		if (!AddContract.getInstance().isVisible()) {
			AddContract.getInstance().setVisible(true);
	    }
	}
	protected void btnUpdateActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a row");
	    } else {
	    	selectedContractId = table.getValueAt(selectedRow, 0).toString();
			if(!UpdateContract.getInstance().isVisible()) {
				UpdateContract.getInstance().setVisible(true);
			}
	    	
	    }
	}
	protected void btnDeleteActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a row for Delete");
	    } else {
	    	int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this contract?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
	    	
	    	if (option == JOptionPane.YES_OPTION) {
	            var contractDAO = new ContractDAO();
	            try {
		            boolean isSuccess = contractDAO.deleteContract(Integer.parseInt(table.getValueAt(selectedRow, 0).toString()));
		            refreshContracts();
	
		            if (isSuccess) {
		                ShowMessage.showMessage("Message", "Contract deleted successfully");
		            } else {
		                ShowMessage.showErrorMessage("Error", "Cannot delete Contract, some error happened!");
		            }
	            } catch (SQLIntegrityConstraintViolationException ex) {
	                ShowMessage.showErrorMessage("Error", "Cannot delete Contract. This Contract is referenced in another table.");
	            }
	        }
	    }
	}
	protected void btnFindActionPerformed(ActionEvent e) {
		String selectedOption = comboBox.getSelectedItem().toString();
	    String searchText = textField.getText().trim();

	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	    table.setRowSorter(sorter);

	    RowFilter<Object, Object> rowFilter = null;
	    switch (selectedOption) {
	        case "Household ID Number":
	            rowFilter = RowFilter.regexFilter("(?i)" + searchText, 6);
	            break;
	        case "Apartment ID":
	            rowFilter = RowFilter.regexFilter("(?i)" + searchText, 4);
	            break;
	        case "Contract Number":
	            rowFilter = RowFilter.regexFilter("(?i)" + searchText, 1);
	            break;
	        case "Status":
	            rowFilter = RowFilter.regexFilter("(?i)" + searchText, 7);
	            break;
	    }

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
