package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.UserDAO;
import utils.ButtonUtils;
import utils.ImagesUtils;
import utils.ShowMessage;

import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.awt.event.ActionEvent;

public class UserManage extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private static UserManage instance;
    private JPanel panelToolBar;
    private JPanel panelContent;
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel panelButton;
    private JPanel panelFind;
    private JButton btnAdd;
    private JButton btnDelete;
    private JTextField textField;
    private JButton btnFind;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UserManage frame = UserManage.getInstance();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    private UserManage() {
        super("User Manage", false, false, false, false);
        setBounds(100, 100, 950, 690);
        setBackground(new Color(238, 242, 243));
        getContentPane().setLayout(null);
        
        panelToolBar = new JPanel();
        panelToolBar.setBounds(0, 0, 934, 141);
        getContentPane().add(panelToolBar);
        panelToolBar.setPreferredSize(new Dimension(getContentPane().getWidth(), 135));
        panelToolBar.setBackground(new Color(255, 255, 255));
        
        panelButton = new JPanel();
        panelButton.setBounds(10, 11, 161, 120);
        panelButton.setBackground(new Color(255, 255, 255));
        panelButton.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Function", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        
        panelFind = new JPanel();
        panelFind.setBounds(181, 11, 289, 120);
        panelFind.setBackground(new Color(255, 255, 255));
        panelFind.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Find by Username", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        
        textField = new JTextField();
        textField.setBounds(10, 49, 187, 33);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textField.setColumns(10);
        
        ImageIcon iconFind = ImagesUtils.createResizedIcon("/images/findButtonIcon.png", 45, 45);
        btnFind = new JButton(null, iconFind);
        btnFind.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnFindActionPerformed(e);
        	}
        });
        btnFind.setBounds(207, 32, 69, 77);
        btnFind.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFind.setMargin(new Insets(10, 0, 10, 0));
        btnFind.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFind.setFocusPainted(false);
        btnFind.setBorder(null);
        btnFind.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnFind);
        

        
        ImageIcon iconAdd = ImagesUtils.createResizedIcon("/images/addButtonIcon.png", 45, 45);
        btnAdd = new JButton("Add", iconAdd);
        btnAdd.setBounds(11, 22, 63, 87);
        btnAdd.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnAddActionPerformed(e);
        	}
        });
        btnAdd.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnAdd.setMargin(new Insets(10, 0, 10, 0));
        btnAdd.setHorizontalTextPosition(SwingConstants.CENTER);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAdd.setFocusPainted(false);
        btnAdd.setBorder(null);
        btnAdd.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnAdd);
        
        ImageIcon iconDelete = ImagesUtils.createResizedIcon("/images/deleteButtonIcon.png", 45, 45);
        btnDelete = new JButton("Delete", iconDelete);
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnDeleteActionPerformed(e);
        	}
        });
        btnDelete.setBounds(85, 21, 63, 88);
        btnDelete.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnDelete.setMargin(new Insets(10, 0, 10, 0));
        btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnDelete.setFocusPainted(false);
        btnDelete.setBorder(null);
        btnDelete.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnDelete);
        
        panelToolBar.setLayout(null);
        panelToolBar.add(panelButton);
        panelButton.setLayout(null);
        panelButton.add(btnAdd);
        panelButton.add(btnDelete);
        panelToolBar.add(panelFind);
        panelFind.setLayout(null);
        panelFind.add(textField);
        panelFind.add(btnFind);
        
        panelContent = new JPanel();
        panelContent.setBounds(0, 135, 934, 525);
        panelContent.setBackground(new Color(255, 255, 255));
        getContentPane().add(panelContent);
        
        scrollPane = new JScrollPane();
        GroupLayout gl_panelContent = new GroupLayout(panelContent);
        gl_panelContent.setHorizontalGroup(
        	gl_panelContent.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelContent.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_panelContent.setVerticalGroup(
        	gl_panelContent.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_panelContent.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        			.addContainerGap())
        );
        
        table = new JTable();
        scrollPane.setViewportView(table);
        panelContent.setLayout(gl_panelContent);
        loadUsers();
    }

    public static UserManage getInstance() {
        if (instance == null) {
            instance = new UserManage();
        }
        return instance;
    }
	protected void btnAddActionPerformed(ActionEvent e) {
		if (!AddUser.getInstance().isVisible()) {
			AddUser.getInstance().setVisible(true);
	    }
	}
	
	private void loadUsers() {
	    DefaultTableModel model = new DefaultTableModel();

	    model.addColumn("ID");
	    model.addColumn("Username");
	    model.addColumn("Role");

	    UserDAO userDAO = new UserDAO();

	    userDAO.getAllUsers().forEach(user -> {
	        model.addRow(new Object[]{
	                user.getId(),
	                user.getUsername(),
	                user.getRole()
	        });
	    });

	    table.setModel(model);
	    table.setRowHeight(30);
	}

	public void refreshUsers() {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0);

	    UserDAO userDAO = new UserDAO();

	    userDAO.getAllUsers().forEach(user -> {
	        model.addRow(new Object[]{
	                user.getId(),
	                user.getUsername(),
	                user.getRole()
	        });
	    });
	}

	protected void btnDeleteActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a row for Delete");
	    } else {
	    	
	    	int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
	    	
	    	if (option == JOptionPane.YES_OPTION) {
	    		var userDAO = new UserDAO();
	    		try {
					boolean isSuccess = userDAO.deleteUser(
						table.getValueAt(selectedRow, 0).toString());
					refreshUsers();
					if(isSuccess) {
						ShowMessage.showMessage("Message", "User deleted successfully");
					} else {
						ShowMessage.showErrorMessage("Error", "Cannot delete User, some error happened!");
					}
	    		} catch (SQLIntegrityConstraintViolationException ex) {
	    			ShowMessage.showErrorMessage("Error", "Cannot delete Staff. This Staff is referenced in another table.");
	    		}
	        }
	    }
	}
	protected void btnFindActionPerformed(ActionEvent e) {
	    String searchText = textField.getText().trim();

	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	    table.setRowSorter(sorter);

	    RowFilter<Object, Object> rowFilter = null;
	    rowFilter = RowFilter.regexFilter("(?i)" + searchText, 1);
	    sorter.setRowFilter(rowFilter);
	}
}
