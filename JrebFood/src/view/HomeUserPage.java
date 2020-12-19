package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.CartController;
import controller.FoodController;
import model.FoodModel;
import model.core.Model;
import view.core.View;

public class HomeUserPage extends View{
	
	private JPanel titlePanel, menuPanel, btnPanel, navPanel;
	private JButton btnAdd, btnCart, btnOrderHistory, btnActiveOrder;
	private JLabel txtTitle;
	private Vector<String> foodList;
	
	private JScrollPane scroll;
	private JScrollBar scrollBar;
	private JTable table;
	
	public HomeUserPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		btnCart = new JButton();
		btnCart.setText("My Cart");
		btnCart.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnOrderHistory = new JButton("Order History");
		btnActiveOrder = new JButton("Active Order");
		
		navPanel = new JPanel(new GridLayout(1, 3, 18, 0));
		navPanel.setBackground(Color.ORANGE);
		
		titlePanel = new JPanel(new GridLayout(2, 1, 10, 20));
		titlePanel.setBorder(null);
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.setBorder(new EmptyBorder(70, 60, 20, 60));
		
		txtTitle = new JLabel();
		txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		txtTitle.setText("Menu");
		
		foodList = new Vector<>();
		
		scrollBar = new JScrollBar();
		
		menuPanel = new JPanel();
		menuPanel.setBackground(Color.ORANGE);
		
		table = new JTable();
		
		loadFood();
		
		scroll = new JScrollPane();
		scroll.setViewportView(table);
		scroll.setPreferredSize(new Dimension(380, 380));
		scroll.setVerticalScrollBar(scrollBar);
		scroll.setBackground(Color.ORANGE);
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		btnAdd = new JButton();
		btnAdd.setText("Add to Cart");
	}
	
	public void loadFood() {
		Vector<String> header = new Vector<>();
		header.add("Food ID");
		header.add("Name");
		header.add("Price");
		header.add("Description");
		
		FoodController foodController = FoodController.getInstance();
		Vector<Model> foods = foodController.getAllAvailableFood();
		
		Vector<Vector<String>> foodData = new Vector<>();
		Vector<String> foodDetail;
		
		for (Model foodList : foods) {
			FoodModel food = (FoodModel) foodList;
			
			foodDetail = new Vector<>();
			foodDetail.add(food.getFoodId().toString());
			foodDetail.add(food.getName());
			foodDetail.add(food.getPrice().toString());
			foodDetail.add(food.getDescription());
			
			foodData.add(foodDetail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(foodData, header) {
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
		titlePanel.add(txtTitle);
		navPanel.add(btnActiveOrder);
		navPanel.add(btnOrderHistory);
		navPanel.add(btnCart);
		titlePanel.add(navPanel);
		add(titlePanel, BorderLayout.NORTH);
		menuPanel.add(scroll);
		add(menuPanel, BorderLayout.CENTER);
		btnPanel.add(btnAdd);
		add(btnPanel, BorderLayout.SOUTH);
	}

	@Override
	public void addListener() {
		btnCart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CartPage().showForm();
			}
		});
		
		btnOrderHistory.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new UserOrderHistoryPage().showForm();
			}
		});
		
		btnActiveOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new UserCurrentOrderPage().showForm();
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CartController cartController = CartController.getInstance();
				int row = table.getSelectedRow();
				int foodId = Integer.parseInt(table.getValueAt(row, 0).toString());
				
				cartController.addCart(foodId);
			}
		});
	}
}
