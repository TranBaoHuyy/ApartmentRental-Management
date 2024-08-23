package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ServiceDAO;
import entity.Service;
import utils.ButtonUtils;
import utils.ShowMessage;
import utils.TextFieldUtils;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

public class AddService extends JDialog {

    private static final long serialVersionUID = 1L;
    private static AddService instance;
    private JPanel contentPane;
    private JLabel lblNewLabel;
    private JTextField textFieldPrice;
    private JLabel lblPrice;
    private JButton btnCancel;
    private JButton btnAdd;
    private JTextField textFieldName;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddService frame = new AddService();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AddService() {
        setResizable(false);
        setTitle("Add Service");
        setBounds(100, 100, 420, 250);
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
        
        lblNewLabel = new JLabel("Name");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        textFieldPrice = new JTextField();
        textFieldPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldPrice.setColumns(10);
        
        lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        //ImageIcon iconCancel = ImagesUtils.createResizedIcon("/images/cancelButtonIcon.png", 20, 20);
        btnCancel = new JButton("Cancel");
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
        
        textFieldName = new JTextField();
        textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldName.setColumns(10);
        
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(lblNewLabel)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
        					.addGap(24)
        					.addComponent(textFieldPrice, 311, 311, 311)))
        			.addContainerGap(30, Short.MAX_VALUE))
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap(127, Short.MAX_VALUE)
        			.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
        			.addGap(30)
        			.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
        			.addGap(76))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(29)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblNewLabel)
        				.addComponent(textFieldName, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
        			.addGap(32)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
        				.addComponent(textFieldPrice, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
        			.addGap(26)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnCancel)
        				.addComponent(btnAdd))
        			.addGap(68))
        );
        contentPane.setLayout(gl_contentPane);
    }

    public static AddService getInstance() {
        if (instance == null) {
            instance = new AddService();
        }
        return instance;
    }
    
	protected void btnAddActionPerformed(ActionEvent e) {
		var service = new Service();
		service.setName(textFieldName.getText());
		
		String priceText = textFieldPrice.getText();

	    if (TextFieldUtils.isPositiveNumber(priceText)) {
	        float price = Float.parseFloat(priceText);

	        service.setPrice(price);

	        var serviceDAO = new ServiceDAO();
	        ShowMessage.showMessage("Info", serviceDAO.addService(service) ? "Service Added Successfully" : "Failed to Add Service");
	        clearTextField();
	        ServiceManage.getInstance().refresh();
	    } else {
	        ShowMessage.showErrorMessage("Error", "Invalid price input. Please enter a positive number.");
	    }
	}
	protected void btnCancelActionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	
	private void clearTextField() {
		textFieldName.setText("");
		textFieldPrice.setText("");
	}
}
