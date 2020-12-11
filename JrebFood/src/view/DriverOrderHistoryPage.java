package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import view.core.View;

public class DriverOrderHistoryPage extends View{

	JPanel contentPane, historyPanel, navPanel, orderHistoryPanel, orderDetailHistoryPanel, orderTablePanel, detailTablePanel;
	JTable orderHistoryTable, orderDetailHistoryTable;
	JLabel title, orderHistoryLabel, orderDetailHistoryLabel;
	JScrollPane orderHistoryScrollPane, orderDetailHistoryScrollPane;
	JButton homeBtn;
	
	Vector<Vector<String>> orderHistoryData;
	Vector<String> orderHistoryHeader, orderHistoryDetail;
	
	Vector<Vector<String>> orderDetailHistoryData;
	Vector<String> orderDetailHistoryHeader, orderDetailHistoryDetail;
	
	public DriverOrderHistoryPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		initPanel();
		
		initNavigation();
		
		initOrderHistoryTable();
		
		initOrderDetailHistoryTable();
		
		initLabel();
	}

	private void initPanel() {
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.ORANGE);
		setContentPane(contentPane);
		
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navPanel.setBackground(Color.ORANGE);
		
		historyPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		historyPanel.setBackground(Color.ORANGE);
		historyPanel.setBorder(new EmptyBorder(20, 20, 100, 20));
		
		orderHistoryPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		orderHistoryPanel.setBackground(Color.ORANGE);
		orderDetailHistoryPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		orderDetailHistoryPanel.setBackground(Color.ORANGE);
		orderTablePanel = new JPanel();
		orderTablePanel.setBackground(Color.ORANGE);
		detailTablePanel = new JPanel();
		detailTablePanel.setBackground(Color.ORANGE);
	}

	private void initNavigation() {
		homeBtn = new JButton("Home");
		homeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
	}

	private void initOrderHistoryTable() {
		orderHistoryTable = new JTable();
		orderHistoryTable.setBackground(Color.ORANGE);

		orderHistoryData = new Vector<>();
		
		orderHistoryHeader = new Vector<>();
		orderHistoryHeader.add("Product ID");
		orderHistoryHeader.add("Username");
		orderHistoryHeader.add("Order Time");
		orderHistoryHeader.add("Status");
		
		orderHistoryDetail = new Vector<>();
		orderHistoryDetail.add("1");
		orderHistoryDetail.add("Jack");
		orderHistoryDetail.add("Sunday, 11 December 2020");
		orderHistoryDetail.add("Finished");
		
		orderHistoryData.add(orderHistoryDetail);
		
		orderHistoryDetail = new Vector<>();
		orderHistoryDetail.add("2");
		orderHistoryDetail.add("Rick");
		orderHistoryDetail.add("Monday, 12 December 2020");
		orderHistoryDetail.add("Finished");

		orderHistoryData.add(orderHistoryDetail);
		
		DefaultTableModel dtm = new DefaultTableModel(orderHistoryData, orderHistoryHeader){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		orderHistoryTable.setModel(dtm);
		
		
		orderHistoryScrollPane = new JScrollPane();
		orderHistoryScrollPane.setViewportView(orderHistoryTable);
		orderHistoryScrollPane.setBounds(0, 20, 490, 100);
		orderHistoryScrollPane.setBackground(Color.ORANGE);
	}

	private void initOrderDetailHistoryTable() {
		orderDetailHistoryTable = new JTable();
		orderDetailHistoryTable.setBackground(Color.ORANGE);
		
		orderDetailHistoryData = new Vector<>();
		
		orderDetailHistoryHeader = new Vector<>();
		orderDetailHistoryHeader.add("Product ID");
		orderDetailHistoryHeader.add("Username");
		orderDetailHistoryHeader.add("Order Time");
		orderDetailHistoryHeader.add("Status");
		
		orderDetailHistoryDetail = new Vector<>();
		orderDetailHistoryDetail.add("1");
		orderDetailHistoryDetail.add("Jack");
		orderDetailHistoryDetail.add("Sunday, 11 December 2020");
		orderDetailHistoryDetail.add("Finished");
		
		orderDetailHistoryData.add(orderDetailHistoryDetail);
		
		orderDetailHistoryHeader = new Vector<>();
		orderDetailHistoryHeader.add("2");
		orderDetailHistoryHeader.add("Rick");
		orderDetailHistoryHeader.add("Monday, 12 December 2020");
		orderDetailHistoryHeader.add("Finished");

		orderDetailHistoryData.add(orderDetailHistoryDetail);
		
		DefaultTableModel dtm = new DefaultTableModel(orderDetailHistoryData, orderDetailHistoryHeader){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		orderDetailHistoryTable.setModel(dtm);
		
		orderDetailHistoryScrollPane = new JScrollPane();
		orderDetailHistoryScrollPane.setViewportView(orderDetailHistoryTable);
		orderDetailHistoryScrollPane.setBounds(0, 20, 490, 100);
		orderDetailHistoryScrollPane.setBackground(Color.ORANGE);
	}

	@Override
	public void addComponent() {
		navPanel.add(homeBtn);
		
		orderTablePanel.add(orderHistoryScrollPane);
		
		detailTablePanel.add(orderDetailHistoryScrollPane);
		
		orderHistoryPanel.add(orderHistoryLabel);
		orderHistoryPanel.add(orderHistoryScrollPane);

		orderDetailHistoryPanel.add(orderDetailHistoryLabel);
		orderDetailHistoryPanel.add(orderDetailHistoryScrollPane);
		
		historyPanel.add(orderHistoryPanel, BorderLayout.CENTER);
		historyPanel.add(orderDetailHistoryPanel, BorderLayout.SOUTH);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(historyPanel, BorderLayout.CENTER);
		
	}

	@Override
	public void addListener() {
		homeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomeDriverPage().showForm();
			}
		});
	}
	
	private void initLabel()
	{
		orderHistoryLabel = new JLabel("Order History");
		orderHistoryLabel.setBounds(10, 140, 50, 20);
		orderHistoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderHistoryLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		orderDetailHistoryLabel = new JLabel("Order Detail History");
		orderDetailHistoryLabel.setBounds(10, 140, 50, 20);
		orderDetailHistoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		orderDetailHistoryLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
	}

}
