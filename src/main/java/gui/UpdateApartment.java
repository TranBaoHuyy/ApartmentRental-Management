package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ApartmentDAO;
import dao.ApartmentTypeDAO;
import dao.HouseholdDAO;
import entity.Apartment;
import entity.ApartmentType;
import utils.ButtonUtils;
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
import javax.swing.DefaultComboBoxModel;

public class UpdateApartment extends JDialog {

    private static final long serialVersionUID = 1L;
    private static UpdateApartment instance;
    private JPanel contentPane;
    private JLabel lblId;
    private JButton btnCancel;
    private JButton btnAdd;
    private JTextField textFieldApartmentId;
    private JPanel panel;
    private JPanel panel_1;
    private JPanel panel_2;
    private JLabel lblId_1;
    @SuppressWarnings("rawtypes")
	private JComboBox comboBoxAreaName;
    private JPanel panel_3;
    private JLabel lblNotes;
    private JTextField textFieldNotes;
    private JPanel panel_4;
    private JLabel lblId_2;
    @SuppressWarnings("rawtypes")
	private JComboBox comboBoxStatus;
    private JPanel panel_5;
    private JLabel lblId_3;
    @SuppressWarnings("rawtypes")
	private JComboBox comboBoxHouseholdId;
    private JPanel panel_6;
    private JLabel lblHouseholdHeadName;
    private JTextField textFieldHouseholdHeadName;
    private JPanel panel_7;
    private JLabel lblId_4;
    @SuppressWarnings("rawtypes")
	private JComboBox comboBoxTypeId;
    private JPanel panel_8;
    private JLabel lblTypeName;
    private JTextField textFieldTypeName;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UpdateApartment frame = new UpdateApartment();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public UpdateApartment() {
        setResizable(false);
        setTitle("Add Apartment");
        setBounds(100, 100, 496, 498);
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
            	removeFrame();
            }
        });
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "General Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panel.setBounds(10, 50, 458, 358);
        contentPane.add(panel);
        panel.setLayout(null);
        
        panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(32, 23, 170, 60);
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
        panel_2.setBounds(32, 106, 170, 60);
        panel.add(panel_2);
        
        lblId_1 = new JLabel("<html>Area Name <font color='red'>(*)</font></html>");
        lblId_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblId_1.setBounds(0, 0, 170, 30);
        panel_2.add(lblId_1);
        
        comboBoxAreaName = new JComboBox();
        comboBoxAreaName.setModel(new DefaultComboBoxModel(new String[] {"Area A", "Area B", "Area C"}));
        comboBoxAreaName.setBounds(0, 30, 170, 30);
        panel_2.add(comboBoxAreaName);
        
        panel_3 = new JPanel();
        panel_3.setBackground(new Color(255, 255, 255));
        panel_3.setLayout(null);
        panel_3.setBounds(32, 189, 170, 60);
        panel.add(panel_3);
        
        lblNotes = new JLabel("Notes");
        lblNotes.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNotes.setBounds(0, 0, 170, 30);
        panel_3.add(lblNotes);
        
        textFieldNotes = new JTextField();
        textFieldNotes.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldNotes.setColumns(10);
        textFieldNotes.setBounds(0, 30, 170, 30);
        panel_3.add(textFieldNotes);
        
        panel_4 = new JPanel();
        panel_4.setBackground(new Color(255, 255, 255));
        panel_4.setLayout(null);
        panel_4.setBounds(32, 272, 170, 60);
        panel.add(panel_4);
        
        lblId_2 = new JLabel("Status");
        lblId_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblId_2.setBounds(0, 0, 170, 30);
        panel_4.add(lblId_2);
        
        comboBoxStatus = new JComboBox();
        comboBoxStatus.setEnabled(false);
        comboBoxStatus.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		comboBoxStatusItemStateChanged(e);
        	}
        });
        comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] {"Vacant", "Occupied"}));
        comboBoxStatus.setBounds(0, 30, 170, 30);
        panel_4.add(comboBoxStatus);
        
        panel_5 = new JPanel();
        panel_5.setLayout(null);
        panel_5.setBackground(Color.WHITE);
        panel_5.setBounds(259, 23, 170, 60);
        panel.add(panel_5);
        
        lblId_3 = new JLabel("Household Id");
        lblId_3.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblId_3.setBounds(0, 0, 170, 30);
        panel_5.add(lblId_3);
        
        comboBoxHouseholdId = new JComboBox();
        comboBoxHouseholdId.setEnabled(false);
        comboBoxHouseholdId.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		comboBoxHouseholdIdItemStateChanged(e);
        	}
        });
        comboBoxHouseholdId.setBounds(0, 30, 170, 30);
        panel_5.add(comboBoxHouseholdId);
        
        panel_6 = new JPanel();
        panel_6.setLayout(null);
        panel_6.setBackground(Color.WHITE);
        panel_6.setBounds(259, 106, 170, 60);
        panel.add(panel_6);
        
        lblHouseholdHeadName = new JLabel("Household Head Name");
        lblHouseholdHeadName.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblHouseholdHeadName.setBounds(0, 0, 170, 30);
        panel_6.add(lblHouseholdHeadName);
        
        textFieldHouseholdHeadName = new JTextField();
        textFieldHouseholdHeadName.setEditable(false);
        textFieldHouseholdHeadName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldHouseholdHeadName.setColumns(10);
        textFieldHouseholdHeadName.setBounds(0, 30, 170, 30);
        panel_6.add(textFieldHouseholdHeadName);
        
        panel_7 = new JPanel();
        panel_7.setLayout(null);
        panel_7.setBackground(Color.WHITE);
        panel_7.setBounds(259, 189, 170, 60);
        panel.add(panel_7);
        
        lblId_4 = new JLabel("<html>Type ID <font color='red'>(*)</font></html>");
        lblId_4.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblId_4.setBounds(0, 0, 170, 30);
        panel_7.add(lblId_4);
        
        comboBoxTypeId = new JComboBox();
        comboBoxTypeId.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		comboBoxTypeIdItemStateChanged(e);
        	}
        });
        comboBoxTypeId.setBounds(0, 30, 170, 30);
        panel_7.add(comboBoxTypeId);
        
        panel_8 = new JPanel();
        panel_8.setLayout(null);
        panel_8.setBackground(Color.WHITE);
        panel_8.setBounds(259, 272, 170, 60);
        panel.add(panel_8);
        
        lblTypeName = new JLabel("Type Name");
        lblTypeName.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTypeName.setBounds(0, 0, 170, 30);
        panel_8.add(lblTypeName);
        
        textFieldTypeName = new JTextField();
        textFieldTypeName.setEditable(false);
        textFieldTypeName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldTypeName.setColumns(10);
        textFieldTypeName.setBounds(0, 30, 170, 30);
        panel_8.add(textFieldTypeName);
        
        lblNewLabel = new JLabel("UPDATE APARTMENT");
        lblNewLabel.setForeground(new Color(0, 64, 128));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 0, 458, 57);
        contentPane.add(lblNewLabel);
        
        btnAdd = new JButton("Update");
        btnAdd.setBounds(261, 419, 89, 27);
        contentPane.add(btnAdd);
        btnAdd.setForeground(new Color(255, 255, 255));
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAddActionPerformed(e);
        	}
        });
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setBorder(new EmptyBorder(5, 15, 5, 15));
        btnAdd.setBackground(new Color(131, 209, 51));
        ButtonUtils.setupNormalButton(btnAdd,new Color(131, 209, 51),new Color(159, 216, 96));
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(380, 419, 88, 27);
        contentPane.add(btnCancel);
        btnCancel.setForeground(new Color(255, 255, 255));
        btnCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCancelActionPerformed(e);
        	}
        });
        btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCancel.setFocusPainted(false);
        btnCancel.setBorderPainted(false);
        btnCancel.setBorder(new EmptyBorder(5, 15, 5, 15));
        btnCancel.setBackground(new Color(179, 41, 20));
        ButtonUtils.setupNormalButton(btnCancel, new Color(179, 41, 20),new Color(219, 50, 24));
        
        lblNewLabel_1 = new JLabel("(*) is required Field");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 13));
        lblNewLabel_1.setBounds(20, 427, 124, 14);
        contentPane.add(lblNewLabel_1);
        
        fillDataComboBoxIds();
    }

    public static UpdateApartment getInstance() {
        if (instance == null) {
            instance = new UpdateApartment();
        }
        return instance;
    }
    
    @SuppressWarnings("unchecked")
	public void fillDataComboBoxIds() {
    	if(comboBoxStatus.getSelectedItem().equals("Occupied")) {
    		HouseholdDAO householdDAO = new HouseholdDAO();
            List<String> householdIds = householdDAO.getAllHouseholdIds();
            comboBoxHouseholdId.removeAllItems();
            for (String householdId : householdIds) {
            	comboBoxHouseholdId.addItem(householdId);
            }
            comboBoxHouseholdId.setEnabled(false);
    	} else {
    	       comboBoxHouseholdId.setEnabled(false);
    	}
        
        
        ApartmentTypeDAO apartmentTypeDAO = new ApartmentTypeDAO();
        List<Integer> apartmentTypes = apartmentTypeDAO.getAllApartmentTypeIds();
        comboBoxTypeId.removeAllItems();
        for (int apartmentType : apartmentTypes) {
        	comboBoxTypeId.addItem(apartmentType);
        }
    }
    
    
    
	protected void btnAddActionPerformed(ActionEvent e) {
		Apartment apartment = new Apartment();
		apartment.setId(textFieldApartmentId.getText().trim());
		apartment.setNotes(textFieldNotes.getText().trim());
		apartment.setAreaName(comboBoxAreaName.getSelectedItem().toString());
		if(comboBoxHouseholdId.getSelectedItem() != null) {
			apartment.setHouseholdId(comboBoxHouseholdId.getSelectedItem().toString());
		} else {
			apartment.setHouseholdId("");
		}
		
		apartment.setTypeId(Integer.parseInt(comboBoxTypeId.getSelectedItem().toString()));
		apartment.setStatus(comboBoxStatus.getSelectedItem().toString());
		
		ApartmentDAO apartmentDAO = new ApartmentDAO();
		ShowMessage.showMessage("Info", apartmentDAO.updateApartment(apartment) ? "Apartment Updated Successfully" : "Failed to Update Apartment");
	    ApartmentManage.getInstance().refreshApartments();
	    removeFrame();
		
	}
	protected void btnCancelActionPerformed(ActionEvent e) {
		removeFrame();
	}
	
	protected void removeFrame() {
		this.setVisible(false);
		instance = null;
	}
	
	public void loadData() {
    	ApartmentDAO apartmentDAO = new ApartmentDAO();
    	Apartment apartment = apartmentDAO.getApartmentById(ApartmentManage.getInstance().getSelectedApartmentId());
    	textFieldApartmentId.setText(apartment.getId());
    	comboBoxAreaName.setSelectedItem(apartment.getAreaName());
    	textFieldNotes.setText(apartment.getNotes());
    	comboBoxStatus.setSelectedItem(apartment.getStatus());
    	comboBoxHouseholdId.setSelectedItem(apartment.getHouseholdId());
    	comboBoxTypeId.setSelectedItem(apartment.getTypeId());
    }
    
	
	public void clearTextField() {
		textFieldApartmentId.setText("");
		textFieldNotes.setText("");
	}
	protected void comboBoxHouseholdIdItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			String selectedHouseholdId = comboBoxHouseholdId.getSelectedItem().toString();
			HouseholdDAO householdDAO = new HouseholdDAO();
		    String householdHeadName = householdDAO.getHouseholdHeadNameById(selectedHouseholdId);
		    textFieldHouseholdHeadName.setText(householdHeadName);
        }
	}
	protected void comboBoxTypeIdItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			int selectedTypeId = Integer.parseInt(comboBoxTypeId.getSelectedItem().toString());
			ApartmentTypeDAO apartmentTypeDAO = new ApartmentTypeDAO();
		    ApartmentType typeName = apartmentTypeDAO.getApartmentTypeById(selectedTypeId);
		    textFieldTypeName.setText(typeName.getType());
        }
	}
	protected void comboBoxStatusItemStateChanged(ItemEvent e) {
		if(comboBoxStatus.getSelectedItem().equals("Occupied")) {
			comboBoxHouseholdId.setEnabled(true);
			fillDataComboBoxIds();
		} else {
			comboBoxHouseholdId.setEnabled(false);
			comboBoxHouseholdId.removeAllItems();
			textFieldHouseholdHeadName.setText("");
		}
	}
}
