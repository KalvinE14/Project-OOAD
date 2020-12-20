import connect.Connect;
import view.FinancialSummaryPage;
import view.FireEmployeePage;
import view.HireEmployeePage;
import view.HomeChefPage;
import view.HomeManagerPage;
import view.InitialPage;
import view.ManageFoodPage;

public class Main {

	public Main() {
//		new InitialPage().showForm();
//		new HomeChefPage().showForm();
//		new HomeManagerPage().showForm();
//		Connect.getConnection();
		new FinancialSummaryPage().showForm();
//		new HireEmployeePage().showForm();
	}

	public static void main(String[] args) {
		new Main();
	}

}
