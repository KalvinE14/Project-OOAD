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
import view.core.View;

public class OrderQueuePage extends View{
	
	JPanel contentPane, navPanel, viewOrderPanel, titlePanel, footerPanel, btnPanel;
	JLabel titleLabel, messageLabel, orderIdLabel;
	JButton confirmBtn, homeBtn, detailBtn;
	JTable table;
	JScrollPane scrollPane;

	public OrderQueuePage() {
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
		
		viewOrderPanel = new JPanel(new BorderLayout());
		viewOrderPanel.setBackground(Color.ORANGE);
		viewOrderPanel.setBorder(new EmptyBorder(0, 20, 50, 20));
		
		titlePanel = new JPanel();
		titlePanel.setBorder(null);
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.setBorder(new EmptyBorder(0, 200, 20, 200));
		
		footerPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		footerPanel.setBackground(Color.ORANGE);
		footerPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
		
		btnPanel = new JPanel(new GridLayout(2, 1, 0, 5));
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setBorder(new EmptyBorder(0, 175, 0, 175));
		
		homeBtn = new JButton("Home");
		homeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		titleLabel = new JLabel("Order Queue");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		table = new JTable();
		table.setBackground(Color.ORANGE);

		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBackground(Color.ORANGE);
		
		confirmBtn = new JButton("Confirm");
		confirmBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		detailBtn = new JButton("Detail");
		detailBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		messageLabel = new JLabel("Order's status has been updated to ready");
		messageLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setVisible(false);
	}

	@Override
	public void addComponent() {
		btnPanel.add(confirmBtn);
		btnPanel.add(detailBtn);
		
		footerPanel.add(btnPanel);
		footerPanel.add(messageLabel);
		
		titlePanel.add(titleLabel);
		
		viewOrderPanel.add(titlePanel, BorderLayout.NORTH);
		viewOrderPanel.add(scrollPane, BorderLayout.CENTER);
		viewOrderPanel.add(footerPanel, BorderLayout.SOUTH);
		
		navPanel.add(homeBtn);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(viewOrderPanel, BorderLayout.CENTER);
		
		loadData();
	}

	@Override
	public void addListener() {
		table.addMouseListener(new MouseListener() {
			
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
				int row = table.getSelectedRow();
				
				orderIdLabel = new JLabel();

				orderIdLabel.setText(table.getValueAt(row, 0).toString());
			}
		});
		homeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomeChefPage().showForm();
			}
		});
		
		confirmBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(orderIdLabel != null)
				{
					OrderController.getInstance().updateStatus(Integer.parseInt(orderIdLabel.getText()), "Cooked");
					messageLabel.setVisible(true);
					messageLabel.setText("Order's status has been updated to cooked!");
					loadData();
				}else
				{
					messageLabel.setVisible(true);
					messageLabel.setText("Please choose the order first!");
				}
				
				orderIdLabel = null;
				
			}
		});
		
		detailBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(orderIdLabel != null)
				{
					dispose();
					OrderController.getInstance().viewOrderQueueDetail(Integer.parseInt(orderIdLabel.getText()));
				}else
				{
					messageLabel.setVisible(true);
					messageLabel.setText("Please choose the order first!");
				}
			}
		});
		
	}
	
	public void loadData() 
	{
		Vector<String> header = new Vector<>();
		header.add("Order ID");
		header.add("Date");
		header.add("Driver Name");
		
		OrderController orderController = OrderController.getInstance();
		
		DefaultTableModel dtm = new DefaultTableModel(orderController.getAllOrderedStatus(), header){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		table.setModel(dtm);
	}

}
