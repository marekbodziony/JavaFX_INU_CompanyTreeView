

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.TreeItem;

public class Employee {

	// Employee fields
	private int empId; 
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Date hireDate;
	private String jobId;
	private float salary;
	private int managerId;
	private int depId;
	private TreeItem<Employee> treeItem;
	private boolean [] dispOptions = new boolean [10];
	
	// constructor
	public Employee (int empId, String firstName, String lastName, String email, String phone, Date hireDate, String jobId, float salary, String managerId, int depId){
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.hireDate = hireDate;
		this.jobId = jobId;
		this.salary = salary;
		if (managerId.length() == 0){ this.managerId = 0;}
		else if (managerId.length()!=0){ this.managerId = Integer.valueOf(managerId);}
		this.depId = depId;
		treeItem = new TreeItem<Employee>(this);
	}
	
	// getters
	public int getEmployeeId() {return empId;}
	public String getEmployeeFirstName(){ return firstName;}
	public String getEmployeeLastName(){ return lastName;}
	public String getEmployeeEmail(){return email;}
	public String getEmployeePhone() {return phone;}
	public Date getEmployeeHireDate() {return hireDate;}
	public String getEmployeeJobId() {return jobId;}
	public float getEmployeeSalary(){ return salary;}
	public int getManagerId() {return managerId;}
	public int getDepartamentId(){ return depId;}
	public TreeItem<Employee> getTreeItem() {return treeItem;}
	
	// setter
	public void setTreeItem(TreeItem<Employee> treeItem){ this.treeItem = treeItem;}
	
	// toString() method override to display specific information about employee
	public String toString(){
		
		String empId,firstName,lastName,email,phone,hireDate,jobId,salary,managerId,depId;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		if (!dispOptions[0]){empId = "";} 		else {empId ="" + this.empId + ", ";}
		if (!dispOptions[1]){firstName = "";} 	else {firstName = this.firstName + ", ";}
		if (!dispOptions[2]){lastName = "";} 	else {lastName = this.lastName + ", ";}
		if (!dispOptions[3]){email = "";} 		else {email = this.email + ", ";}
		if (!dispOptions[4]){phone = "";} 		else {phone = this.phone + ", ";}
		if (!dispOptions[5]){hireDate = "";} 	else {hireDate = ""+ sdf.format(this.hireDate) + ", ";}
		if (!dispOptions[6]){jobId = "";} 		else {jobId = this.jobId + ", ";}
		if (!dispOptions[7]){salary = "";} 		else {salary = Float.toString(this.salary) + ", ";}
		if (!dispOptions[8]){managerId = "";} 	else {managerId = "" + this.managerId + ", ";}
		if (!dispOptions[9]){depId = "";} 		else {depId = ""+ this.depId + ", ";}
		
		//System.out.println("*" + empId+firstName+lastName+email+phone+hireDate+jobId+salary+managerId+depId);
		
		return empId+firstName+lastName+email+phone+hireDate+jobId+salary+managerId+depId;
	}
	
	// set employee display options 
	public void setDispOptions(boolean [] options){
		dispOptions = options;
	}
	
}
