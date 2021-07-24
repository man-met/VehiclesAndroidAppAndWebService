<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vehicle Sale Details</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<div class="container">
	<h3 class="text-center mt-3">Enter Vehicle Sale Record</h3>
	<!-- display the form to enter the sale data of the vehicle -->
		<form action="./soldStatus" method="post">
			<div class="form-group">
				<label for="vehicle_id">Vehicle ID</label>
				<input type="text" class="form-control" id="vehicle_id"  name="vehicle_id" value="${vehicle_id}" readonly>
			</div>
			<div class="form-group">
				<label for="date_sold">Date Sold</label>
				<input type="date" class="form-control" id="date_sold" name="date" value="">
			</div>
			<div class="form-group">
				<label for="vehicle_price">Price Sold</label>
				<input type="number" class="form-control" id="vehicle_price" name="price" placeholder="Enter vehicle sold price">
			</div>
			<div>
				<label for="vehicle_status">Status</label>
				<input type="text" name="status" class="form-control" id="vehicle_status" value="" maxlength="20" placeholder="Enter status e.g. Sold">
			</div>
			<br>
			<br>
			<input type="submit" name="save" value="Save" class="btn btn-success">
			<input type="submit" name="cancel" value="Cancel" class="btn btn-warning">
		</form>
	</div>
</body>
</html>