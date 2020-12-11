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
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import view.core.View;

public class CartPage extends View{
	
	private JPanel titlePanel, cartPanel, btnPanel, contentPane, myCartPanel, navPanel;
	private JLabel titleLabel;
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
		
		btnPanel = new JPanel(new GridLayout(1, 2, 10, 0));
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setBorder(new EmptyBorder(0, 100, 20, 100));
		
		btnCheckout = new JButton("Checkout");
		btnRemove = new JButton("Remove from Cart");
	}
	
	public void loadCart() {
		Vector<String> header = new Vector<>();
		header.add("Food");
		header.add("Quantity");
		
		DefaultTableModel dtm = new DefaultTableModel(cartList, header){
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
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(myCartPanel, BorderLayout.CENTER);
	}

	@Override
	public void addListener() {
		btnCheckout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CartPage().showForm();
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
