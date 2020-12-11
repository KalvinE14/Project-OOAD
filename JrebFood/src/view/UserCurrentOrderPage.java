package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
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

public class UserCurrentOrderPage extends View {
	
	private JPanel titlePanel, currentOrderPanel, btnPanel, navPanel, contentPane, activeOrderPanel;
	private JLabel titleLabel;
	private JTable table;
	private JScrollPane scroll;
	private JScrollBar scrollBar;
	private Vector<Vector<String>> currentOrderList;
	private Vector<String> data;
	private JButton btnDetails, btnCancel, btnHome;

	public UserCurrentOrderPage() {
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
		
		activeOrderPanel = new JPanel(new BorderLayout());
		activeOrderPanel.setBackground(Color.ORANGE);
		
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navPanel.setBackground(Color.ORANGE);
		
		btnHome = new JButton("Home");
		
		titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.setBorder(null);
		//titlePanel.setBorder(new EmptyBorder(70, 200, 20, 200));
		
		titleLabel = new JLabel("Active Order");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		currentOrderPanel = new JPanel();
		currentOrderPanel.setBackground(Color.ORANGE);
		
		scrollBar = new JScrollBar();
		
		currentOrderList = new Vector<>();
		
		table = new JTable();
		
		data = new Vector<>();
		data.add("1");
		data.add("5/12/2020");
		data.add("Marcel");
		data.add("John Doe");
		data.add("West Mountain Street");
		data.add("Cooked");
		
		currentOrderList.add(data);
		
		data = new Vector<>();
		data.add("2");
		data.add("7/11/2020");
		data.add("Athena");
		data.add("null");
		data.add("East Mountain Street");
		data.add("Not Accepted");
		
		currentOrderList.add(data);
		
		data = new Vector<>();
		data.add("3");
		data.add("12/6/2020");
		data.add("Eileene");
		data.add("Jessica");
		data.add("South Mountain Street");
		data.add("Ordered");
		
		currentOrderList.add(data);
		
		loadCurrentOrder();
		
		scroll = new JScrollPane();
		scroll.setBackground(Color.ORANGE);
		scroll.setViewportView(table);
		scroll.setPreferredSize(new Dimension(380, 380));
		scroll.setVerticalScrollBar(scrollBar);
		scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		btnPanel = new JPanel(new GridLayout(1, 2, 20, 0));
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setBorder(new EmptyBorder(10, 100, 20, 100));
		
		btnDetails = new JButton("See Details");
		btnCancel = new JButton("Cancel Order");
	}
	
	public void loadCurrentOrder() {
		Vector<String> header = new Vector<>();
		header.add("Order ID");
		header.add("Date");
		header.add("Username");
		header.add("Driver Name");
		header.add("Address");
		header.add("Status");
		
		DefaultTableModel dtm = new DefaultTableModel(currentOrderList, header){
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
		currentOrderPanel.add(scroll);
		btnPanel.add(btnDetails);
		btnPanel.add(btnCancel);
		navPanel.add(btnHome);
		
		activeOrderPanel.add(titlePanel, BorderLayout.NORTH);
		activeOrderPanel.add(currentOrderPanel, BorderLayout.CENTER);
		activeOrderPanel.add(btnPanel, BorderLayout.SOUTH);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(activeOrderPanel, BorderLayout.CENTER);
	}

	@Override
	public void addListener() {
		btnDetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserCurrentOrderDetailPage().showForm();
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
