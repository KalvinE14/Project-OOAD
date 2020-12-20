package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import model.OrderModel;
import model.core.Model;
import view.core.View;

public class DriverOrderHistoryPage extends View{

	JPanel contentPane, navPanel, orderHistoryPanel, btnPanel;
	JTable orderHistoryTable;
	JLabel title, orderIdLabel;
	JScrollPane orderHistoryScrollPane;
	JButton homeBtn, detailBtn;
	
	Vector<Vector<String>> orderHistoryData;
	Vector<String> orderHistoryHeader, orderHistoryDetail;
	
	public DriverOrderHistoryPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		initPanel();
		
		initButton();
		
		initOrderHistoryTable();
		
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
		orderHistoryPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setBorder(new EmptyBorder(30, 0, 30, 0));
	}

	private void initButton() {
		homeBtn = new JButton("Home");
		homeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		detailBtn = new JButton("Detail");
		detailBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
	}

	private void initOrderHistoryTable() {
		orderHistoryTable = new JTable();
		orderHistoryTable.setBackground(Color.ORANGE);
		
		orderHistoryScrollPane = new JScrollPane();
		orderHistoryScrollPane.setViewportView(orderHistoryTable);
		orderHistoryScrollPane.setBounds(0, 20, 490, 100);
		orderHistoryScrollPane.setBackground(Color.ORANGE);
	}

	@Override
	public void addComponent() {
		navPanel.add(homeBtn);
		
		btnPanel.add(detailBtn);
		
		orderHistoryPanel.add(title, BorderLayout.NORTH);
		orderHistoryPanel.add(orderHistoryScrollPane, BorderLayout.CENTER);
		orderHistoryPanel.add(btnPanel, BorderLayout.SOUTH);
		
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
		
		detailBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(orderIdLabel != null)
				{
					dispose();
					OrderController.getInstance().viewDriverDetailOrderHistory(Integer.parseInt(orderIdLabel.getText()));
				}
			}
		});
		
		orderHistoryTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = orderHistoryTable.getSelectedRow();
				
				orderIdLabel = new JLabel();
				
				orderIdLabel.setText(orderHistoryTable.getValueAt(row, 0).toString());
			}
		});
	}
	
	private void initLabel()
	{
		title = new JLabel("Order History");
		title.setBounds(10, 140, 50, 20);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Segoe UI", Font.BOLD, 24));
	}
	
	private void loadData()
	{
		orderHistoryHeader = new Vector<>();
		orderHistoryHeader.add("Order ID");
		orderHistoryHeader.add("User Name");
		orderHistoryHeader.add("Address");
		orderHistoryHeader.add("Order Time");
		
		OrderController orderController = OrderController.getInstance();
		
		DefaultTableModel dtm = new DefaultTableModel(orderController.getAllDriverOrderHistory(), orderHistoryHeader){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		orderHistoryTable.setModel(dtm);
	}

}
