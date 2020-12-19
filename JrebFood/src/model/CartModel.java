package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.core.Model;

public class CartModel extends Model {
	
	private Integer userId;
	private Integer foodId;
	private Integer qty;
	
	public CartModel() {
		this.tableName = "carts";
	}

	@Override
	public void insert() {
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void deleteCart(Integer userId, Integer foodId) {
		String query = String.format("DELETE FROM %s WHERE userId = %d AND foodId = %d", tableName, userId, foodId);
		con.executeUpdate(query);
	}

	@Override
	public Vector<Model> getAll() {
		Vector<Model> cartData = new Vector<>();
		
		String query = String.format("SELECT * FROM %s", tableName);
		ResultSet rs = con.execQuery(query);
		
		try {
			while(rs.next()) {
				Integer cartId = rs.getInt("cartId");
				Integer foodId = rs.getInt("foodId");
				Integer qty = rs.getInt("qty");
				
				CartModel cart = new CartModel();
				cart.setUserId(cartId);
				cart.setFoodId(foodId);
				cart.setQty(qty);
				
				cartData.add(cart);
			}
			
			return cartData;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void insertCart(int userId, int foodId) {
		Vector<Vector<Object>> data = getAllJoinedCart(userId);
		FoodModel foodModel = new FoodModel();
		int counter = 0;
		
		if(data.size() != 0) {
			for (int i = 0; i < data.size(); i++) {
				if(data.get(i).get(2).equals(foodModel.getNameById(foodId))) {
					String query = String.format("UPDATE %s SET qty = qty + 1 WHERE userId = %d AND foodId = %d", tableName, userId, foodId);
					con.executeUpdate(query);
					counter++;
					break;
				}
			}
		}
		
		if(counter == 0) {
			String query = String.format("INSERT INTO %s VALUES(?, ?, ?)", tableName);
			
			PreparedStatement ps = con.prepStatement(query);
			
			try {
				ps.setInt(1, userId);
				ps.setInt(2, foodId);
				ps.setInt(3, qty);
				
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Vector<Vector<Object>> getAllJoinedCart(int userId){
		Vector<Vector<Object>> cartList = new Vector<>();
		Vector<Object> data;
		
		String query =  String.format("SELECT c.userId, c.foodId, name, qty, price FROM %s c, %s f WHERE c.foodId = f.foodId AND userId = %d", tableName, "foods", userId);
		ResultSet rs = con.execQuery(query);
		
		try {
			while(rs.next()) {
				data = new Vector<>();
				data.add(rs.getInt("c.userId"));
				data.add(rs.getInt("c.foodId"));
				data.add(rs.getString("name"));
				data.add(rs.getInt("qty"));
				data.add(rs.getInt("qty") * rs.getInt("price"));
				
				cartList.add(data);
			}
			
			return cartList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
