package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import dao.ApartmentDAO;
import dao.ApartmentTypeDAO;
import utils.ButtonUtils;
import utils.ImagesUtils;
import utils.ShowMessage;

import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class ApartmentManage extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private static ApartmentManage instance;
    private JPanel panelToolBar;
    private JPanel panelContent;
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel panelButton;
    private JPanel panelFind;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
	private JComboBox<String> comboBox;
    private JTextField textField;
    private JButton btnFind;
    
    private String selectedApartmentId;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ApartmentManage frame = ApartmentManage.getInstance();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    
    public String getSelectedApartmentId() {
		return selectedApartmentId;
	}


	private ApartmentManage() {
        super("Apartment Manage", false, false, false, false);
        setBounds(100, 100, 950, 695);
        setBackground(new Color(238, 242, 243));
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        panelToolBar = new JPanel();
        getContentPane().add(panelToolBar, BorderLayout.NORTH);
        panelToolBar.setPreferredSize(new Dimension(getContentPane().getWidth(), 135));
        panelToolBar.setBackground(new Color(255, 255, 255));
        
        panelButton = new JPanel();
        panelButton.setBounds(10, 11, 225, 113);
        panelButton.setBackground(new Color(255, 255, 255));
        panelButton.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Function", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        
        panelFind = new JPanel();
        panelFind.setBounds(245, 11, 316, 113);
        panelFind.setBackground(new Color(255, 255, 255));
        panelFind.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Find", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        
        
        comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Find by Apartment ID", "Find by Area Name", "Find by Status"}));
        comboBox.setBounds(15, 23, 200, 30);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
        
        textField = new JTextField();
        textField.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		performSearch(e);
        	}
        });
        textField.setBounds(15, 70, 200, 30);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textField.setColumns(10);
        
        ImageIcon iconFind = ImagesUtils.createResizedIcon("/images/findButtonIcon.png", 45, 45);
        btnFind = new JButton(null, iconFind);
        btnFind.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		performSearch(e);
        	}
        });
        btnFind.setBounds(225, 23, 77, 77);
        btnFind.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnFind.setMargin(new Insets(10, 0, 10, 0));
        btnFind.setHorizontalTextPosition(SwingConstants.CENTER);
        btnFind.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnFind.setFocusPainted(false);
        btnFind.setBorder(null);
        btnFind.setBackground(Color.WHITE);
        ButtonUtils.setupButtonForJIFrame(btnFind);
        panelFind.setLayout(null);
        panelFind.add(textField);
        panelFind.add(comboBox);
        panelFind.add(btnFind);
        

        
        ImageIcon iconAdd = ImagesUtils.createResizedIcon("/images/addButtonIcon.png", 45, 45);
        btnAdd = new JButton("Add", iconAdd);
        btnAdd.setBounds(9, 18, 63, 87);
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
        btnDelete.setBounds(81, 18, 63, 87);
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
        btnUpdate.setBounds(153, 18, 63, 87);
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
        
        panelToolBar.setLayout(null);
        panelButton.setLayout(null);
        panelButton.add(btnAdd);
        panelButton.add(btnDelete);
        panelButton.add(btnUpdate);
        panelToolBar.add(panelButton);
        panelToolBar.add(panelFind);
        
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
        			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
        			.addContainerGap())
        );
        
        table = new JTable();
        scrollPane.setViewportView(table);
        panelContent.setLayout(gl_panelContent);
        loadApartments();
    }

    public static ApartmentManage getInstance() {
        if (instance == null) {
            instance = new ApartmentManage();
        }
        return instance;
    }
    
    private void loadApartments() {
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Apartment ID");
        model.addColumn("Notes");
        model.addColumn("Area Name");
        model.addColumn("Household ID");
        model.addColumn("Type");
        model.addColumn("Price");
        model.addColumn("Status");

        ApartmentDAO apartmentDAO = new ApartmentDAO();
        ApartmentTypeDAO apartmentTypeDAO = new ApartmentTypeDAO();
        
        apartmentDAO.getAllApartments().forEach(apartment -> {
            String areaName = apartment.getAreaName();
            if (areaName == null || areaName.isEmpty()) {
                areaName = "N/A";
            }

            model.addRow(new Object[]{
                    apartment.getId(),
                    apartment.getNotes(),
                    areaName,
                    apartment.getHouseholdId() != null ? apartment.getHouseholdId() : "N/A",
                    apartmentTypeDAO.getApartmentTypeById(apartment.getTypeId()).getType(),
                    apartmentTypeDAO.getApartmentTypeById(apartment.getTypeId()).getPrice(),
                    apartment.getStatus()
            });
        });

        table.setModel(model);
        table.setRowHeight(30);
    }

    public void refreshApartments() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        ApartmentDAO apartmentDAO = new ApartmentDAO();
        ApartmentTypeDAO apartmentTypeDAO = new ApartmentTypeDAO();
        
        apartmentDAO.getAllApartments().forEach(apartment -> {
            String areaName = apartment.getAreaName();
            if (areaName == null || areaName.isEmpty()) {
                areaName = "N/A";
            }

            model.addRow(new Object[]{
                    apartment.getId(),
                    apartment.getNotes(),
                    areaName,
                    apartment.getHouseholdId() != null ? apartment.getHouseholdId() : "N/A",
                    apartmentTypeDAO.getApartmentTypeById(apartment.getTypeId()).getType(),
                    apartmentTypeDAO.getApartmentTypeById(apartment.getTypeId()).getPrice(),
                    apartment.getStatus()
            });
        });
    }

	protected void btnAddActionPerformed(ActionEvent e) {
		AddApartment.getInstance().clearTextField();
		AddApartment.getInstance().fillDataComboBoxIds();
		if (!AddApartment.getInstance().isVisible()) {
			AddApartment.getInstance().setVisible(true);		
	    }
	}
	protected void btnUpdateActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a row");
	    } else {
	    	selectedApartmentId = table.getValueAt(selectedRow, 0).toString();
	    	//UpdateApartment.getInstance().clearTextField();
	    	UpdateApartment.getInstance().fillDataComboBoxIds();
	    	UpdateApartment.getInstance().loadData();
			if(!UpdateApartment.getInstance().isVisible()) {
				UpdateApartment.getInstance().setVisible(true);
			}
	    	
	    }
	}
	protected void btnDeleteActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
	    if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a row for Delete");
	    } else {
	        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this Apartment ?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

	        if (option == JOptionPane.YES_OPTION) {
	            var apartmentDAO = new ApartmentDAO();
	            String apartmentId = table.getValueAt(selectedRow, 0).toString();

	            try {
	                boolean isSuccess = apartmentDAO.deleteApartment(apartmentId);
	                refreshApartments();

	                if (isSuccess) {
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
	protected void performSearch(ActionEvent e) {
		String selectedOption = comboBox.getSelectedItem().toString();
	    String searchText = textField.getText().trim();

	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	    table.setRowSorter(sorter);

	    RowFilter<Object, Object> rowFilter = null;
	    switch (selectedOption) {
	        case "Find by Apartment ID":
	            rowFilter = RowFilter.regexFilter(searchText, 0);
	            break;
	        case "Find by Area Name":
	            rowFilter = RowFilter.regexFilter("(?i)" + searchText, 2);
	            break;
	        case "Find by Status":
	            rowFilter = RowFilter.regexFilter("(?i)" + searchText, 6);
	            break;
	    }

	    sorter.setRowFilter(rowFilter);
	}

}
