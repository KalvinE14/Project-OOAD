import connect.Connect;
import view.AvailableOrderPage;
import view.InitialPage;

public class Main {

	public Main() {
		new InitialPage().showForm();
		new AvailableOrderPage().showForm();
		
		Connect.getConnection();
	}

	public static void main(String[] args) {
		new Main();
	}

}
