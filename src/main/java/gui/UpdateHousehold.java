package gui;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import dao.HouseholdDAO;
import entity.Household;
import utils.ButtonUtils;
import utils.ShowMessage;
import utils.TextFieldUtils;

import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

public class UpdateHousehold extends JDialog {

    private static final long serialVersionUID = 1L;
    private static UpdateHousehold instance;
    private JPanel contentPane;
    private JPanel panel;
    private JLabel lblNewLabel;
    private JTextField textFieldId;
    private JLabel lblPrice;
    private JTextField textFieldName;
    private JLabel lblGender;
    private JLabel lblPhone;
    private JTextField textFieldPhone;
    private JLabel lblEmail;
    private JTextField textFieldEmail;
    private JLabel lblIdNumber;
    private JTextField textFieldMemberQuantity;
    private JLabel lblDepartmentId;
    private JButton btnAdd;
    private JButton btnCancel;
    private JPanel panel_1;
    private JLabel lblPicture;
    private JButton btnChoosePicture;
    private JDateChooser dateChooserDoB;
    
    private String fileName = null;
	private String dirName = null;
	private JLabel lblNewLabel_1;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxGender;
	private JLabel lblNewLabel_2;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UpdateHousehold frame = new UpdateHousehold();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public UpdateHousehold() {
        setResizable(false);
        setTitle("Add Staff");
        setBounds(100, 100, 859, 404);
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
        panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "General Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(205, 63, 632, 250);
        contentPane.add(panel);
        panel.setLayout(null);
        
        lblNewLabel = new JLabel("ID Number");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(33, 37, 76, 16);
        panel.add(lblNewLabel);
        
        textFieldId = new JTextField();
        textFieldId.setEditable(false);
        textFieldId.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldId.setColumns(10);
        textFieldId.setBounds(148, 26, 150, 29);
        panel.add(textFieldId);
        
        lblPrice = new JLabel("<html>Head name <font color='red'>(*)</font></html>");
        lblPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPrice.setBounds(33, 90, 150, 16);
        panel.add(lblPrice);
        
        textFieldName = new JTextField();
        textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldName.setColumns(10);
        textFieldName.setBounds(148, 81, 150, 29);
        panel.add(textFieldName);
        
        lblGender = new JLabel("Gender");
        lblGender.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblGender.setBounds(33, 143, 76, 16);
        panel.add(lblGender);
        
        lblPhone = new JLabel("<html>Phone <font color='red'>(*)</font></html>");
        lblPhone.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblPhone.setBounds(33, 196, 76, 16);
        panel.add(lblPhone);
        
        textFieldPhone = new JTextField();
        textFieldPhone.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldPhone.setColumns(10);
        textFieldPhone.setBounds(148, 191, 150, 29);
        panel.add(textFieldPhone);
        
        lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblEmail.setBounds(326, 90, 125, 16);
        panel.add(lblEmail);
        
        textFieldEmail = new JTextField();
        textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldEmail.setColumns(10);
        textFieldEmail.setBounds(451, 81, 150, 29);
        panel.add(textFieldEmail);
        
        lblIdNumber = new JLabel("<html>Member Quantity <font color='red'>(*)</font></html>");
        lblIdNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblIdNumber.setBounds(326, 143, 142, 16);
        panel.add(lblIdNumber);
        
        textFieldMemberQuantity = new JTextField();
        textFieldMemberQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldMemberQuantity.setColumns(10);
        textFieldMemberQuantity.setBounds(469, 136, 132, 29);
        panel.add(textFieldMemberQuantity);
        
        lblDepartmentId = new JLabel("Date of Birth");
        lblDepartmentId.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDepartmentId.setBounds(326, 37, 125, 16);
        panel.add(lblDepartmentId);
        
        dateChooserDoB = new JDateChooser();
        dateChooserDoB.setBounds(451, 29, 150, 26);
        panel.add(dateChooserDoB);
        dateChooserDoB.setDateFormatString("yyyy-MM-dd");
        dateChooserDoB.setDate(new Date());
        
        comboBoxGender = new JComboBox();
        comboBoxGender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Other"}));
        comboBoxGender.setBounds(148, 136, 150, 29);
        panel.add(comboBoxGender);
        comboBoxGender.setSelectedIndex(2);
        
        btnAdd = new JButton("Update");
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAddActionPerformed(e);
        	}
        });
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAdd.setFocusPainted(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setBorder(new EmptyBorder(5, 15, 5, 15));
        btnAdd.setBackground(new Color(131, 209, 51));
        btnAdd.setBounds(630, 327, 89, 27);
        contentPane.add(btnAdd);
        ButtonUtils.setupNormalButton(btnAdd,new Color(131, 209, 51),new Color(159, 216, 96));
        
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
        btnCancel.setBounds(749, 327, 88, 27);
        contentPane.add(btnCancel);
        ButtonUtils.setupNormalButton(btnCancel, new Color(179, 41, 20),new Color(219, 50, 24));
        
        panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Picture", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(10, 63, 185, 250);
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
        
        lblNewLabel_1 = new JLabel("UPDATE HOUSEHOLD");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(new Color(0, 64, 128));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1.setBackground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(10, 0, 827, 71);
        contentPane.add(lblNewLabel_1);
        
        lblNewLabel_2 = new JLabel("(*) is required Field");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 13));
        lblNewLabel_2.setBounds(20, 334, 124, 14);
        contentPane.add(lblNewLabel_2);
    }

    public static UpdateHousehold getInstance() {
        if (instance == null) {
            instance = new UpdateHousehold();
        }
        return instance;
    }
    
    public void loadData() {
    	HouseholdDAO householdDAO = new HouseholdDAO();
    	Household household = householdDAO.getHouseholdById(HouseholdManage.getInstance().getSelectedHouseholdId());
    	
    	textFieldId.setText(household.getIdNumber());
    	textFieldName.setText(household.getHouseholdHeadName());
    	comboBoxGender.setSelectedItem(household.getGender());
    	textFieldPhone.setText(household.getPhoneNumber());
    	dateChooserDoB.setDate(household.getDateOfBirth());
    	textFieldEmail.setText(household.getEmail());
    	textFieldMemberQuantity.setText(household.getMemberQuantity() + "");;
    	
        if (household.getImage() != null && !household.getImage().isEmpty()) {
            String imagePath = household.getImage();
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
	
	protected void btnAddActionPerformed(ActionEvent e) {
		if (textFieldName.getText().trim().isEmpty() 
				|| textFieldPhone.getText().trim().isEmpty() 
				|| textFieldMemberQuantity.getText().trim().isEmpty()) {
	        ShowMessage.showWarningMessage("Warning", "Please fill in all required fields.");
	        return;
	    }
		
		Household household = new Household();
		
		household.setIdNumber(textFieldId.getText().trim());
		household.setHouseholdHeadName(textFieldName.getText().trim());
		household.setGender(comboBoxGender.getSelectedItem().toString());
		household.setPhoneNumber(textFieldPhone.getText().trim());
		
		Date selectedDate = dateChooserDoB.getDate();
		if (selectedDate == null) {
			dateChooserDoB.setDate(new Date());
		    selectedDate = dateChooserDoB.getDate();
		}
		household.setDateOfBirth(selectedDate);
		
		household.setEmail(textFieldEmail.getText().trim());
		
	    if (fileName != null) {
	        File imageDir = new File("images");
	        if (!imageDir.exists()) {
	            imageDir.mkdir();
	        }

	        File destFile = new File("images/" + fileName);
	        try {
	            Files.copy(Paths.get(dirName), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            household.setImage("images/" + fileName);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	    
	    if (TextFieldUtils.isPositiveNumberInt(textFieldMemberQuantity.getText().trim())) {
	    	household.setMemberQuantity(Integer.parseInt(textFieldMemberQuantity.getText().trim()));
	    } else {
	        ShowMessage.showErrorMessage("Error", "Invalid quantity input. Please enter a integer positive number.");
	        return;
	    }
		
	    HouseholdDAO householdDAO = new HouseholdDAO();
	    ShowMessage.showMessage("Info", householdDAO.updateHousehold(household) ? "Household Updated Successfully" : "Failed to Update Household");
	    clearTextField();
	    this.setVisible(false);
	    HouseholdManage.getInstance().refreshHouseholds();
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
	
	public void clearTextField() {
		textFieldId.setText("");
	    textFieldName.setText("");
	    textFieldPhone.setText("");
	    dateChooserDoB.setDate(new Date());
	    textFieldEmail.setText("");
	    textFieldMemberQuantity.setText("");
	    lblPicture.setIcon(null);
	    comboBoxGender.setSelectedIndex(2);

	    fileName = null;
	    dirName = null;
	}
	
	protected void btnCancelActionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
