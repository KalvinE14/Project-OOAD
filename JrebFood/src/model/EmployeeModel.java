package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import model.core.Model;

public class EmployeeModel extends Model{

	private Integer employeeId;
	private Integer roleId;
	private String name;
	private String dob;
	private String email;
	private String password;
	private String status;
	
	
	public EmployeeModel() {
		this.tableName = "employees";
	}

	@Override
	public void insert() {
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Model> getAll() {
		Vector<Model> employeeData = new Vector<>();
		
		String query = String.format("SELECT * FROM %s", this.tableName);
		ResultSet rs = con.execQuery(query);
		
		try {
			while (rs.next()) {
				EmployeeModel em = new EmployeeModel();
				
				em.setEmployeeId(rs.getInt("employeeId"));
				em.setRoleId(rs.getInt("roleId"));
				em.setName(rs.getString("name"));
				em.setDob(rs.getString("dob"));
				em.setEmail(rs.getString("email"));
				em.setPassword(rs.getString("password"));
				em.setStatus(rs.getString("status"));
				
				employeeData.add(em);
			}
			
			return employeeData;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Vector<Model> getSpecificEmployee(String email, String password) {
		Vector<Model> employeeData = new Vector<>();
		
		String query = String.format("SELECT * FROM %s WHERE email LIKE '%s' AND password LIKE '%s'", this.tableName, email, password);
		ResultSet rs = con.execQuery(query);
		
		try {
			while (rs.next()) {
				EmployeeModel em = new EmployeeModel();
				
				em.setEmployeeId(rs.getInt("employeeId"));
				em.setRoleId(rs.getInt("roleId"));
				em.setName(rs.getString("name"));
				em.setDob(rs.getString("dob"));
				em.setEmail(rs.getString("email"));
				em.setPassword(rs.getString("password"));
				em.setStatus(rs.getString("status"));
				
				employeeData.add(em);
			}
			
			return employeeData;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
