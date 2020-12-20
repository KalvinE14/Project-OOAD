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

import controller.OrderDetailController;
import model.OrderDetailModel;
import model.OrderModel;
import model.core.Model;
import view.core.View;

public class UserDetailOrderPage extends View{
	
	private JPanel titlePanel, detailPanel, contentPane, orderHistoryDetailPanel, navPanel;
	private JLabel titleLabel;
	private JTable table;
	private JScrollPane scroll;
	private JScrollBar scrollBar;
	private Vector<Vector<String>> detailList;
	private Vector<String> data;
	private JButton btnHome;

	public UserDetailOrderPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navPanel.setBackground(Color.ORANGE);
		
		btnHome = new JButton("Home");
		
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.ORANGE);
		setContentPane(contentPane);
		
		orderHistoryDetailPanel = new JPanel(new BorderLayout());
		orderHistoryDetailPanel.setBackground(Color.ORANGE);
		
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.setBorder(null);
		titlePanel.setBorder(new EmptyBorder(70, 200, 20, 200));
		
		titleLabel = new JLabel("Order Detail");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		detailPanel = new JPanel();
		detailPanel.setBackground(Color.ORANGE);
		
		table = new JTable();
		
		scrollBar = new JScrollBar();
		
		loadDetail();
		
		scroll = new JScrollPane();
		scroll.setBackground(Color.ORANGE);
		scroll.setViewportView(table);
		scroll.setPreferredSize(new Dimension(380, 380));
		scroll.setVerticalScrollBar(scrollBar);
		scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
	}
	
	public void loadDetail() {
		Vector<String> header = new Vector<>();
		header = new Vector<>();
		header.add("Order ID");
		header.add("Food Name");
		header.add("Food Price");
		header.add("Quantity");
		
		OrderDetailController orderDetailController = OrderDetailController.getInstance();
		
		DefaultTableModel dtm = new DefaultTableModel(orderDetailController.getDetailByOrderId(), header){
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
		detailPanel.add(scroll);
		navPanel.add(btnHome);
		
		orderHistoryDetailPanel.add(titlePanel, BorderLayout.NORTH);
		orderHistoryDetailPanel.add(detailPanel, BorderLayout.CENTER);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(orderHistoryDetailPanel, BorderLayout.CENTER);
	}

	@Override
	public void addListener() {
			btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomeUserPage().showForm();
			}
		});
	}

}
