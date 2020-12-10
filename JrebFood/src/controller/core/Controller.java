package controller.core;

import java.util.Vector;

import model.core.Model;
import view.core.View;

public abstract class Controller {

	public Controller() {
		
	}
	
	public abstract View view();
	public abstract Vector<Model> getAll();

}
