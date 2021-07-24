<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Log In</title>
	</head>
	<body>
		<div class="container">
			<h1 class="text-center mt-3">Log In</h1>
			<form action="./login" method="post">
				<div class="form-group">
					<label for="usernameField">Username</label>
					<input type="text" id="usernameField" class="form-control" name="username">
				</div>
				<div class="form-group">
					<label for="passwordField">Password</label>
					<input type="password" id="passwordField" class="form-control" name="password">
				</div>
				<input type="submit" value="Log In" class="btn btn-primary btn float-left" >
			</form>
			<form action="./signUp">
				<input type="submit" value="Create a New Account" class="btn btn-primary btn float-right">
			</form>
		</div>
	</body>
</html>