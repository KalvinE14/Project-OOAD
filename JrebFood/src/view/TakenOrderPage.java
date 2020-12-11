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

public class TakenOrderPage extends View{

	JPanel contentPane, navPanel, takenOrderPanel, tablePanel, btnPanel;
	JTable table;
	JScrollPane scrollPane;
	JLabel title, id, name, foodName, qty, foodPrice, confirmLabel;
	JButton detailBtn, homeBtn;
	
	Vector<Vector<String>> takenOrderList;
	
	Vector<String> detail, header;
	
	public TakenOrderPage() {
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
		
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navPanel.setBackground(Color.ORANGE);
		
		takenOrderPanel = new JPanel(new BorderLayout());
		takenOrderPanel.setBackground(Color.ORANGE);
		takenOrderPanel.setBorder(new EmptyBorder(30, 0, 50, 0));
		
		tablePanel = new JPanel();
		tablePanel.setBackground(Color.ORANGE);
		tablePanel.setBorder(new EmptyBorder(0, 0, 50, 0));
		
		btnPanel = new JPanel();
		btnPanel.setBackground(Color.ORANGE);
		btnPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
		
		homeBtn = new JButton("Home");
		homeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		table = new JTable();
		table.setBackground(Color.ORANGE);
		
		takenOrderList = new Vector<>();
		
		header = new Vector<>();
		header.add("Order ID");
		header.add("Date");
		header.add("Username");
		header.add("Address");
		header.add("Status");
		
		detail = new Vector<>();
		detail.add("1");
		detail.add("11/12/2020");
		detail.add("Jack");
		detail.add("Earth");
		detail.add("Ordered");
		
		takenOrderList.add(detail);
		
		detail = new Vector<>();
		detail.add("2");
		detail.add("12/12/2020");
		detail.add("Rick");
		detail.add("Mars");
		detail.add("Accepted");
		
		takenOrderList.add(detail);
		
		DefaultTableModel dtm = new DefaultTableModel(takenOrderList, header){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		table.setModel(dtm);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBackground(Color.ORANGE);

		title = new JLabel("Taken Order");
		title.setBounds(10, 140, 50, 20);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Segoe UI", Font.BOLD, 24));
//		id = new JLabel();
//		id.setBounds(60, 140, 200, 20);
//		id.setFont(new Font("Segoe UI", Font.BOLD, 24));
//		name = new JLabel();
//		name.setBounds(60, 180, 200, 20);
//		name.setFont(new Font("Segoe UI", Font.BOLD, 24));

		detailBtn = new JButton("Detail");
		detailBtn.setBounds(10, 140, 50, 20);
		detailBtn.setHorizontalAlignment(SwingConstants.CENTER);
		detailBtn.setFont(new Font("Segoe UI", Font.BOLD, 24));
	}

	@Override
	public void addComponent() {
		navPanel.add(homeBtn);
		
		tablePanel.add(scrollPane);
		
		btnPanel.add(detailBtn);
		
		takenOrderPanel.add(title, BorderLayout.NORTH);
		takenOrderPanel.add(tablePanel, BorderLayout.CENTER);
		takenOrderPanel.add(btnPanel, BorderLayout.SOUTH);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(takenOrderPanel, BorderLayout.CENTER);
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
		
		detailBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new TakenOrderDetailPage().showForm();
			}
		});
	}

}
