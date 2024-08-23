package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.ServiceDAO;
import utils.ButtonUtils;
import utils.ImagesUtils;
import utils.ShowMessage;

import java.awt.event.ActionListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.awt.event.ActionEvent;

public class ServiceManage extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private static ServiceManage instance;
    private JPanel panelToolBar;
    private JPanel panelContent;
    private JScrollPane scrollPane;
    private JTable table;
    private JPanel panelButton;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ServiceManage frame = ServiceManage.getInstance();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
	private ServiceManage() {
        super("Service Manage", false, false, false, false);
        setBounds(100, 100, 950, 695);
        setBackground(new Color(238, 242, 243));
        getContentPane().setLayout(null);
        
        panelToolBar = new JPanel();
        panelToolBar.setBounds(0, 0, 934, 141);
        getContentPane().add(panelToolBar);
        panelToolBar.setPreferredSize(new Dimension(getContentPane().getWidth(), 135));
        panelToolBar.setBackground(new Color(255, 255, 255));
        
        panelButton = new JPanel();
        panelButton.setBounds(10, 11, 281, 120);
        panelButton.setBackground(new Color(255, 255, 255));
        panelButton.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Function", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
     
        ImageIcon iconAdd = ImagesUtils.createResizedIcon("/images/addButtonIcon.png", 45, 45);
        btnAdd = new JButton("Add", iconAdd);
        btnAdd.setBounds(17, 28, 70, 87);
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
        btnDelete.setBounds(104, 27, 70, 88);
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
        btnUpdate.setBounds(191, 27, 70, 88);
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
        panelToolBar.add(panelButton);
        panelButton.setLayout(null);
        panelButton.add(btnAdd);
        panelButton.add(btnDelete);
        panelButton.add(btnUpdate);
        
        panelContent = new JPanel();
        panelContent.setBounds(0, 141, 934, 524);
        panelContent.setBackground(new Color(255, 255, 255));
        getContentPane().add(panelContent);
        panelContent.setLayout(null);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 0, 914, 519);
        
        table = new JTable();
        table.setRowMargin(5);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        scrollPane.setViewportView(table);
        panelContent.add(scrollPane);
        loadService();
    }

    public static ServiceManage getInstance() {
        if (instance == null) {
            instance = new ServiceManage();
        }
        return instance;
    }
    
    private void loadService() {
		DefaultTableModel model = new DefaultTableModel();
			
		
		model.addColumn("id");
		model.addColumn("Name");
		model.addColumn("Price");
		
		
		ServiceDAO serviceDAO = new ServiceDAO();
		serviceDAO.getAllServices().stream().forEach(service -> model.addRow(new Object[] {
				service.getId(),
				service.getName(), 
				service.getPrice()
		}));
		
		table.setModel(model);
		table.setRowHeight(30);
	}
    
    public void refresh() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		var serviceDAO = new ServiceDAO();
		serviceDAO.getAllServices().stream().forEach(service -> model.addRow(new Object[] {
				service.getId(),
				service.getName(), 
				service.getPrice()
		}));
	}
    
	protected void btnAddActionPerformed(ActionEvent e) {
		if (!AddService.getInstance().isVisible()) {
            AddService.getInstance().setVisible(true);
	    }
	}
	protected void btnDeleteActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a row for Delete");
	    } else {
	    	
	    	int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this invoice?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
	    	
	    	if (option == JOptionPane.YES_OPTION) {
	    		var serviceDAO = new ServiceDAO();
	    		try {
					boolean isSuccess = serviceDAO.deleteService(
						Integer.parseInt(table.getValueAt(selectedRow, 0).toString())
						);
					refresh();
					if(isSuccess) {
						ShowMessage.showMessage("Message", "Service deleted successfully");
					} else {
						ShowMessage.showErrorMessage("Error", "Cannot delete service, some error happened!");
					}
	    		} catch (SQLIntegrityConstraintViolationException ex) {
	                ShowMessage.showErrorMessage("Error", "Cannot delete Service. This Service is referenced in another table.");
	            }
	        }
	    	
	    }
	}
	protected void btnUpdateActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		
		if (selectedRow == -1) {
	        ShowMessage.showWarningMessage("Warning", "Please Choose a row for Update");
	    } else {
			UpdateService.getInstance().updateId = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
			UpdateService.getInstance().getTextFieldName().setText(table.getValueAt(selectedRow, 1).toString());
			UpdateService.getInstance().getTextFieldPrice().setText(table.getValueAt(selectedRow, 2).toString());
			if(!UpdateService.getInstance().isVisible()) {
				UpdateService.getInstance().setVisible(true);
			}
	    }
	}
}
