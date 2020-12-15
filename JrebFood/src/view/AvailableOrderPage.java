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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.OrderController;
import model.OrderModel;
import model.core.Model;
import view.core.View;

public class AvailableOrderPage extends View{

	JPanel contentPane, avaiOrderPanel, orderPanel, detailContainerPanel, tablePanel, detailPanel, footPanel, btnPanel, confirmPanel, homePanel;
	JTable table;
	JScrollPane scrollPane;
	JLabel title, id, name, foodName, qty, foodPrice, confirmLabel, orderIdLabel;
	JButton accBtn, orderHistoryBtn, yesBtn, noBtn, homeBtn;
	
	Vector<Vector<String>> availableOrderList;
	
	Vector<String> detail, header;
	
	public AvailableOrderPage() {
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
		
		avaiOrderPanel = new JPanel(new BorderLayout());
		avaiOrderPanel.setBackground(Color.ORANGE);
		avaiOrderPanel.setBorder(new EmptyBorder(30, 0, 50, 0));
		
		orderPanel = new JPanel(new BorderLayout());
		orderPanel.setBackground(Color.ORANGE);
		orderPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
		
		homePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		homePanel.setBackground(Color.ORANGE);
		
		homeBtn = new JButton("Home");
		homeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		table = new JTable();
		table.setBackground(Color.ORANGE);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBackground(Color.ORANGE);
		
		tablePanel = new JPanel();
		tablePanel.setBackground(Color.ORANGE);
		tablePanel.setBorder(new EmptyBorder(0, 20, 0, 40));
		
		title = new JLabel("Available Order");
		title.setBounds(10, 140, 50, 20);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Segoe UI", Font.BOLD, 24));
		id = new JLabel();
		id.setBounds(60, 140, 200, 20);
		id.setFont(new Font("Segoe UI", Font.BOLD, 24));
		name = new JLabel();
		name.setBounds(60, 180, 200, 20);
		name.setFont(new Font("Segoe UI", Font.BOLD, 24));
		
		footPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		footPanel.setBackground(Color.ORANGE);
		footPanel.setBorder(new EmptyBorder(0, 120, 0, 120));
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		
		confirmPanel = new JPanel(new GridLayout(1, 3, 0, 0));
		confirmPanel.setBackground(Color.ORANGE);
		confirmPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
		confirmPanel.setVisible(false);
		
		accBtn = new JButton("Accept");
		accBtn.setBounds(10, 140, 50, 20);
		accBtn.setHorizontalAlignment(SwingConstants.CENTER);
		accBtn.setFont(new Font("Segoe UI", Font.BOLD, 24));
		
		confirmLabel = new JLabel("Are you sure?");
		confirmLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		yesBtn = new JButton("Yes");
		yesBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		noBtn = new JButton("No");
		noBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
	}

	@Override
	public void addComponent() {
		homePanel.add(homeBtn);
		
		tablePanel.add(id);
		tablePanel.add(name);
		tablePanel.add(scrollPane);
		
		confirmPanel.add(confirmLabel);
		confirmPanel.add(yesBtn);
		confirmPanel.add(noBtn);
		
		footPanel.add(accBtn);
		footPanel.add(confirmPanel);
		
		orderPanel.add(tablePanel, BorderLayout.CENTER);
		
		avaiOrderPanel.add(title, BorderLayout.NORTH);
		avaiOrderPanel.add(orderPanel, BorderLayout.CENTER);
		avaiOrderPanel.add(footPanel, BorderLayout.SOUTH);
		
		contentPane.add(homePanel, BorderLayout.NORTH);
		contentPane.add(homePanel, BorderLayout.NORTH);
		contentPane.add(avaiOrderPanel, BorderLayout.CENTER);
		
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
		
		accBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmPanel.setVisible(true);
			}
		});
		
		homeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomeDriverPage().showForm();
			}
		});
		
		yesBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(orderIdLabel != null)
				{
					dispose();
					OrderController.getInstance().viewDetailOrderByDriver(Integer.parseInt(orderIdLabel.getText()));
				}
			}
		});
		
		noBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmPanel.setVisible(false);
			}
		});
	}
	
	private void loadData()
	{
		availableOrderList = new Vector<>();
		
		header = new Vector<>();
		header.add("Order ID");
		header.add("User ID");
		header.add("Address");
		header.add("Order Time");
		
		Vector<Model> list = OrderController.getInstance().getAllAvailableOrder();
		
		for (Model model : list) {
			OrderModel orderModel = (OrderModel) model;
			
			detail = new Vector<>();
			detail.add(orderModel.getOrderId().toString());
			detail.add(orderModel.getUserId().toString());
			detail.add(orderModel.getAddress());
			detail.add(orderModel.getOrderDate());
			
			availableOrderList.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(availableOrderList, header){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		table.setModel(dtm);
	}
}
