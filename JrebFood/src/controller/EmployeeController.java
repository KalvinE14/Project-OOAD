package controller;

import java.util.Vector;

import controller.core.Controller;
import model.EmployeeModel;
import model.OrderModel;
import model.core.Model;
import view.AvailableOrderPage;
import view.HomeDriverPage;
import view.LoginEmployeePage;
import view.core.View;

public class EmployeeController extends Controller{

	private EmployeeModel employeeModel;
	private static EmployeeController controller;
	private Integer employeeId;
	
	public EmployeeController() {
		employeeModel = new EmployeeModel();
	}
	
	public static EmployeeController getInstance() {
		if(controller == null)
		{
			return new EmployeeController();
		}else
		{
			return controller;
		}
	}

	@Override
	public View view() {
		return new LoginEmployeePage();
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Boolean isEmployeeAvailable(String email, String password) {
		if(!employeeModel.getSpecificEmployee(email, password).isEmpty())
		{
			return true;
		}
		
		return false;
	}
	
	public void authenticateEmployee(String email, String password)
	{
		if(isEmployeeAvailable(email, password))
		{
			Vector<Model> employee = new Vector<>();
			employee = employeeModel.getSpecificEmployee(email, password);
			
			for (Model model : employee) {
				EmployeeModel employeeModel = (EmployeeModel) model;
				
				if(employeeModel.getStatus().equals("Inactive"))
				{
					new LoginEmployeePage().showForm();
				}
				
				this.employeeId = employeeModel.getEmployeeId();
				
				// Role = driver
				if(employeeModel.getRoleId() == 1)
				{
					OrderController.getInstance().setDriverId(this.employeeId);
					
					new HomeDriverPage().showForm();
				}
			}
		}else
		{
			new LoginEmployeePage().showForm();
		}
	}

}
