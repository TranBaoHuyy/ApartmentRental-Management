package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JDateChooser;

import dao.ApartmentDAO;
import dao.DataChartDAO;
import dao.ServiceDAO;
import dao.ServiceInvoiceDAO;
import dao.StaffDAO;
import entity.DataChart;
import entity.Service;
import utils.ButtonUtils;
import utils.ShowMessage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class StatisticsManage extends JInternalFrame {

    private static final long serialVersionUID = 1L;

    private static StatisticsManage instance;
    private JTabbedPane tabbedPane;
    private JPanel apartmentTab;
    private JPanel serviceInvoiceTab;
    private JPanel panel_3;
    private JPanel panel;
    private JDateChooser dateChooserFrom;
    private JDateChooser dateChooserTo;
    private JLabel lblNewLabel;
    private JLabel lblTo;
    private JLabel lblStaffId;
    private JComboBox<String> comboBoxStaffId;
    private JLabel lblServiceName;
    private JComboBox<String> comboBoxServiceId;
    private JLabel lblStaffName;
    private JTextField textFieldStaffName;
    private JLabel lblServiceName_1;
    private JTextField textFieldServiceName;
    private JLabel lblApartmentId;
    private JComboBox<String> comboBoxApartmentId;
    private JButton btnFirst;
    private JButton btnPrevious;
    private JComboBox<String> comboBoxNumOfPage;
    private JButton btnNext;
    private JButton btnLast;
    private JLabel lblStatus;
    private JTextField textFieldPageNumber;
    private JLabel lblTotal;
    private JScrollPane scrollPane;
    private JTable table;
    
    private Integer pageNumber = 1;
	private Integer rowsOfPage;
	private Integer totalRows = 0;
	private Double totalPages = 0.0;
	private JButton btnLoadInvoice;
	private JButton btnClear;
	private JLabel lblNewLabel_1;
	private JComboBox<String> comboBoxYear;
	private List<JCheckBox> checkBoxes;
	private JCheckBox chckbxT2;
	private JCheckBox chckbxT1;
	private JCheckBox chckbxT3;
	private JCheckBox chckbxT4;
	private JCheckBox chckbxT5;
	private JCheckBox chckbxT6;
	private JCheckBox chckbxT7;
	private JCheckBox chckbxT8;
	private JCheckBox chckbxT9;
	private JCheckBox chckbxT10;
	private JCheckBox chckbxT11;
	private JCheckBox chckbxT12;
	private JPanel panelMonth;
	private JButton btnDrawChart;
	private JButton btnCheckAll;
	private JButton btnClearAll;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StatisticsManage frame = StatisticsManage.getInstance();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


	private StatisticsManage() {
        super("Statistic", false, false, false, false);
        setBounds(100, 100, 950, 695);
        setBackground(new Color(238, 242, 243));
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane);
        
        apartmentTab = new JPanel();
        apartmentTab.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("Apartment", null, apartmentTab, null);
        tabbedPane.setBackgroundAt(0, new Color(0, 64, 128));

        
        
        apartmentTab.setLayout(null);
      
        
        panel_3 = new JPanel();
        panel_3.setBounds(10, 11, 909, 124);
        apartmentTab.add(panel_3);
        panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Filter Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panel_3.setBackground(new Color(255, 255, 255));
        panel_3.setLayout(null);
        
        lblNewLabel_1 = new JLabel("Select Year");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel_1.setBounds(69, 40, 84, 16);
        panel_3.add(lblNewLabel_1);
        
        comboBoxYear = new JComboBox<String>();
        comboBoxYear.setModel(new DefaultComboBoxModel<String>(new String[] {"2023"}));
        comboBoxYear.setBounds(163, 35, 70, 25);
        panel_3.add(comboBoxYear);
        
        panelMonth = new JPanel();
        panelMonth.setBackground(new Color(255, 255, 255));
        panelMonth.setBounds(340, 14, 559, 99);
        panel_3.add(panelMonth);
        panelMonth.setLayout(null);
        
        checkBoxes = new ArrayList<>();
        
        chckbxT2 = new JCheckBox("February");
        chckbxT2.setBounds(39, 37, 90, 23);
        panelMonth.add(chckbxT2);
        chckbxT2.setBackground(new Color(255, 255, 255));
        chckbxT2.setFont(new Font("Tahoma", Font.BOLD, 13));        
        
        chckbxT3 = new JCheckBox("March");
        chckbxT3.setBounds(39, 67, 90, 23);
        panelMonth.add(chckbxT3);
        chckbxT3.setFont(new Font("Tahoma", Font.BOLD, 13));
        chckbxT3.setBackground(Color.WHITE);        
        
        chckbxT1 = new JCheckBox("January");
        chckbxT1.setBounds(39, 7, 90, 23);
        panelMonth.add(chckbxT1);
        chckbxT1.setFont(new Font("Tahoma", Font.BOLD, 13));
        chckbxT1.setBackground(Color.WHITE);        
        
        chckbxT4 = new JCheckBox("April");
        chckbxT4.setBounds(168, 7, 90, 23);
        panelMonth.add(chckbxT4);
        chckbxT4.setFont(new Font("Tahoma", Font.BOLD, 13));
        chckbxT4.setBackground(Color.WHITE);        
        
        chckbxT5 = new JCheckBox("May");
        chckbxT5.setBounds(168, 37, 90, 23);
        panelMonth.add(chckbxT5);
        chckbxT5.setFont(new Font("Tahoma", Font.BOLD, 13));
        chckbxT5.setBackground(Color.WHITE);        
        
        chckbxT6 = new JCheckBox("June");
        chckbxT6.setBounds(168, 67, 90, 23);
        panelMonth.add(chckbxT6);
        chckbxT6.setFont(new Font("Tahoma", Font.BOLD, 13));
        chckbxT6.setBackground(Color.WHITE);        
        
        chckbxT9 = new JCheckBox("September");
        chckbxT9.setBounds(297, 67, 102, 23);
        panelMonth.add(chckbxT9);
        chckbxT9.setFont(new Font("Tahoma", Font.BOLD, 13));
        chckbxT9.setBackground(Color.WHITE);        
        
        chckbxT8 = new JCheckBox("August");
        chckbxT8.setBounds(297, 37, 90, 23);
        panelMonth.add(chckbxT8);
        chckbxT8.setFont(new Font("Tahoma", Font.BOLD, 13));
        chckbxT8.setBackground(Color.WHITE);       
        
        chckbxT7 = new JCheckBox("July");
        chckbxT7.setBounds(297, 7, 90, 23);
        panelMonth.add(chckbxT7);
        chckbxT7.setFont(new Font("Tahoma", Font.BOLD, 13));
        chckbxT7.setBackground(Color.WHITE);
        
        chckbxT10 = new JCheckBox("October");
        chckbxT10.setBounds(426, 7, 90, 23);
        panelMonth.add(chckbxT10);
        chckbxT10.setFont(new Font("Tahoma", Font.BOLD, 13));
        chckbxT10.setBackground(Color.WHITE);
        
        chckbxT11 = new JCheckBox("November");
        chckbxT11.setBounds(426, 37, 90, 23);
        panelMonth.add(chckbxT11);
        chckbxT11.setFont(new Font("Tahoma", Font.BOLD, 13));
        chckbxT11.setBackground(Color.WHITE);
        
        chckbxT12 = new JCheckBox("December");
        chckbxT12.setBounds(426, 67, 90, 23);
        panelMonth.add(chckbxT12);
        chckbxT12.setFont(new Font("Tahoma", Font.BOLD, 13));
        chckbxT12.setBackground(Color.WHITE);
        
        checkBoxes.add(chckbxT1);
        checkBoxes.add(chckbxT2);
        checkBoxes.add(chckbxT3);
        checkBoxes.add(chckbxT4);
        checkBoxes.add(chckbxT5);
        checkBoxes.add(chckbxT6);
        checkBoxes.add(chckbxT7);
        checkBoxes.add(chckbxT8);
        checkBoxes.add(chckbxT9);
        checkBoxes.add(chckbxT10);
        checkBoxes.add(chckbxT11);
        checkBoxes.add(chckbxT12);
        
        btnDrawChart = new JButton("Draw");
        btnDrawChart.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnDrawChartActionPerformed(e);
        	}
        });
        btnDrawChart.setForeground(new Color(255, 255, 255));
        btnDrawChart.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnDrawChart.setBounds(20, 83, 100, 30);
        panel_3.add(btnDrawChart);
        ButtonUtils.setupNormalButton(btnDrawChart, new Color(131, 209, 51),new Color(159, 216, 96));
        
        btnCheckAll = new JButton("Check All");
        btnCheckAll.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnCheckAllActionPerformed(e);
        	}
        });
        btnCheckAll.setForeground(new Color(255, 255, 255));
        btnCheckAll.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCheckAll.setBounds(129, 83, 100, 30);
        panel_3.add(btnCheckAll);
        ButtonUtils.setupNormalButton(btnCheckAll, new Color(240, 110, 50),new Color(255, 128, 64));
        
        btnClearAll = new JButton("Clear All");
        btnClearAll.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnClearAllActionPerformed(e);
        	}
        });
        btnClearAll.setBackground(new Color(0, 128, 128));
        btnClearAll.setForeground(new Color(255, 255, 255));
        btnClearAll.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnClearAll.setBounds(239, 83, 100, 30);
        panel_3.add(btnClearAll);
        ButtonUtils.setupNormalButton(btnClearAll, new Color(0, 128, 128),new Color(10, 138, 138));
        
        serviceInvoiceTab = new JPanel();
        serviceInvoiceTab.setBackground(new Color(255, 255, 255));
        tabbedPane.addTab("Service Invoice", null, serviceInvoiceTab, null);
        serviceInvoiceTab.setLayout(null);
        
        panel = new JPanel();
        panel.setBounds(10, 11, 909, 132);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 64, 128)), "Filter Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 64, 128)));
        panel.setBackground(new Color(255, 255, 255));
        serviceInvoiceTab.add(panel);
        panel.setLayout(null);
        
        dateChooserFrom = new JDateChooser();
        dateChooserFrom.setBounds(70, 22, 130, 25);
        panel.add(dateChooserFrom);
        
        dateChooserTo = new JDateChooser();
        dateChooserTo.setBounds(291, 22, 130, 25);
        panel.add(dateChooserTo);
        
        lblNewLabel = new JLabel("From");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNewLabel.setBounds(10, 27, 38, 14);
        panel.add(lblNewLabel);
        
        lblTo = new JLabel("To");
        lblTo.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTo.setBounds(259, 27, 22, 14);
        panel.add(lblTo);
        
        lblStaffId = new JLabel("Staff ID");
        lblStaffId.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblStaffId.setBounds(10, 83, 56, 14);
        panel.add(lblStaffId);
        
        comboBoxStaffId = new JComboBox<String>();
        comboBoxStaffId.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		comboBoxStaffIdItemStateChanged(e);
        	}
        });
        comboBoxStaffId.setFont(new Font("Tahoma", Font.PLAIN, 13));
        comboBoxStaffId.setModel(new DefaultComboBoxModel<String>(new String[] {"All Staff"}));
        comboBoxStaffId.setBounds(70, 78, 130, 25);
        panel.add(comboBoxStaffId);
        
        lblServiceName = new JLabel("Service ID");
        lblServiceName.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblServiceName.setBounds(449, 27, 71, 14);
        panel.add(lblServiceName);
        
        comboBoxServiceId = new JComboBox<String>();
        comboBoxServiceId.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		comboBoxServiceIdItemStateChanged(e);
        	}
        });
        comboBoxServiceId.setFont(new Font("Tahoma", Font.PLAIN, 13));
        comboBoxServiceId.setModel(new DefaultComboBoxModel<String>(new String[] {"All Services"}));
        comboBoxServiceId.setBounds(530, 22, 130, 25);
        panel.add(comboBoxServiceId);
        
        lblStaffName = new JLabel("Staff Name");
        lblStaffName.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblStaffName.setBounds(210, 83, 71, 14);
        panel.add(lblStaffName);
        
        textFieldStaffName = new JTextField();
        textFieldStaffName.setEditable(false);
        textFieldStaffName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldStaffName.setBounds(291, 78, 130, 25);
        panel.add(textFieldStaffName);
        textFieldStaffName.setColumns(10);
        
        lblServiceName_1 = new JLabel("Service Name");
        lblServiceName_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblServiceName_1.setBounds(671, 27, 87, 14);
        panel.add(lblServiceName_1);
        
        textFieldServiceName = new JTextField();
        textFieldServiceName.setEditable(false);
        textFieldServiceName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldServiceName.setColumns(10);
        textFieldServiceName.setBounds(769, 22, 130, 25);
        panel.add(textFieldServiceName);
        
        lblApartmentId = new JLabel("Apartment ID");
        lblApartmentId.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblApartmentId.setBounds(449, 83, 99, 14);
        panel.add(lblApartmentId);
        
        comboBoxApartmentId = new JComboBox<String>();
        comboBoxApartmentId.setModel(new DefaultComboBoxModel<String>(new String[] {"All Apartments"}));
        comboBoxApartmentId.setFont(new Font("Tahoma", Font.PLAIN, 13));
        comboBoxApartmentId.setBounds(546, 78, 130, 25);
        panel.add(comboBoxApartmentId);
        
        btnLoadInvoice = new JButton("Load");
        btnLoadInvoice.setForeground(new Color(255, 255, 255));
        btnLoadInvoice.setFont(new Font("Tahoma", Font.BOLD, 15));
        ButtonUtils.setupNormalButton(btnLoadInvoice, new Color(131, 209, 51),new Color(159, 216, 96));
        btnLoadInvoice.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnLoadInvoiceActionPerformed(e);
        	}
        });
        btnLoadInvoice.setBounds(700, 78, 90, 30);
        panel.add(btnLoadInvoice);
        
        btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnClearActionPerformed(e);
        	}
        });
        btnClear.setForeground(Color.WHITE);
        btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
        ButtonUtils.setupNormalButton(btnClear, new Color(240, 110, 50),new Color(255, 128, 64));
        btnClear.setBounds(809, 78, 90, 30);
        panel.add(btnClear);
        
        btnFirst = new JButton("First");
        btnFirst.setBounds(223, 560, 80, 30);
        btnFirst.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnFirstActionPerformed(e);
        	}
        });
        btnFirst.setMnemonic('F');
        ButtonUtils.setupNormalButton(btnFirst, new Color(0, 64, 128), new Color(28, 71, 173));
        serviceInvoiceTab.add(btnFirst);
        
        btnPrevious = new JButton("Prev");
        btnPrevious.setBounds(323, 560, 80, 30);
        btnPrevious.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnPreviousActionPerformed(e);
        	}
        });
        btnPrevious.setMnemonic('F');
        ButtonUtils.setupNormalButton(btnPrevious, new Color(0, 64, 128), new Color(28, 71, 173));
        serviceInvoiceTab.add(btnPrevious);
        
        comboBoxNumOfPage = new JComboBox<String>();
        comboBoxNumOfPage.setBounds(423, 560, 80, 30);
        comboBoxNumOfPage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		comboBoxNumOfPageActionPerformed(e);
        	}
        });
        comboBoxNumOfPage.setModel(new DefaultComboBoxModel<String>(new String[] {"10", "15", "20", "25", "30", "35", "40", "45", "50"}));
        serviceInvoiceTab.add(comboBoxNumOfPage);
        rowsOfPage = Integer.parseInt(comboBoxNumOfPage.getSelectedItem().toString());
        
        btnNext = new JButton("Next");
        btnNext.setBounds(523, 560, 80, 30);
        btnNext.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnNextActionPerformed(e);
        	}
        });
        btnNext.setMnemonic('F');
        ButtonUtils.setupNormalButton(btnNext, new Color(0, 64, 128), new Color(28, 71, 173));
        serviceInvoiceTab.add(btnNext);
        
        btnLast = new JButton("Last");
        btnLast.setBounds(623, 560, 80, 30);
        btnLast.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnLastActionPerformed(e);
        	}
        });
        btnLast.setMnemonic('F');
        ButtonUtils.setupNormalButton(btnLast, new Color(0, 64, 128), new Color(28, 71, 173));
        serviceInvoiceTab.add(btnLast);
        
        lblStatus = new JLabel("Page: 1 of 0");
        lblStatus.setBounds(223, 600, 117, 26);
        lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblStatus.setHorizontalAlignment(SwingConstants.LEFT);
        serviceInvoiceTab.add(lblStatus);
        
        textFieldPageNumber = new JTextField();
        textFieldPageNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textFieldPageNumber.setBounds(443, 600, 40, 20);
        textFieldPageNumber.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textFieldPageNumberActionPerformed(e);
        	}
        });
        textFieldPageNumber.setText("1");
        textFieldPageNumber.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldPageNumber.setColumns(10);
        textFieldPageNumber.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 128, 0)));
        serviceInvoiceTab.add(textFieldPageNumber);
        
        lblTotal = new JLabel("Total: 0");
        lblTotal.setBounds(553, 600, 150, 26);
        lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        serviceInvoiceTab.add(lblTotal);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 159, 909, 390);
        scrollPane.setBackground(new Color(255, 255, 255));
        serviceInvoiceTab.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        
        loadData(); 
        
    }

    public static StatisticsManage getInstance() {
        if (instance == null) {
            instance = new StatisticsManage();
        }
        return instance;
    }
    
    private CategoryDataset createDataset() {
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int selectedYear = Integer.parseInt(comboBoxYear.getSelectedItem().toString());

        List<String> selectedMonths = new ArrayList<>();
        for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                selectedMonths.add(checkBox.getText());
            }
        }

        for (String selectedMonth : selectedMonths) {
            int monthNumber = getMonthNumber(selectedMonth);
            List<DataChart> occupancyData = getOccupancyRatioByYearMonth(selectedYear, monthNumber);
            for (DataChart data : occupancyData) {
                String formattedDate = formatDate((Date) data.getRecordDate());
                dataset.addValue(data.getOccupancyRatio(), selectedMonth, formattedDate);
            }
        }

        return dataset;
    }
    
    private List<DataChart> getOccupancyRatioByYearMonth(int selectedYear, int monthNumber){
    	DataChartDAO chartDAO = new DataChartDAO();;
    	return chartDAO.getOccupancyRatioByYearMonth(selectedYear, monthNumber);
    }
    
    private int getMonthNumber(String monthName) {
        switch (monthName.toLowerCase()) {
            case "january": return 1;
            case "february": return 2;
            case "march": return 3;
            case "april": return 4;
            case "may": return 5;
            case "june": return 6;
            case "july": return 7;
            case "august": return 8;
            case "september": return 9;
            case "october": return 10;
            case "november": return 11;
            case "december": return 12;
            default: return 0;
        }
    }
    
    private String formatDate(Date date) {
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        return dayFormat.format(date);
    }

    
    
    private void createAndDrawChart() {
    	Component[] components = apartmentTab.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                Component[] subComponents = panel.getComponents();
                for (Component subComponent : subComponents) {
                    if (subComponent instanceof ChartPanel) {
                        panel.remove(subComponent);
                        break;
                    }
                }
            }
        }
        
        CategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createLineChart(
                "Apartment Occupancy Rate Chart",
                "Days Axis",
                "Fill Rate Axis (%)",
                dataset
        );

        JPanel chartPanelContainer = new JPanel();
        chartPanelContainer.setLayout(null);
        chartPanelContainer.setBackground(new Color(255, 255, 255));
        chartPanelContainer.setBounds(0, 141, 929, 491);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(0, 0, 929, 490);
        chartPanel.setPreferredSize(new Dimension(560, 370));
        chartPanelContainer.add(chartPanel);
        chartPanel.setLayout(null);
        apartmentTab.add(chartPanelContainer);

    }

    
    public void loadData() {
    	fillStaffIds();
    	fillApartmentIds();
    	fillServiceIds();
    }
    
	private void fillStaffIds() {
        StaffDAO staffDAO = new StaffDAO();
        List<String> staffIds = staffDAO.getAllStaffIds();
        comboBoxStaffId.removeAllItems();
        comboBoxStaffId.addItem("All Staffs");
        for (String staffId : staffIds) {
            comboBoxStaffId.addItem(staffId);
        }
    }
    
    protected void comboBoxStaffIdItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			if(comboBoxStaffId.getSelectedIndex() != 0) {
				String selectedStaffId = comboBoxStaffId.getSelectedItem().toString();
	            displayStaffName(selectedStaffId);
			} else {
				textFieldStaffName.setText("");
			}
			
        }
	}
	
	private void displayStaffName(String staffId) {
	    StaffDAO staffDAO = new StaffDAO();
	    String staffName = staffDAO.getStaffNameById(staffId);
	    textFieldStaffName.setText(staffName);
	}
    
	private void fillApartmentIds() {
    	ApartmentDAO apartmentDAO = new ApartmentDAO();
        List<String> apartmentIds = apartmentDAO.getAllApartmentIds();
        comboBoxApartmentId.removeAllItems();
        comboBoxApartmentId.addItem("All Apartments");
        for (String apartmentId : apartmentIds) {
        	comboBoxApartmentId.addItem(apartmentId);
        }
    }
	
	private void fillServiceIds() {
		ServiceDAO serviceDAO = new ServiceDAO();
		List<Integer> serviceIds = serviceDAO.getAllServiceIds();
        comboBoxServiceId.removeAllItems();
        comboBoxServiceId.addItem("All Services");
        for (Integer serviceId : serviceIds) {
        	comboBoxServiceId.addItem(serviceId.toString());
        }
    }
	
	protected void comboBoxServiceIdItemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			if(comboBoxServiceId.getSelectedIndex() != 0) {
				int selectedServiceId = Integer.parseInt(comboBoxServiceId.getSelectedItem().toString());
	            displayServiceInfo(selectedServiceId);
			}
			else {
				textFieldServiceName.setText("");
			}
        }
	}
	
	private void displayServiceInfo(int serviceId) {
		ServiceDAO serviceDAO = new ServiceDAO();
	    Service service = serviceDAO.getServiceById(serviceId);
	    textFieldServiceName.setText(service.getName());	    
	}
	
	public void loadServiceInvoice() {
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Invoice ID");
		model.addColumn("Invoice Name");
		model.addColumn("Staff ID");
		model.addColumn("Apartment ID");
		model.addColumn("Printing Date");
		model.addColumn("Payment Date");
		model.addColumn("Note");
		model.addColumn("Service ID");
		model.addColumn("Service Name");
		model.addColumn("Quantity");
		model.addColumn("Price");
		model.addColumn("Total");
		model.addColumn("Status");
		
		ServiceDAO serviceDAO = new ServiceDAO();
		ServiceInvoiceDAO serviceInvoiceDAO = new ServiceInvoiceDAO();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fromDate = null;
		Date toDate = null;
		
		if (dateChooserFrom.getDate() != null) {
		    fromDate = new java.sql.Date(dateChooserFrom.getDate().getTime());
		}

		if (dateChooserTo.getDate() != null) {
		    toDate = new java.sql.Date(dateChooserTo.getDate().getTime());
		}
		
		if (fromDate != null && toDate != null && fromDate.after(toDate)) {
	        ShowMessage.showErrorMessage("Error", "From Date must be before To Date.");
	        return;
	    }
		
		String selectedStaffId = (comboBoxStaffId.getSelectedIndex() != 0) ? comboBoxStaffId.getSelectedItem().toString() : null;
		String selectedServiceIdStr = (comboBoxServiceId.getSelectedIndex() != 0) ? comboBoxServiceId.getSelectedItem().toString() : null;
		int selectedServiceId = (selectedServiceIdStr != null) ? Integer.parseInt(selectedServiceIdStr) : -1;
	    String selectedApartmentId = (comboBoxApartmentId.getSelectedIndex() != 0) ? comboBoxApartmentId.getSelectedItem().toString() : null;
	    
	    totalRows = serviceInvoiceDAO.countServiceInvoice(selectedServiceId, selectedApartmentId, selectedStaffId, fromDate, toDate);
		totalPages = Math.ceil(totalRows.doubleValue()/rowsOfPage.doubleValue());
		
		lblStatus.setText("Page: " + pageNumber + " of " + totalPages.intValue());
		lblTotal.setText("Total Invoice " + totalRows);
		
		serviceInvoiceDAO.selectServiceInvoice(pageNumber, rowsOfPage, selectedServiceId, selectedApartmentId, selectedStaffId, fromDate, toDate).stream().forEach(serviceInvoice -> {
			String formattedPrintingDate = serviceInvoice.getPrintingDate() != null ? dateFormat.format(serviceInvoice.getPrintingDate()) : "N/A";
		    String formattedPaymentDate = serviceInvoice.getPaymentDate() != null ? dateFormat.format(serviceInvoice.getPaymentDate()) : "N/A";
		    model.addRow(new Object[] {
		            serviceInvoice.getId(),
		            serviceInvoice.getName(),
		            serviceInvoice.getStaffId(),
		            serviceInvoice.getApartmentId(),
		            formattedPrintingDate,
		            formattedPaymentDate,
		            serviceInvoice.getNotes(),
		            serviceInvoice.getServiceId(),
		            serviceDAO.getServiceNameById(serviceInvoice.getServiceId()),
		            serviceInvoice.getQuantity(),
		            serviceInvoice.getPrice(),
		            serviceInvoice.getQuantity() * serviceInvoice.getPrice(),
		            serviceInvoice.isStatus() ? "Paid" : "Unpaid"
		    });
		});
		
		table.setModel(model);
		table.setRowHeight(30);
	}
	
	public void refresh() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		ServiceDAO serviceDAO = new ServiceDAO();
		ServiceInvoiceDAO serviceInvoiceDAO = new ServiceInvoiceDAO();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		Date fromDate = null;
		Date toDate = null;
		
		if (dateChooserFrom.getDate() != null) {
		    fromDate = new java.sql.Date(dateChooserFrom.getDate().getTime());
		}

		if (dateChooserTo.getDate() != null) {
		    toDate = new java.sql.Date(dateChooserTo.getDate().getTime());
		}
		
		String selectedStaffId = (comboBoxStaffId.getSelectedIndex() != 0) ? comboBoxStaffId.getSelectedItem().toString() : null;
		String selectedServiceIdStr = (comboBoxServiceId.getSelectedIndex() != 0) ? comboBoxServiceId.getSelectedItem().toString() : null;
		int selectedServiceId = (selectedServiceIdStr != null) ? Integer.parseInt(selectedServiceIdStr) : -1;
	    String selectedApartmentId = (comboBoxApartmentId.getSelectedIndex() != 0) ? comboBoxApartmentId.getSelectedItem().toString() : null;
	    
	    totalRows = serviceInvoiceDAO.countServiceInvoice(selectedServiceId, selectedApartmentId, selectedStaffId, fromDate, toDate);
		totalPages = Math.ceil(totalRows.doubleValue()/rowsOfPage.doubleValue());
		
		lblStatus.setText("Page: " + pageNumber + " of " + totalPages.intValue());
		lblTotal.setText("Total Invoice " + totalRows);
	    
		serviceInvoiceDAO.selectServiceInvoice(pageNumber, rowsOfPage, selectedServiceId, selectedApartmentId, selectedStaffId, fromDate, toDate).stream().forEach(serviceInvoice -> {
			String formattedPrintingDate = serviceInvoice.getPrintingDate() != null ? dateFormat.format(serviceInvoice.getPrintingDate()) : "N/A";
		    String formattedPaymentDate = serviceInvoice.getPaymentDate() != null ? dateFormat.format(serviceInvoice.getPaymentDate()) : "N/A";
		    model.addRow(new Object[] {
		            serviceInvoice.getId(),
		            serviceInvoice.getName(),
		            serviceInvoice.getStaffId(),
		            serviceInvoice.getApartmentId(),
		            formattedPrintingDate,
		            formattedPaymentDate,
		            serviceInvoice.getNotes(),
		            serviceInvoice.getServiceId(),
		            serviceDAO.getServiceNameById(serviceInvoice.getServiceId()),
		            serviceInvoice.getQuantity(),
		            serviceInvoice.getPrice(),
		            serviceInvoice.getQuantity() * serviceInvoice.getPrice(),
		            serviceInvoice.isStatus() ? "Paid" : "Unpaid"
		    });
		});
	}
	protected void btnFirstActionPerformed(ActionEvent e) {
		pageNumber = 1;
		textFieldPageNumber.setText(pageNumber.toString());
		refresh();
	}
	protected void btnPreviousActionPerformed(ActionEvent e) {
		if(pageNumber > 1) {
			pageNumber--;
			textFieldPageNumber.setText(pageNumber.toString());
			refresh();
		}
	}
	protected void btnNextActionPerformed(ActionEvent e) {
		if(pageNumber < totalPages.intValue()) {
			pageNumber++;
			textFieldPageNumber.setText(pageNumber.toString());
			refresh();
		}
	}
	protected void btnLastActionPerformed(ActionEvent e) {
		pageNumber = totalPages.intValue();
		textFieldPageNumber.setText(pageNumber.toString());
		refresh();
	}
	protected void comboBoxNumOfPageActionPerformed(ActionEvent e) {
		if(table!=null) {
			pageNumber = 1;
			textFieldPageNumber.setText(pageNumber.toString());
			rowsOfPage = Integer.parseInt(comboBoxNumOfPage.getSelectedItem().toString());
			refresh();
		}
	}
	protected void textFieldPageNumberActionPerformed(ActionEvent e) {
		int page = Integer.parseInt(textFieldPageNumber.getText()) ;
		if(page >= 1 && page <= totalPages.intValue()) {
			pageNumber = page;
			refresh();
		} else {
			JOptionPane.showMessageDialog(null, "Page must be 1 to " + totalPages.intValue());
			textFieldPageNumber.setText(pageNumber.toString());
		}
	}
	protected void btnLoadInvoiceActionPerformed(ActionEvent e) {
		loadServiceInvoice();
	}
	protected void btnClearActionPerformed(ActionEvent e) {

	    dateChooserFrom.setDate(null);
	    dateChooserTo.setDate(null);

	    comboBoxStaffId.setSelectedIndex(0);
	    comboBoxServiceId.setSelectedIndex(0);
	    comboBoxApartmentId.setSelectedIndex(0);

	    pageNumber = 1;
	    textFieldPageNumber.setText("1");
	    comboBoxNumOfPage.setSelectedIndex(0);

	    loadServiceInvoice();
	}
	protected void btnCheckAllActionPerformed(ActionEvent e) {
		Component[] components = panelMonth.getComponents();
	    
	    for (Component component : components) {
	        if (component instanceof JCheckBox) {
	            JCheckBox checkBox = (JCheckBox) component;
	            checkBox.setSelected(true);
	        }
	    }
	}
	protected void btnClearAllActionPerformed(ActionEvent e) {
		Component[] components = panelMonth.getComponents();
	    
	    for (Component component : components) {
	        if (component instanceof JCheckBox) {
	            JCheckBox checkBox = (JCheckBox) component;
	            checkBox.setSelected(false);
	        }
	    }
	}
	protected void btnDrawChartActionPerformed(ActionEvent e) {
		createAndDrawChart();
		 revalidate();
	     repaint();
	}
}
