package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import view.core.View;

public class UserOrderHistoryPage extends View {
	
	private JPanel titlePanel, orderPanel, btnPanel, contentPane, orderHistoryPanel, navPanel;
	private JLabel titleLabel;
	private JTable table;
	private JScrollPane scroll;
	private JScrollBar scrollBar;
	private Vector<Vector<String>> orderList;
	private Vector<String> data;
	private JButton btnDetails, btnHome;

	public UserOrderHistoryPage() {
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
		
		orderHistoryPanel = new JPanel(new BorderLayout());
		orderHistoryPanel.setBackground(Color.ORANGE);
		
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navPanel.setBackground(Color.ORANGE);
		
		btnHome = new JButton("Home");
		
		titlePanel = new JPanel();
		titlePanel.setBorder(null);
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.setBorder(new EmptyBorder(70, 200, 20, 200));
		
		titleLabel = new JLabel("Order History");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		orderPanel = new JPanel();
		orderPanel.setBackground(Color.ORANGE);
		
		scrollBar = new JScrollBar();
		
		orderList = new Vector<>();
		
		table = new JTable();
		
		data = new Vector<>();
		data.add("1");
		data.add("5/12/2020");
		data.add("Marcel");
		data.add("John Doe");
		data.add("West Mountain Street");
		
		orderList.add(data);
		
		data = new Vector<>();
		data.add("2");
		data.add("7/11/2020");
		data.add("Athena");
		data.add("Vanessa");
		data.add("East Mountain Street");
		
		orderList.add(data);
		
		data = new Vector<>();
		data.add("3");
		data.add("12/6/2020");
		data.add("Eileene");
		data.add("Shane");
		data.add("South Mountain Street");
		
		orderList.add(data);
		
		loadHistory();
		
		scroll = new JScrollPane();
		scroll.setBackground(Color.ORANGE);
		scroll.setViewportView(table);
		scroll.setPreferredSize(new Dimension(380, 380));
		scroll.setVerticalScrollBar(scrollBar);
		scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setBorder(new EmptyBorder(10, 100, 20, 100));
		
		btnDetails = new JButton("See Details");
	}

	public void loadHistory() {
		Vector<String> header = new Vector<>();
		header.add("Order ID");
		header.add("Date");
		header.add("Username");
		header.add("Driver Name");
		header.add("Address");
		
		DefaultTableModel dtm = new DefaultTableModel(orderList, header){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		table.setModel(dtm);
	}
	
	@Override
	public void addComponent() {
		titlePanel.add(titleLabel);
		orderPanel.add(scroll);
		btnPanel.add(btnDetails);
		navPanel.add(btnHome);
		
		orderHistoryPanel.add(titlePanel, BorderLayout.NORTH);
		orderHistoryPanel.add(orderPanel, BorderLayout.CENTER);
		orderHistoryPanel.add(btnPanel, BorderLayout.SOUTH);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(orderHistoryPanel, BorderLayout.CENTER);
	}

	@Override
	public void addListener() {
		btnDetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserDetailOrderPage().showForm();
			}
		});
		
		btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomeUserPage().showForm();
			}
		});
	}

}
