package controller;

import java.util.Vector;

import controller.core.Controller;
import model.OrderDetailModel;
import model.OrderModel;
import model.core.Model;
import view.AvailableOrderPage;
import view.core.View;

public class OrderDetailController extends Controller{

	private OrderDetailModel orderDetailModel;
	private static OrderDetailController controller;
	private static Integer driverId;
	private static String status;
	private static Integer orderId;
	

	public OrderDetailController() {
		orderDetailModel = new OrderDetailModel();
	}
	
	public static OrderDetailController getInstance() {
		if(controller == null)
		{
			return new OrderDetailController();
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
	
	public void updateStatus()
	{
		if(this.status.equals("Accepted"))
		{
			OrderController.getInstance().updateStatus(this.orderId, "Ordered");
		}else if(this.status.equals("Cooked"))
		{
			OrderController.getInstance().updateStatus(this.orderId, "Finished");
		}
	}
	
	public Vector<Model> getDetailByOrderId() {
		return orderDetailModel.getDetailByOrderId(this.orderId);
	}
	
	public Vector<Model> getDriverDetailOrderHistory() {
		return orderDetailModel.getDriverDetailOrderHistory(this.orderId);
	}
	
	public Boolean isStatusAccepted()
	{
		System.out.println(this.status);
		
		if(this.status.equals("Accepted"))
		{
			return true;
		}
		
		return false;
	}
	
	public Boolean isStatusCooked()
	{
		if(this.status.equals("Cooked"))
		{
			return true;
		}
		
		return false;
	}
	
//	public Vector<Model> getDetailByOrderId(Integer orderId) {
//		return orderDetailModel.getDetailByOrderId(orderId);
//	}
	
//	public Vector<Model> getAllTakenOrder() {
//		return orderModel.getAllTakenOrder(this.driverId);
//	}
//	
//	public Vector<Model> getAllDriverOrderHistory() {
//		return orderModel.getAllDriverOrderHistory(this.driverId);
//	}
	
	public Integer getDriverId() {
		return driverId;
	}

	public static String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public static Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public View viewAvailableOrder()
	{
		return new AvailableOrderPage();
	}
}
