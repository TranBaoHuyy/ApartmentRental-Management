package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingConstants;
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

import dao.ServiceDAO;
import dao.ServiceInvoiceDAO;
import utils.ButtonUtils;
import utils.ImagesUtils;
import utils.ShowMessage;

import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class ServiceInvoiceManage extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private static ServiceInvoiceManage instance;
    private JPanel panelToolBar;
    private JPanel panelContent;
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel panelButton;
    private JPanel panelFind;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
	private JComboBox<String> comboBoxFind;
    private JTextField textFieldFind;
    private JButton btnFind;
    private int selectServiceInvoiceId;
    private JButton btnExport;
    
    public int getSelectServiceInvoiceId() {
		return selectServiceInvoiceId;
	}


	private ServiceInvoiceManage() {
        super("Service Invoice Manage", false, false, false, false);
        setBounds(100, 100, 950, 695);
        setBackground(new Color(238, 242, 243));
        getContentPane().setLayout(null);
        
        panelToolBar = new JPanel();
        panelToolBar.setBounds(0, 0, 934, 135);
        getContentPane().add(panelToolBar);
        panelToolBar.setPreferredSize(new Dimension(getContentPane().getWidth(), 135));
        panelToolBar.setBackground(new Color(255, 255, 255));
        
        panelButton = new JPanel();
        panelButton.setBounds(10, 11, 317, 120);
        panelButton.setBackground(new Color(255, 255, 255));
        panelButton.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Function", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        
        panelFind = new JPanel();
        panelFind.setBounds(337, 11, 260, 120);
        panelFind.setBackground(new Color(255, 255, 255));
        panelFind.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Find By", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        
      
        
        comboBoxFind = new JComboBox<String>();
        comboBoxFind.setModel(new DefaultComboBoxModel<String>(new String[] {"Find By ID", "Find By Apartment ID", "Find By Service Name", "Find By Payment Status"}));
        comboBoxFind.setBounds(10, 32, 159, 29);
        comboBoxFind.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        textFieldFind = new JTextField();
        textFieldFind.setBounds(10, 76, 159, 29);
        textFieldFind.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldFind.setColumns(10);
        
        ImageIcon iconFind = ImagesUtils.createResizedIcon("/images/findButtonIcon.png", 45, 45);
        btnFind = new JButton(null, iconFind);
        btnFind.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnFindActionPerformed(e);
        	}
        });
        btnFind.setBounds(179, 28, 69, 77);
        btnFind.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFind.setMargin(new Insets(10, 0, 10, 0));
        btnFind.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFind.setFocusPainted(false);
        btnFind.setBorder(null);
        btnFind.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnFind);
        panelFind.setLayout(null);
        panelFind.add(textFieldFind);
        panelFind.add(comboBoxFind);
        panelFind.add(btnFind);
        

        
        ImageIcon iconAdd = ImagesUtils.createResizedIcon("/images/addButtonIcon.png", 45, 45);
        btnAdd = new JButton("Add", iconAdd);
        btnAdd.setBounds(12, 28, 63, 87);
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
        btnDelete.setBounds(87, 27, 63, 88);
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
        btnUpdate.setBounds(162, 27, 63, 88);
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
        btnExport.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnExport.setMargin(new Insets(10, 0, 10, 0));
        btnExport.setHorizontalTextPosition(SwingConstants.CENTER);
        btnExport.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnExport.setFocusPainted(false);
        btnExport.setBorder(null);
        btnExport.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnExport);
        btnExport.setBounds(237, 28, 63, 88);
        panelButton.add(btnExport);
        
        panelToolBar.add(panelFind);
  
        panelContent = new JPanel();
        panelContent.setBounds(0, 134, 934, 526);
        panelContent.setBackground(new Color(255, 255, 255));
        getContentPane().add(panelContent);
        panelContent.setLayout(null);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 914, 498);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        panelContent.add(scrollPane);
        loadServiceInvoice();
        
    }

    public static ServiceInvoiceManage getInstance() {
        if (instance == null) {
            instance = new ServiceInvoiceManage();
        }
        return instance;
    }
    
    private void loadServiceInvoice() {
		DefaultTableModel model = new DefaultTableModel();
			
		
		model.addColumn("Invoice ID");
		model.addColumn("Invoice Name");
		model.addColumn("Staff ID");
		model.addColumn("Apartment ID");
		model.addColumn("Printing Date");
		model.addColumn("Payment Date");
		model.addColumn("Note");
		model.addColumn("Service ID");
		model.addColumn("Service Name");
		model.addColumn("Quantity");
		model.addColumn("Price");
		model.addColumn("Total");
		model.addColumn("Status");
		
		ServiceDAO serviceDAO = new ServiceDAO();
		ServiceInvoiceDAO serviceInvoiceDAO = new ServiceInvoiceDAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		serviceInvoiceDAO.getAllServiceInvoices().stream().forEach(serviceInvoice -> {
		    String formattedPrintingDate = serviceInvoice.getPrintingDate() != null ? dateFormat.format(serviceInvoice.getPrintingDate()) : "N/A";
		    String formattedPaymentDate = serviceInvoice.getPaymentDate() != null ? dateFormat.format(serviceInvoice.getPaymentDate()) : "N/A";
		    model.addRow(new Object[] {
		            serviceInvoice.getId(),
		            serviceInvoice.getName(),
		            serviceInvoice.getStaffId(),
		            serviceInvoice.getApartmentId(),
		            formattedPrintingDate,
		            formattedPaymentDate,
		            serviceInvoice.getNotes(),
		            serviceInvoice.getServiceId(),
		            serviceDAO.getServiceNameById(serviceInvoice.getServiceId()),
		            serviceInvoice.getQuantity(),
		            serviceInvoice.getPrice(),
		            serviceInvoice.getQuantity() * serviceInvoice.getPrice(),
		            serviceInvoice.isStatus() ? "Paid" : "Unpaid"
		    });
		});
		
		table.setModel(model);
		table.setRowHeight(30);
	}
    
    public void refresh() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		ServiceDAO serviceDAO = new ServiceDAO();
		ServiceInvoiceDAO serviceInvoiceDAO = new ServiceInvoiceDAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		serviceInvoiceDAO.getAllServiceInvoices().stream().forEach(serviceInvoice -> {
			String formattedPrintingDate = serviceInvoice.getPrintingDate() != null ? dateFormat.format(serviceInvoice.getPrintingDate()) : "N/A";
		    String formattedPaymentDate = serviceInvoice.getPaymentDate() != null ? dateFormat.format(serviceInvoice.getPaymentDate()) : "N/A";
		    model.addRow(new Object[] {
		            serviceInvoice.getId(),
		            serviceInvoice.getName(),
		            serviceInvoice.getStaffId(),
		            serviceInvoice.getApartmentId(),
		            formattedPrintingDate,
		            formattedPaymentDate,
		            serviceInvoice.getNotes(),
		            serviceInvoice.getServiceId(),
		            serviceDAO.getServiceNameById(serviceInvoice.getServiceId()),
		            serviceInvoice.getQuantity(),
		            serviceInvoice.getPrice(),
		            serviceInvoice.getQuantity() * serviceInvoice.getPrice(),
		            serviceInvoice.isStatus() ? "Paid" : "Unpaid"
		    });
		});
	}
    
	protected void btnAddActionPerformed(ActionEvent e) {
		AddServiceInvoice.getInstance().loadData();
		if (!AddServiceInvoice.getInstance().isVisible()) {
			AddServiceInvoice.getInstance().setVisible(true);
	    }
	}
	protected void btnDeleteActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a row for Delete");
	    } else {
	    	int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this service invoice?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
	    	
	    	if (option == JOptionPane.YES_OPTION) {
	            var serviceInvoiceDAO = new ServiceInvoiceDAO();
	            try {
		            boolean isSuccess = serviceInvoiceDAO.deleteServiceInvoice(Integer.parseInt(table.getValueAt(selectedRow, 0).toString()));
		            refresh();
	
		            if (isSuccess) {
		                ShowMessage.showMessage("Message", "Service invoice deleted successfully");
		            } else {
		                ShowMessage.showErrorMessage("Error", "Cannot delete service invoice, some error happened!");
		            }
	            } catch (SQLIntegrityConstraintViolationException ex) {
	                ShowMessage.showErrorMessage("Error", "Cannot delete Service Invoice. This Invoice is referenced in another table.");
	            }
	        }
	    }
	}
	
	protected void btnUpdateActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
	
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a row for Update");
	    } else {
	    	selectServiceInvoiceId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
	    	UpdateServiceInvoice.getInstance().loadData();
			if(!UpdateServiceInvoice.getInstance().isVisible()) {
				UpdateServiceInvoice.getInstance().setVisible(true);
			}
	    }
	}
	protected void btnFindActionPerformed(ActionEvent e) {
		String selectedOption = comboBoxFind.getSelectedItem().toString();
        String searchText = textFieldFind.getText().trim();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        switch (selectedOption) {
            case "Find By ID":
                filters.add(RowFilter.regexFilter("(?i)" + searchText, 0));
                break;
            case "Find By Apartment ID":
                filters.add(RowFilter.regexFilter("(?i)" + searchText, 3));
                break;
            case "Find By Service Name":
                filters.add(RowFilter.regexFilter("(?i)" + searchText, 8));
                break;
            case "Find By Payment Status":
                filters.add(RowFilter.regexFilter("(?i)" + searchText, 12));
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
