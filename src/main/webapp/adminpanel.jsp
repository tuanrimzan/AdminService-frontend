<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employees</title>
<link rel="stylesheet" href="views/bootstrap.min.css" />
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/main.js"></script>
</head>
<body>
	<div class="container"> 
		<div class="row">
			<div class="col-8"> 
				<h1 id="txtForm" class="m-3">Add Employee Details</h1> 
				<form id="formEmployee">
					<!-- Employee ID -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblID">ID: </span>
						</div>
							<input type="text" maxlength=5 id="txtID" name="empID" required>
					</div>
					<!-- Employee Name -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblName">Name: </span>
						</div>
							<input type="text" id="txtName" name="name" required>
					</div>
					<!-- Employee Email -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblEmail">Email: </span>
						</div>
							<input type="text" id="txtEmail" name="email" required>
					</div>
					<!-- Employee Type -->
					<div class="input-group input-group-sm mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="lblType">Type: </span>
						</div>
						<select id="ddlType" name="empType" required>
						<option value="0">--Select Type--</option>
						<option value="Engineer">Engineer</option>
						<option value="Supporter">Supporter</option>
						</select>
					</div>
					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<input type="button" id="btnSave" value="Save" class="btn btn-primary">
				</form>
			</div>
		</div>
		<br> 
		<div class="row">
			<div id="employeesTable" class="card col-12" style="margin-top:10px">
				<h1>Employee Table</h1>
				<div id="card_table"></div>
			</div>
		</div>
	</div>
</body>
</html>