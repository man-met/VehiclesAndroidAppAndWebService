<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Developer: API Key</title>
		<meta <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	</head>
	<body>
		<div class="mx-3">
			<h1 class="text-center mt-3">Developer Zone</h1>
			
			<br>
				<!-- display a button that directs to all vehicles -->
				<form action="home" method="get">
					<button type="submit" name="show_all_vehicles"  class="btn btn-primary" >Home</button>
				</form>
			<br>
			
			<h2>Hi ${firstName} ${lastName},</h2>
			<h3>Your API Key is: ${apiKey}</h3>
			
		</div>
	</body>
</html>