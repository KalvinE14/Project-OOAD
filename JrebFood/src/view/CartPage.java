package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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
import controller.EmployeeController;
import controller.OrderController;
import controller.OrderDetailController;
import controller.UserController;
import model.CartModel;
import view.core.View;

public class CartPage extends View{
	
	private JPanel titlePanel, cartPanel, btnPanel, contentPane, myCartPanel, navPanel, bottomPanel, labelPanel;
	private JLabel titleLabel, totalLabel, priceLabel;
	private JTable table;
	private JScrollPane scroll;
	private JScrollBar scrollBar;
	private JButton btnCheckout, btnRemove, btnHome;
	private Vector<Vector<String>> cartList;
	private Vector<String> data;
	
	public CartPage() {
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
		
		myCartPanel = new JPanel(new BorderLayout());
		myCartPanel.setBackground(Color.ORANGE);
		
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navPanel.setBackground(Color.ORANGE);
		
		btnHome = new JButton("Home");
		
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.setBorder(null);
		titlePanel.setBorder(new EmptyBorder(70, 200, 20, 200));
		
		titleLabel = new JLabel("My Cart");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		cartPanel = new JPanel();
		cartPanel.setBackground(Color.ORANGE);
		
		scrollBar = new JScrollBar();
		
		cartList = new Vector<>();
		
		table = new JTable();
		
		data = new Vector<>();
		data.add("Pizza");
		data.add("1");
		
		cartList.add(data);
		
		data = new Vector<>();
		data.add("Burger");
		data.add("6");
		
		cartList.add(data);
		
		data = new Vector<>();
		data.add("Indomie");
		data.add("10");
		
		cartList.add(data);
		
		loadCart();
		
		scroll = new JScrollPane();
		scroll.setBackground(Color.ORANGE);
		scroll.setViewportView(table);
		scroll.setPreferredSize(new Dimension(380, 380));
		scroll.setVerticalScrollBar(scrollBar);
		scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		bottomPanel = new JPanel(new GridLayout(2, 1, 10, 0));
		bottomPanel.setBackground(Color.ORANGE);
		
		labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		labelPanel.setBackground(Color.ORANGE);
		
		totalLabel = new JLabel("Total Price : ");
		
		CartController cartController = CartController.getInstance();
		priceLabel = new JLabel(cartController.getTotalPrice().toString());
		
		btnPanel = new JPanel(new GridLayout(1, 2, 10, 0));
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setBorder(new EmptyBorder(0, 100, 15, 100));
		
		btnCheckout = new JButton("Checkout");
		btnRemove = new JButton("Remove from Cart");
	}
	
	public void loadCart() {
		Vector<String> header = new Vector<>();
		header.add("User ID");
		header.add("Food ID");
		header.add("Food");
		header.add("Quantity");
		header.add("Price");
		
		CartController cartController = CartController.getInstance();
		
		DefaultTableModel dtm = new DefaultTableModel(cartController.getCartDataByUserId(), header){
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
		cartPanel.add(scroll);
		navPanel.add(btnHome);
		
		myCartPanel.add(titlePanel, BorderLayout.NORTH);
		myCartPanel.add(cartPanel, BorderLayout.CENTER);
		myCartPanel.add(btnPanel, BorderLayout.SOUTH);
		
		btnPanel.add(btnCheckout);
		btnPanel.add(btnRemove);
		
		labelPanel.add(totalLabel);
		labelPanel.add(priceLabel);
		
		bottomPanel.add(labelPanel);
		bottomPanel.add(btnPanel);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(myCartPanel, BorderLayout.CENTER);
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	private void showCheckoutConfirmation(ActionEvent e) {
		int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure want to checkout this cart ?");
		
		switch(confirmation) {
		case JOptionPane.YES_OPTION:
			OrderController orderController = OrderController.getInstance();
			orderController.addOrder();
			
			Integer lastId = orderController.getLastOrderId();
			
			CartController cartController = CartController.getInstance();
			Vector<Vector<Object>> cartData = cartController.getCartDataByUserId();
			
			OrderDetailController orderDetailController = OrderDetailController.getInstance();
			
			for (Vector<Object> data : cartData) {
				orderDetailController.addDetail(lastId, Integer.parseInt(data.get(1).toString()), Integer.parseInt(data.get(3).toString()));
			}
			
			for (Vector<Object> data : cartData) {
				cartController.deleteSpecificCart(Integer.parseInt(data.get(1).toString()));
			}
			
			loadCart();
			priceLabel.setText(cartController.getTotalPrice().toString());
			JOptionPane.showMessageDialog(this, "Checkout success");
			break;
		}
	}
	
	private void showRemoveCartConfirmation(ActionEvent e) {
		int confirmation = JOptionPane.showConfirmDialog(this, "Remove this food from your cart ?");
		
		switch(confirmation) {
		case JOptionPane.YES_OPTION:
			int row = table.getSelectedRow();
			CartController cartController = CartController.getInstance();
			int foodIdValue = Integer.parseInt(table.getValueAt(row, 1).toString());
			cartController.deleteSpecificCart(foodIdValue);
			
			loadCart();
			
			priceLabel.setText(cartController.getTotalPrice().toString());
			JOptionPane.showMessageDialog(this, "Cart removed");
			break;
		}
	}

	@Override
	public void addListener() {
		btnCheckout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showCheckoutConfirmation(e);
			}
		});
		
		btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomeUserPage().showForm();
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showRemoveCartConfirmation(e);
			}
		});
		
	}

}
