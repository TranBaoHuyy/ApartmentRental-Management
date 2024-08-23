package gui;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import dao.ApartmentDAO;
import dao.HouseholdDAO;
import dao.ServiceDAO;
import dao.ServiceInvoiceDAO;
import dao.StaffDAO;
import entity.Apartment;
import entity.Service;
import entity.ServiceInvoice;
import utils.ButtonUtils;
import utils.ShowMessage;
import utils.TextFieldUtils;

import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.LineBorder;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateServiceInvoice extends JDialog {

    private static final long serialVersionUID = 1L;
    private static UpdateServiceInvoice instance;
    private JPanel contentPane;
    private JPanel panelGeneralInfo;
    private JLabel lblInvoiceName;
    private JTextField textFieldInvoiceName;
    private JLabel lblStaffId;
    private JLabel lblStaffName;
    private JTextField textStaffName;
    private JLabel lblPrintingDate;
    private JLabel lblInvoiceName_1;
    private JTextField textFieldHouseholdHead;
    private JLabel lblApartmentId;
    private JLabel lblApartmentName;
    private JTextField textFieldApartmentName;
    private JDateChooser dateChooserPrintingDate;
    @SuppressWarnings("rawtypes")
	private JComboBox comboBoxStaffId;
    @SuppressWarnings("rawtypes")
	private JComboBox comboBoxApartmentId;
    private JButton btnUpdate;
    private JButton btnCancel;
    private JPanel panelInvoiceDetail;
    private JLabel lblServiceid;
    @SuppressWarnings("rawtypes")
	private JComboBox comboBoxServiceId;
    private JLabel lblQuantity;
    private JTextField textServiceName;
    private JLabel lblNNote;
    private JLabel lblPrice;
    private JTextField textPrice;
    private JTextField textQuantity;
    private JLabel lblNotes;
    private JTextField textFieldNotes;
    private JLabel lblNotes_1;
    private JTextField textNotes;
    private JTextField textFieldTotal;
    private JLabel lblTotal;
    private ServiceInvoice serviceInvoice;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UpdateServiceInvoice frame = new UpdateServiceInvoice();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

	@SuppressWarnings("rawtypes")
	public UpdateServiceInvoice() {
        setResizable(false);
        setTitle("Add Service Invoice");
        setBounds(100, 100, 780, 504);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setPreferredSize(new Dimension(420, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setLocationRelativeTo(null);
        setModal(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        panelGeneralInfo = new JPanel();
        panelGeneralInfo.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "General Infomation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panelGeneralInfo.setBackground(new Color(255, 255, 255));
        panelGeneralInfo.setBounds(23, 21, 718, 203);
        contentPane.add(panelGeneralInfo);
        panelGeneralInfo.setLayout(null);
        
        lblInvoiceName = new JLabel("Invoice Name");
        lblInvoiceName.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblInvoiceName.setBounds(29, 42, 86, 16);
        panelGeneralInfo.add(lblInvoiceName);
        
        textFieldInvoiceName = new JTextField();
        textFieldInvoiceName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldInvoiceName.setColumns(10);
        textFieldInvoiceName.setBounds(135, 36, 173, 29);
        panelGeneralInfo.add(textFieldInvoiceName);
        
        lblStaffId = new JLabel("Staff Id");
        lblStaffId.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblStaffId.setBounds(29, 82, 86, 16);
        panelGeneralInfo.add(lblStaffId);
        
        lblStaffName = new JLabel("Staff Name");
        lblStaffName.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblStaffName.setBounds(29, 122, 86, 16);
        panelGeneralInfo.add(lblStaffName);
        
        textStaffName = new JTextField();
        textStaffName.setEditable(false);
        textStaffName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textStaffName.setColumns(10);
        textStaffName.setBounds(135, 116, 173, 29);
        panelGeneralInfo.add(textStaffName);
        
        lblPrintingDate = new JLabel("Printing Date");
        lblPrintingDate.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPrintingDate.setBounds(29, 162, 86, 16);
        panelGeneralInfo.add(lblPrintingDate);
        
        lblInvoiceName_1 = new JLabel("Household Head");
        lblInvoiceName_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblInvoiceName_1.setBounds(391, 122, 112, 16);
        panelGeneralInfo.add(lblInvoiceName_1);
        
        textFieldHouseholdHead = new JTextField();
        textFieldHouseholdHead.setEditable(false);
        textFieldHouseholdHead.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldHouseholdHead.setColumns(10);
        textFieldHouseholdHead.setBounds(513, 116, 173, 29);
        panelGeneralInfo.add(textFieldHouseholdHead);
        
        lblApartmentId = new JLabel("Apartment ID");
        lblApartmentId.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblApartmentId.setBounds(391, 42, 112, 16);
        panelGeneralInfo.add(lblApartmentId);
        
        lblApartmentName = new JLabel("Area Name");
        lblApartmentName.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblApartmentName.setBounds(391, 82, 112, 16);
        panelGeneralInfo.add(lblApartmentName);
        
        textFieldApartmentName = new JTextField();
        textFieldApartmentName.setEditable(false);
        textFieldApartmentName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldApartmentName.setColumns(10);
        textFieldApartmentName.setBounds(513, 76, 173, 29);
        panelGeneralInfo.add(textFieldApartmentName);
        
        dateChooserPrintingDate = new JDateChooser();
        dateChooserPrintingDate.setBounds(135, 158, 173, 29);
        panelGeneralInfo.add(dateChooserPrintingDate);

        dateChooserPrintingDate.setDateFormatString("yyyy-MM-dd");
        dateChooserPrintingDate.setDate(new Date());
        
        comboBoxStaffId = new JComboBox();
        comboBoxStaffId.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		comboBoxStaffIdItemStateChanged(e);
        	}
        });
        comboBoxStaffId.setBounds(135, 76, 173, 29);
        panelGeneralInfo.add(comboBoxStaffId);
        
        comboBoxApartmentId = new JComboBox();
        comboBoxApartmentId.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		comboBoxApartmentIdItemStateChanged(e);
        	}
        });
        comboBoxApartmentId.setBounds(513, 36, 173, 29);
        panelGeneralInfo.add(comboBoxApartmentId);
        
        lblNotes = new JLabel("Notes");
        lblNotes.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNotes.setBounds(391, 162, 86, 16);
        panelGeneralInfo.add(lblNotes);
        
        textFieldNotes = new JTextField();
        textFieldNotes.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldNotes.setColumns(10);
        textFieldNotes.setBounds(513, 158, 173, 29);
        panelGeneralInfo.add(textFieldNotes);
        
        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnUpdateActionPerformed(e);
        	}
        });

        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnUpdate.setFocusPainted(false);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setBorder(new EmptyBorder(5, 15, 5, 15));
        btnUpdate.setBackground(new Color(131, 209, 51));
        btnUpdate.setBounds(285, 427, 89, 27);
        contentPane.add(btnUpdate);
        ButtonUtils.setupNormalButton(btnUpdate,new Color(131, 209, 51),new Color(159, 216, 96));
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCancelActionPerformed(e);
        	}
        });
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCancel.setFocusPainted(false);
        btnCancel.setBorderPainted(false);
        btnCancel.setBorder(new EmptyBorder(5, 15, 5, 15));
        btnCancel.setBackground(new Color(179, 41, 20));
        btnCancel.setBounds(404, 427, 88, 27);
        contentPane.add(btnCancel);
        ButtonUtils.setupNormalButton(btnCancel, new Color(179, 41, 20),new Color(219, 50, 24));
        
        panelInvoiceDetail = new JPanel();
        panelInvoiceDetail.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Service Invoice Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panelInvoiceDetail.setBackground(new Color(255, 255, 255));
        panelInvoiceDetail.setBounds(23, 244, 718, 172);
        contentPane.add(panelInvoiceDetail);
        panelInvoiceDetail.setLayout(null);
        
        lblServiceid = new JLabel("Service Id");
        lblServiceid.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblServiceid.setBounds(29, 45, 86, 16);
        panelInvoiceDetail.add(lblServiceid);
        
        comboBoxServiceId = new JComboBox();
        comboBoxServiceId.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		comboBoxServiceIdItemStateChanged(e);
        	}
        });
        comboBoxServiceId.setBounds(135, 39, 173, 29);
        panelInvoiceDetail.add(comboBoxServiceId);
        
        lblQuantity = new JLabel("Service Name");
        lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblQuantity.setBounds(29, 85, 96, 16);
        panelInvoiceDetail.add(lblQuantity);
        
        textServiceName = new JTextField();
        textServiceName.setEditable(false);
        textServiceName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textServiceName.setColumns(10);
        textServiceName.setBounds(135, 79, 173, 29);
        panelInvoiceDetail.add(textServiceName);
        
        lblNNote = new JLabel("Quantity");
        lblNNote.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNNote.setBounds(391, 45, 86, 16);
        panelInvoiceDetail.add(lblNNote);
        
        lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPrice.setBounds(391, 85, 40, 16);
        panelInvoiceDetail.add(lblPrice);
        
        textPrice = new JTextField();
        textPrice.setEditable(false);
        textPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textPrice.setColumns(10);
        textPrice.setBounds(513, 79, 173, 29);
        panelInvoiceDetail.add(textPrice);
        
        textQuantity = new JTextField();
        textQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textQuantity.setColumns(10);
        textQuantity.setBounds(513, 39, 173, 29);
        panelInvoiceDetail.add(textQuantity);
        textQuantity.getDocument().addDocumentListener(new DocumentListener() {
    	    @Override
    	    public void insertUpdate(DocumentEvent e) {
    	        updateTotal();
    	    }

    	    @Override
    	    public void removeUpdate(DocumentEvent e) {
    	        updateTotal();
    	    }

    	    @Override
    	    public void changedUpdate(DocumentEvent e) {
    	        updateTotal();
    	    }
    	});
        
        lblNotes_1 = new JLabel("Notes");
        lblNotes_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNotes_1.setBounds(29, 125, 86, 16);
        panelInvoiceDetail.add(lblNotes_1);
        
        textNotes = new JTextField();
        textNotes.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textNotes.setColumns(10);
        textNotes.setBounds(135, 119, 173, 29);
        panelInvoiceDetail.add(textNotes);
        
        textFieldTotal = new JTextField();
        textFieldTotal.setEditable(false);
        textFieldTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldTotal.setColumns(10);
        textFieldTotal.setBounds(513, 119, 173, 29);
        panelInvoiceDetail.add(textFieldTotal);
        
        lblTotal = new JLabel("Total");
        lblTotal.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTotal.setBounds(391, 125, 40, 16);
        panelInvoiceDetail.add(lblTotal);
    }

    public static UpdateServiceInvoice getInstance() {
        if (instance == null) {
            instance = new UpdateServiceInvoice();
        }
        return instance;
    }
    
    public void loadData() {
    	fillStaffIds();
    	fillApartmentIds();
    	fillServiceIds();
    	
    	ServiceInvoiceDAO dao = new ServiceInvoiceDAO();
    	serviceInvoice = dao.getServiceInvoiceById(ServiceInvoiceManage.getInstance().getSelectServiceInvoiceId());
    	
    	if (serviceInvoice != null) {
    	textFieldInvoiceName.setText(serviceInvoice.getName());
    	comboBoxStaffId.setSelectedItem(serviceInvoice.getStaffId());
    	dateChooserPrintingDate.setDate(serviceInvoice.getPrintingDate());
    	comboBoxApartmentId.setSelectedItem(serviceInvoice.getApartmentId());
    	textNotes.setText(serviceInvoice.getNotes());
    	comboBoxServiceId.setSelectedItem(serviceInvoice.getServiceId());
    	textQuantity.setText(serviceInvoice.getQuantity() + "");
    	}
    }
    
    @SuppressWarnings("unchecked")
	private void fillStaffIds() {
        StaffDAO staffDAO = new StaffDAO();
        List<String> staffIds = staffDAO.getAllStaffIds();
        comboBoxStaffId.removeAllItems();
        for (String staffId : staffIds) {
            comboBoxStaffId.addItem(staffId);
        }
    }
    
    protected void comboBoxStaffIdItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			String selectedStaffId = comboBoxStaffId.getSelectedItem().toString();
            displayStaffName(selectedStaffId);
        }
	}
	
	private void displayStaffName(String staffId) {
	    StaffDAO staffDAO = new StaffDAO();
	    String staffName = staffDAO.getStaffNameById(staffId);
	    textStaffName.setText(staffName);
	}
    
	@SuppressWarnings("unchecked")
	private void fillApartmentIds() {
    	ApartmentDAO apartmentDAO = new ApartmentDAO();
        List<String> apartmentIds = apartmentDAO.getAllApartmentIds();
        comboBoxApartmentId.removeAllItems();
        for (String apartmentId : apartmentIds) {
        	comboBoxApartmentId.addItem(apartmentId);
        }
    }
	
	protected void comboBoxApartmentIdItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
            String selectedApartmentId = comboBoxApartmentId.getSelectedItem().toString();
            displayApartmentInfo(selectedApartmentId);
        }
	}

	private void displayApartmentInfo(String apartmentId) {
		ApartmentDAO apartmentDAO = new ApartmentDAO();
	    String areaName = apartmentDAO.getAreaNameById(apartmentId);
	    textFieldApartmentName.setText(areaName);
	    HouseholdDAO householdDAO = new HouseholdDAO();
	    
	    Apartment apartment = apartmentDAO.getApartmentById(apartmentId);
	    String householdName = householdDAO.getHouseholdHeadNameById(apartment.getHouseholdId());
	    
	    textFieldHouseholdHead.setText(householdName);
	    textFieldNotes.setText(apartment.getNotes());
	}
	
	@SuppressWarnings("unchecked")
	private void fillServiceIds() {
		ServiceDAO serviceDAO = new ServiceDAO();
		List<Integer> serviceIds = serviceDAO.getAllServiceIds();
        comboBoxServiceId.removeAllItems();
        for (Integer serviceId : serviceIds) {
        	comboBoxServiceId.addItem(serviceId);
        }
    }
	
	protected void comboBoxServiceIdItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
            int selectedServiceId = Integer.parseInt(comboBoxServiceId.getSelectedItem().toString());
            displayServiceInfo(selectedServiceId);
        }
	}
	
	private void displayServiceInfo(int serviceId) {
		ServiceDAO serviceDAO = new ServiceDAO();
	    Service service = serviceDAO.getServiceById(serviceId);
	    textServiceName.setText(service.getName());
	    textPrice.setText(service.getPrice() + "");
	    
	}
	

	private void updateTotal() {
	    try {
	        int quantity = Integer.parseInt(textQuantity.getText());
	        double price = Double.parseDouble(textPrice.getText());
	        if(quantity <= 0) {
	        	textFieldTotal.setText("Invalid");
	        } else {
	        	double total = quantity * price;
		        textFieldTotal.setText(String.valueOf(total));
	        }
	    } catch (NumberFormatException ex) {
	        textFieldTotal.setText("Invalid");
	    }
	}
	
	protected void btnCancelActionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	

	protected void btnUpdateActionPerformed(ActionEvent e) {
		ServiceInvoice serviceInvoiceUpdate = new ServiceInvoice();
		
		if(textFieldInvoiceName.getText().trim().isEmpty()) {
			serviceInvoiceUpdate.setName(textServiceName.getText() + " Service Invoice");
		} else {
			serviceInvoiceUpdate.setName(textFieldInvoiceName.getText());
		}
		
		serviceInvoiceUpdate.setStaffId(comboBoxStaffId.getSelectedItem().toString());
		
		Date selectedDate = dateChooserPrintingDate.getDate();
		if (selectedDate == null) {
		    dateChooserPrintingDate.setDate(new Date());
		    selectedDate = dateChooserPrintingDate.getDate();
		}
		serviceInvoiceUpdate.setPrintingDate(selectedDate);
		
		String quantityText = textQuantity.getText();

	    if (TextFieldUtils.isPositiveNumberInt(quantityText)) {
	        int quantity = Integer.parseInt(quantityText);

	        serviceInvoiceUpdate.setId(serviceInvoice.getId());
	        serviceInvoiceUpdate.setQuantity(quantity);
	        serviceInvoiceUpdate.setApartmentId(comboBoxApartmentId.getSelectedItem().toString());
	        serviceInvoiceUpdate.setNotes(textNotes.getText());
	        serviceInvoiceUpdate.setServiceId(Integer.parseInt(comboBoxServiceId.getSelectedItem().toString()));
	        serviceInvoiceUpdate.setPrice(Double.parseDouble(textPrice.getText().toString()));
			serviceInvoiceUpdate.setStatus(false);
			ServiceInvoiceDAO dao = new ServiceInvoiceDAO();
	        ShowMessage.showMessage("Info", dao.updateServiceInvoice(serviceInvoiceUpdate) ? "Service Invoice Updated Successfully" : "Failed to Update Service Invoice");
	        ServiceInvoiceManage.getInstance().refresh();
	        this.setVisible(false);
	    } else {
	        ShowMessage.showErrorMessage("Error", "Invalid quantity input. Please enter a integer positive number.");
	    }
	}
}
