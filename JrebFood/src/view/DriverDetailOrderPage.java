package view;

import java.awt.BorderLayout;
import java.awt.Color;
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

import view.core.View;

public class DriverDetailOrderPage extends View{

	JPanel contentPane, detailInfoPanel, btnPanel, custPanel, orderPanel, custTablePanel, ordTablePanel, custInfoPanel;
	JTable custInfoTable, orderDetailTable;
	JLabel title, custLabel, orderDetailLabel, custNameLabel, custGenderLabel, custEmailLabel, custPhoneLabel, custAddressLabel;
	JButton orderBtn;
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
		custInfoPanel.add(custGenderLabel);
		custInfoPanel.add(custPhoneLabel);
		custInfoPanel.add(custEmailLabel);
		custInfoPanel.add(custAddressLabel);
		
		ordTablePanel.add(orderScrollPane);
		
		custPanel.add(custLabel);
		custPanel.add(custInfoPanel);
		
		orderPanel.add(orderDetailLabel);
		orderPanel.add(ordTablePanel);
		
		detailInfoPanel.add(custPanel);
		detailInfoPanel.add(orderPanel);
		
		btnPanel.add(orderBtn);
		
		contentPane.add(title, BorderLayout.NORTH);
		contentPane.add(detailInfoPanel, BorderLayout.CENTER);
		contentPane.add(btnPanel, BorderLayout.SOUTH);
	}

	@Override
	public void addListener() {
		orderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DriverCurrentOrderPage().showForm();
			}
		});
	}
	
	private void initPanel()
	{
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(20, 0, 20, 0));
		setContentPane(contentPane);
		
		detailInfoPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		detailInfoPanel.setBackground(Color.ORANGE);
		detailInfoPanel.setBorder(new EmptyBorder(30, 0, 50, 0));
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
		
		custPanel = new JPanel(new GridLayout(2, 1, 0, -25));
		custPanel.setBackground(Color.ORANGE);
		custPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		orderPanel = new JPanel(new GridLayout(2, 1, 0, -25));
		orderPanel.setBackground(Color.ORANGE);
		orderPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
		
		custInfoPanel = new JPanel(new GridLayout(5, 1, 0, 0));
		custInfoPanel.setBackground(Color.ORANGE);
		
		ordTablePanel = new JPanel();
		ordTablePanel.setBorder(null);
		ordTablePanel.setBackground(Color.ORANGE);
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
		custNameLabel = new JLabel("Jack");
		custNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		custGenderLabel = new JLabel("Male");
		custGenderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custGenderLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		custAddressLabel = new JLabel("Bambu Street");
		custAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custAddressLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		custPhoneLabel = new JLabel("081222222222");
		custPhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		custPhoneLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		custEmailLabel = new JLabel("jack@gmail.com");
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
	}

	private void initOrderDetailTable()
	{
		orderDetailTable = new JTable();
		orderDetailTable.setBackground(Color.ORANGE);
		
		orderDetailData = new Vector<>();
		
		orderHeader = new Vector<>();
		orderHeader.add("Order ID");
		orderHeader.add("Food Name");
		orderHeader.add("Food Price");
		orderHeader.add("Quantity");
		
		orderDetail = new Vector<>();
		orderDetail.add("1");
		orderDetail.add("Fried Rice");
		orderDetail.add("Rp. 10000");
		orderDetail.add("2");
		
		orderDetailData.add(orderDetail);
		
		orderDetail = new Vector<>();
		orderDetail.add("2");
		orderDetail.add("Spaghetti");
		orderDetail.add("Rp. 20000");
		orderDetail.add("1");
		
		orderDetailData.add(orderDetail);
		
		DefaultTableModel dtmOrder = new DefaultTableModel(orderDetailData, orderHeader){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		orderDetailTable.setModel(dtmOrder);
		
		orderScrollPane = new JScrollPane();
		orderScrollPane.setViewportView(orderDetailTable);
		orderScrollPane.setBounds(0, 20, 490, 100);
		orderScrollPane.setBackground(Color.ORANGE);
	}
}
