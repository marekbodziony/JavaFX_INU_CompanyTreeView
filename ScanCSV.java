import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Scanner;

public class ScanCSV {
	
	private Hashtable<Integer, Employee> empList = new Hashtable<Integer, Employee>();
	
	// get employees list from CSV file
		public Hashtable<Integer, Employee> getEmployeesListFromFile(String file) throws FileNotFoundException{
			
			Scanner scanner = new Scanner(new File(getClass().getResource(file).getFile()));
			scanner.useDelimiter("\r\n");
			
			scanner.next();
			while (scanner.hasNext()){
				String line = scanner.next();
				//System.out.println(line);
				String [] emplDetails = line.split(";");
							
				Employee emp = new Employee(Integer.valueOf(emplDetails[0]),emplDetails[1],emplDetails[2],emplDetails[3],emplDetails[4], new Date(),emplDetails[6], 
						Float.valueOf(emplDetails[7]),(emplDetails[8]),Integer.valueOf(emplDetails[9]));
				
				int empId = Integer.valueOf(emplDetails[0]);
				empList.put(empId,emp);   
			}
			scanner.close();
			
			return empList;
		}
}
