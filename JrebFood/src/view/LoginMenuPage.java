package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

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

public class LoginMenuPage extends View {
	JPanel contentPane, loginMenuPanel, loginBtnPanel, navPanel;
	JLabel titleLabel;
	JButton loginUserBtn, loginEmployeeBtn, btnHome;
	
	public LoginMenuPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.ORANGE);
		setContentPane(contentPane);
		
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navPanel.setBackground(Color.ORANGE);
		
		loginMenuPanel = new JPanel(new BorderLayout());
		loginMenuPanel.setBackground(Color.ORANGE);
		loginMenuPanel.setBorder(new EmptyBorder(50, 0, 180, 0));
		
		loginBtnPanel = new JPanel(new GridLayout(2, 1, 0, 50));
		loginBtnPanel.setBackground(Color.ORANGE);
		loginBtnPanel.setBorder(new EmptyBorder(150, 120, 0, 120));
		
		titleLabel = new JLabel("Login Menu");
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnHome = new JButton("Home");
		
		loginUserBtn = new JButton("Login as User");
		loginUserBtn.setHorizontalAlignment(SwingConstants.CENTER);
		loginUserBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
		loginEmployeeBtn = new JButton("Login as Employee");
		loginEmployeeBtn.setHorizontalAlignment(SwingConstants.CENTER);
		loginEmployeeBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
	}

	@Override
	public void addComponent() {
		loginBtnPanel.add(loginUserBtn);
		loginBtnPanel.add(loginEmployeeBtn);
		
		loginMenuPanel.add(titleLabel, BorderLayout.NORTH);
		loginMenuPanel.add(loginBtnPanel, BorderLayout.CENTER);
		
		navPanel.add(btnHome);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(loginMenuPanel, BorderLayout.CENTER);
	}

	@Override
	public void addListener() {
		loginUserBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginUserPage().showForm();
			}
		});
		
		loginEmployeeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginEmployeePage().showForm();
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
