package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.core.Model;

public class OrderDetailModel extends Model{

	private Integer orderId;
	private Integer foodId;
	private Integer qty;
	
	public OrderDetailModel() {
		this.tableName = "order_details";
	}

	@Override
	public void insert() {
		String query = String.format("INSERT INTO %s VALUES(?, ?, ?)", tableName);
		
		PreparedStatement ps = con.prepStatement(query);
		
		try {
			ps.setInt(1, orderId);
			ps.setInt(2, foodId);
			ps.setInt(3, qty);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public Integer getTotalOrderPrice(Integer orderId) {
		String query = String.format("SELECT * FROM %s od, %s f WHERE orderId = %d AND od.foodId = f.foodId", tableName, "foods", orderId);
		ResultSet rs = con.execQuery(query);
		
		
		try {
			Integer totalPrice = 0;
			while(rs.next()) {
				totalPrice += rs.getInt("qty") * rs.getInt("price");
			}
			return totalPrice;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public Vector<Model> getAll() {
		Vector<Model> orderDetailData = new Vector<>();
		
		String query = String.format("SELECT * FROM %s", this.tableName);
		ResultSet rs = con.execQuery(query);
		
		try {
			while (rs.next()) {
				OrderDetailModel odm = new OrderDetailModel();
				
				odm.setOrderId(rs.getInt("orderId"));
				odm.setFoodId(rs.getInt("foodId"));
				odm.setQty(rs.getInt("qty"));
				
				orderDetailData.add(odm);
			}
			
			return orderDetailData;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Vector<Model> getDetailByOrderId(Integer orderId) {
		Vector<Model> orderDetailData = new Vector<>();
		
		String query = String.format("SELECT * FROM %s WHERE orderId=%d", this.tableName, orderId);
		ResultSet rs = con.execQuery(query);
		
		try {
			while (rs.next()) {
				OrderDetailModel odm = new OrderDetailModel();
				
				odm.setOrderId(rs.getInt("orderId"));
				odm.setFoodId(rs.getInt("foodId"));
				odm.setQty(rs.getInt("qty"));
				
				orderDetailData.add(odm);
			}
			
			return orderDetailData;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Vector<Model> getDriverDetailOrderHistory(Integer orderId) {
		Vector<Model> orderDetailData = new Vector<>();
		
		String query = String.format("SELECT od.orderId, od.foodId, od.qty FROM %s od JOIN orders o ON od.orderId=o.orderId "
				+ "WHERE status LIKE 'Finished' AND od.orderId=%d", this.tableName, orderId);
		ResultSet rs = con.execQuery(query);
		
		try {
			while (rs.next()) {
				OrderDetailModel odm = new OrderDetailModel();
				
				odm.setOrderId(rs.getInt("orderId"));
				odm.setFoodId(rs.getInt("foodId"));
				odm.setQty(rs.getInt("qty"));
				
				orderDetailData.add(odm);
			}
			
			return orderDetailData;
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

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
}
