package controller;

import java.util.Vector;

import controller.core.Controller;
import model.UserModel;
import model.core.Model;
import view.HomeUserPage;
import view.LoginUserPage;
import view.RegisterPage;
import view.RegisterSuccessPage;
import view.core.View;

public class UserController extends Controller {
	
	private UserModel userModel;
	private static UserController instance;
	private static Integer userId;

	public UserController() {
		userModel = new UserModel();
	}
	
	public static UserController getInstance() {
		if(instance == null)
		{
			return new UserController();
		}else
		{
			return instance;
		}
	}

	@Override
	public View view() {
		return new RegisterPage();
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void validateRegistration(String email, String password, String name, String address, String phoneNumber) {
		UserModel user = new UserModel();
		user = userModel.getUserByEmailPass(email, password);
		
		if(user == null) {
			UserModel userModel = new UserModel();
			userModel.setName(name);
			userModel.setAddress(address);
			userModel.setEmail(email);
			userModel.setPhoneNumber(phoneNumber);
			userModel.setPassword(password);
			
			userModel.insert();
			new RegisterSuccessPage().showForm();
		}
		else {
			new LoginUserPage().showForm();
		}
	}
	
	public void validateLogin(String email, String password) {
		UserModel user = new UserModel();
		user = userModel.getUserByEmailPass(email, password);
		
		if(user == null) {
			new LoginUserPage().showForm();
		}
		else {
			this.userId = user.getUserId();
			new HomeUserPage().showForm();
		}
	}
	
	public UserModel getUserByUserId() {
		return userModel.getUserByUserId(this.userId);
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
