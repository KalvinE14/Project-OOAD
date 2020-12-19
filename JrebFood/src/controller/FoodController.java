package controller;

import java.util.Vector;

import controller.core.Controller;
import model.FoodModel;
import model.core.Model;
import view.core.View;

public class FoodController extends Controller {
	
	private FoodModel foodModel;
	private static FoodController instance;

	public FoodController() {
		foodModel = new FoodModel();
	}
	
	public static FoodController getInstance() {
		if(instance == null)
		{
			return new FoodController();
		}else
		{
			return instance;
		}
	}

	@Override
	public View view() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return foodModel.getAll();
	}
	
	public Vector<Model> getAllAvailableFood(){
		return foodModel.getAllAvailableFood();
	}
	
	public Boolean insert(String foodName, String foodDescription, String foodPrice)
	{
		FoodModel fm = new FoodModel();
		
		if(foodName.isEmpty() || foodDescription.isEmpty() || foodPrice.isEmpty())
		{
			return false;
		}
		
		try {
			Integer.parseInt(foodPrice);
		} catch (Exception e) {
			return false;
		}
		
		if(fm.getDataByName(foodName).size() != 0)
		{
			return false;
		}
		
		fm.setName(foodName);
		fm.setDescription(foodDescription);
		fm.setPrice(Integer.parseInt(foodPrice));
		fm.setStatus("Available");
		fm.insert();
		
		return true;
	}
	
	public void updateAvailability(Integer foodId, String status)
	{
		FoodModel fm = new FoodModel();
		
		if(status.equals("Available"))
		{
			fm.setFoodId(foodId);
			fm.updateAvailability("Not Available");
		}else
		{
			fm.setFoodId(foodId);
			fm.updateAvailability("Available");
		}
	}
	
	public void removeFood(Integer foodId)
	{
		FoodModel fm = new FoodModel();
		
		fm.setFoodId(foodId);
		
		fm.removeFood();
	}
}
