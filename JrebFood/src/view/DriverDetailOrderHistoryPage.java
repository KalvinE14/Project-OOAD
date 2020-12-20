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

import controller.OrderController;
import controller.OrderDetailController;
import model.OrderDetailModel;
import model.OrderModel;
import model.core.Model;
import view.core.View;

public class DriverDetailOrderHistoryPage extends View{

	JPanel contentPane, navPanel, orderHistoryPanel;
	JTable orderDetailHistoryTable;
	JLabel title;
	JScrollPane orderDetailHistoryScrollPane;
	JButton homeBtn;
	
	Vector<Vector<String>> orderDetailHistoryData;
	Vector<String> orderDetailHistoryHeader, orderDetailHistoryDetail;
	
	public DriverDetailOrderHistoryPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		initPanel();
		
		initNavigation();
		
		initOrderDetailHistoryTable();
		
		initLabel();
	}

	private void initPanel() {
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.ORANGE);
		setContentPane(contentPane);
		
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navPanel.setBackground(Color.ORANGE);
		navPanel.setBorder(new EmptyBorder(0, 0, 30, 0));
		
		orderHistoryPanel = new JPanel(new BorderLayout());
		orderHistoryPanel.setBackground(Color.ORANGE);
		orderHistoryPanel.setBorder(new EmptyBorder(0, 30, 50, 30));
	}

	private void initNavigation() {
		homeBtn = new JButton("Home");
		homeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
	}

	private void initOrderDetailHistoryTable() {
		orderDetailHistoryTable = new JTable();
		orderDetailHistoryTable.setBackground(Color.ORANGE);
		
		orderDetailHistoryScrollPane = new JScrollPane();
		orderDetailHistoryScrollPane.setViewportView(orderDetailHistoryTable);
		orderDetailHistoryScrollPane.setBounds(0, 20, 490, 100);
		orderDetailHistoryScrollPane.setBackground(Color.ORANGE);
	}

	@Override
	public void addComponent() {
		navPanel.add(homeBtn);
		
		orderHistoryPanel.add(title, BorderLayout.NORTH);
		orderHistoryPanel.add(orderDetailHistoryScrollPane, BorderLayout.CENTER);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(orderHistoryPanel, BorderLayout.CENTER);
		
		loadData();
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
		title = new JLabel("Order Detail History");
		title.setBounds(10, 140, 50, 20);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Segoe UI", Font.BOLD, 24));
	}
	
	public void loadData()
	{
		orderDetailHistoryHeader = new Vector<>();
		orderDetailHistoryHeader.add("Order ID");
		orderDetailHistoryHeader.add("Food Name");
		orderDetailHistoryHeader.add("Food Price");
		orderDetailHistoryHeader.add("Quantity");
		
		OrderDetailController orderDetailController = OrderDetailController.getInstance();
		
		DefaultTableModel dtm = new DefaultTableModel(orderDetailController.getDetailByOrderId(), orderDetailHistoryHeader){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		orderDetailHistoryTable.setModel(dtm);
	}

}
