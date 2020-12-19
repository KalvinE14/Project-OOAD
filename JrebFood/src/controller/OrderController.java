package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import controller.core.Controller;
import model.OrderModel;
import model.UserModel;
import model.core.Model;
import view.AvailableOrderPage;
import view.DriverDetailOrderHistoryPage;
import view.DriverDetailOrderPage;
import view.TakenOrderDetailPage;
import view.UserCurrentOrderDetailPage;
import view.UserDetailOrderPage;
import view.core.View;

public class OrderController extends Controller{

	private OrderModel orderModel;
	private static OrderController controller;
	private static Integer driverId;
	private static String status;
	private static Integer orderId;
	
	public OrderController() {
		orderModel = new OrderModel();
	}
	
	public static OrderController getInstance() {
		if(controller == null)
		{
			return new OrderController();
		}else
		{
			return controller;
		}
	}

	@Override
	public View view() {
		
		return null;
	}

	@Override
	public Vector<Model> getAll() {
		
		return null;
	}
	
	public void updateStatusAndDriverId(Integer orderId)
	{
		OrderModel om = new OrderModel();
		
		om.setDriverId(driverId);
		om.setOrderId(orderId);
		
		om.updateStatusAndDriverId();
	}
	
	public void updateStatus(Integer orderId, String status)
	{
		OrderModel om = new OrderModel();
		
		om.setStatus(status);
		om.setOrderId(orderId);
		
		om.updateStatus();
	}
	
	public Vector<Model> getAllAvailableOrder() {
		return orderModel.getAllAvailableOrder();
	}
	
	public Vector<Model> getAllTakenOrder() {
		return orderModel.getAllTakenOrder(this.driverId);
	}
	
	public Vector<Model> getAllDriverOrderHistory() {
		return orderModel.getAllDriverOrderHistory(this.driverId);
	}
	
	public Vector<Vector<Object>> getUserActiveOrder(){
		Vector<Vector<Object>> dataAcc = orderModel.getAllUserActiveOrder(UserController.getInstance().getUserId());
		Vector<Vector<Object>> dataNotAcc = orderModel.getAllNotAcceptedOrder(UserController.getInstance().getUserId());
		
		for (Vector<Object> data : dataNotAcc) {
			dataAcc.add(data);
		}
		return dataAcc;
	}

	public View viewAvailableOrder()
	{
		return new AvailableOrderPage();
	}
	
	public void viewDetailOrderByDriver(Integer orderId)
	{
		this.orderId = orderId;
		
		OrderDetailController.getInstance().setDriverId(this.driverId);
		OrderDetailController.getInstance().setOrderId(this.orderId);
		
		updateStatusAndDriverId(orderId);
		
		new DriverDetailOrderPage().showForm();
	}
	
	public void viewDetailTakenOrder(String status, Integer orderId)
	{
		this.status = status;
		this.orderId = orderId;
		
		OrderDetailController.getInstance().setDriverId(this.driverId);
		OrderDetailController.getInstance().setStatus(this.status);
		OrderDetailController.getInstance().setOrderId(this.orderId);
		
		new TakenOrderDetailPage().showForm();
	}
	
	public void viewDriverDetailOrderHistory(Integer orderId)
	{
		this.orderId = orderId;
		
		OrderDetailController.getInstance().setDriverId(this.driverId);
		OrderDetailController.getInstance().setOrderId(this.orderId);
		
		new DriverDetailOrderHistoryPage().showForm();
	}
	
	public Vector<Model> viewUserOrderHistory() {
		Integer userId = UserController.getInstance().getUserId();
		return orderModel.getAllUserOrderHistory(userId);
	}
	
	public void viewUserOrderHistoryDetail(Integer orderId) {
		this.orderId = orderId;
		
		OrderDetailController.getInstance().setOrderId(orderId);
		
		new UserDetailOrderPage().showForm();
	}
	
	public void viewUserActiveOrderDetail(Integer orderId) {
		this.orderId = orderId;
		
		OrderDetailController.getInstance().setOrderId(orderId);
		
		new UserCurrentOrderDetailPage().showForm();
	}
	
	public void cancelOrder(Integer orderId, String status) {
		this.status = status;
		
		if(this.status.equals("Not accepted")) {
			orderModel.removeOrder(orderId);
		}
	}
	
	public void addOrder() {
		UserController userController = UserController.getInstance();
		
		OrderModel orderModel = new OrderModel();
		UserModel user = userController.getUserByUserId();
		
		Date date = new Date();
		String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		
		orderModel.setOrderDate(formattedDate);
		orderModel.setAddress(user.getAddress());
		orderModel.setUserId(userController.getUserId());
		orderModel.setStatus("Not accepted");
		
		orderModel.insert();
	}
	
	public Integer getLastOrderId() {
		return orderModel.getLastOrderId();
	}
	
	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

}
