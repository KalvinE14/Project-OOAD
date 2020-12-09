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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import view.core.View;

public class DriverCurrentOrderPage extends View{

	JPanel contentPane, orderPanel, btnPanel;
	
	JLabel title;
	JLabel orderIdValue, usernameValue, orderTimeValue, orderStatusValue, orderReadyValue;
	JLabel orderIdLabel, usernameLabel, orderTimeLabel, orderStatusLabel, orderReadyLabel;
	
	Vector<String> orderData;
	
	JButton finalizeBtn;
	
	public DriverCurrentOrderPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(20, 0, 50, 0));
		setContentPane(contentPane);
		
		orderPanel = new JPanel(new GridLayout(5, 2, -100, 0));
		orderPanel.setBackground(Color.ORANGE);
		orderPanel.setBorder(new EmptyBorder(75, 60, 75, 0));
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		
		title = new JLabel("Current Order");
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
		orderReadyLabel = new JLabel("Ready : ");
		orderReadyLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		getOrderData();
		
		finalizeBtn = new JButton("Finalize");
		finalizeBtn.setBounds(10, 140, 50, 20);
		finalizeBtn.setHorizontalAlignment(SwingConstants.CENTER);
		finalizeBtn.setFont(new Font("Segoe UI", Font.BOLD, 24));
	}

	@Override
	public void addComponent() {
		orderPanel.add(orderIdLabel);
		orderPanel.add(orderIdValue);
		orderPanel.add(usernameLabel);
		orderPanel.add(usernameValue);
		orderPanel.add(orderTimeLabel);
		orderPanel.add(orderTimeValue);
		orderPanel.add(orderStatusLabel);
		orderPanel.add(orderStatusValue);
		orderPanel.add(orderReadyLabel);
		orderPanel.add(orderReadyValue);
		
		btnPanel.add(finalizeBtn);
		
		contentPane.add(title, BorderLayout.NORTH);
		contentPane.add(orderPanel, BorderLayout.CENTER);
		contentPane.add(btnPanel, BorderLayout.SOUTH);
	}

	@Override
	public void addListener() {
		finalizeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DriverOrderHistoryPage().showForm();
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
		orderReadyValue = new JLabel("No");
		orderReadyValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
	}

}
