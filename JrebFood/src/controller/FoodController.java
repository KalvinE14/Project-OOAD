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
		return null;
	}
	
	public Vector<Model> getAllAvailableFood(){
		return foodModel.getAllAvailableFood();
	}
}
