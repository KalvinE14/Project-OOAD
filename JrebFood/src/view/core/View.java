package view.core;

import javax.swing.JFrame;

public abstract class View extends JFrame{
	
	protected int width;
	protected int height;

	public View() {
		initialize();
		addComponent();
		addListener();
	}
	
	public void showForm(){
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	
	public abstract void initialize();
	public abstract void addComponent();
	public abstract void addListener();

}
