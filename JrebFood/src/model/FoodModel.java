package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.core.Model;

public class FoodModel extends Model {
	
	private Integer foodId;
	private String name;
	private Integer price;
	private String description;
	private String status;

	public FoodModel() {
		this.tableName = "foods";
	}

	@Override
	public void insert() {
		String query = String.format("INSERT INTO %s VALUES(null, ?, ?, ?, ?)", tableName);
		
		PreparedStatement ps = con.prepStatement(query);
		
		try {
			ps.setString(1, name);
			ps.setInt(2, price);
			ps.setString(3, description);
			ps.setString(4, status);
			
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

	@Override
	public Vector<Model> getAll() {
		String query = String.format("SELECT * FROM %s", tableName);
		ResultSet rs = con.execQuery(query);
		
		Vector<Model> foods = new Vector<>();
		
		try {
			while(rs.next()) {
				FoodModel food = new FoodModel();
				food.setFoodId(rs.getInt("foodId"));
				food.setName(rs.getString("name"));
				food.setPrice(rs.getInt("price"));
				food.setDescription(rs.getString("description"));
				food.setStatus(rs.getString("status"));
				
				foods.add(food);
			}
			
			return foods;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public Vector<Model> getAllAvailableFood(){
		String query = String.format("SELECT * FROM %s WHERE status LIKE 'Available'", tableName);
		ResultSet rs = con.execQuery(query);
		
		Vector<Model> foods = new Vector<>();
		
		try {
			while(rs.next()) {
				FoodModel food = new FoodModel();
				food.setFoodId(rs.getInt("foodId"));
				food.setName(rs.getString("name"));
				food.setPrice(rs.getInt("price"));
				food.setDescription(rs.getString("description"));
				
				foods.add(food);
			}
			
			return foods;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public String getNameById(Integer foodId) {
		String query = String.format("SELECT * FROM %s WHERE foodId = %d", tableName,foodId);
		ResultSet rs = con.execQuery(query);
		
		try {
			String foodName = "";
			if(rs.next()) {
				foodName = rs.getString("name");
			}
			
			return foodName;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public Vector<Model> getDataByName(String name)
	{
		String query = String.format("SELECT * FROM %s WHERE name LIKE '%s'", tableName, name);
		ResultSet rs = con.execQuery(query);
		
		Vector<Model> foods = new Vector<>();
		
		try {
			while(rs.next()) {
				FoodModel food = new FoodModel();
				food.setFoodId(rs.getInt("foodId"));
				food.setName(rs.getString("name"));
				food.setPrice(rs.getInt("price"));
				food.setDescription(rs.getString("description"));
				food.setStatus(rs.getString("status"));
				
				foods.add(food);
			}
			
			return foods;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	public void updateAvailability(String status)
	{
		String query = String.format("UPDATE %s SET status = '%s' WHERE foodId = ?", tableName, status);
		
		PreparedStatement ps = con.prepStatement(query);
		
		try {
			ps.setInt(1, foodId);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeFood()
	{
		String query = String.format("DELETE FROM %s WHERE foodId = ?", tableName);
		
		PreparedStatement ps = con.prepStatement(query);
		
		try {
			ps.setInt(1, foodId);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
