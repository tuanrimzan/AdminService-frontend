package controller;

import java.util.ArrayList;
import java.util.Iterator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Employee;
import service.AdminManageService;
import service.AdminManageServiceImpl;

@Path("/Employee")
public class AdminManageController {

	//Employee service
	AdminManageService adminService = new AdminManageServiceImpl();
	ArrayList<Employee> employees = new ArrayList<>();
	
	//Insert
	@POST
	@Path("/Add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmployee(@FormParam("empID") String empID, @FormParam("name") String name
			,@FormParam("email") String email, @FormParam("empType") String empType) {
		
		String output = adminService.insertEmployee(new Employee(empID, name, email, empType));
		return output;
	}

	//View
	@GET
	@Path("/View")
	@Produces(MediaType.APPLICATION_JSON)
	public String viewEmployees(){
		
		String output = "";
		
		employees = adminService.viewEmployees();
		//Return table
		output = "<table class='table table-striped' border='1'>"
				 + "<tr><th>ID</th>"
				 + "<th>Name</th>"
				 + "<th>Email</th>"
				 + "<th>Type</th>";
		
		Iterator iter = employees.iterator();
	    while (iter.hasNext()) {
	    	Employee employee = (Employee) iter.next();
	    	  
	    	output += "<tr><td>" + employee.getEmpID() + "</td>";
	    	output += "<td>" + employee.getName() + "</td>";
	    	output += "<td>" + employee.getEmail() + "</td>";
	    	output += "<td>" + employee.getEmpType()+ "</td>";
	    	  
	    	  
	    	output += "<td><input id='btnUpdate' type='button' value='Update' "
	    			+ "class='btn btn-secondary' data-empID='" + employee.getEmpID() + "'></td>"
	    			+ "<td><input id='btnRemove' type='button' value='Remove' "
	    			+ "class='btn btn-danger' data-empID='" + employee.getEmpID() + "'></td></tr>";
	    }
		return output;
	}

	//Delete
	@DELETE
	@Path("/Delete/{empID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmployee(@PathParam("empID") String empID) {
		
		String response = adminService.deleteEmployee(empID);
		return response;
	}

	//Update
	@PUT
	@Path("/Update/{empID}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmployee(@PathParam("empID") String empID , @FormParam("name") String name,
			@FormParam("email") String email, @FormParam("empType") String empType) {

		String output = adminService.updateEmployee(empID, new Employee(
					null, name, email, empType));
		return output;
	}
}
