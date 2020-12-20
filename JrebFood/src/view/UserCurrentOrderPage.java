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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.CartController;
import controller.OrderController;
import view.core.View;

public class UserCurrentOrderPage extends View {
	
	private JPanel titlePanel, currentOrderPanel, btnPanel, navPanel, contentPane, activeOrderPanel;
	private JLabel titleLabel;
	private JTable table;
	private JScrollPane scroll;
	private JScrollBar scrollBar;
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
		
		table = new JTable();
	
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
		header.add("Driver Name");
		header.add("Address");
		header.add("Status");
		
		OrderController orderController = OrderController.getInstance();
		
		DefaultTableModel dtm = new DefaultTableModel(orderController.getUserActiveOrder(), header){
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
	
	private void showConfirmation(ActionEvent e) {
		int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure want to cancel this order ?");
		
		switch(confirmation) {
		case JOptionPane.YES_OPTION:
			int row = table.getSelectedRow();
			int orderId = Integer.parseInt(table.getValueAt(row, 0).toString());
			String status = table.getValueAt(row, 4).toString();
			
			OrderController orderController = OrderController.getInstance();
			orderController.cancelOrder(orderId, status);
			
			loadCurrentOrder();
			JOptionPane.showMessageDialog(this, "Order canceled");
			break;
		}
	}

	@Override
	public void addListener() {
		btnDetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int orderId = Integer.parseInt(table.getValueAt(row, 0).toString());
				
				dispose();
				OrderController.getInstance().viewUserActiveOrderDetail(orderId);
			}
		});
		
		btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomeUserPage().showForm();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showConfirmation(e);
			}
		});
	}

}
