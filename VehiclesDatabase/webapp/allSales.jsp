<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Sales</title>
 <meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
	<div class="mx-3">
		<h1 class="text-center mt-3">All Sales</h1>
		<br>
		<br>
			<!-- display a button that directs to all vehicles -->
			<form action="home" method="get">
				<button type="submit" name="show_all_vehicles"  class="btn btn-primary" >Show All Vehicles</button>
			</form>
		<br>
		<!-- display all the sale records -->
		<table class="table table-striped">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Sold Date</th>
					<th scope="col">Sold Price</th>
					<th scope="col">Status</th>
					<th scope="col">Vehicle ID</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${allSales}" var="c">
					<tr>
						<td>${c.getSold_date()}</td>
						<td>${c.getSold_price()}</td>
						<td>${c.getStatus_info()}</td>
						<td>${c.getVehicle_id()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>