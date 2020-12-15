package controller;

import java.util.Vector;

import controller.core.Controller;
import model.OrderModel;
import model.core.Model;
import view.AvailableOrderPage;
import view.DriverDetailOrderHistoryPage;
import view.DriverDetailOrderPage;
import view.TakenOrderDetailPage;
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
	
	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

}
