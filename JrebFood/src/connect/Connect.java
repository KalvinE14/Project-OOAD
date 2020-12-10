package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	
	private String USERNAME = "root";
	private String PASSWORD = "";
	private String DATABASE = "jrebfood";
	private String HOST = "localhost:3306"; 
	private String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

	private Connection con;
	private Statement stat;
	private ResultSet rs;
	private ResultSetMetaData rsm;
	private static Connect connect;
	
	private Connect() {
		try {  
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);  
            stat = con.createStatement(); 
            System.out.println("Connect Success");
        } catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("Connect Failed");
        }  
	}
	
	public synchronized static Connect getConnection()
	{
		if(connect == null)
		{
			connect = new Connect();
		}
		
		return connect;
	}
	
	public ResultSet execQuery(String query)
	{
		rs = null;
		
		try {
			rs = stat.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public void executeUpdate(String query)
	{
		try {
			stat.executeUpdate(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
	
	public PreparedStatement prepStatement(String query)
	{
    	PreparedStatement ps = null;
    	
    	try {
			ps = con.prepareStatement(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	return ps;
    }

}
