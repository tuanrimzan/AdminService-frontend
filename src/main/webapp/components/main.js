$(document).ready(function() 
{ 
 $("#alertSuccess").hide(); 
 $("#alertError").hide(); 
 getAllEmployees();
}); 

function onItemSaveComplete(response, status) {
	if (status == "success") {

		var resultSet = JSON.parse(response.responseText);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();

			//Show updated table
			$("#employeesTable").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
}

function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response.responseText);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();

			$("#employeesTable").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

function validateItemForm() { 
	if ($("#txtID").val().trim() == "") { 
	 return "Insert employee id"; 
	}
	if ($("#txtName").val().trim() == "") { 
	 return "Insert employee name"; 
	}
	if ($("#txtEmail").val().trim() == "") { 
	 return "Insert employee email"; 
	}
	if ($("#ddlType").val() == "0") 
	{ 
	return "Select employee type"; 
	} 
	return true; 
}

$(document).on("click", "#btnSave", function(event) {

	$("#txtID").prop('disabled', false);
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	$.ajax(
		{
			url: "AdminServlet",
			type: type,
			data: $("#formEmployee").serialize(),
			dataType: "text",
			complete: function(response, status) {
				console.log(response.responseText);
				onItemSaveComplete(response, status);
			}
		});
	
});

function getAllEmployees() {
	$.ajax(
		{
			url: "AdminServlet",
			type: "GET",
			dataType: "text",
			complete: function(response, status) {
				$("#employeesTable").html(response.responseText);
				console.log(response);
			}
		});
}

$(document).on("click", "#btnRemove", function(event) {

	$.ajax(
		{
			url: "AdminServlet",
			type: "DELETE",
			data: "empID=" + $(this).data("empid"),
			dataType: "text",
			complete: function(response, status) {
				onItemDeleteComplete(response, status);

			}
		});
});

$(document).on("click", "#btnUpdate", function(event) {

	$("#txtForm").text("Update Employee Details");

	$("#hidItemIDSave").val($(this).closest("tr").find('td:eq(0)').text());
	$("#txtID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#txtID").prop('disabled', true);

	$("#txtName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#txtEmail").val($(this).closest("tr").find('td:eq(2)').text());
	$("#ddlType").val($(this).closest("tr").find('td:eq(3)').text());

});