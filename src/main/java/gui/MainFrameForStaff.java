package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.StaffDAO;
import entity.Staff;
import utils.ImagesUtils;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JLabel;

public class MainFrameForStaff extends JFrame {

	private static final long serialVersionUID = 1L;
	private static MainFrameForStaff instance;
	private JPanel contentPane;
	private JPanel menuArea;
	private JDesktopPane desktopPane;
	
	private JButton btnServiceInvoices;
	private JButton btnApartments;
	private JButton btnContracts;
	
	private ServiceInvoiceManageForStaff jInFrameServiceInvoice;
	private ApartmentManageForStaff jInFrameApartmentForStaff;
	private ContractManageForStaff jInFrameContract;
	private ProfileManagerForStaff jInFrameProfile;
	
	private JButton currentButton;
	private JLabel lblImage;
	private JLabel lblUsername;
	
	private String idUser;
	private JButton btnLogout;
	private JButton btnProfile;
	
	public static void main(String[] args) {		
		SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            MainFrameForStaff frame = new MainFrameForStaff();
			frame.setVisible(true);
        });
	}	
	
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdUser() {
		return idUser;
	}

	public MainFrameForStaff() {
		setTitle("Staff Main GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menuArea = new JPanel();
		menuArea.setBounds(0, 0, 250, 661);
		menuArea.setBackground(new Color(58, 70, 82));
		menuArea.setPreferredSize(new Dimension(250, contentPane.getHeight()));
		contentPane.add(menuArea);
		
		ImageIcon iconInvoice = ImagesUtils.createResizedIcon("/images/serviceInvoicecButtonIcon.png", 35, 35);
		btnServiceInvoices = new JButton("Service Invoices", iconInvoice);
		btnServiceInvoices.setBounds(1, 303, 249, 50);
		btnServiceInvoices.setHorizontalAlignment(SwingConstants.LEFT);
		btnServiceInvoices.setFocusPainted(false);
		btnServiceInvoices.setBorderPainted(false);
		btnServiceInvoices.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnServiceInvoices.setName("service invoices");
	    setupButton(btnServiceInvoices);

	    ImageIcon iconApartment = ImagesUtils.createResizedIcon("/images/apartmentButtonIcon.png", 35, 35);
	    btnApartments = new JButton("Apartments", iconApartment);
	    btnApartments.setBounds(1, 242, 249, 50);
	    btnApartments.setHorizontalAlignment(SwingConstants.LEFT);
	    btnApartments.setForeground(new Color(255, 255, 255));
	    btnApartments.setBackground(new Color(58, 70, 82));
	    btnApartments.setFont(new Font("Tahoma", Font.BOLD, 17));
	    btnApartments.setFocusPainted(false);
	    btnApartments.setBorderPainted(false);
	    btnApartments.setBorder(new EmptyBorder(0, 20, 0, 20));
	    btnApartments.setName("apartments");
	    setupButton(btnApartments);
		
		ImageIcon iconContract = ImagesUtils.createResizedIcon("/images/contractButtonIcon.png", 35, 35);
		btnContracts = new JButton("Contracts", iconContract);
		btnContracts.setBounds(1, 364, 249, 50);
		btnContracts.setHorizontalAlignment(SwingConstants.LEFT);
		btnContracts.setForeground(Color.WHITE);
		btnContracts.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnContracts.setFocusPainted(false);
		btnContracts.setBorderPainted(false);
		btnContracts.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnContracts.setBackground(new Color(58, 70, 82));
		btnContracts.setName("contracts");
		setupButton(btnContracts);

		menuArea.setLayout(null);
		menuArea.add(btnApartments);
		menuArea.add(btnServiceInvoices);
		menuArea.add(btnContracts);

		
		
		lblImage = new JLabel("");
		lblImage.setBounds(63, 11, 120, 116);
		menuArea.add(lblImage);
		
		lblUsername = new JLabel("New label");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(new Color(0, 128, 192));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUsername.setBounds(0, 138, 250, 34);
		menuArea.add(lblUsername);
		
		ImageIcon iconLogout = ImagesUtils.createResizedIcon("/images/logoutButtonIcon.png", 35, 35);
		btnLogout = new JButton("Logout", iconLogout);
		btnLogout.setName("logout");
		btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnLogout.setFocusPainted(false);
		btnLogout.setBorderPainted(false);
		btnLogout.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnLogout.setBackground(new Color(58, 70, 82));
		btnLogout.setBounds(1, 600, 249, 50);
		btnLogout.setName("logout");
		setupButton(btnLogout);
		menuArea.add(btnLogout);
		
		ImageIcon iconProfile = ImagesUtils.createResizedIcon("/images/profileButtonIcon.png", 35, 35);
		btnProfile = new JButton("Profile", iconProfile);
		btnProfile.setName("profile");
		btnProfile.setHorizontalAlignment(SwingConstants.LEFT);
		btnProfile.setForeground(Color.WHITE);
		btnProfile.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnProfile.setFocusPainted(false);
		btnProfile.setBorderPainted(false);
		btnProfile.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnProfile.setBackground(new Color(58, 70, 82));
		btnProfile.setBounds(1, 539, 249, 50);
		btnProfile.setName("profile");
		setupButton(btnProfile);
		menuArea.add(btnProfile);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(249, 0, 935, 661);
		contentPane.add(desktopPane);
		
		jInFrameServiceInvoice = ServiceInvoiceManageForStaff.getInstance();
		jInFrameServiceInvoice.setLocation(-7, -27);
        desktopPane.add(jInFrameServiceInvoice);
        
        jInFrameApartmentForStaff = ApartmentManageForStaff.getInstance();
        jInFrameApartmentForStaff.setLocation(-7, -27);
        desktopPane.add(jInFrameApartmentForStaff);
        
        jInFrameContract = ContractManageForStaff.getInstance();
		jInFrameContract.setLocation(-7, -27);
        desktopPane.add(jInFrameContract);
        
        jInFrameProfile = ProfileManagerForStaff.getInstance();
        jInFrameProfile.setLocation(-7, -27);
        desktopPane.add(jInFrameProfile);
        
        if (!jInFrameProfile.isVisible()) {
        	jInFrameProfile.setVisible(true);
        	currentButton = btnProfile;
        	currentButton.setBackground(new Color(131, 209, 51));
        }  
        
	}
	
	public static MainFrameForStaff getInstance() {
        if (instance == null) {
            instance = new MainFrameForStaff();
        }
        return instance;
    }
	
	private void setupButton(JButton button) {
	    button.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseEntered(MouseEvent e) {
	            button.setBackground(new Color(131, 209, 51));
	            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	        	if(currentButton != button) {
	            button.setBackground(new Color(58, 70, 82));
	        	}
	            button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	        }
	    });
	    button.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            handleButtonClick(button);
	        }
	    });
	    button.setForeground(Color.WHITE);
	    button.setFont(new Font("Tahoma", Font.BOLD, 17));
	    button.setFocusPainted(false);
	    button.setBorderPainted(false);
	    button.setBorder(new EmptyBorder(0, 20, 0, 20));
	    button.setBackground(new Color(58, 70, 82));
	}
	
	public void loadData() {
		StaffDAO staffDAO = new StaffDAO();
		Staff staff = staffDAO.getStaffById(idUser);
		if(staff != null) {
			lblUsername.setText(staff.getName() + " (Staff)");
			
			if (staff.getImage() != null && !staff.getImage().isEmpty()) {
	            String imagePath = staff.getImage();
	            File imageFile = new File(imagePath);

	            if (imageFile.exists()) {
	                lblImage.setIcon(new ImageIcon(new ImageIcon(imagePath)
	                        .getImage().getScaledInstance(
	                        		lblImage.getWidth(),
	                        		lblImage.getHeight(),
	                                Image.SCALE_SMOOTH
	                        )
	                ));
	            } else {
	            	lblImage.setIcon(null);
	            }
	        } else {
	        	lblImage.setIcon(null);
	        }
		}
		
	}

	private void handleButtonClick(JButton button) {

        if (currentButton != null) {
            currentButton.setBackground(new Color(58, 70, 82));
        }

        currentButton = button;
        currentButton.setBackground(new Color(131, 209, 51));
        
	    JInternalFrame[] allFrames = desktopPane.getAllFrames();
	    for (JInternalFrame frame : allFrames) {
	        if (frame.isVisible()) {
	            frame.setVisible(false);
	            break;
	        }
	    }
	    switch (button.getName()) {
        case "service invoices":
            if (!jInFrameServiceInvoice.isVisible()) {
            	jInFrameServiceInvoice.setVisible(true);
            }
            break;
        case "apartments":
            if (!jInFrameApartmentForStaff.isVisible()) {
            	jInFrameApartmentForStaff.setVisible(true);
            }
            break;
        
        case "contracts":
            if (!jInFrameContract.isVisible()) {
            	jInFrameContract.setVisible(true);
            }
            break;
        case "logout":
        	dispose();
        	instance = null;
        	System.gc();
    		Login.getInstance().setVisible(true);
            break;
        case "profile":
        	if (!jInFrameProfile.isVisible()) {
            	jInFrameProfile.setVisible(true);
            	jInFrameProfile.loadData();
            }
        	break;
	    }
	}

}
