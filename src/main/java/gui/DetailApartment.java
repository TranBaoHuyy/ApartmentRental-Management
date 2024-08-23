package gui;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ApartmentDAO;
import dao.ApartmentTypeDAO;
import dao.HouseholdDAO;
import dao.ServiceDAO;
import dao.ServiceInvoiceDAO;
import entity.Apartment;
import entity.ApartmentType;
import entity.Household;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DetailApartment extends JDialog {

    private static final long serialVersionUID = 1L;
    private static DetailApartment instance;
    private JPanel contentPane;
    private JLabel lblId;
    private JTextField textFieldApartmentId;
    private JPanel panel;
    private JPanel panel_1;
    private JPanel panel_2;
    private JLabel lblId_1;
    private JPanel panel_3;
    private JLabel lblNotes;
    private JTextField textFieldNotes;
    private JPanel panel_4;
    private JLabel lblId_2;
    private JPanel panel_8;
    private JLabel lblTypeName;
    private JTextField textFieldType;
    private JLabel lblNewLabel;
    private JPanel panel_5;
    private JLabel lblPrice;
    private JTextField textFieldPrice;
    private JTextField textFieldAreaName;
    private JTextField textFieldStatus;
    private JPanel panel_6;
    private JPanel panel_7;
    private JLabel lblIdNumber;
    private JTextField textFieldHouseholdHeadId;
    private JPanel panel_9;
    private JLabel lblId_4;
    private JTextField textFieldPhone;
    private JPanel panel_10;
    private JLabel lblEmail;
    private JTextField textFieldEmail;
    private JPanel panel_11;
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel panel_12;
    private JLabel lblId_3;
    private JTextField textFieldHeadName;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DetailApartment frame = new DetailApartment();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

	public DetailApartment() {
        setResizable(false);
        setTitle("Apartment Detail");
        setBounds(100, 100, 1200, 450);
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
        
        panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Base Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panel.setBounds(10, 50, 394, 341);
        contentPane.add(panel);
        panel.setLayout(null);
        
        panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(18, 45, 170, 60);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        lblId = new JLabel("Apartment Id");
        lblId.setBounds(0, 0, 170, 30);
        panel_1.add(lblId);
        lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        textFieldApartmentId = new JTextField();
        textFieldApartmentId.setEditable(false);
        textFieldApartmentId.setBounds(0, 30, 170, 30);
        panel_1.add(textFieldApartmentId);
        textFieldApartmentId.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldApartmentId.setColumns(10);
        
        panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setLayout(null);
        panel_2.setBounds(18, 150, 170, 60);
        panel.add(panel_2);
        
        lblId_1 = new JLabel("Area Name");
        lblId_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblId_1.setBounds(0, 0, 170, 30);
        panel_2.add(lblId_1);
        
        textFieldAreaName = new JTextField();
        textFieldAreaName.setEditable(false);
        textFieldAreaName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldAreaName.setColumns(10);
        textFieldAreaName.setBounds(0, 30, 170, 30);
        panel_2.add(textFieldAreaName);
        
        panel_3 = new JPanel();
        panel_3.setBackground(new Color(255, 255, 255));
        panel_3.setLayout(null);
        panel_3.setBounds(18, 255, 170, 60);
        panel.add(panel_3);
        
        lblNotes = new JLabel("Notes");
        lblNotes.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNotes.setBounds(0, 0, 170, 30);
        panel_3.add(lblNotes);
        
        textFieldNotes = new JTextField();
        textFieldNotes.setEditable(false);
        textFieldNotes.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldNotes.setColumns(10);
        textFieldNotes.setBounds(0, 30, 170, 30);
        panel_3.add(textFieldNotes);
        
        panel_4 = new JPanel();
        panel_4.setBackground(new Color(255, 255, 255));
        panel_4.setLayout(null);
        panel_4.setBounds(206, 255, 170, 60);
        panel.add(panel_4);
        
        lblId_2 = new JLabel("Status");
        lblId_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblId_2.setBounds(0, 0, 170, 30);
        panel_4.add(lblId_2);
        
        textFieldStatus = new JTextField();
        textFieldStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldStatus.setEditable(false);
        textFieldStatus.setColumns(10);
        textFieldStatus.setBounds(0, 30, 170, 30);
        panel_4.add(textFieldStatus);
        
        panel_8 = new JPanel();
        panel_8.setLayout(null);
        panel_8.setBackground(Color.WHITE);
        panel_8.setBounds(206, 45, 170, 60);
        panel.add(panel_8);
        
        lblTypeName = new JLabel("Type");
        lblTypeName.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTypeName.setBounds(0, 0, 170, 30);
        panel_8.add(lblTypeName);
        
        textFieldType = new JTextField();
        textFieldType.setEditable(false);
        textFieldType.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldType.setColumns(10);
        textFieldType.setBounds(0, 30, 170, 30);
        panel_8.add(textFieldType);
        
        panel_5 = new JPanel();
        panel_5.setLayout(null);
        panel_5.setBackground(Color.WHITE);
        panel_5.setBounds(206, 150, 170, 60);
        panel.add(panel_5);
        
        lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPrice.setBounds(0, 0, 170, 30);
        panel_5.add(lblPrice);
        
        textFieldPrice = new JTextField();
        textFieldPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldPrice.setEditable(false);
        textFieldPrice.setColumns(10);
        textFieldPrice.setBounds(0, 30, 170, 30);
        panel_5.add(textFieldPrice);
        
        lblNewLabel = new JLabel("APARTMENT DETAIL");
        lblNewLabel.setForeground(new Color(0, 64, 128));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 0, 1164, 57);
        contentPane.add(lblNewLabel);
        
        panel_6 = new JPanel();
        panel_6.setLayout(null);
        panel_6.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Household Head Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panel_6.setBackground(Color.WHITE);
        panel_6.setBounds(414, 50, 207, 341);
        contentPane.add(panel_6);
        
        panel_7 = new JPanel();
        panel_7.setLayout(null);
        panel_7.setBackground(Color.WHITE);
        panel_7.setBounds(18, 20, 170, 60);
        panel_6.add(panel_7);
        
        lblIdNumber = new JLabel("ID Number");
        lblIdNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblIdNumber.setBounds(0, 0, 170, 30);
        panel_7.add(lblIdNumber);
        
        textFieldHouseholdHeadId = new JTextField();
        textFieldHouseholdHeadId.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldHouseholdHeadId.setEditable(false);
        textFieldHouseholdHeadId.setColumns(10);
        textFieldHouseholdHeadId.setBounds(0, 30, 170, 30);
        panel_7.add(textFieldHouseholdHeadId);
        
        panel_9 = new JPanel();
        panel_9.setLayout(null);
        panel_9.setBackground(Color.WHITE);
        panel_9.setBounds(18, 180, 170, 60);
        panel_6.add(panel_9);
        
        lblId_4 = new JLabel("Phone");
        lblId_4.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblId_4.setBounds(0, 0, 170, 30);
        panel_9.add(lblId_4);
        
        textFieldPhone = new JTextField();
        textFieldPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldPhone.setEditable(false);
        textFieldPhone.setColumns(10);
        textFieldPhone.setBounds(0, 30, 170, 30);
        panel_9.add(textFieldPhone);
        
        panel_10 = new JPanel();
        panel_10.setLayout(null);
        panel_10.setBackground(Color.WHITE);
        panel_10.setBounds(18, 260, 170, 60);
        panel_6.add(panel_10);
        
        lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblEmail.setBounds(0, 0, 170, 30);
        panel_10.add(lblEmail);
        
        textFieldEmail = new JTextField();
        textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldEmail.setEditable(false);
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(0, 30, 170, 30);
        panel_10.add(textFieldEmail);
        
        panel_12 = new JPanel();
        panel_12.setLayout(null);
        panel_12.setBackground(Color.WHITE);
        panel_12.setBounds(18, 100, 170, 60);
        panel_6.add(panel_12);
        
        lblId_3 = new JLabel("Household Head Name");
        lblId_3.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblId_3.setBounds(0, 0, 170, 30);
        panel_12.add(lblId_3);
        
        textFieldHeadName = new JTextField();
        textFieldHeadName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldHeadName.setEditable(false);
        textFieldHeadName.setColumns(10);
        textFieldHeadName.setBounds(0, 30, 170, 30);
        panel_12.add(textFieldHeadName);
        
        panel_11 = new JPanel();
        panel_11.setLayout(null);
        panel_11.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "List of Service Invoices", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panel_11.setBackground(Color.WHITE);
        panel_11.setBounds(631, 50, 543, 341);
        contentPane.add(panel_11);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 23, 523, 307);
        panel_11.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        
    }

    public static DetailApartment getInstance() {
        if (instance == null) {
            instance = new DetailApartment();
        }
        return instance;
    }
	
	public void loadData() {
    	ApartmentDAO apartmentDAO = new ApartmentDAO();
    	ApartmentTypeDAO apartmentTypeDAO = new ApartmentTypeDAO();
    	HouseholdDAO householdDAO = new HouseholdDAO();
    	
    	
    	Apartment apartment = apartmentDAO.getApartmentById(ApartmentManageForStaff.getInstance().getSelectedApartmentId());
    	Household household = householdDAO.getHouseholdById(apartment.getHouseholdId());
    	ApartmentType apartmentType = apartmentTypeDAO.getApartmentTypeById(apartment.getTypeId());
    	
    	textFieldApartmentId.setText(apartment.getId());
    	textFieldAreaName.setText(apartment.getAreaName());
    	textFieldNotes.setText(apartment.getNotes());
    	textFieldType.setText(apartmentType.getType());
    	textFieldPrice.setText(apartmentType.getPrice().toString());
    	textFieldStatus.setText(apartment.getStatus());
    	
		textFieldHouseholdHeadId.setText(apartment.getStatus().equals("Vacant") ? "N/A" : apartment.getHouseholdId());
		textFieldPhone.setText(apartment.getStatus().equals("Vacant") ? "N/A" : household.getPhoneNumber());
		textFieldEmail.setText(apartment.getStatus().equals("Vacant") ? "N/A" : household.getEmail());
		textFieldHeadName.setText(apartment.getStatus().equals("Vacant") ? "N/A" : household.getHouseholdHeadName());

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Inv ID");
		model.addColumn("Inv Name");
		model.addColumn("Print Date");
		model.addColumn("Payment Date");
		model.addColumn("Service Name");
		model.addColumn("Quantity");
		model.addColumn("Price");
		model.addColumn("Total");
		model.addColumn("Status");
		
		ServiceDAO serviceDAO = new ServiceDAO();
		ServiceInvoiceDAO serviceInvoiceDAO = new ServiceInvoiceDAO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		serviceInvoiceDAO.getServiceInvoicesByApartmentId(apartment.getId()).stream().forEach(serviceInvoice -> {
			String formattedPrintingDate = serviceInvoice.getPrintingDate() != null ? dateFormat.format(serviceInvoice.getPrintingDate()) : "N/A";
		    String formattedPaymentDate = serviceInvoice.getPaymentDate() != null ? dateFormat.format(serviceInvoice.getPaymentDate()) : "N/A";
		    model.addRow(new Object[] {
		            serviceInvoice.getId(),
		            serviceInvoice.getName(),
		            formattedPrintingDate,
		            formattedPaymentDate,
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
    
	
	public void clearTextField() {
		textFieldApartmentId.setText("");
		textFieldNotes.setText("");
	}
}
