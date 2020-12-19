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
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.UserController;
import view.core.View;

public class RegisterPage extends View{
	
	private JPanel titlePanel, formPanel, btnPanel, genderPanel;
	private JLabel titleLabel, emailLabel, passLabel, usernameLabel, addressLabel, phoneLabel, genderLabel;
	private JTextField emailTxt, usernameTxt, addressTxt, phoneTxt;
	private JPasswordField passTxt;
	private JRadioButton male, female;
	private ButtonGroup gender;
	private JButton btnRegister;

	public RegisterPage() {
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
		
		titleLabel = new JLabel("Register");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		formPanel = new JPanel(new GridLayout(5, 2, 0, 25));
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
	}

	@Override
	public void addComponent() {
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
	}

}
