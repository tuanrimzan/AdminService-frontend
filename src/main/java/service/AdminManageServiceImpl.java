package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class AdminManageServiceImpl implements AdminManageService{
	
	//DB parameters
	private static final String USERNAME = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/electrogriddb";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String PASSWORD = "";
	private static Connection connection = null;
	private static String query = "";
	private static PreparedStatement preparedStatement = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	
	private static ArrayList<Employee> employeeList = null;
	String output = "";
	
	//Employee model
	private static Employee employee;
	
	//Connection
	private Connection connect() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			return connection;
		}
		else {
			try {
				Class.forName(DRIVER);
				connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
				System.out.println("Successfully Connected to the Electro Main Database");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return connection;
		}
	}
	
	//Insert employee
	@Override
	public String insertEmployee(Employee employee) {
		
		try {
			connection  = connect();
			if (connection == null ) {
				output = "Error while connectiong to the database";
				return output;
			}

			//Query
			query = "INSERT INTO `employee` (`empID`, `name`, `email`, `empType`)"
					+ " VALUES (?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, employee.getEmpID());
			preparedStatement.setString(2, employee.getName());
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setString(4, employee.getEmpType());
			preparedStatement.execute();

			connection.close();

			output = "Inserted Successfully";
			query = "";

		} catch (Exception e) {
			output = "Error while inserting the employee";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//View Employees
	@Override
	public ArrayList<Employee> viewEmployees() {
		//Employee attributes
		String empID = "";
		String name = "";
		String email = "";
		String empType = "";
		//Employee List
		employeeList = new ArrayList<Employee>();
		
		//Connection
		try {
			connection = connect();
			
			if(connection == null) {
				System.err.println("Error while connecting to the database");
				return null;
			}
			
			//Query
			query = "SELECT * FROM employee";
			
			//Execute
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			//Get all results
			while(resultSet.next()) {
				empID = resultSet.getString("empID");
				name = resultSet.getString("name");
				email = resultSet.getString("email");
				empType = resultSet.getString("empType");
				
				//Add to list
				employeeList.add(new Employee(empID, name, email, empType));
			}
			
		}catch(Exception e) {
			System.err.println("Error getting data " + e.getMessage());
		}
		
		return employeeList;
	}
	
	//Update employee
	@Override
	public String updateEmployee(String empID, Employee employee) {
		try {
			connection  = connect();
			if (connection == null ) {
				output = "Error while connectiong to the database";
				return output;
			}

			//Query
			query = "UPDATE employee SET name = ?, email = ?, empType = ? WHERE empID = " + "'"+empID+"'";

			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmail());
			preparedStatement.setString(3, employee.getEmpType());
			
			preparedStatement.executeUpdate();

			connection.close();

			output = "Updated Successfully";
			query = "";

		} catch (Exception e) {
			output = "Error while updating the employee";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//Delete employee
	@Override
	public String deleteEmployee(String empID) {
		//Connection
		try {
			connection = connect();
			
			if(connection == null) {
				output = "Error while connectiong to the database";
				return output;
			}
			
			//Query
			query = "DELETE FROM employee WHERE empID = " + "'"+empID+"'";
			statement = connection.createStatement();
			int del = statement.executeUpdate(query);
			
			if(del > 0) {
				output = "Employee removed";
			}
			else {
				output = "Employee not found";
			}
			
		}catch(Exception e) {
			output = "Error deleting data " + e.getMessage();
			System.err.println(output);
			return output;
		}
		return output;
	}
}
