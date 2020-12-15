package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.core.Model;

public class OrderModel extends Model{

	private Integer orderId;
	private String orderDate;
	private String address;
	private Integer userId;
	private Integer driverId;
	private String status;
	
	
	public OrderModel() {
		this.tableName = "orders";
	}

	@Override
	public void insert() {
		
	}

	@Override
	public void update() {
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Model> getAll() {
		Vector<Model> orderData = new Vector<>();
		
		String query = String.format("SELECT * FROM %s", this.tableName);
		ResultSet rs = con.execQuery(query);
		
		try {
			while (rs.next()) {
				OrderModel om = new OrderModel();
				
				om.setOrderId(rs.getInt("orderId"));
				om.setOrderDate(rs.getString("orderDate"));
				om.setAddress(rs.getString("address"));
				om.setUserId(rs.getInt("userId"));
				om.setDriverId(rs.getInt("driverId"));
				om.setStatus(rs.getString("status"));
				
				orderData.add(om);
			}
			
			return orderData;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void updateStatusAndDriverId()
	{
		String query = String.format("UPDATE %s SET status = 'Accepted', driverId = ? WHERE orderId = ?", tableName);
		
		PreparedStatement ps = con.prepStatement(query);
		
		try {
			ps.setInt(1, driverId);
			ps.setInt(2, orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateStatus()
	{
		String query = String.format("UPDATE %s SET status = ? WHERE orderId = ?", tableName);
		
		PreparedStatement ps = con.prepStatement(query);
		
		try {
			ps.setString(1, status);
			ps.setInt(2, orderId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Vector<Model> getAllAvailableOrder() {
		Vector<Model> orderData = new Vector<>();
		
		String query = String.format("SELECT orderId, userId, address, orderDate FROM %s WHERE status LIKE 'Not accepted'", this.tableName);
		ResultSet rs = con.execQuery(query);
		
		try {
			while (rs.next()) {
				OrderModel om = new OrderModel();
				
				om.setOrderId(rs.getInt("orderId"));
				om.setOrderDate(rs.getString("orderDate"));
				om.setAddress(rs.getString("address"));
				om.setUserId(rs.getInt("userId"));
				
				orderData.add(om);
			}
			
			return orderData;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Vector<Model> getAllTakenOrder(Integer driverId) {
		Vector<Model> orderData = new Vector<>();
		
		String query = String.format("SELECT orderId, orderDate, userId, address, status FROM %s "
				+ "WHERE (status LIKE 'Accepted' OR status LIKE 'Ordered' OR status LIKE 'Cooked') "
				+ "AND driverId = %d", this.tableName, driverId);
		ResultSet rs = con.execQuery(query);
		
		try {
			while (rs.next()) {
				OrderModel om = new OrderModel();
				
				om.setOrderId(rs.getInt("orderId"));
				om.setOrderDate(rs.getString("orderDate"));
				om.setUserId(rs.getInt("userId"));
				om.setAddress(rs.getString("address"));
				om.setStatus(rs.getString("status"));
				
				orderData.add(om);
			}
			
			return orderData;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Vector<Model> getAllDriverOrderHistory(Integer driverId) {
		Vector<Model> orderData = new Vector<>();
		
		String query = String.format("SELECT orderId, userId, address, orderDate FROM %s "
				+ "WHERE status LIKE 'Finished' "
				+ "AND driverId = %d", this.tableName, driverId);
		ResultSet rs = con.execQuery(query);
		
		try {
			while (rs.next()) {
				OrderModel om = new OrderModel();
				
				om.setOrderId(rs.getInt("orderId"));
				om.setUserId(rs.getInt("userId"));
				om.setAddress(rs.getString("address"));
				om.setOrderDate(rs.getString("orderDate"));
				
				orderData.add(om);
			}
			
			return orderData;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
