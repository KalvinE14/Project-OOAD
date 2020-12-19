package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import view.core.View;

public class HomeChefPage extends View{
	
	JTextField titleTxt;
	JPanel btnPanel;
	JButton btnManage, btnOrder;
	

	public HomeChefPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		titleTxt = new JTextField();
		titleTxt.setBackground(Color.ORANGE);
		titleTxt.setHorizontalAlignment(SwingConstants.CENTER);
		titleTxt.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleTxt.setText("Chef Home");
		titleTxt.setBorder(null);
		titleTxt.setColumns(10);
		titleTxt.setEditable(false);
		titleTxt.setBorder(new EmptyBorder(70, 0, 0, 0));
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setLayout(new GridLayout(2, 1, 0, 35));
		btnPanel.setBorder(new EmptyBorder(190, 70, 150, 70));
		
		btnManage = new JButton("Manage Food");
		
		btnOrder = new JButton("View Order");
	}

	@Override
	public void addComponent() {
		btnPanel.add(btnManage);
		btnPanel.add(btnOrder);
		
		add(titleTxt, BorderLayout.NORTH);
		add(btnPanel, BorderLayout.CENTER);
		
	}

	@Override
	public void addListener() {
		btnManage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ManageFoodPage().showForm();
				
			}
		});
		
		btnOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OrderQueuePage().showForm();
				
			}
		});
		
	}

}
