package service;

import java.util.ArrayList;
import model.Employee;

public interface AdminManageService {
	public String insertEmployee(Employee employee);
	public ArrayList<Employee> viewEmployees();
	public String updateEmployee(String empID, Employee employee);
	public String deleteEmployee(String empID);
}
