package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import dao.UserDAO;
import entity.User;
import utils.ShowMessage;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Login instance;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JLabel lblManagement;
	private JLabel lblImage;
	private JLabel lblNewLabel_2;
	private JTextField textFieldUsername;
	private JLabel lblNewLabel_3;
	private JButton btnLogin;
	private JButton btnNewButton;
	private JPasswordField passwordField;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            Login frame = new Login();
            frame.setVisible(true);
        });
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 466);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(131, 209, 51));
		panel.setBounds(0, 0, 292, 427);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Apartment Rental");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setPreferredSize(new Dimension(150, 0));
        
        lblNewLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 33, 292, 48);
		panel.add(lblNewLabel);
		
		lblManagement = new JLabel("Management");
		lblManagement.setPreferredSize(new Dimension(150, 0));
		lblManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagement.setForeground(Color.WHITE);
		lblManagement.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblManagement.setBounds(0, 76, 292, 40);
		panel.add(lblManagement);
		
		String imagePath = "/images/householdButtonIcon.png";
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
		lblImage = new JLabel(imageIcon);
		lblImage.setBounds(0, 127, 292, 300);
		panel.add(lblImage);
		
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(50, 70, 82));
		panel_1.setBounds(291, 0, 275, 427);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(25, 87, 87, 34);
		panel_1.add(lblNewLabel_2);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setText("admin1");
		textFieldUsername.setForeground(new Color(255, 255, 255));
		textFieldUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		textFieldUsername.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		textFieldUsername.setBackground(new Color(50, 70, 82));
		textFieldUsername.setBounds(25, 132, 200, 26);
		panel_1.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		textFieldUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLoginActionPerformed(null);
                }
            }
        });
		
		lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(25, 176, 87, 34);
		panel_1.add(lblNewLabel_3);
		
		btnLogin = new JButton("Log in");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoginActionPerformed(e);
			}
		});
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBorderPainted(false);
		btnLogin.setBackground(new Color(131, 209, 51));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLogin.setBounds(25, 276, 200, 34);
		panel_1.add(btnLogin);
		btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnLogin.setBackground(new Color(141, 219, 51));
            	btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnLogin.setBackground(new Color(131, 209, 51));
            	btnLogin.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
		
		btnNewButton = new JButton("Forgot Password ?");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(50, 70, 82));
		btnNewButton.setBounds(68, 322, 133, 23);
		btnNewButton.setFocusPainted(false);
		panel_1.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnNewButton.setText("<html><u>Forgot Password ?</u></html>");
            	btnNewButton.setForeground(new Color(131, 209, 51));
            	btnNewButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnNewButton.setText("Forgot Password ?");
            	btnNewButton.setForeground(new Color(255, 255, 255));
            	btnNewButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 15));
		passwordField.setForeground(new Color(255, 255, 255));
		passwordField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		passwordField.setBackground(new Color(50, 70, 82));
		passwordField.setBounds(25, 221, 200, 26);
		panel_1.add(passwordField);
		passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnLoginActionPerformed(null);
                }
            }
        });
		
		
	}
	
	public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }
	
	protected void btnLoginActionPerformed(ActionEvent e) {
		
		 String username = textFieldUsername.getText();
		 String password = new String(passwordField.getPassword());

		    if (username.isEmpty() || password.isEmpty()) {
		    	ShowMessage.showWarningMessage("Warning", "Please enter both username and password.");
		        return;
		    }

		    UserDAO userDAO = new UserDAO();
		    User user = userDAO.loginUser(username, password);
		    if (user != null) {
		        if(user.getRole().equals("Admin")) {
		        	MainFrame mainFrame = MainFrame.getInstance();
		        	mainFrame.setIdUser(user.getId());
		        	ProfileManager.getInstance().loadData();
	                mainFrame.loadData();
	                
		        	if (!MainFrame.getInstance().isVisible()) {
		        		MainFrame.getInstance().setVisible(true);
		        		this.setVisible(false);
		            }
		        } else {
		        	MainFrameForStaff mainFrameForStaff = MainFrameForStaff.getInstance();
		        	mainFrameForStaff.setIdUser(user.getId());
		        	ProfileManagerForStaff.getInstance().loadData();
		        	mainFrameForStaff.loadData();
	                
		        	if (!MainFrameForStaff.getInstance().isVisible()) {
		        		MainFrameForStaff.getInstance().setVisible(true);
		        		this.setVisible(false);
		            }
		        }
		    } else {
		    	ShowMessage.showWarningMessage("Warning", "Login failed. User not found or invalid password.");
		    }
	}
}
