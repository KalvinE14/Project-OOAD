package view;

import view.core.View;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class InitialPage extends View {
	JTextField txtJrebfood;
	JPanel btnPanel;
	JButton btnLogin, btnRegister;
	
	public InitialPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		txtJrebfood = new JTextField();
		txtJrebfood.setBackground(Color.ORANGE);
		txtJrebfood.setHorizontalAlignment(SwingConstants.CENTER);
		txtJrebfood.setFont(new Font("Segoe UI", Font.BOLD, 24));
		txtJrebfood.setText("JrebFood");
		txtJrebfood.setBorder(null);
		txtJrebfood.setColumns(10);
		txtJrebfood.setEditable(false);
		txtJrebfood.setBorder(new EmptyBorder(70, 0, 0, 0));
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setLayout(new GridLayout(2, 1, 0, 35));
		btnPanel.setBorder(new EmptyBorder(190, 70, 150, 70));
		
		btnLogin = new JButton("Login");
		
		btnRegister = new JButton("Register");
	}

	@Override
	public void addComponent() {
		btnPanel.add(btnLogin);
		btnPanel.add(btnRegister);
		
		add(txtJrebfood, BorderLayout.NORTH);
		add(btnPanel, BorderLayout.CENTER);
	}

	@Override
	public void addListener() {
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginPage().showForm();
			}
		});
		
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RegisterPage().showForm();
			}
		});
	}

}
