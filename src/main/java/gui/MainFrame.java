package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.StaffDAO;
import dao.UserDAO;
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
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static MainFrame instance;
	private JPanel contentPane;
	private JPanel menuArea;
	private JDesktopPane desktopPane;
	
	private JButton btnServiceInvoices;
	private JButton btnApartments;
	private JButton btnServices;
	private JButton btnHouseholds;
	private JButton btnContracts;
	private JButton btnEmployees;
	private JButton btnUsers;
	
	private ServiceInvoiceManage jInFrameServiceInvoice;
	private ApartmentManage jInFrameApartment;
	private ServiceManage jInFrameService;
	private HouseholdManage jInFrameHousehold;
	private ContractManage jInFrameContract;
	private StaffManage jInFrameStaff;
	private UserManage jInFrameUser;
	private StatisticsManage jInFrameStatistic;
	private ProfileManager jInFrameProfile;
	
	private JButton currentButton;
	private JButton btnStatistics;
	private JLabel lblImage;
	private JLabel lblUsername;
	
	private String idUser;
	private JButton btnLogout;
	
	public static void main(String[] args) {		
		SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            MainFrame frame = new MainFrame();
			frame.setVisible(true);
        });
	}	
	
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdUser() {
		return idUser;
	}

	public MainFrame() {
		setTitle("Main GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menuArea = new JPanel();
		menuArea.setBounds(0, 0, 250, 665);
		menuArea.setBackground(new Color(58, 70, 82));
		menuArea.setPreferredSize(new Dimension(250, contentPane.getHeight()));
		contentPane.add(menuArea);
		
		ImageIcon iconInvoice = ImagesUtils.createResizedIcon("/images/serviceInvoicecButtonIcon.png", 35, 35);
		btnServiceInvoices = new JButton("Service Invoices", iconInvoice);
		btnServiceInvoices.setBounds(0, 321, 249, 50);
		btnServiceInvoices.setHorizontalAlignment(SwingConstants.LEFT);
		btnServiceInvoices.setFocusPainted(false);
		btnServiceInvoices.setBorderPainted(false);
		btnServiceInvoices.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnServiceInvoices.setName("service invoices");
	    setupButton(btnServiceInvoices);

	    ImageIcon iconApartment = ImagesUtils.createResizedIcon("/images/apartmentButtonIcon.png", 35, 35);
	    btnApartments = new JButton("Apartments", iconApartment);
	    btnApartments.setBounds(0, 199, 249, 50);
	    btnApartments.setHorizontalAlignment(SwingConstants.LEFT);
	    btnApartments.setForeground(new Color(255, 255, 255));
	    btnApartments.setBackground(new Color(58, 70, 82));
	    btnApartments.setFont(new Font("Tahoma", Font.BOLD, 17));
	    btnApartments.setFocusPainted(false);
	    btnApartments.setBorderPainted(false);
	    btnApartments.setBorder(new EmptyBorder(0, 20, 0, 20));
	    btnApartments.setName("apartments");
	    setupButton(btnApartments);
		
	    ImageIcon iconService = ImagesUtils.createResizedIcon("/images/serviceButtonIcon.png", 35, 35);
		btnServices = new JButton("Services", iconService);
		btnServices.setBounds(0, 260, 249, 50);
		btnServices.setHorizontalAlignment(SwingConstants.LEFT);
		btnServices.setForeground(Color.WHITE);
		btnServices.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnServices.setFocusPainted(false);
		btnServices.setBorderPainted(false);
		btnServices.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnServices.setBackground(new Color(58, 70, 82));
		btnServices.setName("services");
		setupButton(btnServices);
		
		ImageIcon iconHousehold = ImagesUtils.createResizedIcon("/images/householdButtonIcon.png", 35, 35);
		btnHouseholds = new JButton("Households", iconHousehold);
		btnHouseholds.setBounds(0, 377, 249, 50);
		btnHouseholds.setHorizontalAlignment(SwingConstants.LEFT);
		btnHouseholds.setForeground(Color.WHITE);
		btnHouseholds.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnHouseholds.setFocusPainted(false);
		btnHouseholds.setBorderPainted(false);
		btnHouseholds.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnHouseholds.setBackground(new Color(58, 70, 82));
		btnHouseholds.setName("households");
		setupButton(btnHouseholds);
		
		ImageIcon iconContract = ImagesUtils.createResizedIcon("/images/contractButtonIcon.png", 35, 35);
		btnContracts = new JButton("Contracts", iconContract);
		btnContracts.setBounds(0, 433, 249, 50);
		btnContracts.setHorizontalAlignment(SwingConstants.LEFT);
		btnContracts.setForeground(Color.WHITE);
		btnContracts.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnContracts.setFocusPainted(false);
		btnContracts.setBorderPainted(false);
		btnContracts.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnContracts.setBackground(new Color(58, 70, 82));
		btnContracts.setName("contracts");
		setupButton(btnContracts);
		
		ImageIcon iconEmployee = ImagesUtils.createResizedIcon("/images/employeeButtonIcon.png", 35, 35);
		btnEmployees = new JButton("Staffs", iconEmployee);
		btnEmployees.setBounds(0, 489, 249, 50);
		btnEmployees.setHorizontalAlignment(SwingConstants.LEFT);
		btnEmployees.setForeground(Color.WHITE);
		btnEmployees.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnEmployees.setFocusPainted(false);
		btnEmployees.setBorderPainted(false);
		btnEmployees.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnEmployees.setBackground(new Color(58, 70, 82));
		btnEmployees.setName("staffs");
		setupButton(btnEmployees);
		
		ImageIcon iconUser = ImagesUtils.createResizedIcon("/images/userButtonIcon.png", 35, 35);
		btnUsers = new JButton("Users", iconUser);
		btnUsers.setBounds(0, 545, 249, 50);
		btnUsers.setHorizontalAlignment(SwingConstants.LEFT);
		btnUsers.setForeground(Color.WHITE);
		btnUsers.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnUsers.setFocusPainted(false);
		btnUsers.setBorderPainted(false);
		btnUsers.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnUsers.setBackground(new Color(58, 70, 82));
		btnUsers.setName("users");
		setupButton(btnUsers);
		menuArea.setLayout(null);
		menuArea.add(btnApartments);
		menuArea.add(btnServices);
		menuArea.add(btnServiceInvoices);
		menuArea.add(btnUsers);
		menuArea.add(btnHouseholds);
		menuArea.add(btnContracts);
		menuArea.add(btnEmployees);
		
		ImageIcon iconStatistic = ImagesUtils.createResizedIcon("/images/statisticButtonIcon.png", 35, 35);
		btnStatistics = new JButton("Statistics", iconStatistic);
		btnStatistics.setName("users");
		btnStatistics.setHorizontalAlignment(SwingConstants.LEFT);
		btnStatistics.setForeground(Color.WHITE);
		btnStatistics.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnStatistics.setFocusPainted(false);
		btnStatistics.setBorderPainted(false);
		btnStatistics.setBorder(new EmptyBorder(0, 20, 0, 20));
		btnStatistics.setBackground(new Color(58, 70, 82));
		btnStatistics.setBounds(0, 600, 249, 50);
		btnStatistics.setName("statistic");
		setupButton(btnStatistics);
		menuArea.add(btnStatistics);
	
		lblImage = new JLabel("");
		lblImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblImageMouseClicked(e);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblImageMouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblImageMouseExited(e);
			}
		});
		lblImage.setForeground(new Color(255, 255, 255));
		lblImage.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblImage.setBorder(null);
		lblImage.setBounds(60, 11, 130, 130);
		menuArea.add(lblImage);
		
		lblUsername = new JLabel("New label");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(new Color(255, 128, 64));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUsername.setBounds(0, 145, 250, 50);
		menuArea.add(lblUsername);
		
		ImageIcon iconLogout = ImagesUtils.createResizedIcon("/images/logoutButtonIcon.png", 35, 35);
		btnLogout = new JButton("",iconLogout);
		btnLogout.setBounds(10, 11, 40, 40);
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnLogout.setFocusPainted(false);
		btnLogout.setBorderPainted(false);
	    btnLogout.setBorder(new EmptyBorder(0, 10, 0, 10));
	    btnLogout.setBackground(new Color(58, 70, 82));
	    btnLogout.setName("logout");
	    setupButton(btnLogout);
		menuArea.add(btnLogout);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 255, 255));
		desktopPane.setBounds(249, 0, 935, 665);
		contentPane.add(desktopPane);
		
		jInFrameServiceInvoice = ServiceInvoiceManage.getInstance();
		jInFrameServiceInvoice.setLocation(-7, -27);
        desktopPane.add(jInFrameServiceInvoice);
        
		jInFrameApartment = ApartmentManage.getInstance();
		jInFrameApartment.setLocation(-7, -27);
        desktopPane.add(jInFrameApartment);
        
        jInFrameService = ServiceManage.getInstance();
        jInFrameService.setLocation(-7, -27);
        desktopPane.add(jInFrameService);
        
        jInFrameHousehold = HouseholdManage.getInstance();
		jInFrameHousehold.setLocation(-7, -27);
        desktopPane.add(jInFrameHousehold);
        
        jInFrameContract = ContractManage.getInstance();
		jInFrameContract.setLocation(-7, -27);
        desktopPane.add(jInFrameContract);
        
        jInFrameStaff = StaffManage.getInstance();
        jInFrameStaff.setLocation(-7, -27);
        desktopPane.add(jInFrameStaff);
        
        jInFrameUser = UserManage.getInstance();
		jInFrameUser.setLocation(-7, -27);
        desktopPane.add(jInFrameUser); 
        
        jInFrameStatistic = StatisticsManage.getInstance();
        jInFrameStatistic.setLocation(-7, -27);
        desktopPane.add(jInFrameStatistic);
        
        jInFrameProfile = ProfileManager.getInstance();
        jInFrameProfile.setLocation(-7, -27);
        desktopPane.add(jInFrameProfile);
        
        if (!jInFrameProfile.isVisible()) {
        	jInFrameProfile.setVisible(true);        	
        }  
	}
	
	public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
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
		UserDAO userDAO = new UserDAO();
		if(staff != null) {
			lblUsername.setText(staff.getName() + " (" + userDAO.getUserById(idUser).getRole() + ")");
			
			if (staff.getImage() != null && !staff.getImage().isEmpty()) {
	            String imagePath = staff.getImage();
	            File imageFile = new File(imagePath);

	            if (imageFile.exists()) {
	                lblImage.setIcon(new ImageIcon(new ImageIcon(imagePath)
	                        .getImage().getScaledInstance(
	                        		lblImage.getWidth() - 20,
	                        		lblImage.getHeight() - 20,
	                                Image.SCALE_SMOOTH
	                        )
	                ));
	            } else {
	            	lblImage.setText("No Avatar");
	            }
	        } else {
	        	lblImage.setText("No Avatar");
	        }
		}
		
	}

	private void handleButtonClick(JButton button) {
		lblImage.setBorder(null);
		
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
            if (!jInFrameApartment.isVisible()) {
            	jInFrameApartment.setVisible(true);
            }
            break;
        case "services":
            if (!jInFrameService.isVisible()) {
            	jInFrameService.setVisible(true);
            }
            break;
        case "households":
            if (!jInFrameHousehold.isVisible()) {
            	jInFrameHousehold.setVisible(true);
            }
            break;
        case "contracts":
            if (!jInFrameContract.isVisible()) {
            	jInFrameContract.setVisible(true);
            }
            break;
        case "staffs":
            if (!jInFrameStaff.isVisible()) {
            	jInFrameStaff.setVisible(true);
            }
            break;
        case "users":
            if (!jInFrameUser.isVisible()) {
            	jInFrameUser.setVisible(true);
            }
            break;
        case "statistic":
            if (!jInFrameStatistic.isVisible()) {
            	jInFrameStatistic.setVisible(true);
            }
            break;
        case "logout":
        	dispose();
        	instance = null;
        	System.gc();
    		Login.getInstance().setVisible(true);
            break;
	    }
	}
	protected void lblImageMouseClicked(MouseEvent e) {
		if (!jInFrameProfile.isVisible()) {
        	jInFrameProfile.setVisible(true);
        	jInFrameProfile.loadData();
        	currentButton.setBackground(new Color(58, 70, 82));
        	currentButton = null;
        }
	}
	protected void lblImageMouseEntered(MouseEvent e) {
		lblImage.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblImage.setBorder(new LineBorder(new Color(131, 209, 51), 5));
	}
	protected void lblImageMouseExited(MouseEvent e) {
		lblImage.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
		if (currentButton != null) lblImage.setBorder(null);
	}
}
