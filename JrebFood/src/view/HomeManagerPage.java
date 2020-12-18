package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import view.core.View;

public class HomeManagerPage extends View{
	
	JTextField titleTxt;
	JPanel btnPanel;
	JButton btnHire, btnFire, btnFinancial;

	public HomeManagerPage() {
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
		titleTxt.setText("Manager Home");
		titleTxt.setBorder(null);
		titleTxt.setColumns(10);
		titleTxt.setEditable(false);
		titleTxt.setBorder(new EmptyBorder(70, 0, 0, 0));
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setLayout(new GridLayout(2, 1, 0, 35));
		btnPanel.setBorder(new EmptyBorder(190, 70, 150, 70));
		
		btnHire = new JButton("Hire Employee");
		btnFire = new JButton("Fire Employee");
		btnFinancial = new JButton("Financial Summary");
		
	}

	@Override
	public void addComponent() {
		btnPanel.add(btnHire);
		btnPanel.add(btnFire);
		btnPanel.add(btnFinancial);
		
		add(titleTxt, BorderLayout.NORTH);
		add(btnPanel, BorderLayout.CENTER);
		
	}

	@Override
	public void addListener() {
		btnHire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HireEmployeePage().showForm();
				
			}
		});
		
		btnFire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FireEmployeePage().showForm();
				
			}
		});
		
		btnFinancial.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new FinancialSummaryPage().showForm();
				
			}
		});
		
	}

}
