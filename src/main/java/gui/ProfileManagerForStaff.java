package gui;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import com.toedter.calendar.JDateChooser;

import dao.DepartmentDAO;
import dao.StaffDAO;
import dao.UserDAO;
import entity.Staff;
import utils.ButtonUtils;
import utils.PasswordEncoder;
import utils.ShowMessage;

import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JPasswordField;


public class ProfileManagerForStaff extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private static ProfileManagerForStaff instance;
    private JPanel panel;
    private JPanel panel_1;
    private JLabel lblYourStaffId;
    private JTextField textFieldStaffId;
    private JLabel lblPrice;
    private JTextField textFieldName;
    private JRadioButton rdbtnMale;
    private JRadioButton rdbtnFemale;
    private JLabel lblGender;
    private JLabel lblAddress;
    private JTextField textFieldAddress;
    private JLabel lblPhone;
    private JTextField textFieldPhone;
    private JLabel lblEmail;
    private JTextField textFieldEmail;
    private JLabel lblIdNumber;
    private JTextField textFieldIdNumber;
    private JLabel lblDepartmentId;
    private JLabel lblDepartmentName;
    private JTextField textFieldDepartmentName;
    private JLabel lblDepartmentId_1;
    private JComboBox<Integer> comboBoxDepartmentId;
    private JDateChooser dateChooserDoB;
    private JButton btnUpdate;
    private JLabel lblPicture;
    private JButton btnChangeAvatar;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel;
    private String fileName = null;
	private String dirName = null;
	private JPanel panel_2;
	private JLabel lblUsername;
	private JTextField textFieldUsername;
	private JLabel lblOldPassword;
	private JLabel lblPhone_1;
	private JButton btnChangePassword;
	private JLabel lblPhone_2;
	private JPasswordField passwordFieldOld;
	private JPasswordField passwordFieldNew;
	private JPasswordField passwordFieldNewRepeat;
	private JLabel lblNewLabel_2;
    
	private ProfileManagerForStaff() {
        super("Profile Manage", false, false, false, false);
        getContentPane().setBackground(new Color(255, 255, 255));
        setBounds(100, 100, 950, 695);
        setBackground(new Color(238, 242, 243));
        getContentPane().setLayout(null);
        
        panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(10, 11, 914, 643);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        panel_1 = new JPanel();
        panel_1.setBounds(10, 57, 894, 360);
        panel_1.setLayout(null);
        panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Your Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panel_1.setBackground(Color.WHITE);
        panel.add(panel_1);
        
        lblYourStaffId = new JLabel("Staff ID");
        lblYourStaffId.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblYourStaffId.setBounds(250, 33, 76, 16);
        panel_1.add(lblYourStaffId);
        
        textFieldStaffId = new JTextField();
        textFieldStaffId.setText((String) null);
        textFieldStaffId.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldStaffId.setEditable(false);
        textFieldStaffId.setColumns(10);
        textFieldStaffId.setBounds(336, 27, 150, 29);
        panel_1.add(textFieldStaffId);
        
        lblPrice = new JLabel("<html>Full Name <font color='red'>(*)</font></html>");
        lblPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPrice.setBounds(250, 90, 90, 16);
        panel_1.add(lblPrice);
        
        textFieldName = new JTextField();
        textFieldName.setText((String) null);
        textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldName.setColumns(10);
        textFieldName.setBounds(336, 83, 150, 29);
        panel_1.add(textFieldName);
        
        rdbtnMale = new JRadioButton("Male");
        rdbtnMale.setBackground(Color.WHITE);
        rdbtnMale.setBounds(336, 142, 65, 23);
        panel_1.add(rdbtnMale);
        
        rdbtnFemale = new JRadioButton("Female");
        rdbtnFemale.setBackground(Color.WHITE);
        rdbtnFemale.setBounds(403, 142, 83, 23);
        panel_1.add(rdbtnFemale);
        
        lblGender = new JLabel("Gender");
        lblGender.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblGender.setBounds(250, 143, 76, 16);
        panel_1.add(lblGender);
        
        lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblAddress.setBounds(250, 197, 76, 16);
        panel_1.add(lblAddress);
        
        textFieldAddress = new JTextField();
        textFieldAddress.setText((String) null);
        textFieldAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldAddress.setColumns(10);
        textFieldAddress.setBounds(336, 190, 150, 29);
        panel_1.add(textFieldAddress);
        
        lblPhone = new JLabel("Phone");
        lblPhone.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPhone.setBounds(250, 253, 76, 16);
        panel_1.add(lblPhone);
        
        textFieldPhone = new JTextField();
        textFieldPhone.setText((String) null);
        textFieldPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldPhone.setColumns(10);
        textFieldPhone.setBounds(336, 246, 150, 29);
        panel_1.add(textFieldPhone);
        
        lblEmail = new JLabel("<html>Email <font color='red'>(*)</font></html>");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblEmail.setBounds(551, 33, 125, 16);
        panel_1.add(lblEmail);
        
        textFieldEmail = new JTextField();
        textFieldEmail.setText((String) null);
        textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(693, 27, 150, 29);
        panel_1.add(textFieldEmail);
        
        lblIdNumber = new JLabel("<html>ID Number <font color='red'>(*)</font></html>");
        lblIdNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblIdNumber.setBounds(551, 90, 125, 16);
        panel_1.add(lblIdNumber);
        
        textFieldIdNumber = new JTextField();
        textFieldIdNumber.setText((String) null);
        textFieldIdNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldIdNumber.setColumns(10);
        textFieldIdNumber.setBounds(693, 83, 150, 29);
        panel_1.add(textFieldIdNumber);
        
        lblDepartmentId = new JLabel("Date of Birth");
        lblDepartmentId.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDepartmentId.setBounds(551, 143, 125, 16);
        panel_1.add(lblDepartmentId);
        
        lblDepartmentName = new JLabel("Department Name");
        lblDepartmentName.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDepartmentName.setBounds(551, 253, 125, 16);
        panel_1.add(lblDepartmentName);
        
        textFieldDepartmentName = new JTextField();
        textFieldDepartmentName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldDepartmentName.setEditable(false);
        textFieldDepartmentName.setColumns(10);
        textFieldDepartmentName.setBounds(693, 246, 150, 29);
        panel_1.add(textFieldDepartmentName);
        
        lblDepartmentId_1 = new JLabel("<html>Department ID <font color='red'>(*)</font></html>");
        lblDepartmentId_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDepartmentId_1.setBounds(551, 197, 125, 16);
        panel_1.add(lblDepartmentId_1);
        
        comboBoxDepartmentId = new JComboBox<Integer>();
        comboBoxDepartmentId.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		comboBoxDepartmentIdItemStateChanged(e);
        	}
        });
        comboBoxDepartmentId.setBounds(693, 192, 150, 27);
        panel_1.add(comboBoxDepartmentId);
        
        dateChooserDoB = new JDateChooser();
        dateChooserDoB.setDateFormatString("yyyy-MM-dd");
        dateChooserDoB.setBounds(693, 139, 150, 26);
        panel_1.add(dateChooserDoB);
        
        lblPicture = new JLabel("");
        lblPicture.setBounds(26, 45, 165, 165);
        panel_1.add(lblPicture);
        
        btnUpdate = new JButton("Update Profile");
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnUpdateActionPerformed(e);
        	}
        });
        btnUpdate.setBounds(693, 302, 150, 27);
        panel_1.add(btnUpdate);
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnUpdate.setFocusPainted(false);
        btnUpdate.setBorderPainted(false);
        btnUpdate.setBorder(new EmptyBorder(5, 15, 5, 15));
        btnUpdate.setBackground(new Color(131, 209, 51));
        ButtonUtils.setupNormalButton(btnUpdate,new Color(131, 209, 51),new Color(159, 216, 96));
        
        lblNewLabel = new JLabel("(*) is required Field");
        lblNewLabel.setBounds(336, 309, 124, 14);
        panel_1.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 13));
        
        btnChangeAvatar = new JButton("Change Avatar");
        btnChangeAvatar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnChangeAvatarActionPerformed(e);
        	}
        });
        btnChangeAvatar.setBounds(26, 230, 165, 27);
        panel_1.add(btnChangeAvatar);
        btnChangeAvatar.setForeground(Color.WHITE);
        btnChangeAvatar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnChangeAvatar.setFocusPainted(false);
        btnChangeAvatar.setBorderPainted(false);
        btnChangeAvatar.setBorder(new EmptyBorder(5, 15, 5, 15));
        btnChangeAvatar.setBackground(new Color(0, 128, 0));
        ButtonUtils.setupNormalButton(btnChangeAvatar, new Color(0, 128, 0),new Color(20, 148, 20));
        
        lblNewLabel_1 = new JLabel("YOUR PROFILE MANAGER");
        lblNewLabel_1.setBounds(10, 11, 894, 44);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(new Color(0, 64, 128));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1.setBackground(Color.WHITE);
        panel.add(lblNewLabel_1);
        
        panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Change Password", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panel_2.setBackground(new Color(255, 255, 255));
        panel_2.setBounds(10, 430, 894, 200);
        panel.add(panel_2);
        
        lblUsername = new JLabel("Username");
        lblUsername.setBounds(80, 35, 76, 16);
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(210, 27, 150, 29);
        textFieldUsername.setText((String) null);
        textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldUsername.setEditable(false);
        textFieldUsername.setColumns(10);
        
        lblOldPassword = new JLabel("<html>Old Password <font color='red'>(*)</font></html>");
        lblOldPassword.setBounds(80, 91, 150, 16);
        lblOldPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        lblPhone_1 = new JLabel("<html>New Password <font color='red'>(*)</font></html>");
        lblPhone_1.setBounds(440, 35, 150, 16);
        lblPhone_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        btnChangePassword = new JButton("Change");
        btnChangePassword.setBounds(620, 147, 150, 27);
        btnChangePassword.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnChangePasswordActionPerformed(e);
        	}
        });
        btnChangePassword.setForeground(Color.WHITE);
        btnChangePassword.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnChangePassword.setFocusPainted(false);
        btnChangePassword.setBorderPainted(false);
        btnChangePassword.setBorder(new EmptyBorder(5, 15, 5, 15));
        btnChangePassword.setBackground(new Color(0, 128, 128));
        ButtonUtils.setupNormalButton(btnChangePassword,new Color(0, 128, 128),new Color(10, 138, 138));
        
        lblPhone_2 = new JLabel("<html>New Password Repeat <font color='red'>(*)</font></html>");
        lblPhone_2.setBounds(440, 91, 170, 16);
        lblPhone_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        passwordFieldOld = new JPasswordField();
        passwordFieldOld.setBounds(210, 83, 150, 29);
        
        passwordFieldNew = new JPasswordField();
        passwordFieldNew.setBounds(620, 27, 150, 29);
        
        passwordFieldNewRepeat = new JPasswordField();
        passwordFieldNewRepeat.setBounds(620, 83, 150, 29);
        
        lblNewLabel_2 = new JLabel("(*) is required Field");
        lblNewLabel_2.setBounds(210, 153, 124, 14);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 13));
        panel_2.setLayout(null);
        panel_2.add(lblUsername);
        panel_2.add(textFieldUsername);
        panel_2.add(lblOldPassword);
        panel_2.add(lblPhone_1);
        panel_2.add(btnChangePassword);
        panel_2.add(lblPhone_2);
        panel_2.add(passwordFieldOld);
        panel_2.add(passwordFieldNew);
        panel_2.add(passwordFieldNewRepeat);
        panel_2.add(lblNewLabel_2);
    }

    public static ProfileManagerForStaff getInstance() {
        if (instance == null) {
            instance = new ProfileManagerForStaff();
        }
        return instance;
    }
    
    public void loadData() {
    	fillDepartmentIds();
    	
    	StaffDAO staffDAO = new StaffDAO();
    	Staff staff = staffDAO.getStaffById(MainFrameForStaff.getInstance().getIdUser());
    	
    	UserDAO userDAO = new UserDAO();
    	
    	textFieldStaffId.setText(staff.getId());
    	textFieldName.setText(staff.getName());
    	if (staff.isGender()) {
    	    rdbtnMale.setSelected(true);
    	} else {
    	    rdbtnFemale.setSelected(true);
    	}
    	textFieldAddress.setText(staff.getAddress());
    	textFieldPhone.setText(staff.getPhoneNumber());
    	dateChooserDoB.setDate(staff.getDob());
    	textFieldEmail.setText(staff.getEmail());
    	textFieldIdNumber.setText(staff.getIdNumber());;
    	comboBoxDepartmentId.setSelectedItem(staff.getDepartmentId());
    	textFieldUsername.setText(userDAO.getUserNameById(staff.getId()));
    	
        if (staff.getImage() != null && !staff.getImage().isEmpty()) {
            String imagePath = staff.getImage();
            File imageFile = new File(imagePath);

            if (imageFile.exists()) {
                fileName = imageFile.getName();
                dirName = imageFile.getAbsolutePath();

                lblPicture.setIcon(new ImageIcon(new ImageIcon(imagePath)
                        .getImage().getScaledInstance(
                                lblPicture.getWidth(),
                                lblPicture.getHeight(),
                                Image.SCALE_SMOOTH
                        )
                ));
            } else {
                lblPicture.setIcon(null);
                fileName = null;
                dirName = null;
            }
        } else {
            lblPicture.setIcon(null);
            fileName = null;
            dirName = null;
        }
  	
    }
    
	private void fillDepartmentIds() {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        List<Integer> departmentIds = departmentDAO.getAllDepartmentIds();
        comboBoxDepartmentId.removeAllItems();
        for (int departmentId : departmentIds) {
        	comboBoxDepartmentId.addItem(departmentId);
        }
    }
	
	protected void btnUpdateActionPerformed(ActionEvent e) {
		if (textFieldName.getText().trim().isEmpty() 
				|| textFieldEmail.getText().trim().isEmpty() 
				|| textFieldIdNumber.getText().trim().isEmpty()) {
	        ShowMessage.showWarningMessage("Warning", "Please fill in all required fields.");
	        return;
	    }
		try {
			Staff updatedStaff = new Staff();
			
			updatedStaff.setId(textFieldStaffId.getText().trim());
			updatedStaff.setName(textFieldName.getText().trim());
			updatedStaff.setGender(rdbtnMale.isSelected());
			updatedStaff.setAddress(textFieldAddress.getText().trim());
			updatedStaff.setPhoneNumber(textFieldPhone.getText().trim());
			
			Date selectedDate = dateChooserDoB.getDate();
			if (selectedDate == null) {
				dateChooserDoB.setDate(new Date());
			    selectedDate = dateChooserDoB.getDate();
			}
			updatedStaff.setDob(selectedDate);
			updatedStaff.setEmail(textFieldEmail.getText().trim());
			updatedStaff.setIdNumber(textFieldIdNumber.getText().trim());
			updatedStaff.setDepartmentId(Integer.parseInt(comboBoxDepartmentId.getSelectedItem().toString()));
			
	        if (fileName != null && dirName != null) {
	            String newImagePath = "images" + File.separator + fileName;

	            File newImageFile = new File(newImagePath);

	            File imageDir = new File("images");
	            if (!imageDir.exists()) {
	                imageDir.mkdir();
	            }

	            Files.copy(Paths.get(dirName), newImageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            updatedStaff.setImage(newImagePath);
	        }

	        StaffDAO staffDAO = new StaffDAO();
		    ShowMessage.showMessage("Info", staffDAO.updateStaff(updatedStaff) ? "Staff Updated Successfully" : "Failed to Update Staff");
		    loadData();
		    StaffManage.getInstance().refreshStaffs();
		    MainFrameForStaff.getInstance().loadData();
		}
	    catch (Exception e2) {
			e2.printStackTrace();
		}
		
	}
	
	protected void comboBoxDepartmentIdItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			int selectedDepartmentId = Integer.parseInt(comboBoxDepartmentId.getSelectedItem().toString());
			DepartmentDAO departmentDAO = new DepartmentDAO();
		    String departmentName = departmentDAO.getDepartmentNameById(selectedDepartmentId);
		    textFieldDepartmentName.setText(departmentName);
        }
	}
	protected void btnChangeAvatarActionPerformed(ActionEvent e) {
		var chooser = new JFileChooser();
		chooser.setDialogTitle("Open Image");
		chooser.setFileFilter(
				new FileNameExtensionFilter("image", "png", "jpg", "gif")
				);
		chooser.setAcceptAllFileFilterUsed(false);
		int result = chooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			fileName = f.getName();
			dirName = f.getAbsolutePath();
			lblPicture.setIcon(
					new ImageIcon(
						new ImageIcon(f.getAbsolutePath())
								.getImage()
								.getScaledInstance(	
									lblPicture.getWidth(), 
									lblPicture.getHeight(), 
									Image.SCALE_SMOOTH
								)
					)
				);
		}
	}
	protected void btnChangePasswordActionPerformed(ActionEvent e) {
		String userId = textFieldStaffId.getText();
		String username = textFieldUsername.getText();
		String passwordOld = new String(passwordFieldOld.getPassword());
	    String passwordNew = new String(passwordFieldNew.getPassword());
	    String passwordNewRepeat = new String(passwordFieldNewRepeat.getPassword());
	    System.out.println(userId + "," + passwordOld);
	    
	    if (passwordOld.isEmpty() || passwordNew.isEmpty() || passwordNewRepeat.isEmpty()) {
	        ShowMessage.showErrorMessage("Error", "Please fill required fields.");
	        return;
	    }

	    if (!passwordNew.equals(passwordNewRepeat)) {
	        ShowMessage.showErrorMessage("Error", "Passwords do not match. Please enter matching passwords.");
	        return;
	    }
	    
	    String hashedNewPassword = PasswordEncoder.hashPassword(new String(passwordNew));
	    
	    UserDAO userDAO = new UserDAO();
    	boolean isSuccess = userDAO.changePassword(userId, username, hashedNewPassword, passwordOld);
    	if(!isSuccess) {
    		ShowMessage.showErrorMessage("Error", "Failed to change Password");
    		return;
    	}
    	ShowMessage.showMessage("Info","Password changed Successfully");
    	passwordFieldOld.setText("");
    	passwordFieldNew.setText("");
    	passwordFieldNewRepeat.setText("");
	}
}
