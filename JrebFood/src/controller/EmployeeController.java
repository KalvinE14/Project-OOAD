package controller;

import java.util.Vector;

import controller.core.Controller;
import model.EmployeeModel;
import model.OrderModel;
import model.core.Model;
import view.AvailableOrderPage;
import view.HomeDriverPage;
import view.HomeManagerPage;
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
	
	public void fireSelectedEmployee(Integer employeeId) {
		employeeModel.fireEmployee(employeeId);
	}
	
	public void addEmployee(Integer roleId, String name, String dob, String email, String password) {
		EmployeeModel employee = new EmployeeModel();
		
		employee.setRoleId(roleId);
		employee.setName(name);
		employee.setDob(dob);
		employee.setEmail(email);
		employee.setPassword(password);
		employee.setStatus("Active");
		
		employee.insert();
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
				else if(employeeModel.getRoleId() == 3) {
					new HomeManagerPage().showForm();
				}
			}
		}else
		{
			new LoginEmployeePage().showForm();
		}
	}
	
	public Vector<Model> getActiveEmployee(){
		return employeeModel.getAllActiveEmployee();
	}
	
	public Vector<Vector<Object>> getFinancialSummary(){
		
		Vector<Vector<Object>> financialSummaryList = new Vector<>();
		Vector<Object> financialSummary = new Vector<>();
		
		Vector<Model> employeeList = employeeModel.getAllActiveEmployee();
		
		for (Model model : employeeList) {
			EmployeeModel em = (EmployeeModel) model;
			
			if(em.getRoleId() == 1) {
				OrderModel om = new OrderModel();
				
				financialSummary = new Vector<>();
				financialSummary.add(em.getEmployeeId());
				financialSummary.add(em.getName());
				financialSummary.add(om.getTotalOrderCompleteByEmployeeId(em.getEmployeeId()));
				financialSummary.add(OrderController.getInstance().getTotalOrderPriceByEmployeeId(em.getEmployeeId()));
				
				financialSummaryList.add(financialSummary);
			}
			
		}
		
		return financialSummaryList;
	}
	
	public Vector<Vector<Object>> getSpecificFinancialSummary(Integer employeeId){
		
		Vector<Vector<Object>> financialSummaryList = new Vector<>();
		Vector<Object> financialSummary = new Vector<>();
		
		Vector<Model> employeeList = employeeModel.getAllActiveEmployee();
		
		for (Model model : employeeList) {
			EmployeeModel em = (EmployeeModel) model;
			
			if(em.getEmployeeId() == employeeId) {
				if(em.getRoleId() == 1) {
					OrderModel om = new OrderModel();
					
					financialSummary = new Vector<>();
					financialSummary.add(em.getEmployeeId());
					financialSummary.add(em.getName());
					financialSummary.add(om.getTotalOrderCompleteByEmployeeId(em.getEmployeeId()));
					financialSummary.add(OrderController.getInstance().getTotalOrderPriceByEmployeeId(em.getEmployeeId()));
					
					financialSummaryList.add(financialSummary);
				}
			}
		}
		
		return financialSummaryList;
	}
	
	public Integer getTotalIncome() {
		Vector<Vector<Object>> financialRecap = new Vector<>();
		financialRecap = getFinancialSummary();
		
		Integer totalIncome = 0;
		
		for (Vector<Object> fr : financialRecap) {
			totalIncome += Integer.parseInt(fr.get(3).toString());
		}
		
		return totalIncome;
	}

}
