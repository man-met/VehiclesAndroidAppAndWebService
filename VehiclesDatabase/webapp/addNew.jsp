<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<title>Add Vehicle</title>
	</head>
	<body>
		<div class="mx-3">
			<h1 class="text-center mt-3">Add New Vehicle</h1>
			<div class="container">
			<c:choose>
			
				<c:when test="${!loggedin}">
					<h2>You have to login to access this page</h2>
					<br>
					
					<form action="./login">
						<button type="submit" class="btn btn-primary" >Log in</button>
					</form>
				</c:when>
				<c:otherwise>
				
					<form action="./addNew" method="post">
						<input type="hidden" name="vehicle_id" value="-999">
						<div class="form-group">
							<label for="make">Make</label>
							<input type="text" class="form-control" id="make" name="make" value="">
						</div>
						<div class="form-group">
							<label for="model">Model</label>
							<input type="text" class="form-control" id="model" name="model" value="">
						</div>
						<div class="form-group">
							<label for="year">Year</label>
							<input type="text" class="form-control" id="year" name="year" value="">
						</div>
						<div class="form-group">
							<label for="price">Price</label>
							<input type="text" class="form-control" id="price" name="price" value="">
						</div>
						<div class="form-group">
							<label for="license_number">Licenses Number</label>
							<input type="text" class="form-control" id="license_number" name="license_number" value="">
						</div>
						<div class="form-group">
							<label for="colour">Colour</label>
							<input type="text" class="form-control" id="colour" name="colour" value="">
						</div>
						<div class="form-group">
							<label for="number_doors">Number Doors</label>
							<input type="text" class="form-control" id="number_doors" name="number_doors" value="">
						</div>
						<div class="form-group">
							<label for="transmission">Transmission</label>
							<input type="text"  class="form-control" id="transmission" name="transmission" value="">
						</div>
						<div class="form-group">
							<label for="mileage">Mileage</label>
							<input type="text" class="form-control" id="mileage" name="mileage" value="">
						</div>
						<div class="form-group">
							<label for="fuel_type">Fuel Type</label>
							<input type="text" class="form-control" id="fuel_type" name="fuel_type" value="">
						</div>
						<div class="form-group">
							<label for="engine_size">Engine Size</label>
							<input type="text" class="form-control" id="engine_size" name="engine_size" value="">
						</div>
						<div class="form-group">
							<label for="body_style">Body Style</label>
							<input type="text" class="form-control" id="body_style" name="body_style" value="">
						</div>
						<div class="form-group">
							<label for="condition">Condition</label>
							<input type="text" class="form-control" id="condition" name="condition" value="">
						</div>
						<div class="form-group">
							<label for="notes">Notes</label>
							<input type="text" class="form-control" id="notes" name="notes" value="">
						</div>
						<input type="submit" value="addNew" class="btn btn-warning btn float-left">
					</form>
					<form action="./home">
						<button type="submit" class="btn btn-secondary">Cancel</button>
					</form>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</body>
</html>