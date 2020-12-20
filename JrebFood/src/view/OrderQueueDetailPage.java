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
import view.core.View;

public class OrderQueueDetailPage extends View{
	
	JPanel contentPane, navPanel, viewOrderDetailPanel, titlePanel;
	JLabel titleLabel;
	JButton homeBtn;
	JTable table;
	JScrollPane scrollPane;

	public OrderQueueDetailPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.ORANGE);
		setContentPane(contentPane);
		
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navPanel.setBackground(Color.ORANGE);
		navPanel.setBorder(new EmptyBorder(0, 0, 30, 0));
		
		viewOrderDetailPanel = new JPanel(new BorderLayout());
		viewOrderDetailPanel.setBackground(Color.ORANGE);
		viewOrderDetailPanel.setBorder(new EmptyBorder(0, 20, 50, 20));
		
		titlePanel = new JPanel();
		titlePanel.setBorder(null);
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.setBorder(new EmptyBorder(0, 200, 20, 200));
		
		homeBtn = new JButton("Home");
		homeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		titleLabel = new JLabel("Order Queue Detail");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		table = new JTable();
		table.setBackground(Color.ORANGE);

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBackground(Color.ORANGE);
	}

	@Override
	public void addComponent() {
		
		titlePanel.add(titleLabel);
		
		viewOrderDetailPanel.add(titlePanel, BorderLayout.NORTH);
		viewOrderDetailPanel.add(scrollPane, BorderLayout.CENTER);
		
		navPanel.add(homeBtn);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(viewOrderDetailPanel, BorderLayout.CENTER);
		
		loadData();
	}

	@Override
	public void addListener() {
		homeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomeChefPage().showForm();
			}
		});
	}
	
	public void loadData() 
	{
		Vector<String> header = new Vector<>();
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

}
