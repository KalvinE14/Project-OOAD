package controller;

import java.util.Vector;

import controller.core.Controller;
import model.CartModel;
import model.core.Model;
import view.CartPage;
import view.core.View;

public class CartController extends Controller {
	
	private static CartController instance;
	private CartModel cartModel;
	
	public CartController() {
		cartModel = new CartModel();
	}
	
	public static CartController getInstance() {
		if(instance == null)
		{
			return new CartController();
		}else
		{
			return instance;
		}
	}

	@Override
	public View view() {
		return new CartPage();
	}

	@Override
	public Vector<Model> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Vector<Vector<Object>> getCartDataByUserId(){
		Vector<Vector<Object>> cartData = new Vector<>();
		UserController userController = UserController.getInstance();
		cartData = cartModel.getAllJoinedCart(userController.getUserId());
		return cartData;
	}
	
	public void deleteSpecificCart(int foodId) {
		UserController userController = UserController.getInstance();
		cartModel.deleteCart(userController.getUserId(), foodId);
	}

	public void addCart(int foodId) {
		UserController userController = UserController.getInstance();
		
		CartModel cart = new CartModel();
		cart.setUserId(userController.getUserId());
		cart.setFoodId(foodId);
		cart.setQty(1);
		
		cart.insertCart(userController.getUserId(), foodId);
	}
	
	public Integer getTotalPrice() {
		Vector<Vector<Object>> cartData = new Vector<>();
		UserController userController = UserController.getInstance();
		cartData = cartModel.getAllJoinedCart(userController.getUserId());
		
		Integer totalPrice = 0;
		
		for (Vector<Object> cart : cartData) {
			totalPrice = totalPrice + Integer.parseInt(cart.get(4).toString());
		}
		
		return totalPrice;
	}
}
