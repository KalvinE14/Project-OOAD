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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import view.core.View;

public class TakenOrderDetailPage extends View{

	JPanel contentPane, navPanel, takenDetailPanel, orderPanel, footerPanel;
	
	JLabel title, msg;
	JLabel orderIdValue, usernameValue, foodNameValue, orderTimeValue, orderStatusValue;
	JLabel orderIdLabel, usernameLabel, foodNameLabel, orderTimeLabel, orderStatusLabel;
	
	Vector<String> orderData;
	
	JButton confirmBtn, finishBtn, homeBtn;
	
	public TakenOrderDetailPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(0, 0, 50, 0));
		setContentPane(contentPane);
		
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navPanel.setBackground(Color.ORANGE);
		navPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
		
		takenDetailPanel = new JPanel(new BorderLayout());
		takenDetailPanel.setBackground(Color.ORANGE);
		
		orderPanel = new JPanel(new GridLayout(5, 2, -100, 0));
		orderPanel.setBackground(Color.ORANGE);
		orderPanel.setBorder(new EmptyBorder(30, 60, 50, 0));
		
		footerPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		footerPanel.setBackground(Color.ORANGE);
		footerPanel.setBorder(new EmptyBorder(0, 175, 0, 175));
		
		homeBtn = new JButton("Home");
		homeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		title = new JLabel("Order Detail");
		title.setBounds(10, 140, 50, 20);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Segoe UI", Font.BOLD, 24));
		
		orderIdLabel = new JLabel("Order ID : ");
		orderIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		usernameLabel = new JLabel("Username : ");
		usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		orderTimeLabel = new JLabel("Order Time : ");
		orderTimeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		orderStatusLabel = new JLabel("Status : ");
		orderStatusLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		foodNameLabel = new JLabel("Food Name : ");
		foodNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		getOrderData();
		
		confirmBtn = new JButton("Confirm");
		confirmBtn.setBounds(10, 140, 50, 20);
		confirmBtn.setHorizontalAlignment(SwingConstants.CENTER);
		confirmBtn.setFont(new Font("Segoe UI", Font.BOLD, 24));
		
		finishBtn = new JButton("Finish");
		finishBtn.setBounds(10, 140, 50, 20);
		finishBtn.setHorizontalAlignment(SwingConstants.CENTER);
		finishBtn.setFont(new Font("Segoe UI", Font.BOLD, 24));
		
		msg = new JLabel("Order finish!");
		msg.setHorizontalAlignment(SwingConstants.CENTER);
		msg.setFont(new Font("Segoe UI", Font.BOLD, 15));
		msg.setVisible(false);
	}

	@Override
	public void addComponent() {
		orderPanel.add(orderIdLabel);
		orderPanel.add(orderIdValue);
		orderPanel.add(usernameLabel);
		orderPanel.add(usernameValue);
		orderPanel.add(foodNameLabel);
		orderPanel.add(foodNameValue);
		orderPanel.add(orderTimeLabel);
		orderPanel.add(orderTimeValue);
		orderPanel.add(orderStatusLabel);
		orderPanel.add(orderStatusValue);
		
		footerPanel.add(confirmBtn);
		footerPanel.add(msg);
		
		takenDetailPanel.add(title, BorderLayout.NORTH);
		takenDetailPanel.add(orderPanel, BorderLayout.CENTER);
		takenDetailPanel.add(footerPanel, BorderLayout.SOUTH);
		
		navPanel.add(homeBtn);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(takenDetailPanel, BorderLayout.CENTER);
	}

	@Override
	public void addListener() {
		confirmBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				dispose();
//				new DriverOrderHistoryPage().showForm();
				msg.setVisible(true);
			}
		});
		
		finishBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				msg.setVisible(true);
			}
		});
		
		homeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new HomeDriverPage().showForm();
			}
		});
		
	}
	
	private void getOrderData()
	{
		orderIdValue = new JLabel("1");
		orderIdValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
		usernameValue = new JLabel("Jack");
		usernameValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
		orderTimeValue = new JLabel("Sunday, 11 December 2020");
		orderTimeValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
		orderStatusValue = new JLabel("Ordered");
		orderStatusValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
		foodNameValue = new JLabel("Nasi Kuning");
		foodNameValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
	}

}
