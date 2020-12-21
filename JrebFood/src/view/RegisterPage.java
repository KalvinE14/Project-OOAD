package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import view.core.View;

public class RegisterPage extends View{
	
	private JPanel titlePanel, formPanel, btnPanel, navPanel;
	private JLabel titleLabel, emailLabel, passLabel, usernameLabel, addressLabel, phoneLabel;
	private JTextField emailTxt, usernameTxt, addressTxt, phoneTxt;
	private JPasswordField passTxt;
	private JButton btnRegister, btnHome;

	public RegisterPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navPanel.setBackground(Color.ORANGE);
		navPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		
		titlePanel = new JPanel(new GridLayout(2, 1, 0, 0));
		titlePanel.setBorder(null);
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.setBorder(new EmptyBorder(0, 0, 20, 100));
		
		titleLabel = new JLabel("Register");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		titleLabel.setBorder(new EmptyBorder(20, 200, 0, 0));
		
		formPanel = new JPanel(new GridLayout(5, 2, 0, 40));
		formPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
		formPanel.setBackground(Color.ORANGE);
		
		emailLabel = new JLabel("Email");
		passLabel = new JLabel("Password");
		usernameLabel = new JLabel("Name");
		addressLabel = new JLabel("Address");
		phoneLabel = new JLabel("Phone");
		
		emailTxt = new JTextField();
		passTxt = new JPasswordField();
		usernameTxt = new JTextField();
		addressTxt = new JTextField();
		phoneTxt = new JTextField();
		
		btnPanel = new JPanel();
		btnPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
		btnPanel.setBackground(Color.ORANGE);
		btnRegister = new JButton("Register");
		
		btnHome = new JButton("Home");
	}

	@Override
	public void addComponent() {
		navPanel.add(btnHome);
		titlePanel.add(navPanel);
		titlePanel.add(titleLabel);
		add(titlePanel, BorderLayout.NORTH);
		
		formPanel.add(emailLabel);
		formPanel.add(emailTxt);
		formPanel.add(passLabel);
		formPanel.add(passTxt);
		formPanel.add(usernameLabel);
		formPanel.add(usernameTxt);
		formPanel.add(addressLabel);
		formPanel.add(addressTxt);
		formPanel.add(phoneLabel);
		formPanel.add(phoneTxt);
		
		add(formPanel, BorderLayout.CENTER);
		
		btnPanel.add(btnRegister);
		add(btnPanel, BorderLayout.SOUTH);
	}

	@Override
	public void addListener() {
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				UserController userController = UserController.getInstance();
				userController.validateRegistration(emailTxt.getText(), new String(passTxt.getPassword()), usernameTxt.getText(), addressTxt.getText(), phoneTxt.getText());
			}
		});
		
		btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new InitialPage().showForm();
			}
		});
	}

}
