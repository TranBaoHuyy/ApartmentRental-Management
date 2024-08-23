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

import dao.StaffDAO;
import dao.UserDAO;
import entity.Staff;
import entity.User;
import utils.ButtonUtils;
import utils.PasswordEncoder;
import utils.ShowMessage;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class AddUser extends JDialog {

    private static final long serialVersionUID = 1L;
    private static AddUser instance;
    private JPanel contentPane;
    private JLabel lblNewLabel;
    private JLabel lblPrice;
    private JButton btnCancel;
    private JButton btnAdd;
    private JTextField textFieldUsername;
    private JLabel lblPasswordRepeat;
    @SuppressWarnings("rawtypes")
	private JComboBox comboBoxRole;
    private JLabel lblRole;
    private JPasswordField passwordField;
    private JPasswordField passwordRepeatField;
    private JLabel lblStaffId;
    @SuppressWarnings("rawtypes")
	private JComboBox comboBoxStaffId;
    private JLabel lblNewLabel_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddUser frame = new AddUser();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public AddUser() {
        setResizable(false);
        setTitle("Add User");
        setBounds(100, 100, 436, 326);
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
        
        lblNewLabel = new JLabel("<html>Username <font color='red'>(*)</font></html>");
        lblNewLabel.setBounds(24, 66, 89, 29);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        lblPrice = new JLabel("<html>Password <font color='red'>(*)</font></html>");
        lblPrice.setBounds(24, 106, 89, 30);
        lblPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        //ImageIcon iconCancel = ImagesUtils.createResizedIcon("/images/cancelButtonIcon.png", 20, 20);
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(302, 241, 88, 27);
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
        
        ///ImageIcon iconAdd = ImagesUtils.createResizedIcon("/images/add2ButtonIcon.png", 20, 20);
        btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAddActionPerformed(e);
        	}
        });
        btnAdd.setBounds(190, 241, 89, 27);
        btnAdd.setForeground(new Color(255, 255, 255));

        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setBorder(new EmptyBorder(5, 15, 5, 15));
        btnAdd.setBackground(new Color(131, 209, 51));
        ButtonUtils.setupNormalButton(btnAdd,new Color(131, 209, 51),new Color(159, 216, 96));
        
        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(190, 66, 200, 29);
        textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldUsername.setColumns(10);
        contentPane.setLayout(null);
        contentPane.add(lblNewLabel);
        contentPane.add(textFieldUsername);
        contentPane.add(lblPrice);
        contentPane.add(btnAdd);
        contentPane.add(btnCancel);
        
        lblPasswordRepeat = new JLabel("<html>Password repeat <font color='red'>(*)</font></html>");
        lblPasswordRepeat.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPasswordRepeat.setBounds(24, 147, 135, 29);
        contentPane.add(lblPasswordRepeat);
        
        comboBoxRole = new JComboBox();
        comboBoxRole.setModel(new DefaultComboBoxModel(new String[] {"Staff", "Admin"}));
        comboBoxRole.setBounds(190, 188, 200, 29);
        contentPane.add(comboBoxRole);
        
        lblRole = new JLabel("<html>Role <font color='red'>(*)</font></html>");
        lblRole.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblRole.setBounds(24, 187, 89, 30);
        contentPane.add(lblRole);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(190, 107, 200, 29);
        contentPane.add(passwordField);
        
        passwordRepeatField = new JPasswordField();
        passwordRepeatField.setBounds(190, 147, 200, 29);
        contentPane.add(passwordRepeatField);
        
        lblStaffId = new JLabel("<html>Staff ID <font color='red'>(*)</font></html>");
        lblStaffId.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblStaffId.setBounds(24, 23, 89, 29);
        contentPane.add(lblStaffId);
        
        comboBoxStaffId = new JComboBox();
        comboBoxStaffId.setBounds(190, 23, 200, 29);
        contentPane.add(comboBoxStaffId);
        
        lblNewLabel_1 = new JLabel("(*) is required Field");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 13));
        lblNewLabel_1.setBounds(24, 248, 124, 14);
        contentPane.add(lblNewLabel_1);
        
        loadStaffsWithoutUser();
    }

    public static AddUser getInstance() {
        if (instance == null) {
            instance = new AddUser();
        }
        return instance;
    }
    
    @SuppressWarnings("unchecked")
	public void loadStaffsWithoutUser() {
        StaffDAO staffDAO = new StaffDAO();
        List<Staff> staffsWithoutUser = staffDAO.getStaffsWithoutUser();

        comboBoxStaffId.removeAllItems();

        for (Staff staff : staffsWithoutUser) {
            comboBoxStaffId.addItem(staff.getId());
        }
    }
    
	protected void btnCancelActionPerformed(ActionEvent e) {
		removeFrame();
	}
	protected void btnAddActionPerformed(ActionEvent e) {
		 if (comboBoxStaffId.getSelectedItem() == null) {
		        ShowMessage.showErrorMessage("Error", "Please select a staff ID.");
		        return;
		    }
		 
		String username = textFieldUsername.getText();
	    char[] password = passwordField.getPassword();
	    char[] passwordRepeat = passwordRepeatField.getPassword();
	    String role = comboBoxRole.getSelectedItem().toString();
	    String staffId = comboBoxStaffId.getSelectedItem().toString();

	    if (username.trim().isEmpty() || password.length == 0 || passwordRepeat.length == 0 || staffId.trim().isEmpty()) {
	        ShowMessage.showErrorMessage("Error", "Please fill required fields.");
	        return;
	    }

	    if (!new String(password).equals(new String(passwordRepeat))) {
	        ShowMessage.showErrorMessage("Error", "Passwords do not match. Please enter matching passwords.");
	        return;
	    }

	    String hashedPassword = PasswordEncoder.hashPassword(new String(password));

	    User user = new User();
	    user.setUsername(username);
	    user.setPassword(hashedPassword);
	    user.setRole(role);
	    user.setId(staffId);

	    UserDAO userDAO = new UserDAO();
    	boolean isSuccess = userDAO.addUser(user);
    	if(!isSuccess) {
    		ShowMessage.showErrorMessage("Error", "Failed to Add User");
    		return;
    	}
    	ShowMessage.showMessage("Info","User Added Successfully");	    
	    UserManage.getInstance().refreshUsers();
	    removeFrame();
	}	
	
	protected void removeFrame() {
		this.setVisible(false);
		instance = null;
	}
}
