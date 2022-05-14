package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Employee;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
       
    AdminManageController admin = new AdminManageController();
    
    // Convert request parameters to a Map
    private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = admin.viewEmployees();

		response.getWriter().write(output);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String res;
		
		String empID = request.getParameter("empID");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String empType = request.getParameter("empType");

		String output = admin.insertEmployee(empID, name, email, empType);

		// Get items
		String employees = admin.viewEmployees();

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (output != "error") {
			res = "{\"status\":\"success\", \"data\": \"" + employees + "\"}";
		} else {
			res = "{\"status\":\"error\", \"data\": \"" + output + "\"}";
		}

		response.getWriter().write(res);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);

		String res;
		
		
		
		String empID = paras.get("empID").toString();
		String name = paras.get("name").toString();
		name = name.replaceAll("\\W" , " ");
		String email = paras.get("email").toString();
		email = email.replaceAll("(%40)" , "@");
		String empType = paras.get("empType").toString();

		String output = admin.updateEmployee(empID, name, email, empType);

		// Get items
		String employees = admin.viewEmployees();

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (output != "error") {
			res = "{\"status\":\"success\", \"data\": \"" + employees + "\"}";
		} else {
			res = "{\"status\":\"error\", \"data\": \"" + output + "\"}";
		}

		response.getWriter().write(res);
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		System.out.println(paras.get("empID").toString());
		String output = admin.deleteEmployee(paras.get("empID").toString());

		String employees = admin.viewEmployees();

		if (output != "error") {
			output = "{\"status\":\"success\", \"data\": \"" + employees + "\"}";
		} else {
			output = "{\"status\":\"error\", \"data\": \"" + output + "\"}";
		}
		response.getWriter().write(output);
	}

}
