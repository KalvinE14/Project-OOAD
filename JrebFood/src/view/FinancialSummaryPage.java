package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeController;
import view.core.View;

public class FinancialSummaryPage extends View{
	
	private JPanel titlePanel, orderPanel, totalIncomePanel, contentPane, employeePanel, navPanel, btnPanel;
	private JLabel titleLabel, totalLabel;
	private JTable table;
	private JScrollPane scroll;
	private JScrollBar scrollBar;
	private JButton btnHome, btnFilter, btnResetFilter;

	public FinancialSummaryPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.ORANGE);
		setContentPane(contentPane);
		
		employeePanel = new JPanel(new BorderLayout());
		employeePanel.setBackground(Color.ORANGE);
		
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navPanel.setBackground(Color.ORANGE);
		
		btnHome = new JButton("Home");
		btnFilter = new JButton("Filter");
		btnResetFilter = new JButton("Reset Filter");
		
		titlePanel = new JPanel();
		titlePanel.setBorder(null);
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.setBorder(new EmptyBorder(70, 200, 20, 200));
		
		titleLabel = new JLabel("Financial Summary");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		orderPanel = new JPanel();
		orderPanel.setBackground(Color.ORANGE);
		
		scrollBar = new JScrollBar();
		
		table = new JTable();
		
		loadFinancialSummary();
		
		scroll = new JScrollPane();
		scroll.setBackground(Color.ORANGE);
		scroll.setViewportView(table);
		scroll.setPreferredSize(new Dimension(380, 380));
		scroll.setVerticalScrollBar(scrollBar);
		scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		totalIncomePanel = new JPanel();
		totalIncomePanel.setBackground(Color.ORANGE);
		totalIncomePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		totalLabel = new JLabel();
		totalLabel.setText("Total Income: Rp. " + EmployeeController.getInstance().getTotalIncome().toString());
		totalLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		btnPanel = new JPanel(new GridLayout(2, 1, 10, -26));
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setBorder(new EmptyBorder(0, 55, 20, 55));
		
	}

	private void loadFinancialSummary() {
		Vector<String> header = new Vector<>();
		header.add("Employee ID");
		header.add("Employee Name");
		header.add("Total Order Complete");
		header.add("Total Order Price");
		
		DefaultTableModel dtm = new DefaultTableModel(EmployeeController.getInstance().getFinancialSummary() , header){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		table.setModel(dtm);
	}
	
	private void loadSpecificFinancialSummary(Integer employeeId) {
		Vector<String> header = new Vector<>();
		header.add("Employee ID");
		header.add("Employee Name");
		header.add("Total Order Complete");
		header.add("Total Order Price");
		
		DefaultTableModel dtm = new DefaultTableModel(EmployeeController.getInstance().getSpecificFinancialSummary(employeeId) , header){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		table.setModel(dtm);
	}

	@Override
	public void addComponent() {
		titlePanel.add(titleLabel);
		orderPanel.add(scroll);
		navPanel.add(btnHome);
		
		totalIncomePanel.add(totalLabel);
		
		employeePanel.add(titlePanel, BorderLayout.NORTH);
		employeePanel.add(orderPanel, BorderLayout.CENTER);
		employeePanel.add(totalIncomePanel, BorderLayout.SOUTH);
		
		btnPanel.add(btnFilter);
		btnPanel.add(btnResetFilter);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(employeePanel, BorderLayout.CENTER);
		contentPane.add(btnPanel, BorderLayout.SOUTH);
	}

	@Override
	public void addListener() {
		btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomeManagerPage().showForm();
					
			}
		});
		
		btnFilter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				Integer employeeId = Integer.parseInt(table.getValueAt(row, 0).toString());
				loadSpecificFinancialSummary(employeeId);
				btnFilter.setVisible(false);
			}
		});
		
		btnResetFilter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadFinancialSummary();
				btnFilter.setVisible(true);
			}
		});
	}

}
