

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CompanyView {
	
	// components
	private Label viewLbl;
	private ToggleGroup viewTypeTGroup;
	private RadioButton employeesRBtn;
	private RadioButton structureRBtn;
	
	private CheckBox empId; 
	private CheckBox firstName;
	private CheckBox lastName;
	private CheckBox email;
	private CheckBox phone;
	private CheckBox hireDate;
	private CheckBox jobId;
	private CheckBox salary;
	private CheckBox managerId;
	private CheckBox depId;
	private boolean [] viewOptions = new boolean [10];
	
	private Button rollBtn;
	private Button okBtn;
	
	private TreeView<Employee> employeeTreeView;
	private TreeView<Employee> structureTreeView;
	
	private Hashtable<Integer, Employee> employeeList;
	
	
	public void show(Hashtable<Integer, Employee> empList){
		
		employeeList = empList;
		
		// title label
		viewLbl = new Label("Widok");
		viewLbl.setFont(Font.font(viewLbl.getFont().getFamily(),FontWeight.BOLD,viewLbl.getFont().getSize()+5));
		
		// view types radio buttons (we can chose employee view or structure view)
		viewTypeTGroup = new ToggleGroup();
		employeesRBtn = new RadioButton("Przelozony / podwladny");
		employeesRBtn.setToggleGroup(viewTypeTGroup);
		employeesRBtn.setSelected(true);
		structureRBtn = new RadioButton("Struktura organizacyjna");
		structureRBtn.setToggleGroup(viewTypeTGroup);
		
		// checkboxes to select which type of user data should be visible in company tree view 
		empId = new CheckBox("Identyfikator");
		empId.setSelected(true);
		firstName = new CheckBox("Imie"); 
		firstName.setSelected(true);
		lastName = new CheckBox("Nazwisko"); 
		lastName.setSelected(true);
		email = new CheckBox("E-mail");
		email.setSelected(true);
		phone = new CheckBox("Numer tel.");
		phone.setSelected(true);
		hireDate = new CheckBox("Data zatrudnienia");
		hireDate.setSelected(true);
		jobId = new CheckBox("Stanowisko");
		jobId.setSelected(true);
		salary = new CheckBox("Placa");
		managerId = new CheckBox("Id przelozonego");
		depId = new CheckBox("Dzial");
		
		// "roll" button options
		rollBtn = new Button("unroll");
		rollBtn.setPrefWidth(80);
		rollBtn.setOnAction(e-> {
			if (rollBtn.getText().equals("roll")) {
				rollBtn.setText("unroll");
				unrollEmployeesList();
				//System.out.println("Rozwijam liste ...........");
			}
			else if (rollBtn.getText().equals("unroll")) {
				rollBtn.setText("roll");
				rollEmployeesList();
				//System.out.println("Zwijam liste ...........");
			}
		});
		
		// "OK" button options
		okBtn = new Button("OK");
		okBtn.setPrefWidth(100);
		okBtn.setOnAction(e->{
			//employeeTreeView.refresh();
			if (employeesRBtn.isSelected()) {
				checkWhatShouldBeVisible();
				employeeTreeView.refresh();
				}
			else {setStructureTreeView();}
			
		});
		
		// employee tree view options		
		employeeTreeView = new TreeView<Employee>();
		employeeTreeView.setPadding(new Insets(5));
		employeeTreeView.setMaxSize(650,200);
		drawEmployeesTreeView();
		
		// employee tree view options		
		structureTreeView = new TreeView<Employee>();
		structureTreeView.setPadding(new Insets(5));
		structureTreeView.setMaxSize(650,200);
		
		HBox  viewTypeHBox = new HBox(50,employeesRBtn,structureRBtn);
		viewTypeHBox.setAlignment(Pos.CENTER);
				
		GridPane employeeDetailsGPane = new GridPane();
		employeeDetailsGPane.addColumn(0, empId,email,jobId);
		employeeDetailsGPane.addColumn(1, firstName,phone,salary,depId);
		employeeDetailsGPane.addColumn(2, lastName,hireDate,managerId);
		employeeDetailsGPane.setAlignment(Pos.CENTER);
		employeeDetailsGPane.setHgap(60);
		employeeDetailsGPane.setVgap(10);
		
		HBox rollHbox = new HBox(rollBtn);
		rollHbox.setMaxWidth(650);
		rollHbox.setAlignment(Pos.BASELINE_LEFT);
		
		VBox mainVbox = new VBox(30);
		mainVbox.getChildren().addAll(viewLbl,viewTypeHBox,employeeDetailsGPane,employeeTreeView,rollHbox,okBtn);
		mainVbox.setMargin(viewLbl, new Insets(60,0,0,0));
		mainVbox.setAlignment(Pos.TOP_CENTER);
		
		Scene scene = new Scene(mainVbox,800,650);
		
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Struktura firmy (TreeView)");
		stage.setResizable(false);
		stage.show();
	}
	// roll employees list on tree view
	private void rollEmployeesList() {
		
		Enumeration<Integer> keys = employeeList.keys();
		while (keys.hasMoreElements()){
			Employee emp = employeeList.get(keys.nextElement());
			emp.getTreeItem().setExpanded(false);
		}
		employeeTreeView.refresh();
	}
	// unroll employees list on tree view
	private void unrollEmployeesList() {

		Enumeration<Integer> keys = employeeList.keys();
		while (keys.hasMoreElements()){
			Employee emp = employeeList.get(keys.nextElement());
			emp.getTreeItem().setExpanded(true);
		}
		employeeTreeView.refresh();
	}

	// method set employees tree view
	private void drawEmployeesTreeView (){
		
		checkWhatShouldBeVisible();	
		System.out.println("Widok pracownik / przelozony");
				
		Enumeration<Integer> keys = employeeList.keys();
		
		while (keys.hasMoreElements()){
			
			Integer empId = keys.nextElement();
			Employee emp = employeeList.get(empId);
						
			//emp.setDispOptions(viewOptions);
			emp.getTreeItem().setExpanded(true);
			
			if(employeeList.get(empId).getManagerId() == 0){
				employeeTreeView.setRoot(emp.getTreeItem());
			}
			else {
				employeeList.get(employeeList.get(empId).getManagerId()).getTreeItem().getChildren().add(emp.getTreeItem());
			}
		}		
	}
	
	// method set company structure tree view 
	private void setStructureTreeView (){
		
		System.out.println("Widok struktury organizacji");
	}
	
	// method checks which employee details should be visible on tree list
	private void checkWhatShouldBeVisible(){
		
		// check what option should be visible
		if (empId.isSelected()){ viewOptions[0] = true;}	else { viewOptions[0] = false;}
		if (firstName.isSelected()){ viewOptions[1] = true;}	else { viewOptions[1] = false;}
		if (lastName.isSelected()){ viewOptions[2] = true;}	else { viewOptions[2] = false;}
		if (email.isSelected()){ viewOptions[3] = true;}	else { viewOptions[3] = false;}
		if (phone.isSelected()){ viewOptions[4] = true;}	else { viewOptions[4] = false;}
		if (hireDate.isSelected()){ viewOptions[5] = true;}	else { viewOptions[5] = false;}
		if (jobId.isSelected()){ viewOptions[6] = true;}	else { viewOptions[6] = false;}
		if (salary.isSelected()){ viewOptions[7] = true;}	else { viewOptions[7] = false;}
		if (managerId.isSelected()){ viewOptions[8] = true;}	else { viewOptions[8] = false;}
		if (depId.isSelected()){ viewOptions[9] = true;}	else { viewOptions[9] = false;}
		
		// set employee display options
		Enumeration<Integer> keys = employeeList.keys();
		while (keys.hasMoreElements()){			
			Employee emp = employeeList.get(keys.nextElement());						
			emp.setDispOptions(viewOptions);
		}
		
	}
	
}
