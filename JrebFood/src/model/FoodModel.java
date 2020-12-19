package model;

import java.sql.ResultSet;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Model> getAll() {
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
