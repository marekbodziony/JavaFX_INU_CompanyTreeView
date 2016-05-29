

import java.io.FileNotFoundException;
import java.util.Hashtable;

import javafx.application.Application;
import javafx.stage.Stage;

public class Test extends Application {

	private CompanyView companyStructure;
	private Hashtable<Integer, Employee> empList = new Hashtable<Integer,Employee>();
	
	// start method
	public void start(Stage primaryStage) throws FileNotFoundException {
		
		// get employees list from csv file
		ScanCSV csvFile = new ScanCSV();
		empList = csvFile.getEmployeesListFromFile("Employees.csv");
		
		// show company structure view
		companyStructure = new CompanyView();
		companyStructure.show(empList);
		
				
	}
	
	// main method
	public static void main(String[] args){
		launch(args);
	}
	
}
