package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.ApartmentDAO;
import dao.ApartmentTypeDAO;
import dao.ContractDAO;
import dao.HouseholdDAO;
import dao.StaffDAO;
import entity.ApartmentType;
import entity.Household;
import entity.Staff;
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

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;


public class ContractManageForStaff extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private static ContractManageForStaff instance;
    private JPanel panelToolBar;
    private JPanel panelContent;
    private JScrollPane scrollPane;
    private JTable table;
    
    private String selectedContractId;
    private JPanel panelFind;
    private JComboBox<String> comboBox;
    private JButton btnFind;
    private JTextField textField;
    private JPanel panelFind_1;
    private JButton btnExpiringSoon;
    private JButton btnCreateContract;
    private JButton btnEndContract;
    private JButton btnPrintContract;;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ContractManageForStaff frame = ContractManageForStaff.getInstance();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    
    public String getSelectedContractId() {
		return selectedContractId;
	}

	private ContractManageForStaff() {
        super("Contract Manage", false, false, false, false);
        setBounds(100, 100, 950, 695);
        setBackground(new Color(238, 242, 243));
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        panelToolBar = new JPanel();
        getContentPane().add(panelToolBar, BorderLayout.NORTH);
        panelToolBar.setPreferredSize(new Dimension(getContentPane().getWidth(), 135));
        panelToolBar.setBackground(new Color(255, 255, 255));
        
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
        
        panelFind_1 = new JPanel();
        panelFind_1.setLayout(null);
        panelFind_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Action Button", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panelFind_1.setBackground(Color.WHITE);
        
        ImageIcon iconContract = ImagesUtils.createResizedIcon("/images/ContractButtonIcon.png", 45, 45);
        btnExpiringSoon = new JButton("Expiring Soon", iconContract);
        btnExpiringSoon.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnExpiringSoonActionPerformed(e);
        	}
        });
        btnExpiringSoon.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnExpiringSoon.setMargin(new Insets(10, 0, 10, 0));
        btnExpiringSoon.setHorizontalTextPosition(SwingConstants.CENTER);
        btnExpiringSoon.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnExpiringSoon.setFocusPainted(false);
        btnExpiringSoon.setBorder(null);
        btnExpiringSoon.setBackground(Color.WHITE);
        btnExpiringSoon.setBounds(326, 22, 130, 80);
        ButtonUtils.setupButtonForJIFrame(btnExpiringSoon);
        panelFind_1.add(btnExpiringSoon);
        
        ImageIcon iconAddContrac = ImagesUtils.createResizedIcon("/images/addContractButtonIcon.png", 45, 45);
        btnCreateContract = new JButton("Create Contract", iconAddContrac);
        btnCreateContract.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCreateContractActionPerformed(e);
        	}
        });
        btnCreateContract.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnCreateContract.setMargin(new Insets(10, 0, 10, 0));
        btnCreateContract.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCreateContract.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCreateContract.setFocusPainted(false);
        btnCreateContract.setBorder(null);
        btnCreateContract.setBackground(Color.WHITE);
        btnCreateContract.setBounds(22, 22, 130, 80);
        ButtonUtils.setupButtonForJIFrame(btnCreateContract);
        panelFind_1.add(btnCreateContract);
        
        ImageIcon iconEndContract = ImagesUtils.createResizedIcon("/images/endContractButtonIcon.png", 45, 45);
        btnEndContract = new JButton("End Contract", iconEndContract);
        btnEndContract.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnVacantApartmentActionPerformed(e);
        	}
        });
        btnEndContract.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnEndContract.setMargin(new Insets(10, 0, 10, 0));
        btnEndContract.setHorizontalTextPosition(SwingConstants.CENTER);
        btnEndContract.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnEndContract.setFocusPainted(false);
        btnEndContract.setBorder(null);
        btnEndContract.setBackground(Color.WHITE);
        btnEndContract.setBounds(174, 22, 130, 80);
        panelFind_1.add(btnEndContract);
        ButtonUtils.setupButtonForJIFrame(btnEndContract);
        
        ImageIcon iconPrint = ImagesUtils.createResizedIcon("/images/PrintButtonIcon.png", 45, 45);
        btnPrintContract = new JButton("Print Contract", iconPrint);
        btnPrintContract.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnPrintContractActionPerformed(e);
        	}
        });
        btnPrintContract.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnPrintContract.setMargin(new Insets(10, 0, 10, 0));
        btnPrintContract.setHorizontalTextPosition(SwingConstants.CENTER);
        btnPrintContract.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnPrintContract.setFocusPainted(false);
        btnPrintContract.setBorder(null);
        btnPrintContract.setBackground(Color.WHITE);
        btnPrintContract.setBounds(478, 22, 130, 80);
        ButtonUtils.setupButtonForJIFrame(btnPrintContract);
        panelFind_1.add(btnPrintContract);
        
        
        GroupLayout gl_panelToolBar = new GroupLayout(panelToolBar);
        gl_panelToolBar.setHorizontalGroup(
        	gl_panelToolBar.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panelToolBar.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(panelFind_1, GroupLayout.PREFERRED_SIZE, 631, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
        			.addComponent(panelFind, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        gl_panelToolBar.setVerticalGroup(
        	gl_panelToolBar.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelToolBar.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panelToolBar.createParallelGroup(Alignment.LEADING)
        				.addComponent(panelFind_1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
        				.addComponent(panelFind, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
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

    public static ContractManageForStaff getInstance() {
        if (instance == null) {
            instance = new ContractManageForStaff();
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
	protected void btnVacantApartmentActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a Contract");
	    } else {
	    	ContractDAO contractDAO = new ContractDAO();
	    	Boolean isSuccess = contractDAO.updateContractStatusToFalse(Integer.parseInt(table.getValueAt(selectedRow, 0).toString()));
	    	ShowMessage.showMessage("Info", isSuccess ? "Make Contract Expired Success" : "Failed to make Contract Expired");
	    	
	    	ApartmentDAO apartmentDAO = new ApartmentDAO();
	    	apartmentDAO.updateApartmentStatusToVacant(table.getValueAt(selectedRow, 4).toString());
	    	ApartmentManageForStaff.getInstance().refreshApartments();
	    	
	    	refreshContracts();
	    	
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
	protected void btnCreateContractActionPerformed(ActionEvent e) {
		
	    	AddContractForStaff2.getInstance().fillData();
	    	AddContractForStaff2.getInstance().clearTextField();
			if(!AddContractForStaff2.getInstance().isVisible()) {
				AddContractForStaff2.getInstance().setVisible(true);
			}	    	
	    
	}
	protected void btnExpiringSoonActionPerformed(ActionEvent e) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    Date currentDate = new Date();

	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(currentDate);
	    calendar.add(Calendar.MONTH, 1);
	    Date oneMonthLater = calendar.getTime();

	    DefaultTableModel model = (DefaultTableModel) table.getModel();

	    RowFilter<Object, Object> rowFilter = new RowFilter<Object, Object>() {
	        public boolean include(Entry<? extends Object, ? extends Object> entry) {
	            try {
	                String dateString = (String) entry.getValue(3);
	                Date endDate = dateFormat.parse(dateString);

	                String status = (String) entry.getValue(7);

	                return oneMonthLater.after(endDate) && status.equalsIgnoreCase("validated");
	            } catch (ParseException e) {
	                e.printStackTrace();
	                return false;
	            }
	        }
	    };

	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	    sorter.setRowFilter(rowFilter);
	    table.setRowSorter(sorter);
	}
	protected void btnPrintContractActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a Contract");
	    } else {
	    	printInvoice(selectedRow);
		}	
	}
	
	private void printInvoice(int selectedRow) {
	    //SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    HouseholdDAO householdDAO = new HouseholdDAO();
	    Household household = householdDAO.getHouseholdById(table.getValueAt(selectedRow, 6).toString());
	    
	    ApartmentDAO apartmentDAO = new ApartmentDAO();
	    ApartmentTypeDAO apartmentTypeDAO = new ApartmentTypeDAO();
	    ApartmentType apartmentType = apartmentTypeDAO.getApartmentTypeById(
	    		apartmentDAO.getApartmentById(table.getValueAt(selectedRow, 4).toString()).getTypeId());
	    
	    StaffDAO staffDAO = new StaffDAO();
	    Staff staff = staffDAO.getStaffById(table.getValueAt(selectedRow, 5).toString());
	    try {
	        String reportPath = "/reports/Contract.jrxml";
	        InputStream inputStream = getClass().getResourceAsStream(reportPath);
	        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

	        JRTableModelDataSource dataSource = new JRTableModelDataSource((DefaultTableModel) table.getModel());

	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("printDate", table.getValueAt(selectedRow, 2).toString());
	        parameters.put("householdHeadName", household.getHouseholdHeadName().toString());
	        parameters.put("idNumber", household.getIdNumber().toString());
	        parameters.put("phoneNumber", household.getPhoneNumber().toString());
	        parameters.put("memberQuantity", household.getMemberQuantity() + "");
	        parameters.put("contractNumber", table.getValueAt(selectedRow, 1).toString());
	        parameters.put("startDate", table.getValueAt(selectedRow, 2).toString());
	        parameters.put("endDate", table.getValueAt(selectedRow, 3).toString());
	        parameters.put("price", apartmentType.getPrice() + "");
	        parameters.put("staffName", staff.getName().toString());
	        parameters.put("staffId", staff.getId().toString());


	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

	        JasperViewer viewer = new JasperViewer(jasperPrint, false);
	        viewer.setVisible(true);

	    } catch (JRException er) {
	        er.printStackTrace();
	        ShowMessage.showErrorMessage("Print Error", "An error occurred during printing.");
	    }
	}
}
