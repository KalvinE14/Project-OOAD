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

import view.core.View;

public class AvailableOrderPage extends View{

	JPanel avlOrdPanel, orderPanel, detailContainerPanel, tablePanel, detailPanel, btnPanel;
	JTable table;
	JScrollPane scrollPane;
	JLabel title, id, name, foodName, qty, foodPrice;
	JButton accBtn;
	
	
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
		
		avlOrdPanel = new JPanel(new BorderLayout());
		avlOrdPanel.setBackground(Color.ORANGE);
		avlOrdPanel.setBorder(new EmptyBorder(20, 0, 50, 0));
		
		orderPanel = new JPanel(new BorderLayout());
		orderPanel.setBackground(Color.ORANGE);
		orderPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
		
		table = new JTable();
		table.setBackground(Color.ORANGE);
		
		availableOrderList = new Vector<>();
		
		header = new Vector<>();
		header.add("Product ID");
		header.add("Username");
		header.add("Order Time");
		
		detail = new Vector<>();
		detail.add("1");
		detail.add("Jack");
		detail.add("Sunday, 11 December 2020");
		
		availableOrderList.add(detail);
		
		detail = new Vector<>();
		detail.add("2");
		detail.add("Rick");
		detail.add("Monday, 12 December 2020");
		
		availableOrderList.add(detail);
		
		DefaultTableModel dtm = new DefaultTableModel(availableOrderList, header){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		table.setModel(dtm);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 20, 490, 100);
		scrollPane.setBackground(Color.ORANGE);
		
		tablePanel = new JPanel();
		tablePanel.setBorder(null);
		tablePanel.setBackground(Color.ORANGE);
		tablePanel.setLayout(null);
		
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
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
		
		accBtn = new JButton("Accept");
		accBtn.setBounds(10, 140, 50, 20);
		accBtn.setHorizontalAlignment(SwingConstants.CENTER);
		accBtn.setFont(new Font("Segoe UI", Font.BOLD, 24));
	}

	@Override
	public void addComponent() {
		tablePanel.add(id);
		tablePanel.add(name);
		tablePanel.add(scrollPane);
		
		btnPanel.add(accBtn);
		
		orderPanel.add(tablePanel, BorderLayout.CENTER);
		
		avlOrdPanel.add(title, BorderLayout.NORTH);
		avlOrdPanel.add(orderPanel, BorderLayout.CENTER);
		avlOrdPanel.add(btnPanel, BorderLayout.SOUTH);
		
		add(avlOrdPanel);
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
//				int row = table.getSelectedRow();
				
				
			}
		});
		
		accBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DriverDetailOrderPage().showForm();
			}
		});
	}

}
