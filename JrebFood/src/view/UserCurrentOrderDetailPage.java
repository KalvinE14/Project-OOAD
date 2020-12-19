package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.OrderDetailController;
import model.OrderDetailModel;
import model.core.Model;
import view.core.View;

public class UserCurrentOrderDetailPage extends View{
	
	private JPanel titlePanel, detailPanel, navPanel, currOrderDetailPanel, contentPane;
	private JLabel titleLabel;
	private JTable table;
	private JScrollPane scroll;
	private JScrollBar scrollBar;
	private Vector<Vector<String>> detailList;
	private Vector<String> data;
	private JButton btnHome;

	public UserCurrentOrderDetailPage() {
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
		
		currOrderDetailPanel = new JPanel(new BorderLayout());
		currOrderDetailPanel.setBackground(Color.ORANGE);
		
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.setBorder(null);
		titlePanel.setBorder(new EmptyBorder(70, 200, 20, 200));
		
		titleLabel = new JLabel("Active Order Detail");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		detailPanel = new JPanel();
		detailPanel.setBackground(Color.ORANGE);
		
		scrollBar = new JScrollBar();
		
		detailList = new Vector<>();
		
		table = new JTable();
		
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
		header.add("Order ID");
		header.add("Food ID");
		header.add("Quantity");
		
		Vector<Vector<String>> activeOrderDetail = new Vector<>();
		Vector<String> detail = new Vector<>();
		
		Vector<Model> orderDetail = OrderDetailController.getInstance().getDetailByOrderId();
		
		for (Model model : orderDetail) {
			
			OrderDetailModel od = (OrderDetailModel) model;
			
			detail = new Vector<>();
			
			detail.add(od.getOrderId().toString());
			detail.add(od.getFoodId().toString());
			detail.add(od.getQty().toString());
			
			activeOrderDetail.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(activeOrderDetail, header){
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
		
		currOrderDetailPanel.add(titlePanel, BorderLayout.NORTH);
		currOrderDetailPanel.add(detailPanel, BorderLayout.CENTER);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(currOrderDetailPanel, BorderLayout.CENTER);
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
