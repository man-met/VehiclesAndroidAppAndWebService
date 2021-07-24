<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Vehicles Database</title>
		<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	</head>
	
	
	<body>
		<div class="mx-3">
			<h1 class="text-center mt-3">Current Vehicles</h1>
			
			<c:choose>
			
				<c:when test="${!loggedin}">
					<form action="./login">
						<button type="submit" class="btn btn-primary btn float-right" >Log in</button>
					</form>
					<br>
				</c:when>
				
				<c:otherwise>
					<form action="./logout" class="d-inline bg-success">
						<button type="submit" class="btn btn-primary btn float-right" >Log out</button>
					</form>
					
					<form method="POST" action="./apiKey" class="d-inline bg-success">
						<button type="submit" class="btn btn-success float-right" >API Key</button>
					</form>
					
					<form action="./allSales" method="post">
						<button type="submit" name="show_all_sales"  class="btn btn-primary" >Show All Sales</button>
					</form>
					
					<br>
					
					<form action="./home" method="post">
						<label class="label label-primary">Search By</label>
						<select id="search" name="searchBy">
							<option value="all">All</option>
							<option value="make">Make</option>
							<option value ="model">Model</option>
							<option value="price_range">Price Range</option>
						</select>
						<input type="text" name="value1">
						<input type="text" name="value2">
						<button type="submit" class="btn btn-primary">Search</button>
					</form>
					
				</c:otherwise>
				
			</c:choose>
			<br>
			
			
			<table class="table table-striped">
			
				<thead class="thead-dark">
					<tr>
						<th scope="col">#</th>
						<th scope="col">Make</th>
						<th scope="col">Model</th>
						<th scope="col">Year</th>
						<th scope="col">Price £</th>
						<th scope="col">License</th>
						<th scope="col">Colour</th>
						<th scope="col">Doors</th>
						<th scope="col">Transmission</th>
						<th scope="col">Mileage</th>
						<th scope="col">Fuel</th>
						<th scope="col">Engine size</th>
						<th scope="col">Body Style</th>
						<th scope="col">Condition</th>
						<th scope="col">Notes</th>
						<c:if test="${loggedin}">
							<th scope="col">Update</th>
							<th scope="col">Delete</th>
							<th scope="col">Sold</th>
						</c:if>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${allVehicles}" var="c">
							<tr>
								<th scope="row">${c.getVehicle_id()}</th>
								<td>${c.getMake()}</td>
								<td>${c.getModel()}</td>
								<td>${c.getYear()}</td>
								<td>${c.getPrice()}</td>
								<td>${c.getLicense_number()}</td>
								<td>${c.getColour()}</td>
								<td>${c.getNumber_doors()}</td>
								<td>${c.getTransmission()}</td>
								<td>${c.getMileage()}</td>
								<td>${c.getFuel_type()}</td>
								<td>${c.getEngine_size()}</td>
								<td>${c.getBody_style()}</td>
								<td>${c.getCondition()}</td>
								<td>${c.getNotes()}</td>
								
								<c:if test="${loggedin}">
									<td scope="col">
									<a href="./update?vehicle_id=${c.getVehicle_id()}" class="btn btn-warning">Update</a>
									</td>
									<td scope="col">
										<a href="./delete?vehicle_id=${c.getVehicle_id()}" class="btn btn-danger">Delete</a>
									</td>
									<c:choose>
									<c:when test="${c.isSold()}">
										<td>
											<form action="./soldStatus" method="get">
												<button type="submit" name="sold" value="${c.getVehicle_id()}" class="btn btn-danger" >Sold</button>
											</form>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<form action="./soldStatus" method="get">
												<button type="submit" name="available" value ="${c.getVehicle_id()}" class="btn btn-success" >Available</button>
											</form>
										</td>
									</c:otherwise>
								</c:choose>
								</c:if>
							</tr>
					</c:forEach>
				</tbody>
				
			</table>
			
			<c:if test="${loggedin}">
				<form action="./addNew">
					<button type="submit" class="btn btn-primary" >Add New</button>
				</form>		
			</c:if>
		</div>
	</body>
	
</html>
