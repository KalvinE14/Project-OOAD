package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.core.Model;

public class UserModel extends Model{
	
	private Integer userId;
	private String name;
	private String address;
	private String email;
	private String phoneNumber;
	private String password;

	public UserModel() {
		this.tableName = "users";
	}

	@Override
	public void insert() {
		String query = String.format("INSERT INTO %s VALUES(null, ?, ?, ?, ?, ?)", tableName);
		
		PreparedStatement ps = con.prepStatement(query);
		
		try {
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setString(3, email);
			ps.setString(4, phoneNumber);
			ps.setString(5, password);
			
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
		// TODO Auto-generated method stub
		return null;
	}
	
	public UserModel getUserByEmailPass(String email, String password) {
		String query = String.format("SELECT * FROM %s WHERE email LIKE '%s' AND password LIKE '%s'", this.tableName, email, password);
		ResultSet rs = con.execQuery(query);
		
		try {
			if(rs.next()) {
				UserModel user = new UserModel();
				user.setUserId(rs.getInt("userId"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setPhoneNumber(rs.getString("phoneNumber"));
				user.setPassword(rs.getString("password"));
				
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public UserModel getUserByUserId(Integer userId) {
		String query = String.format("SELECT * FROM %s WHERE userId = %d", this.tableName, userId);
		ResultSet rs = con.execQuery(query);
		
		try {
			if(rs.next()) {
				UserModel user = new UserModel();
				user.setUserId(rs.getInt("userId"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setPhoneNumber(rs.getString("phoneNumber"));
				user.setPassword(rs.getString("password"));
				
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public UserModel getUserByOrderId(Integer orderId) {
		String query = String.format("SELECT name, u.address, phoneNumber, email FROM %s u JOIN %s o "
				+ "ON u.userId=o.userId "
				+ "WHERE orderId = %d", this.tableName, "orders", orderId);
		ResultSet rs = con.execQuery(query);
		
		try {
			if(rs.next()) {
				UserModel user = new UserModel();
				user.setName(rs.getString("name"));
				user.setAddress(rs.getString("address"));
				user.setPhoneNumber(rs.getString("phoneNumber"));
				user.setEmail(rs.getString("email"));
				
				return user;
			}
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
