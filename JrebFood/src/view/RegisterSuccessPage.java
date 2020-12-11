package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import view.core.View;

public class RegisterSuccessPage extends View{
	
	private JPanel successPanel, btnPanel;
	private JLabel success;
	private JButton btnToLogin;
	
	public RegisterSuccessPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		successPanel = new JPanel();
		successPanel.setBackground(Color.ORANGE);
		successPanel.setBorder(new EmptyBorder(250, 0, 0, 0));
		
		success = new JLabel("Registration Success !");
		success.setFont(new Font("Tahoma", Font.BOLD, 30));
		success.setForeground(Color.BLUE);
		success.setVerticalAlignment(SwingConstants.CENTER);
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setBorder(new EmptyBorder(0, 0, 100, 0));
		
		btnToLogin = new JButton("Return to Login");
		
	}

	@Override
	public void addComponent() {
		successPanel.add(success);
		add(successPanel, BorderLayout.CENTER);
		btnPanel.add(btnToLogin);
		add(btnPanel, BorderLayout.SOUTH);
	}

	@Override
	public void addListener() {
		btnToLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomePage().showForm();
			}
		});
	}

}
