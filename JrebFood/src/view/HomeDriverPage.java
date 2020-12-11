package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import view.core.View;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeDriverPage extends View {
	JPanel contentPane, homeDriverPanel, btnPanel;
	JLabel titleLabel;
	JButton toOrderHistoryBtn, toOrderListBtn, toAvailableListBtn;
	
	public HomeDriverPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		setContentPane(contentPane);
		
		homeDriverPanel = new JPanel(new BorderLayout());
		homeDriverPanel.setBackground(Color.ORANGE);
		homeDriverPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
		
		btnPanel = new JPanel(new GridLayout(3, 1, 0, 50));
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setBorder(new EmptyBorder(100, 120, 0, 120));
		
		titleLabel = new JLabel("Home");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		toAvailableListBtn = new JButton("Available Order");
		toAvailableListBtn.setHorizontalAlignment(SwingConstants.CENTER);
		toAvailableListBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
		toOrderListBtn = new JButton("Order List");
		toOrderListBtn.setHorizontalAlignment(SwingConstants.CENTER);
		toOrderListBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
		toOrderHistoryBtn = new JButton("Order History");
		toOrderHistoryBtn.setHorizontalAlignment(SwingConstants.CENTER);
		toOrderHistoryBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
	}

	@Override
	public void addComponent() {
		btnPanel.add(toAvailableListBtn);
		btnPanel.add(toOrderListBtn);
		btnPanel.add(toOrderHistoryBtn);
		
		homeDriverPanel.add(titleLabel, BorderLayout.NORTH);
		homeDriverPanel.add(btnPanel, BorderLayout.CENTER);
		
		contentPane.add(homeDriverPanel);
	}

	@Override
	public void addListener() {
		toAvailableListBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AvailableOrderPage().showForm();
			}
		});
		
		toOrderListBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TakenOrderPage().showForm();
			}
		});
		toOrderHistoryBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DriverOrderHistoryPage().showForm();
			}
		});
	
	}

}
