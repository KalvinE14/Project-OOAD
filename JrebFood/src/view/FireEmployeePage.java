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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeController;
import model.EmployeeModel;
import model.OrderModel;
import model.core.Model;
import view.core.View;

public class FireEmployeePage extends View{
	
	private JPanel titlePanel, orderPanel, btnPanel, contentPane, employeePanel, navPanel;
	private JLabel titleLabel;
	private JTable table;
	private JScrollPane scroll;
	private JScrollBar scrollBar;
	private JButton btnFire, btnHome;

	public FireEmployeePage() {
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
		
		titlePanel = new JPanel();
		titlePanel.setBorder(null);
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.setBorder(new EmptyBorder(70, 200, 20, 200));
		
		titleLabel = new JLabel("Employee List");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		orderPanel = new JPanel();
		orderPanel.setBackground(Color.ORANGE);
		
		scrollBar = new JScrollBar();
		
		table = new JTable();
		
		loadEmployee();
		
		scroll = new JScrollPane();
		scroll.setBackground(Color.ORANGE);
		scroll.setViewportView(table);
		scroll.setPreferredSize(new Dimension(380, 380));
		scroll.setVerticalScrollBar(scrollBar);
		scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setBorder(new EmptyBorder(10, 100, 20, 100));
		
		btnFire = new JButton("Fire Employee");
		
	}
	
	public void loadEmployee() {
		Vector<String> header = new Vector<>();
		header.add("Employee ID");
		header.add("Role ID");
		header.add("Name");
		header.add("Date of Birth");
		header.add("Email");
		
		EmployeeController employeeController = EmployeeController.getInstance();
		
		Vector<Vector<String>> employeeList = new Vector<>();
		Vector<String> employee = new Vector<>();
		
		Vector<Model> employeeModel = employeeController.getActiveEmployee();
		
		for (Model model : employeeModel) {
			EmployeeModel em = (EmployeeModel) model;
			employee = new Vector<>();
			employee.add(em.getEmployeeId().toString());
			employee.add(em.getRoleId().toString());
			employee.add(em.getName());
			employee.add(em.getDob());
			employee.add(em.getEmail());
			
			employeeList.add(employee);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(employeeList , header){
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
		btnPanel.add(btnFire);
		navPanel.add(btnHome);
		
		employeePanel.add(titlePanel, BorderLayout.NORTH);
		employeePanel.add(orderPanel, BorderLayout.CENTER);
		employeePanel.add(btnPanel, BorderLayout.SOUTH);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(employeePanel, BorderLayout.CENTER);
		
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
		
		btnFire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				EmployeeController employeeController = EmployeeController.getInstance();
				
				int row = table.getSelectedRow();
				Integer employeeId = Integer.parseInt(table.getValueAt(row, 0).toString());
				
				employeeController.deleteSelectedEmployee(employeeId);
				loadEmployee();
				
			}
		});
		
	}

}
