package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ApartmentDAO;
import dao.ApartmentTypeDAO;
import dao.ContractDAO;
import dao.HouseholdDAO;
import entity.Apartment;
import entity.Contract;
import entity.Household;
import utils.ButtonUtils;
import utils.ImagesUtils;
import utils.ShowMessage;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class AddContractForStaff2 extends JDialog {

    private static final long serialVersionUID = 1L;
    private static AddContractForStaff2 instance;
    private JPanel contentPane;
    private JLabel lblId;
    private JButton btnAdd;
    private JTextField textFieldContractNumber;
    private JPanel panel;
    private JPanel panel_1;
    private JPanel panel_2;
    private JLabel lblId_1;
    private JLabel lblNewLabel;
    private JDateChooser dateChooserStartDate;
    private JPanel panel_3;
    private JLabel lblId_5;
    private JDateChooser dateChooserEndDate;
    private JPanel panel_4;
    private JPanel panel_11;
    private JLabel lblId_7;
    private JPanel panel_12;
    private JLabel lblApartmentType;
    private JTextField textFieldApartmentType;
    private JPanel panel_14;
    private JLabel lblTypeName_1;
    private JTextField textFieldApartmentPrice;
    private JPanel panel_9;
    private JPanel panel_10;
    private JLabel lblId_3;
    @SuppressWarnings("rawtypes")
	public JComboBox comboBoxHouseholdIdNumber;
    private JPanel panel_13;
    private JLabel lblHouseholdHeadName;
    private JTextField textFieldHouseholdHeadName;
    private JPanel panel_15;
    private JLabel lblOuseholdHeadId;
    private JTextField textFieldHouseholdHeadPhone;
    private JPanel panel_16;
    private JLabel lblId_4;
    @SuppressWarnings("rawtypes")
	private JComboBox comboBoxStatus;
    private JPanel panel_17;
    private JLabel lblTypeName_2;
    private JTextField textFieldAreaName;
    private JPanel panel_19;
    private JLabel lblPhoneNumber_1;
    private JTextField textFieldHhPhone;
    private JComboBox<String> comboBoxApartmentId;
    private JButton btnNewButton;
    private JLabel lblNewLabel_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddContractForStaff2 frame = new AddContractForStaff2();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressWarnings({ "rawtypes", "unchecked"})
	public AddContractForStaff2() {
        setResizable(false);
        setTitle("Create Contract");
        setBounds(100, 100, 747, 524);
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
        panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "General Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panel.setBounds(10, 72, 230, 356);
        contentPane.add(panel);
        panel.setLayout(null);
        
        panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(30, 23, 170, 60);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        lblId = new JLabel("Contract Number");
        lblId.setBounds(0, 0, 170, 30);
        panel_1.add(lblId);
        lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        textFieldContractNumber = new JTextField();
        textFieldContractNumber.setEditable(false);
        textFieldContractNumber.setBounds(0, 30, 170, 30);
        panel_1.add(textFieldContractNumber);
        textFieldContractNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldContractNumber.setColumns(10);
        
        panel_2 = new JPanel();
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setLayout(null);
        panel_2.setBounds(30, 106, 170, 60);
        panel.add(panel_2);
        
        lblId_1 = new JLabel("<html>Start Date <font color='red'>(*)</font></html>");
        lblId_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblId_1.setBounds(0, 0, 170, 30);
        panel_2.add(lblId_1);
        
        dateChooserStartDate = new JDateChooser();
        dateChooserStartDate.setBounds(0, 31, 170, 29);
        panel_2.add(dateChooserStartDate);
        dateChooserStartDate.setDateFormatString("yyyy-MM-dd");
        dateChooserStartDate.setDate(new Date());
        
        panel_3 = new JPanel();
        panel_3.setLayout(null);
        panel_3.setBackground(Color.WHITE);
        panel_3.setBounds(30, 189, 170, 60);
        panel.add(panel_3);
        
        lblId_5 = new JLabel("<html>End Date <font color='red'>(*)</font></html>");
        lblId_5.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblId_5.setBounds(0, 0, 170, 30);
        panel_3.add(lblId_5);
        
        dateChooserEndDate = new JDateChooser();
        dateChooserEndDate.setBounds(0, 31, 170, 29);
        panel_3.add(dateChooserEndDate);
        dateChooserEndDate.setDateFormatString("yyyy-MM-dd");
        
        panel_16 = new JPanel();
        panel_16.setLayout(null);
        panel_16.setBackground(Color.WHITE);
        panel_16.setBounds(30, 272, 170, 60);
        panel.add(panel_16);
        
        lblId_4 = new JLabel("Status");
        lblId_4.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblId_4.setBounds(0, 0, 170, 30);
        panel_16.add(lblId_4);
        
        comboBoxStatus = new JComboBox();
        comboBoxStatus.setEnabled(false);
        comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] {"Validated", "Expired"}));
        comboBoxStatus.setBounds(0, 30, 170, 30);
        panel_16.add(comboBoxStatus);
        
        lblNewLabel = new JLabel("CREATE CONTRACT FORM");
        lblNewLabel.setForeground(new Color(0, 64, 128));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 0, 710, 76);
        contentPane.add(lblNewLabel);
        
        btnAdd = new JButton("Create");
        btnAdd.setBounds(525, 439, 170, 35);
        contentPane.add(btnAdd);
        btnAdd.setForeground(new Color(255, 255, 255));
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAddActionPerformed(e);
        	}
        });
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setBorder(new EmptyBorder(5, 15, 5, 15));
        btnAdd.setBackground(new Color(131, 209, 51));
        ButtonUtils.setupNormalButton(btnAdd,new Color(131, 209, 51),new Color(159, 216, 96));
        
        panel_4 = new JPanel();
        panel_4.setLayout(null);
        panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Apartment Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panel_4.setBackground(Color.WHITE);
        panel_4.setBounds(250, 72, 230, 356);
        contentPane.add(panel_4);
        
        panel_11 = new JPanel();
        panel_11.setLayout(null);
        panel_11.setBackground(Color.WHITE);
        panel_11.setBounds(30, 23, 170, 60);
        panel_4.add(panel_11);
        
        lblId_7 = new JLabel("<html>Apartment ID <font color='red'>(*)</font></html>");
        lblId_7.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblId_7.setBounds(0, 0, 170, 30);
        panel_11.add(lblId_7);
        
        comboBoxApartmentId = new JComboBox();
        comboBoxApartmentId.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		comboBoxApartmentIdItemStateChanged(e);
        	}
        });
        comboBoxApartmentId.setModel(new DefaultComboBoxModel(new String[] {"Select Apartment ID"}));
        comboBoxApartmentId.setBounds(0, 30, 170, 30);
        panel_11.add(comboBoxApartmentId);
        
        panel_12 = new JPanel();
        panel_12.setLayout(null);
        panel_12.setBackground(Color.WHITE);
        panel_12.setBounds(30, 106, 170, 60);
        panel_4.add(panel_12);
        
        lblApartmentType = new JLabel("Apartment Type");
        lblApartmentType.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblApartmentType.setBounds(0, 0, 170, 30);
        panel_12.add(lblApartmentType);
        
        textFieldApartmentType = new JTextField();
        textFieldApartmentType.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldApartmentType.setEditable(false);
        textFieldApartmentType.setColumns(10);
        textFieldApartmentType.setBounds(0, 30, 170, 30);
        panel_12.add(textFieldApartmentType);
        
        panel_14 = new JPanel();
        panel_14.setLayout(null);
        panel_14.setBackground(Color.WHITE);
        panel_14.setBounds(30, 189, 170, 60);
        panel_4.add(panel_14);
        
        lblTypeName_1 = new JLabel("Apartment Price");
        lblTypeName_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTypeName_1.setBounds(0, 0, 170, 30);
        panel_14.add(lblTypeName_1);
        
        textFieldApartmentPrice = new JTextField();
        textFieldApartmentPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldApartmentPrice.setEditable(false);
        textFieldApartmentPrice.setColumns(10);
        textFieldApartmentPrice.setBounds(0, 30, 170, 30);
        panel_14.add(textFieldApartmentPrice);
        
        panel_17 = new JPanel();
        panel_17.setLayout(null);
        panel_17.setBackground(Color.WHITE);
        panel_17.setBounds(30, 272, 170, 60);
        panel_4.add(panel_17);
        
        lblTypeName_2 = new JLabel("Area Name");
        lblTypeName_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTypeName_2.setBounds(0, 0, 170, 30);
        panel_17.add(lblTypeName_2);
        
        textFieldAreaName = new JTextField();
        textFieldAreaName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldAreaName.setEditable(false);
        textFieldAreaName.setColumns(10);
        textFieldAreaName.setBounds(0, 30, 170, 30);
        panel_17.add(textFieldAreaName);
        
        panel_9 = new JPanel();
        panel_9.setLayout(null);
        panel_9.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Household's Head Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panel_9.setBackground(Color.WHITE);
        panel_9.setBounds(490, 72, 230, 356);
        contentPane.add(panel_9);
        
        panel_10 = new JPanel();
        panel_10.setLayout(null);
        panel_10.setBackground(Color.WHITE);
        panel_10.setBounds(30, 23, 170, 60);
        panel_9.add(panel_10);
        
        lblId_3 = new JLabel("<html>Head ID Number <font color='red'>(*)</font></html>");
        lblId_3.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblId_3.setBounds(0, 0, 133, 30);
        panel_10.add(lblId_3);
        
        comboBoxHouseholdIdNumber = new JComboBox();
        comboBoxHouseholdIdNumber.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		comboBoxHouseholdIdNumberItemStateChanged(e);
        	}
        });
        comboBoxHouseholdIdNumber.setBounds(0, 30, 170, 30);
        panel_10.add(comboBoxHouseholdIdNumber);
        
        ImageIcon iconAdd = ImagesUtils.createResizedIcon("/images/addButtonIcon3.png", 20, 20);
        btnNewButton = new JButton((String) null, iconAdd);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnNewButtonActionPerformed(e);
        	}
        });
        btnNewButton.setBounds(145, 0, 25, 25);
        panel_10.add(btnNewButton);
        ButtonUtils.setupNormalButton(btnNewButton,new Color(131, 209, 51),new Color(159, 216, 96));
        
        panel_13 = new JPanel();
        panel_13.setLayout(null);
        panel_13.setBackground(Color.WHITE);
        panel_13.setBounds(30, 106, 170, 60);
        panel_9.add(panel_13);
        
        lblHouseholdHeadName = new JLabel("Household Head Name");
        lblHouseholdHeadName.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblHouseholdHeadName.setBounds(0, 0, 170, 30);
        panel_13.add(lblHouseholdHeadName);
        
        textFieldHouseholdHeadName = new JTextField();
        textFieldHouseholdHeadName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldHouseholdHeadName.setEditable(false);
        textFieldHouseholdHeadName.setColumns(10);
        textFieldHouseholdHeadName.setBounds(0, 30, 170, 30);
        panel_13.add(textFieldHouseholdHeadName);
        
        panel_15 = new JPanel();
        panel_15.setLayout(null);
        panel_15.setBackground(Color.WHITE);
        panel_15.setBounds(30, 272, 170, 60);
        panel_9.add(panel_15);
        
        lblOuseholdHeadId = new JLabel("Quantity of Member");
        lblOuseholdHeadId.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblOuseholdHeadId.setBounds(0, 0, 170, 30);
        panel_15.add(lblOuseholdHeadId);
        
        textFieldHouseholdHeadPhone = new JTextField();
        textFieldHouseholdHeadPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldHouseholdHeadPhone.setEditable(false);
        textFieldHouseholdHeadPhone.setColumns(10);
        textFieldHouseholdHeadPhone.setBounds(0, 30, 170, 30);
        panel_15.add(textFieldHouseholdHeadPhone);
        
        panel_19 = new JPanel();
        panel_19.setLayout(null);
        panel_19.setBackground(Color.WHITE);
        panel_19.setBounds(30, 189, 170, 60);
        panel_9.add(panel_19);
        
        lblPhoneNumber_1 = new JLabel("Phone Number");
        lblPhoneNumber_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPhoneNumber_1.setBounds(0, 0, 170, 30);
        panel_19.add(lblPhoneNumber_1);
        
        textFieldHhPhone = new JTextField();
        textFieldHhPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldHhPhone.setEditable(false);
        textFieldHhPhone.setColumns(10);
        textFieldHhPhone.setBounds(0, 30, 170, 30);
        panel_19.add(textFieldHhPhone);
        
        lblNewLabel_1 = new JLabel("(*) is required Field");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 13));
        lblNewLabel_1.setBounds(36, 452, 124, 14);
        contentPane.add(lblNewLabel_1);
        
    }

    public static AddContractForStaff2 getInstance() {
        if (instance == null) {
            instance = new AddContractForStaff2();
        }
        return instance;
    }

	@SuppressWarnings("unchecked")
	public void fillData() {
		ApartmentDAO apartmentDAO = new ApartmentDAO();
		List<Apartment> apartments = apartmentDAO.getVacantApartments();
		comboBoxApartmentId.removeAllItems();
		comboBoxApartmentId.addItem(new String("Select Apartment ID"));
		for (Apartment apartment : apartments) {
			comboBoxApartmentId.addItem(apartment.getId());
		}
		
		HouseholdDAO householdDAO = new HouseholdDAO();
		List<String> householdIds = householdDAO.getAllHouseholdIds();
		comboBoxHouseholdIdNumber.removeAllItems();
		comboBoxHouseholdIdNumber.addItem(new String("Select Household Id Number"));
		for (String householdId : householdIds) {
			comboBoxHouseholdIdNumber.addItem(householdId);
		}
    }
	
	
    
	protected void btnAddActionPerformed(ActionEvent e) {
		
		Contract contract = new Contract();
		contract.setContractNumber(textFieldContractNumber.getText());
		
		Date selectedStartDate = dateChooserStartDate.getDate();
		Date selectedEndDate = dateChooserEndDate.getDate();
		if (selectedStartDate == null || selectedEndDate == null || selectedStartDate.compareTo(selectedEndDate) >= 0) {
			ShowMessage.showWarningMessage("Warning", "Start Date must be before End Date. Please choose valid dates.");
			return;
		}
		contract.setStartDate(selectedStartDate);
		contract.setEndDate(selectedEndDate);
		
		if(	
			comboBoxHouseholdIdNumber.getSelectedItem().toString().equals("Select Household Id Number")
		){
			ShowMessage.showWarningMessage("Warning", "Please select Household Head ID Number");
			return;
		}
		
		if(	
				comboBoxApartmentId.getSelectedItem().toString().equals("Select Apartment ID")
			){
				ShowMessage.showWarningMessage("Warning", "Please select Apartment");
				return;
			}
		
		contract.setApartmentId(comboBoxApartmentId.getSelectedItem().toString());
		contract.setStaffId(MainFrameForStaff.getInstance().getIdUser());
		contract.setHouseholdIdNumber(comboBoxHouseholdIdNumber.getSelectedItem().toString());
		contract.setStatus(comboBoxStatus.getSelectedItem().toString().equals("Validated") ? true : false);
		
		ContractDAO contractDAO = new ContractDAO();
		Boolean isSuccess = contractDAO.addContract(contract);
		if(isSuccess) {
			ShowMessage.showMessage("Info", "Contract Added Successfully");
			
		    ApartmentDAO apartmentDAO = new ApartmentDAO();
		    apartmentDAO.updateApartmentStatusToOccupied(comboBoxApartmentId.getSelectedItem().toString(), comboBoxHouseholdIdNumber.getSelectedItem().toString());
		    
		    ApartmentManageForStaff.getInstance().refreshApartments();
		    
		    ContractManageForStaff.getInstance().refreshContracts();
		    
		    clearTextField();
		    this.setVisible(false);
		} else {
			ShowMessage.showErrorMessage("Error", "Failed to Add Contract");
			return;
		}
	}
	
	public void clearTextField() {
		
		comboBoxApartmentId.setSelectedIndex(0);
		comboBoxHouseholdIdNumber.setSelectedIndex(0);
		
		textFieldContractNumber.setText("");
		dateChooserStartDate.setDate(new Date());
		dateChooserEndDate.setDate(null);
	}
	protected void comboBoxHouseholdIdNumberItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			if(comboBoxHouseholdIdNumber.getSelectedItem().toString().equals("Select Household Id Number")) {
				textFieldHouseholdHeadName.setText("");
				textFieldHouseholdHeadPhone.setText("");
				textFieldHhPhone.setText("");
			} else {
				String selectedHouseholdIdNumber = comboBoxHouseholdIdNumber.getSelectedItem().toString();
				
				HouseholdDAO householdDAO = new HouseholdDAO();
				Household household = householdDAO.getHouseholdById(selectedHouseholdIdNumber);

				textFieldHouseholdHeadName.setText(household.getHouseholdHeadName());
				textFieldHouseholdHeadPhone.setText(household.getMemberQuantity() + "");
				textFieldHhPhone.setText(household.getPhoneNumber());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss");
				textFieldContractNumber.setText("CON-" + LocalDateTime.now().format(formatter)+ "-" + household.getIdNumber());
			}
        }
	}
	protected void comboBoxApartmentIdItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			if(comboBoxApartmentId.getSelectedItem().toString().equals("Select Apartment ID")) {
				textFieldApartmentType.setText("");
			    textFieldApartmentPrice.setText("");
			    textFieldAreaName.setText("");
			} else {
				String selectedApartmentId = comboBoxApartmentId.getSelectedItem().toString();
				ApartmentDAO apartmentDAO = new ApartmentDAO();
			    int apartmentTypeId = apartmentDAO.getApartmentById(selectedApartmentId).getTypeId();
			    ApartmentTypeDAO apartmentTypeDAO = new ApartmentTypeDAO();
			    textFieldApartmentType.setText(apartmentTypeDAO.getApartmentTypeById(apartmentTypeId).getType());
			    textFieldApartmentPrice.setText(apartmentTypeDAO.getApartmentTypeById(apartmentTypeId).getPrice() + "");
			    textFieldAreaName.setText(apartmentDAO.getAreaNameById(selectedApartmentId));
			}
        }
	}
	protected void btnNewButtonActionPerformed(ActionEvent e) {
		if (!AddHousehold.getInstance().isVisible()) {
			AddHousehold.getInstance().setVisible(true);
	    }
	}
}
