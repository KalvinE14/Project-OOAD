package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import view.core.View;

public class FireEmployeePage extends View{
	
	JPanel btnPanel, titlePanel, formPanel;
	JLabel titleLabel, emailLabel;
	JTextField emailTxt;
	JButton btnBack, btnFire;

	public FireEmployeePage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		titlePanel = new JPanel();
		titlePanel.setBorder(null);
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.setBorder(new EmptyBorder(70, 200, 20, 200));
		
		titleLabel = new JLabel("Fire Form");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		formPanel = new JPanel();
		formPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		formPanel.setLayout(new GridLayout(6, 2, 0, 25));
		formPanel.setBackground(Color.ORANGE);
		
		emailLabel = new JLabel();
		emailLabel.setText("Email Employee");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailTxt = new JTextField();
		
		btnPanel = new JPanel();
		btnPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
		btnPanel.setBackground(Color.ORANGE);
		
		btnBack = new JButton("Back");
		btnFire = new JButton("Fire");
		
	}

	@Override
	public void addComponent() {
		titlePanel.add(titleLabel);
		add(titlePanel, BorderLayout.NORTH);
		
		formPanel.add(emailLabel);
		formPanel.add(emailTxt);

		add(formPanel, BorderLayout.CENTER);
		
		btnPanel.add(btnFire);
		btnPanel.add(btnBack);
		add(btnPanel, BorderLayout.SOUTH);
		
	}

	@Override
	public void addListener() {
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomeManagerPage().showForm();
				
			}
		});
		
	}

}
