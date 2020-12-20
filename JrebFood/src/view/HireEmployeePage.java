package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.EmployeeController;
import view.core.View;

public class HireEmployeePage extends View{
	
	JPanel btnPanel, titlePanel, formPanel, typePanel;
	JLabel titleLabel, emailLabel, typeLabel, passLabel, nameLabel, addressLabel, phoneLabel, roleLabel, dobLabel;
	JTextField emailTxt, nameTxt, addressTxt, phoneTxt, dobTxt;
	JButton btnBack, btnHire;
	JPasswordField password;
	
	private JRadioButton driver, chef;
	private ButtonGroup type;

	public HireEmployeePage() {
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
		
		titleLabel = new JLabel("Hire Form");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		formPanel = new JPanel();
		formPanel.setBorder(new EmptyBorder(50, 50, 10, 50));
		formPanel.setLayout(new GridLayout(7, 2, 0, 25));
		formPanel.setBackground(Color.ORANGE);
		
		emailLabel = new JLabel();
		emailLabel.setText("Email");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailTxt = new JTextField();
		
		passLabel = new JLabel("Password");
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		password = new JPasswordField();
		
		nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameTxt = new JTextField();
		
		addressLabel = new JLabel("Address");
		addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addressTxt = new JTextField();
		
		phoneLabel = new JLabel("Phone");
		phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phoneTxt = new JTextField();
		
		roleLabel = new JLabel("Role");
		roleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		dobLabel = new JLabel("Date of Birth");
		dobLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dobTxt = new JTextField();
		
		typeLabel = new JLabel("Position");
		
		driver = new JRadioButton("Driver");
		driver.setBackground(Color.ORANGE);
		driver.setActionCommand(driver.getText());
		chef = new JRadioButton("Chef");
		chef.setBackground(Color.ORANGE);
		chef.setActionCommand(chef.getText());
		
		typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		typePanel.setBackground(Color.ORANGE);
		type = new ButtonGroup();
		
		btnPanel = new JPanel();
		btnPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
		btnPanel.setBackground(Color.ORANGE);
		
		btnBack = new JButton("Back");
		btnHire = new JButton("Hire");
	}

	@Override
	public void addComponent() {
		titlePanel.add(titleLabel);
		add(titlePanel, BorderLayout.NORTH);
		
		formPanel.add(emailLabel);
		formPanel.add(emailTxt);
		formPanel.add(passLabel);
		formPanel.add(password);
		formPanel.add(nameLabel);
		formPanel.add(nameTxt);
		formPanel.add(addressLabel);
		formPanel.add(addressTxt);
		formPanel.add(dobLabel);
		formPanel.add(dobTxt);
		formPanel.add(phoneLabel);
		formPanel.add(phoneTxt);
		
		typePanel.add(driver);
		typePanel.add(chef);
		
		type.add(driver);
		type.add(chef);
		
		formPanel.add(roleLabel);
		formPanel.add(typePanel);
		add(formPanel, BorderLayout.CENTER);
		
		btnPanel.add(btnHire);
		btnPanel.add(btnBack);
		add(btnPanel, BorderLayout.SOUTH);
		
	}
	
	private void showConfirmation(ActionEvent e) {
		int confirmation = JOptionPane.showConfirmDialog(this, "Hire this employee ?");
		
		switch(confirmation) {
		case JOptionPane.YES_OPTION:
			EmployeeController employeeController = EmployeeController.getInstance();
			
			Integer roleId = 0;
			
			if(type.getSelection().getActionCommand().equals("Driver")) {
				roleId = 1;
			}
			else if(type.getSelection().getActionCommand().equals("Chef")) {
				roleId = 2;
			}
			employeeController.addEmployee(roleId, nameTxt.getText(), dobTxt.getText(), emailTxt.getText(), new String(password.getPassword()));
			JOptionPane.showMessageDialog(this, "Employee hired");
			dispose();
			new HomeManagerPage().showForm();
			break;
		}
	}

	@Override
	public void addListener() {
		
		btnHire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showConfirmation(e);
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomeManagerPage().showForm();
				
			}
		});
		
	}

}
