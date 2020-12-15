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

public class TakenOrderDetailPage extends View{

	JPanel contentPane, navPanel, takenDetailPanel, tablePanel, footerPanel, btnPanel;
	JLabel title, msg;
	JTable table;
	JScrollPane scrollPane;
	JButton confirmBtn, finishBtn, homeBtn;
	
	Vector<Vector<String>> orderData;
	
	Vector<String> detail, header;
	
	public TakenOrderDetailPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		setBackground(Color.ORANGE);
		
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(0, 0, 50, 0));
		setContentPane(contentPane);
		
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navPanel.setBackground(Color.ORANGE);
		navPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
		
		takenDetailPanel = new JPanel(new BorderLayout());
		takenDetailPanel.setBackground(Color.ORANGE);
		
		tablePanel = new JPanel();
		tablePanel.setBackground(Color.ORANGE);
		tablePanel.setBorder(new EmptyBorder(0, 0, 50, 0));
		
		footerPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		footerPanel.setBackground(Color.ORANGE);
		footerPanel.setBorder(new EmptyBorder(50, 175, 0, 175));
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		
		homeBtn = new JButton("Home");
		homeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		title = new JLabel("Taken Order Detail");
		title.setBounds(10, 140, 50, 20);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Segoe UI", Font.BOLD, 24));
		
		table = new JTable();
		table.setBackground(Color.ORANGE);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBackground(Color.ORANGE);
		
		finishBtn = new JButton("Finish");
		finishBtn.setBounds(10, 140, 50, 20);
		finishBtn.setHorizontalAlignment(SwingConstants.CENTER);
		finishBtn.setFont(new Font("Segoe UI", Font.BOLD, 24));
		
		confirmBtn = new JButton("Confirm");
		confirmBtn.setBounds(10, 140, 50, 20);
		confirmBtn.setHorizontalAlignment(SwingConstants.CENTER);
		confirmBtn.setFont(new Font("Segoe UI", Font.BOLD, 24));
		
		checkStatus();
		
		msg = new JLabel();
		msg.setHorizontalAlignment(SwingConstants.CENTER);
		msg.setFont(new Font("Segoe UI", Font.BOLD, 15));
		msg.setVisible(false);
	}

	@Override
	public void addComponent() {

		tablePanel.add(scrollPane);
		
		btnPanel.add(confirmBtn);
		btnPanel.add(finishBtn);
		
		footerPanel.add(btnPanel);
		footerPanel.add(msg);
		
		takenDetailPanel.add(title, BorderLayout.NORTH);
		takenDetailPanel.add(tablePanel, BorderLayout.CENTER);
		takenDetailPanel.add(footerPanel, BorderLayout.SOUTH);
		
		navPanel.add(homeBtn);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(takenDetailPanel, BorderLayout.CENTER);
		
		loadData();
	}

	@Override
	public void addListener() {
		confirmBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OrderDetailController.getInstance().updateStatus();
				msg.setText("User's order has  been ordered to the restaurant");
				msg.setVisible(true);
				confirmBtn.setVisible(false);
			}
		});
		
		finishBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				OrderDetailController.getInstance().updateStatus();
				msg.setText("Order finish!");
				msg.setVisible(true);
				finishBtn.setVisible(false);
			}
		});
		
		homeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new HomeDriverPage().showForm();
			}
		});
		
	}
	
	private void checkStatus()
	{
		if(OrderDetailController.getInstance().isStatusAccepted())
		{
			confirmBtn.setVisible(true);
			finishBtn.setVisible(false);
			
			return;
		}
		
		if(OrderDetailController.getInstance().isStatusCooked())
		{
			confirmBtn.setVisible(false);
			finishBtn.setVisible(true);
			
			return;
		}
		
		confirmBtn.setVisible(false);
		finishBtn.setVisible(false);
	}
	
	private void loadData()
	{
		orderData = new Vector<>();
		
		header = new Vector<>();
		header.add("Order ID");
		header.add("Food ID");
		header.add("Quantity");
		
		Vector<Model> list = OrderDetailController.getInstance().getDetailByOrderId();
		
		for (Model model : list) {
			OrderDetailModel orderModel = (OrderDetailModel) model;
			
			detail = new Vector<>();
			detail.add(orderModel.getOrderId().toString());
			detail.add(orderModel.getFoodId().toString());
			detail.add(orderModel.getQty().toString());
			
			orderData.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(orderData, header){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		table.setModel(dtm);
	}

}
