package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import controller.OrderDetailController;
import controller.UserController;
import model.OrderDetailModel;
import model.OrderModel;
import model.UserModel;
import model.core.Model;
import view.core.View;

public class DriverDetailOrderPage extends View{

	JPanel contentPane, navPanel, infoPanel, detailInfoPanel, btnPanel, custPanel, orderPanel, custInfoPanel;
	JTable custInfoTable, orderDetailTable;
	JLabel title, custLabel, orderDetailLabel, custNameLabel, custGenderLabel, custEmailLabel, custPhoneLabel, custAddressLabel;
	JButton orderBtn, homeBtn;
	JScrollPane custScrollPane, orderScrollPane;
	
	Vector<String> customerData;
	
	Vector<Vector<String>> orderDetailData;
	Vector<String> orderHeader, orderDetail;
	
	public DriverDetailOrderPage() {
		super();
		this.width = 500;
		this.height = 600;
	}
	
	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		initPanel();
		
		initOrderDetailTable();
		
		initLabel();
		
		initBtn();
	}
	
	@Override
	public void addComponent() {
		custInfoPanel.add(custNameLabel);
		custInfoPanel.add(custPhoneLabel);
		custInfoPanel.add(custEmailLabel);
		custInfoPanel.add(custAddressLabel);
		
		custPanel.add(custLabel);
		custPanel.add(custInfoPanel);
		
		orderPanel.add(orderDetailLabel);
		orderPanel.add(orderScrollPane);
		
		detailInfoPanel.add(custPanel);
		detailInfoPanel.add(orderPanel);
		
		btnPanel.add(orderBtn);
		
		navPanel.add(homeBtn);
		
		infoPanel.add(title, BorderLayout.NORTH);
		infoPanel.add(detailInfoPanel, BorderLayout.CENTER);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(infoPanel, BorderLayout.CENTER);
		
		loadOrderDetailData();
		
		loadUserData();
	}

	@Override
	public void addListener() {
		homeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomeDriverPage().showForm();
			}
		});
	}
	
	private void initPanel()
	{
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(0, 0, 20, 0));
		setContentPane(contentPane);
		
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navPanel.setBackground(Color.ORANGE);
		navPanel.setBorder(new EmptyBorder(0, 0, 30, 0));
		
		infoPanel = new JPanel(new BorderLayout());
		infoPanel.setBackground(Color.ORANGE);
		infoPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
		
		detailInfoPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		detailInfoPanel.setBackground(Color.ORANGE);
		detailInfoPanel.setBorder(new EmptyBorder(30, 0, 0, 0));
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
		
		custPanel = new JPanel(new GridLayout(2, 1, 0, -25));
		custPanel.setBackground(Color.ORANGE);
		custPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		orderPanel = new JPanel(new GridLayout(2, 1, 0, -25));
		orderPanel.setBackground(Color.ORANGE);
		orderPanel.setBorder(new EmptyBorder(20, 30, 0, 30));
		
		custInfoPanel = new JPanel(new GridLayout(5, 1, 0, 0));
		custInfoPanel.setBackground(Color.ORANGE);
	}


	private void initLabel() {
		title = new JLabel("Order Information");
		title.setBounds(10, 140, 50, 20);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Segoe UI", Font.BOLD, 24));
		
		custLabel = new JLabel("Customer Information");
		custLabel.setBounds(10, 140, 50, 20);
		custLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		custNameLabel = new JLabel();
		custNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		custAddressLabel = new JLabel();
		custAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custAddressLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		custPhoneLabel = new JLabel();
		custPhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custPhoneLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		custEmailLabel = new JLabel();
		custEmailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custEmailLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		orderDetailLabel = new JLabel("Order Detail");
		orderDetailLabel.setBounds(10, 140, 50, 20);
		orderDetailLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderDetailLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
	}
	
	private void initBtn() {
		orderBtn = new JButton("Order");
		orderBtn.setBounds(10, 140, 50, 20);
		orderBtn.setHorizontalAlignment(SwingConstants.CENTER);
		orderBtn.setFont(new Font("Segoe UI", Font.BOLD, 24));
		
		homeBtn = new JButton("Home");
		homeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
	}

	private void initOrderDetailTable()
	{
		orderDetailTable = new JTable();
		orderDetailTable.setBackground(Color.ORANGE);
		
		orderScrollPane = new JScrollPane();
		orderScrollPane.setViewportView(orderDetailTable);
		orderScrollPane.setBounds(0, 20, 490, 100);
		orderScrollPane.setBackground(Color.ORANGE);
	}
	
	private void loadOrderDetailData()
	{
		orderHeader = new Vector<>();
		orderHeader.add("Order ID");
		orderHeader.add("Food Name");
		orderHeader.add("Food Price");
		orderHeader.add("Quantity");
		
		OrderDetailController orderDetailController = OrderDetailController.getInstance();
		
		DefaultTableModel dtm = new DefaultTableModel(orderDetailController.getDetailByOrderId(), orderHeader){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		orderDetailTable.setModel(dtm);
	}
	
	private void loadUserData()
	{
		OrderDetailController orderDetailController = OrderDetailController.getInstance();
		
		UserModel userModel = orderDetailController.getUserByOrderId();
		
		custNameLabel.setText(userModel.getName());
		custAddressLabel.setText(userModel.getAddress());
		custPhoneLabel.setText(userModel.getPhoneNumber());
		custEmailLabel.setText(userModel.getEmail());
	}
}
