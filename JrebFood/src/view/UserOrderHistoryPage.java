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

import controller.OrderController;
import controller.OrderDetailController;
import model.OrderModel;
import model.core.Model;
import view.core.View;

public class UserOrderHistoryPage extends View {
	
	private JPanel titlePanel, orderPanel, btnPanel, contentPane, orderHistoryPanel, navPanel;
	private JLabel titleLabel;
	private JTable table;
	private JScrollPane scroll;
	private JScrollBar scrollBar;
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
		
		table = new JTable();
		
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
		header.add("Order Date");
		header.add("Driver ID");
		
		OrderController orderController = OrderController.getInstance();
		
		Vector<Vector<String>> orderList = new Vector<>();
		Vector<String> order = new Vector<>();
		
		Vector<Model> orderModel = orderController.viewUserOrderHistory();
		
		for (Model model : orderModel) {
			
			OrderModel om = (OrderModel) model;
			order = new Vector<>();
			order.add(om.getOrderId().toString());
			order.add(om.getOrderDate().toString());
			order.add(om.getDriverId().toString());
			
			orderList.add(order);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(orderList , header){
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
				int row = table.getSelectedRow();
				int orderId = Integer.parseInt(table.getValueAt(row, 0).toString());
				
				dispose();
				OrderController.getInstance().viewUserOrderHistoryDetail(orderId);
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
