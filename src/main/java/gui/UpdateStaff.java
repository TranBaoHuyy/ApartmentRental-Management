package gui;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import dao.DepartmentDAO;
import dao.StaffDAO;
import entity.Staff;
import utils.ButtonUtils;
import utils.ShowMessage;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;

public class UpdateStaff extends JDialog {

    private static final long serialVersionUID = 1L;
    private static UpdateStaff instance;
    private JPanel contentPane;
    private JPanel panel;
    private JLabel lblNewLabel;
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
    @SuppressWarnings("rawtypes")
	private JComboBox comboBoxDepartmentId;
    private JButton btnUpdate;
    private JButton btnCancel;
    private JPanel panel_1;
    private JLabel lblPicture;
    private JButton btnChoosePicture;
    private JDateChooser dateChooserDoB;
    
    private String fileName = null;
	private String dirName = null;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UpdateStaff frame = new UpdateStaff();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressWarnings("rawtypes")
	public UpdateStaff() {
        setResizable(false);
        setTitle("Update Staff");
        setBounds(100, 100, 859, 415);
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
        panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "General Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(205, 72, 632, 250);
        contentPane.add(panel);
        panel.setLayout(null);
        
        lblNewLabel = new JLabel("<html>Staff ID <font color='red'>(*)</font></html>");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(33, 40, 76, 16);
        panel.add(lblNewLabel);
        
        textFieldStaffId = new JTextField();
        textFieldStaffId.setEditable(false);
        textFieldStaffId.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldStaffId.setColumns(10);
        textFieldStaffId.setBounds(119, 34, 150, 29);
        panel.add(textFieldStaffId);
        
        lblPrice = new JLabel("<html>Full Name <font color='red'>(*)</font></html>");
        lblPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPrice.setBounds(33, 80, 90, 16);
        panel.add(lblPrice);
        
        textFieldName = new JTextField();
        textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldName.setColumns(10);
        textFieldName.setBounds(119, 74, 150, 29);
        panel.add(textFieldName);
        
        rdbtnMale = new JRadioButton("Male");
        rdbtnMale.setBackground(Color.WHITE);
        rdbtnMale.setBounds(119, 117, 65, 23);
        panel.add(rdbtnMale);
        
        rdbtnFemale = new JRadioButton("Female");
        rdbtnFemale.setBackground(Color.WHITE);
        rdbtnFemale.setBounds(186, 117, 83, 23);
        panel.add(rdbtnFemale);
        
        ButtonGroup genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(rdbtnMale);
        genderButtonGroup.add(rdbtnFemale);
        ///rdbtnMale.setSelected(true);
        
        lblGender = new JLabel("Gender");
        lblGender.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblGender.setBounds(33, 121, 76, 16);
        panel.add(lblGender);
        
        lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblAddress.setBounds(33, 161, 76, 16);
        panel.add(lblAddress);
        
        textFieldAddress = new JTextField();
        textFieldAddress.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldAddress.setColumns(10);
        textFieldAddress.setBounds(119, 155, 150, 29);
        panel.add(textFieldAddress);
        
        lblPhone = new JLabel("Phone");
        lblPhone.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPhone.setBounds(33, 201, 76, 16);
        panel.add(lblPhone);
        
        textFieldPhone = new JTextField();
        textFieldPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldPhone.setColumns(10);
        textFieldPhone.setBounds(119, 195, 150, 29);
        panel.add(textFieldPhone);
        
        lblEmail = new JLabel("<html>Email <font color='red'>(*)</font></html>");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblEmail.setBounds(326, 40, 125, 16);
        panel.add(lblEmail);
        
        textFieldEmail = new JTextField();
        textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(451, 34, 150, 29);
        panel.add(textFieldEmail);
        
        lblIdNumber = new JLabel("<html>ID Number <font color='red'>(*)</font></html>");
        lblIdNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblIdNumber.setBounds(326, 80, 125, 16);
        panel.add(lblIdNumber);
        
        textFieldIdNumber = new JTextField();
        textFieldIdNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldIdNumber.setColumns(10);
        textFieldIdNumber.setBounds(451, 74, 150, 29);
        panel.add(textFieldIdNumber);
        
        lblDepartmentId = new JLabel("Date of Birth");
        lblDepartmentId.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDepartmentId.setBounds(326, 121, 125, 16);
        panel.add(lblDepartmentId);
        
        lblDepartmentName = new JLabel("Department Name");
        lblDepartmentName.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDepartmentName.setBounds(326, 201, 125, 16);
        panel.add(lblDepartmentName);
        
        textFieldDepartmentName = new JTextField();
        textFieldDepartmentName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldDepartmentName.setEditable(false);
        textFieldDepartmentName.setColumns(10);
        textFieldDepartmentName.setBounds(451, 195, 150, 29);
        panel.add(textFieldDepartmentName);
        
        lblDepartmentId_1 = new JLabel("<html>Department ID <font color='red'>(*)</font></html>");
        lblDepartmentId_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDepartmentId_1.setBounds(326, 161, 125, 16);
        panel.add(lblDepartmentId_1);
        
        comboBoxDepartmentId = new JComboBox();
        comboBoxDepartmentId.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		comboBoxDepartmentIdItemStateChanged(e);
        	}
        });
        comboBoxDepartmentId.setBounds(451, 157, 150, 27);
        panel.add(comboBoxDepartmentId);
        
        dateChooserDoB = new JDateChooser();
        dateChooserDoB.setBounds(451, 117, 150, 26);
        panel.add(dateChooserDoB);
        dateChooserDoB.setDateFormatString("yyyy-MM-dd");
        
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
        btnUpdate.setBounds(607, 333, 89, 27);
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
        btnCancel.setBounds(726, 333, 88, 27);
        contentPane.add(btnCancel);
        ButtonUtils.setupNormalButton(btnCancel, new Color(179, 41, 20),new Color(219, 50, 24));
        
        panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Picture", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(10, 72, 185, 250);
        contentPane.add(panel_1);
        panel_1.setLayout(null);
        
        lblPicture = new JLabel("");
        lblPicture.setBounds(10, 21, 165, 184);
        panel_1.add(lblPicture);
        
        btnChoosePicture = new JButton("Choose Picture");
        btnChoosePicture.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnChoosePictureActionPerformed(e);
        	}
        });
        btnChoosePicture.setForeground(Color.WHITE);
        btnChoosePicture.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnChoosePicture.setFocusPainted(false);
        btnChoosePicture.setBorderPainted(false);
        btnChoosePicture.setBorder(new EmptyBorder(5, 15, 5, 15));
        btnChoosePicture.setBackground(new Color(0, 128, 0));
        btnChoosePicture.setBounds(10, 216, 165, 27);
        ButtonUtils.setupNormalButton(btnChoosePicture, new Color(0, 128, 0),new Color(20, 148, 20));
        panel_1.add(btnChoosePicture);
        
        lblNewLabel_1 = new JLabel("UPDATE STAFF FORM");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(new Color(0, 64, 128));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1.setBackground(Color.WHITE);
        lblNewLabel_1.setBounds(10, 11, 823, 71);
        contentPane.add(lblNewLabel_1);
        
        lblNewLabel_2 = new JLabel("(*) is required Field");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 13));
        lblNewLabel_2.setBounds(20, 341, 124, 14);
        contentPane.add(lblNewLabel_2);
        loadData();
    }

    public static UpdateStaff getInstance() {
        if (instance == null) {
            instance = new UpdateStaff();
        }
        return instance;
    }
    
    public void loadData() {
    	fillDepartmentIds();
    	
    	StaffDAO staffDAO = new StaffDAO();
    	Staff staff = staffDAO.getStaffById(StaffManage.getInstance().getSelectedStaffId());
    	
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
    
    @SuppressWarnings("unchecked")
	private void fillDepartmentIds() {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        List<Integer> departmentIds = departmentDAO.getAllDepartmentIds();
        comboBoxDepartmentId.removeAllItems();
        for (int departmentId : departmentIds) {
        	comboBoxDepartmentId.addItem(departmentId);
        }
    }
	
	
	protected void btnChoosePictureActionPerformed(ActionEvent e) {
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
		    clearTextField();
		    this.setVisible(false);
		    StaffManage.getInstance().refreshStaffs();
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
	
	public void clearTextField() {
		textFieldStaffId.setText("");
	    textFieldName.setText("");
	    rdbtnMale.setSelected(true);
	    textFieldAddress.setText("");
	    textFieldPhone.setText("");
	    dateChooserDoB.setDate(new Date());
	    textFieldEmail.setText("");
	    textFieldIdNumber.setText("");
	    comboBoxDepartmentId.setSelectedIndex(0);
	    textFieldDepartmentName.setText("");
	    lblPicture.setIcon(null);

	    fileName = null;
	    dirName = null;
	}
	
	protected void btnCancelActionPerformed(ActionEvent e) {
		removeFrame();
	}
	
	protected void removeFrame() {
		this.setVisible(false);
		instance = null;
	}
	
}
