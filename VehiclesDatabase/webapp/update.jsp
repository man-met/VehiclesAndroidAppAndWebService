<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<title>Update</title>
	</head>
	<body>
		<div class="mx-3">
			<h1>Update</h1>
			
			<c:choose>
				<c:when test="${!loggedin}">
					<h2>You have to login to access this page</h2>
					<br>
					<form action="./login">
						<button type="submit" class="btn btn-primary" >Log in</button>
					</form>
				</c:when>
				<c:otherwise>
					<!--  display the form to update the vehicle record in the database -->
					<form action="./update" method="post">
						<input type="hidden" name="vehicle_id" value="${vehicleInfo.getVehicle_id()}">
						<br>
						Make: <input type="text" name="make" value="${vehicleInfo.getMake()}">
						<br>
						Model: <input type="text" name="model" value="${vehicleInfo.getModel()}">
						<br>
						Year: <input type="text" name="year" value="${vehicleInfo.getYear()}">
						<br>
						Price: <input type="text" name="price" value="${vehicleInfo.getPrice()}">
						<br>
						License: <input type="text" pattern="[a-zA-Z]{1}[a-zA-Z0-9]{1}[0-9]{2}[a-zA-Z]{3}" name="license_number" value="${vehicleInfo.getLicense_number()}">
						<br>
						Colour: <input type="text" name="colour" value="${vehicleInfo.getColour()}">
						<br>
						Doors: <input type="text" name="number_doors" value="${vehicleInfo.getNumber_doors()}">
						<br>
						Transmission: <input type="text" name="transmission" value="${vehicleInfo.getTransmission()}">
						<br>
						Mileage: <input type="text" name="mileage" value="${vehicleInfo.getMileage()}">
						<br>
						Fuel: <input type="text" name="fuel_type" value="${vehicleInfo.getFuel_type()}">
						<br>
						Engine Size: <input type="text" name="engine_size" value="${vehicleInfo.getEngine_size()}">
						<br>
						Body Style: <input type="text" name="body_style" value="${vehicleInfo.getBody_style()}">
						<br>
						Condition: <input type="text" name="condition" value="${vehicleInfo.getCondition()}">
						<br>
						Notes: <input type="text" name="notes" value="${vehicleInfo.getNotes()}">
						<br>
						<br>
						<input type="submit" value="Update" class="btn btn-warning">
					</form>
					<br>
					<form action="./home">
						<button type="submit" class="btn btn-secondary">Cancel</button>
					</form>
				</c:otherwise>
			</c:choose>
			
		</div>
	</body>
</html>