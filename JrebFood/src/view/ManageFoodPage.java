package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.FoodController;
import controller.OrderController;
import model.FoodModel;
import model.OrderModel;
import model.core.Model;
import view.core.View;

public class ManageFoodPage extends View{
	
	JPanel contentPane, navPanel, manageFoodPanel, titlePanel, foodDataPanel, foodFormPanel, tablePanel, controlPanel, controlBtnPanel, 
			confirmUpdatePanel, confirmDeletePanel, confirmContainerPanel, formPanel, insertPanel;
	JLabel titleLabel, confirmUpdateLabel, confirmDeleteLabel, manageMessageLabel, foodNameLabel, foodDetailLabel, foodPriceLabel, addNewLabel, addMessageLabel,
			foodIdLabel, foodStatusLabel;
	JTextField foodNameTxt, foodDetailTxt, foodPriceTxt;
	JButton insertBtn, updateAvailabilityBtn, removeBtn, homeBtn, yesUpdateBtn, noUpdateBtn, yesDeleteBtn, noDeleteBtn;
	JScrollPane scrollPane;
	JTable foodTable;
	
	Vector<Vector<String>> foodData;
	Vector<String> header, detail;
	
	public ManageFoodPage() {
		super();
		this.width = 500;
		this.height = 600;
	}

	@Override
	public void initialize() {
		initPanel();
		
		homeBtn = new JButton("Home");
		homeBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
		
		titleLabel = new JLabel("Manage Food");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		foodTable = new JTable();
		foodTable.setBackground(Color.ORANGE);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(foodTable);
		scrollPane.setBackground(Color.ORANGE);
		
		updateAvailabilityBtn = new JButton("Change Availability");
		updateAvailabilityBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		removeBtn = new JButton("Remove");
		removeBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		confirmUpdateLabel = new JLabel("Update Availability?");
		confirmUpdateLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		confirmDeleteLabel = new JLabel("Remove Food?");
		confirmDeleteLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		yesUpdateBtn = new JButton("Yes");
		yesUpdateBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		noUpdateBtn = new JButton("No");
		noUpdateBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		yesDeleteBtn = new JButton("Yes");
		yesDeleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		noDeleteBtn = new JButton("No");
		noDeleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		
		manageMessageLabel = new JLabel();
		manageMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		manageMessageLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		manageMessageLabel.setVisible(false);
		
		addNewLabel = new JLabel("Add New Menu");
		addNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		
		foodNameLabel = new JLabel("Food Name");
		foodNameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		foodDetailLabel = new JLabel("Food Description");
		foodDetailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		foodPriceLabel = new JLabel("Food Price");
		foodPriceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		foodNameTxt = new JTextField();
		foodDetailTxt = new JTextField();
		foodPriceTxt = new JTextField();
		
		insertBtn = new JButton("Insert");
		insertBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
	
		addMessageLabel = new JLabel();
		addMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addMessageLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addMessageLabel.setVisible(false);
	}

	@Override
	public void addComponent() {
		confirmUpdatePanel.add(confirmUpdateLabel);
		confirmUpdatePanel.add(yesUpdateBtn);
		confirmUpdatePanel.add(noUpdateBtn);
		
		confirmDeletePanel.add(confirmDeleteLabel);
		confirmDeletePanel.add(yesDeleteBtn);
		confirmDeletePanel.add(noDeleteBtn);
		
		confirmContainerPanel.add(confirmUpdatePanel);
		confirmContainerPanel.add(manageMessageLabel);
		confirmContainerPanel.add(confirmDeletePanel);
		
		controlBtnPanel.add(updateAvailabilityBtn);
		controlBtnPanel.add(removeBtn);
		
		controlPanel.add(controlBtnPanel);
		controlPanel.add(confirmContainerPanel);
		
		foodDataPanel.add(scrollPane);
		foodDataPanel.add(controlPanel);
		
		insertPanel.add(insertBtn);
		insertPanel.add(addMessageLabel);
		
		formPanel.add(foodNameLabel);
		formPanel.add(foodNameTxt);
		formPanel.add(foodDetailLabel);
		formPanel.add(foodDetailTxt);
		formPanel.add(foodPriceLabel);
		formPanel.add(foodPriceTxt);
		
		foodFormPanel.add(addNewLabel, BorderLayout.NORTH);
		foodFormPanel.add(formPanel, BorderLayout.CENTER);
		foodFormPanel.add(insertPanel, BorderLayout.SOUTH);
		
		titlePanel.add(titleLabel);
		
		manageFoodPanel.add(titlePanel, BorderLayout.NORTH);
		manageFoodPanel.add(foodDataPanel, BorderLayout.CENTER);
		manageFoodPanel.add(foodFormPanel, BorderLayout.SOUTH);
		
		navPanel.add(homeBtn);
		
		contentPane.add(navPanel, BorderLayout.NORTH);
		contentPane.add(manageFoodPanel, BorderLayout.CENTER);
		
		loadData();
	}

	@Override
	public void addListener() {
		foodTable.addMouseListener(new MouseListener() {
			
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
				int row = foodTable.getSelectedRow();
				
				foodIdLabel = new JLabel();
				foodStatusLabel = new JLabel();
				
				foodIdLabel.setText(foodTable.getValueAt(row, 0).toString());
				foodStatusLabel.setText(foodTable.getValueAt(row, 4).toString());
			}
		});

		homeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new HomeChefPage().showForm();
				
			}
		});
		
		updateAvailabilityBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				manageMessageLabel.setVisible(false);
				addMessageLabel.setVisible(false);
				confirmDeletePanel.setVisible(false);
				
				if(foodIdLabel != null)
				{
					confirmUpdatePanel.setVisible(true);
				}else
				{
					manageMessageLabel.setVisible(true);
					manageMessageLabel.setText("Please choose the food first!");
				}
			}
		});
		
		yesUpdateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FoodController.getInstance().updateAvailability(Integer.parseInt(foodIdLabel.getText()), foodStatusLabel.getText());
				loadData();
				manageMessageLabel.setText("Food availability has been changed!");
				manageMessageLabel.setVisible(true);
				confirmUpdatePanel.setVisible(false);
				addMessageLabel.setVisible(false);
				foodIdLabel = null;
				foodStatusLabel = null;
			}
		});
		
		noUpdateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmUpdatePanel.setVisible(false);
				addMessageLabel.setVisible(false);
			}
		});
		
		removeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				manageMessageLabel.setVisible(false);
				addMessageLabel.setVisible(false);
				confirmUpdatePanel.setVisible(false);
				
				if(foodIdLabel != null)
				{
					confirmDeletePanel.setVisible(true);
				}else
				{
					manageMessageLabel.setVisible(true);
					manageMessageLabel.setText("Please choose the food first!");
				}
			}
		});
		
		yesDeleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FoodController.getInstance().removeFood(Integer.parseInt(foodIdLabel.getText()));
				loadData();
				manageMessageLabel.setText("Food has been removed!");
				manageMessageLabel.setVisible(true);
				confirmDeletePanel.setVisible(false);
				addMessageLabel.setVisible(false);
				foodIdLabel = null;
				foodStatusLabel = null;
			}
		});
		
		noDeleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmDeletePanel.setVisible(false);
				addMessageLabel.setVisible(false);
			}
		});
		
		insertBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				manageMessageLabel.setVisible(false);
				
				if(FoodController.getInstance().insert(foodNameTxt.getText(), foodDetailTxt.getText(), foodPriceTxt.getText()))
				{
					addMessageLabel.setText("Insert Success!");
					
					loadData();
				}else
				{
					addMessageLabel.setText("Please Input Valid Data. Insert Failed!");
				}
				
				addMessageLabel.setVisible(true);
				
				foodNameTxt.setText("");
				foodDetailTxt.setText("");
				foodPriceTxt.setText("");
			}
		});
	}
	
	private void loadData()
	{
		foodData = new Vector<>();
		
		header = new Vector<>();
		header.add("Food ID");
		header.add("Food Name");
		header.add("Food Description");
		header.add("Food Price");
		header.add("Food Status");
		
		Vector<Model> list = FoodController.getInstance().getAll();
		
		for (Model model : list) {
			FoodModel foodModel = (FoodModel) model;
			
			detail = new Vector<>();
			detail.add(foodModel.getFoodId().toString());
			detail.add(foodModel.getName());
			detail.add(foodModel.getDescription());
			detail.add(foodModel.getPrice().toString());
			detail.add(foodModel.getStatus());
			
			foodData.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(foodData, header){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		foodTable.setModel(dtm);
	}

	private void initPanel() {
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(Color.ORANGE);
		setContentPane(contentPane);
		
		navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		navPanel.setBackground(Color.ORANGE);
		navPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		manageFoodPanel = new JPanel(new BorderLayout());
		manageFoodPanel.setBackground(Color.ORANGE);
		manageFoodPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
		
		titlePanel = new JPanel();
		titlePanel.setBorder(null);
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.setBorder(new EmptyBorder(0, 200, 5, 200));
		
		foodDataPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		foodDataPanel.setBackground(Color.ORANGE);
		foodDataPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		tablePanel = new JPanel();
		tablePanel.setBackground(Color.ORANGE);
		tablePanel.setBorder(new EmptyBorder(0, 0, 100, 0));
		
		controlPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		controlPanel.setBackground(Color.ORANGE);
		controlPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		controlBtnPanel = new JPanel(new GridLayout(1, 2, 0, 0));
		controlBtnPanel.setBackground(Color.ORANGE);
		
		confirmUpdatePanel = new JPanel(new GridLayout(1, 3, 5, 0));
		confirmUpdatePanel.setBackground(Color.ORANGE);
		confirmUpdatePanel.setVisible(false);
		
		confirmDeletePanel = new JPanel(new GridLayout(1, 3, 5, 0));
		confirmDeletePanel.setBackground(Color.ORANGE);
		confirmDeletePanel.setVisible(false);
		
		confirmContainerPanel = new JPanel();
		confirmContainerPanel.setBackground(Color.ORANGE);
		confirmContainerPanel.setBorder(new EmptyBorder(10, 85, 0, 85));
		
		foodFormPanel = new JPanel(new BorderLayout());
		foodFormPanel.setBackground(Color.ORANGE);
		foodFormPanel.setBorder(new EmptyBorder(20, 0, 25, 0));
		
		formPanel = new JPanel(new GridLayout(3, 2, 0, 10));
		formPanel.setBackground(Color.ORANGE);
		formPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
		
		insertPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		insertPanel.setBackground(Color.ORANGE);
	}
	
}
