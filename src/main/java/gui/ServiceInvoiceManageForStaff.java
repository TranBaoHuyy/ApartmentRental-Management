package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
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
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

public class ServiceInvoiceManageForStaff extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private static ServiceInvoiceManageForStaff instance;
    private JPanel panelToolBar;
    private JPanel panelContent;
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel panelButton;
    private JPanel panelFind;
	private JComboBox<String> comboBox;
    private JTextField textField;
    private JButton btnFind;
    private int selectServiceInvoiceId;
    private JButton btnPrint;
    private JButton btnCreateInvoice;
    private JButton btnInvoicePayment;
    private JButton btnExportData;
    private JLabel lblNewLabel;
    private JDateChooser dateChooserFrom;
    private JLabel lblTo;
    private JDateChooser dateChooserTo;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ServiceInvoiceManageForStaff frame = ServiceInvoiceManageForStaff.getInstance();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    
    public int getSelectServiceInvoiceId() {
		return selectServiceInvoiceId;
	}


	private ServiceInvoiceManageForStaff() {
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
        panelButton.setBounds(10, 11, 400, 120);
        panelButton.setBackground(new Color(255, 255, 255));
        panelButton.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Function", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        
        panelFind = new JPanel();
        panelFind.setBounds(420, 11, 504, 120);
        panelFind.setBackground(new Color(255, 255, 255));
        panelFind.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Find By", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        
      
        
        comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Find By ID", "Find By Apartment ID", "Find By Service Name", "Find By Payment Status"}));
        comboBox.setBounds(20, 32, 159, 29);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        textField = new JTextField();
        textField.setBounds(20, 76, 159, 29);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textField.setColumns(10);
        
        ImageIcon iconFind = ImagesUtils.createResizedIcon("/images/findButtonIcon.png", 45, 45);
        btnFind = new JButton("", iconFind);
        btnFind.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnFindActionPerformed(e);
        	}
        });
        btnFind.setBounds(425, 28, 69, 77);
        btnFind.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFind.setMargin(new Insets(10, 0, 10, 0));
        btnFind.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFind.setFocusPainted(false);
        btnFind.setBorder(null);
        btnFind.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnFind);
        panelFind.setLayout(null);
        panelFind.add(textField);
        panelFind.add(comboBox);
        panelFind.add(btnFind);
        
        panelToolBar.setLayout(null);
        panelButton.setLayout(null);
        panelToolBar.add(panelButton);       
        
        ImageIcon printIcon = ImagesUtils.createResizedIcon("/images/printButtonIcon.png", 45, 45);
        btnPrint = new JButton("Print", printIcon);
        btnPrint.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnPrintActionPerformed(e);
        	}
        });
        btnPrint.setBounds(304, 29, 80, 80);
        panelButton.add(btnPrint);
        btnPrint.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnPrint.setMargin(new Insets(10, 0, 10, 0));
        btnPrint.setHorizontalTextPosition(SwingConstants.CENTER);
        btnPrint.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnPrint.setFocusPainted(false);
        btnPrint.setBorder(null);
        btnPrint.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnPrint);
        
        ImageIcon addIcon = ImagesUtils.createResizedIcon("/images/addButtonIcon.png", 45, 45);
        btnCreateInvoice = new JButton("Create", addIcon);
        btnCreateInvoice.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCreateInvoiceActionPerformed(e);
        	}
        });
        btnCreateInvoice.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCreateInvoice.setMargin(new Insets(10, 0, 10, 0));
        btnCreateInvoice.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCreateInvoice.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCreateInvoice.setFocusPainted(false);
        btnCreateInvoice.setBorder(null);
        btnCreateInvoice.setBackground(Color.WHITE);
        btnCreateInvoice.setBounds(16, 29, 80, 80);
        ButtonUtils.setupButtonForJIFrame(btnCreateInvoice);
        panelButton.add(btnCreateInvoice);
        
        ImageIcon payIcon = ImagesUtils.createResizedIcon("/images/paidButtonIcon.png", 45, 45);
        btnInvoicePayment = new JButton("Payment", payIcon);
        btnInvoicePayment.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnInvoicePaymentActionPerformed(e);
        	}
        });
        btnInvoicePayment.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnInvoicePayment.setMargin(new Insets(10, 0, 10, 0));
        btnInvoicePayment.setHorizontalTextPosition(SwingConstants.CENTER);
        btnInvoicePayment.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnInvoicePayment.setFocusPainted(false);
        btnInvoicePayment.setBorder(null);
        btnInvoicePayment.setBackground(Color.WHITE);
        btnInvoicePayment.setBounds(112, 29, 80, 80);
        ButtonUtils.setupButtonForJIFrame(btnInvoicePayment);
        panelButton.add(btnInvoicePayment);
        
        ImageIcon exportIcon = ImagesUtils.createResizedIcon("/images/exportButtonIcon.png", 45, 45);
        btnExportData = new JButton("Export", exportIcon);
        btnExportData.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnExportDataActionPerformed(e);
        	}
        });
        btnExportData.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnExportData.setMargin(new Insets(10, 0, 10, 0));
        btnExportData.setHorizontalTextPosition(SwingConstants.CENTER);
        btnExportData.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnExportData.setFocusPainted(false);
        btnExportData.setBorder(null);
        btnExportData.setBackground(Color.WHITE);
        btnExportData.setBounds(208, 29, 80, 80);
        ButtonUtils.setupButtonForJIFrame(btnExportData);
        panelButton.add(btnExportData);
        panelToolBar.add(panelFind);
        
        lblNewLabel = new JLabel("From");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(208, 32, 34, 29);
        panelFind.add(lblNewLabel);
        
        dateChooserFrom = new JDateChooser();
        dateChooserFrom.setBounds(252, 32, 150, 29);
        panelFind.add(dateChooserFrom);
        dateChooserFrom.setDateFormatString("dd-MM-yyyy");
        
        lblTo = new JLabel("To");
        lblTo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTo.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTo.setBounds(208, 76, 34, 29);
        panelFind.add(lblTo);
        
        dateChooserTo = new JDateChooser();
        dateChooserTo.setBounds(252, 76, 150, 29);
        panelFind.add(dateChooserTo);
        dateChooserTo.setDateFormatString("dd-MM-yyyy");
  
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

    public static ServiceInvoiceManageForStaff getInstance() {
        if (instance == null) {
            instance = new ServiceInvoiceManageForStaff();
        }
        return instance;
    }
    
    @SuppressWarnings("serial")
	private void loadServiceInvoice() {
		DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) {
                    return Date.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };
		
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		serviceInvoiceDAO.getAllServiceInvoices().stream().forEach(serviceInvoice -> {
			java.util.Date printingDate = null;
	        try {
				printingDate = serviceInvoice.getPrintingDate() != null ? dateFormat.parse(dateFormat.format(serviceInvoice.getPrintingDate())) : null;
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}

	        String formattedPaymentDate = serviceInvoice.getPaymentDate() != null ? dateFormat.format(serviceInvoice.getPaymentDate()) : "N/A";
		    model.addRow(new Object[] {
		            serviceInvoice.getId(),
		            serviceInvoice.getName(),
		            serviceInvoice.getStaffId(),
		            serviceInvoice.getApartmentId(),
		            printingDate,
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
			java.util.Date printingDate = null;
	        try {
				printingDate = serviceInvoice.getPrintingDate() != null ? dateFormat.parse(dateFormat.format(serviceInvoice.getPrintingDate())) : null;
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}

	        String formattedPaymentDate = serviceInvoice.getPaymentDate() != null ? dateFormat.format(serviceInvoice.getPaymentDate()) : "N/A";
		    model.addRow(new Object[] {
		            serviceInvoice.getId(),
		            serviceInvoice.getName(),
		            serviceInvoice.getStaffId(),
		            serviceInvoice.getApartmentId(),
		            printingDate,
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
    protected void btnFindActionPerformed(ActionEvent e) {

        String selectedOption = comboBox.getSelectedItem().toString();
        String searchText = textField.getText().trim();

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

        if (dateChooserFrom.getDate() != null && dateChooserTo.getDate() != null) {
        	Date fromDate = new Date(dateChooserFrom.getDate().getTime());
            Date toDate = new Date(dateChooserTo.getDate().getTime());

            filters.add(RowFilter.dateFilter(RowFilter.ComparisonType.AFTER, fromDate, 4));
            filters.add(RowFilter.dateFilter(RowFilter.ComparisonType.BEFORE, toDate, 4));
        }

        RowFilter<Object, Object> rowFilter = RowFilter.andFilter(filters);
        sorter.setRowFilter(rowFilter);
       
    }

	protected void btnCreateInvoiceActionPerformed(ActionEvent e) {
		if (!AddServiceInvoiceForStaff.getInstance().isVisible()) {
			AddServiceInvoiceForStaff.getInstance().setVisible(true);
	    }
	}
	protected void btnInvoicePaymentActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a Invoice for Payment");
	    } else {
	    	ServiceInvoiceDAO serviceInvoiceDAO = new ServiceInvoiceDAO();
	        int serviceInvoiceId = (int) table.getValueAt(selectedRow, 0);

	        Date paymentDate = Date.valueOf(LocalDate.now());

	        boolean success = serviceInvoiceDAO.setStatusToTrue(serviceInvoiceId, paymentDate);

	        if (success) {
	        	refresh();
				int choice = JOptionPane.showConfirmDialog(this, "Invoice Payment Successful. Do you want to print the paid invoice?", "Print Invoice", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
				     printInvoice(selectedRow);
				}  
	        } else {
	            ShowMessage.showErrorMessage("Error", "Failed to Process Invoice Payment");
	        }
	    }
	}
	
	protected void btnExportDataActionPerformed(ActionEvent e) {
    
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

	protected void btnPrintActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose an Invoice for Print");
	        return;
	    }
		
		printInvoice(selectedRow);
		
	}
	
	private void printInvoice(int selectedRow) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    
	    java.util.Date date = (java.util.Date) table.getValueAt(selectedRow, 4);
	    SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM");
	    String monthName = dateFormat1.format(date);

	    try {
	        String reportPath = "/reports/Invoice.jrxml";
	        InputStream inputStream = getClass().getResourceAsStream(reportPath);
	        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

	        JRTableModelDataSource dataSource = new JRTableModelDataSource((DefaultTableModel) table.getModel());

	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("invoiceName", table.getValueAt(selectedRow, 8).toString() + " invoice for " + monthName);
	        parameters.put("staffId", table.getValueAt(selectedRow, 2).toString());
	        parameters.put("apartmentId", table.getValueAt(selectedRow, 3).toString());
	        parameters.put("printingDate", dateFormat.format(table.getValueAt(selectedRow, 4)));
	        parameters.put("paymentDate", table.getValueAt(selectedRow, 5).toString());
	        parameters.put("serviceName", table.getValueAt(selectedRow, 8).toString());
	        parameters.put("status", table.getValueAt(selectedRow, 12).toString());
	        parameters.put("quantity", table.getValueAt(selectedRow, 9).toString());
	        parameters.put("price", table.getValueAt(selectedRow, 10).toString());
	        parameters.put("totalPrice", table.getValueAt(selectedRow, 11).toString());

	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

	        JasperViewer viewer = new JasperViewer(jasperPrint, false);
	        viewer.setVisible(true);

	    } catch (JRException er) {
	        er.printStackTrace();
	        ShowMessage.showErrorMessage("Print Error", "An error occurred during printing.");
	    }
	}
}
