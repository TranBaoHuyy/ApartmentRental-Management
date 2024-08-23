package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.text.SimpleDateFormat;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import dao.StaffDAO;
import entity.Staff;
import utils.ButtonUtils;
import utils.ImagesUtils;
import utils.ShowMessage;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLIntegrityConstraintViolationException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;

public class StaffManage extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private static StaffManage instance;
    private JPanel panelToolBar;
    private JPanel panelContent;
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel panelButton;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
    
    private String selectedStaffId;
    private JPanel panelFind;
    private JComboBox<String> comboBox;
    private JButton btnFind;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StaffManage frame = StaffManage.getInstance();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    
    public String getSelectedStaffId() {
		return selectedStaffId;
	}

	private StaffManage() {
        super("Employee Manage", false, false, false, false);
        setBounds(100, 100, 950, 695);
        setBackground(new Color(238, 242, 243));
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        panelToolBar = new JPanel();
        getContentPane().add(panelToolBar, BorderLayout.NORTH);
        panelToolBar.setPreferredSize(new Dimension(getContentPane().getWidth(), 135));
        panelToolBar.setBackground(new Color(255, 255, 255));
        
        panelButton = new JPanel();
        panelButton.setBackground(new Color(255, 255, 255));
        panelButton.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Function", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        
        panelFind = new JPanel();
        panelFind.setLayout(null);
        panelFind.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Find By", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panelFind.setBackground(Color.WHITE);
        
        comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Staff ID", "Name", "ID Number", "Department ID"}));
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
        comboBox.setBounds(20, 23, 150, 30);
        panelFind.add(comboBox);
        
        ImageIcon iconFind = ImagesUtils.createResizedIcon("/images/findButtonIcon.png", 45, 45);
        btnFind = new JButton("Find", iconFind);
        btnFind.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnFindActionPerformed(e);
        	}
        });
        btnFind.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFind.setMargin(new Insets(10, 0, 10, 0));
        btnFind.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFind.setFocusPainted(false);
        btnFind.setBorder(null);
        btnFind.setBackground(Color.WHITE);
        btnFind.setBounds(180, 23, 77, 77);
        ButtonUtils.setupButtonForJIFrame(btnFind);
        panelFind.add(btnFind);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(20, 70, 150, 30);
        panelFind.add(textField);
        
        
        GroupLayout gl_panelToolBar = new GroupLayout(panelToolBar);
        gl_panelToolBar.setHorizontalGroup(
        	gl_panelToolBar.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelToolBar.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(panelButton, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(panelFind, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(400, Short.MAX_VALUE))
        );
        gl_panelToolBar.setVerticalGroup(
        	gl_panelToolBar.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelToolBar.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panelToolBar.createParallelGroup(Alignment.LEADING)
        				.addComponent(panelFind, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
        				.addComponent(panelButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addContainerGap())
        );

        ImageIcon iconAdd = ImagesUtils.createResizedIcon("/images/addButtonIcon.png", 45, 45);
        btnAdd = new JButton("Add", iconAdd);
        btnAdd.setBounds(10, 15, 63, 87);
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
        btnDelete.setBounds(87, 14, 63, 88);
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnDeleteActionPerformed(e);
        	}
        });
        btnDelete.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnDelete.setMargin(new Insets(10, 0, 10, 0));
        btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnDelete.setFocusPainted(false);
        btnDelete.setBorder(null);
        btnDelete.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnDelete);
        
        ImageIcon iconUpdate = ImagesUtils.createResizedIcon("/images/updateButtonIcon.png", 45, 45);
        btnUpdate = new JButton("Update", iconUpdate);
        btnUpdate.setBounds(164, 14, 63, 88);
        btnUpdate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnUpdateActionPerformed(e);
        	}
        });
        btnUpdate.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnUpdate.setMargin(new Insets(10, 0, 10, 0));
        btnUpdate.setHorizontalTextPosition(SwingConstants.CENTER);
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnUpdate.setFocusPainted(false);
        btnUpdate.setBorder(null);
        btnUpdate.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnUpdate);
        
        panelButton.setLayout(null);
        panelButton.add(btnAdd);
        panelButton.add(btnDelete);
        panelButton.add(btnUpdate);
        panelToolBar.setLayout(gl_panelToolBar);
        
        panelContent = new JPanel();
        panelContent.setBackground(new Color(255, 255, 255));
        getContentPane().add(panelContent, BorderLayout.CENTER);
        
        scrollPane = new JScrollPane();
        GroupLayout gl_panelContent = new GroupLayout(panelContent);
        gl_panelContent.setHorizontalGroup(
        	gl_panelContent.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelContent.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_panelContent.setVerticalGroup(
        	gl_panelContent.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, gl_panelContent.createSequentialGroup()
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
        			.addContainerGap())
        );
        
        table = new JTable();
        scrollPane.setViewportView(table);
        panelContent.setLayout(gl_panelContent);
        loadStaffs();
    }

    public static StaffManage getInstance() {
        if (instance == null) {
            instance = new StaffManage();
        }
        return instance;
    }
    
    private void loadStaffs() {
    	@SuppressWarnings("serial")
		DefaultTableModel model = new DefaultTableModel() {
    	    @Override
    	    public boolean isCellEditable(int row, int column) {
    	        return false;
    	    }
    	};

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Gender");
        model.addColumn("Address");
        model.addColumn("Phone Number");
        model.addColumn("Date of Birth");
        model.addColumn("Email");
        model.addColumn("Image");
        model.addColumn("ID Number");
        model.addColumn("Department ID");

        StaffDAO staffDAO = new StaffDAO();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        staffDAO.getAllStaffs().forEach(staff -> {
            String formattedDob = (staff.getDob() != null)
                    ? dateFormat.format(staff.getDob())
                    : "N/A";

            model.addRow(new Object[]{
                    staff.getId(),
                    staff.getName(),
                    staff.isGender() ? "Male" : "Female",
                    staff.getAddress(),
                    staff.getPhoneNumber(),
                    formattedDob,
                    staff.getEmail(),
                    staff.getImage(),
                    staff.getIdNumber(),
                    staff.getDepartmentId()
            });
        });

        table.setModel(model);
        TableColumn imageColumn = table.getColumnModel().getColumn(7);
        imageColumn.setCellRenderer(new ImageRenderer());
        table.setRowHeight(90);
    }
    
    @SuppressWarnings("serial")
	private static class ImageRenderer extends DefaultTableCellRenderer {
    	private int size = 60;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String imagePath = (String) value;

            if (imagePath != null && !imagePath.isEmpty()) {
                ImageIcon icon = new ImageIcon(imagePath);
                Image image = icon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);

                JLabel label = new JLabel();
                label.setIcon(new ImageIcon(image));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                table.setRowHeight(row, size+30);
                return label;
            } else {
                return c;
            }
        }
    }

    public void refreshStaffs() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        StaffDAO staffDAO = new StaffDAO();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        staffDAO.getAllStaffs().forEach(staff -> {
            String formattedDob = (staff.getDob() != null)
                    ? dateFormat.format(staff.getDob())
                    : "N/A";

            model.addRow(new Object[]{
                    staff.getId(),
                    staff.getName(),
                    staff.isGender() ? "Male" : "Female",
                    staff.getAddress(),
                    staff.getPhoneNumber(),
                    formattedDob,
                    staff.getEmail(),
                    staff.getImage(),
                    staff.getIdNumber(),
                    staff.getDepartmentId()
            });
        });
    }

	protected void btnAddActionPerformed(ActionEvent e) {
		if (!AddStaff.getInstance().isVisible()) {
			AddStaff.getInstance().setVisible(true);
	    }
	}
	protected void btnUpdateActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a row");
	    } else {
	    	selectedStaffId = table.getValueAt(selectedRow, 0).toString();
			if(!UpdateStaff.getInstance().isVisible()) {
				UpdateStaff.getInstance().setVisible(true);
			}
	    	
	    	
	    }
	}
	protected void btnDeleteActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
	    if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a row for Delete");
	    } else {
	        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Staff ?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

	        if (option == JOptionPane.YES_OPTION) {
	            var staffDAO = new StaffDAO();
	            String staffId = table.getValueAt(selectedRow, 0).toString();

	            Staff staff = staffDAO.getStaffById(staffId);
	            String imagePath = staff.getImage();

	            try {
	                boolean isSuccess = staffDAO.deleteStaff(staffId);
	                refreshStaffs();

	                if (isSuccess) {
	                    
	                    deleteImage(imagePath);

	                    ShowMessage.showMessage("Message", "Staff deleted successfully");
	                } else {
	                    ShowMessage.showErrorMessage("Error", "Cannot delete Staff, some error happened!");
	                }
	            } catch (SQLIntegrityConstraintViolationException ex) {
	                ShowMessage.showErrorMessage("Error", "Cannot delete Staff. This Staff is referenced in another table.");
	            }
	        }
	    }
	}
	
	private void deleteImage(String imagePath) {
	    if (imagePath != null && !imagePath.isEmpty()) {
	        try {
	            File file = new File(imagePath);
	            if (file.exists()) {
	                if (file.delete()) {
	                    System.out.println("Image deleted successfully");
	                } else {
	                    System.out.println("Failed to delete image");
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	protected void btnFindActionPerformed(ActionEvent e) {
		String selectedOption = comboBox.getSelectedItem().toString();
	    String searchText = textField.getText().trim();

	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	    table.setRowSorter(sorter);

	    RowFilter<Object, Object> rowFilter = null;
	    switch (selectedOption) {
	        case "Staff ID":
	            rowFilter = RowFilter.regexFilter("(?i)" + searchText, 0);
	            break;
	        case "Name":
	            rowFilter = RowFilter.regexFilter("(?i)" + searchText, 1);
	            break;
	        case "ID Number":
	            rowFilter = RowFilter.regexFilter("(?i)" + searchText, 8);
	            break;
	        case "Department ID":
	            rowFilter = RowFilter.regexFilter("(?i)" + searchText, 9);
	            break;
	    }

	    sorter.setRowFilter(rowFilter);
	}
}
