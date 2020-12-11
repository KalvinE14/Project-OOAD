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

public class LoginUserPage extends View {
	JLabel txtLogin, txtEmail, txtPassword;
	JPanel titlePanel, formPanel, btnPanel, fieldPanel;
	JTextField emailValue;
	JPasswordField passwordValue;
	JButton btnSubmit;
	
	public LoginUserPage() {
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
		
		txtLogin = new JLabel();
		txtLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
		txtLogin.setText("Login as User");
		txtLogin.setBorder(new EmptyBorder(70, 0, 20, 0));
		
		fieldPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		fieldPanel.setBackground(Color.ORANGE);
		
		formPanel = new JPanel(new GridLayout(3, 2, 0, 30));
		formPanel.setBorder(new EmptyBorder(70, 70, 0, 90));
		formPanel.setBackground(Color.ORANGE);
		
		txtEmail = new JLabel();
		txtEmail.setText("Email");
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		emailValue = new JTextField();
		
		txtPassword = new JLabel();
		txtPassword.setText("Password");
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		passwordValue = new JPasswordField();
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		
		btnSubmit = new JButton();
		btnSubmit.setText("Login");
	}

	@Override
	public void addComponent() {
		titlePanel.add(txtLogin);
		btnPanel.add(btnSubmit);
		
		formPanel.add(txtEmail);
		formPanel.add(emailValue);
		formPanel.add(txtPassword);
		formPanel.add(passwordValue);
		formPanel.add(btnPanel);
		
		fieldPanel.add(formPanel);
		fieldPanel.add(btnPanel);
		
		add(titlePanel, BorderLayout.NORTH);
		add(fieldPanel, BorderLayout.CENTER);
	}

	@Override
	public void addListener() {
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomeUserPage().showForm();
			}
		});
		
	}

}
