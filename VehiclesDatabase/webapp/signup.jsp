<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<title>Sign up!</title>
	</head>
	<body>
		<div class="container">
			<h1 class="text-center mt-3">Sign Up</h1>
			<form action="./signUp" method="post">
				<div class="form-group">
					<label for="firstNameField">First Name</label>
					<input type="text" id="firstNameField" class="form-control" name="firstName">
				</div>
				<div class="form-group">
					<label for="lastNameField">Last Name</label>
					<input type="text" id="lastNameField" class="form-control" name="lastName">
				</div>
				<div class="form-group">
					<label for="usernameField">Username</label>
					<input type="text" id="usernameField" class="form-control" name="username">
				</div>
				<div class="form-group">
					<label for="passwordField">Password</label>
					<input type="password" id="passwordField" class="form-control" name="password">
				</div>
				<div class="form-group">
					<label for="confirmPasswordField">Confirm your Password</label>
					<input type="password" id="confirmPasswordField" class="form-control" name="confirmPassword">
				</div>
				<input type="submit" value="Sign Up">
			</form>
		</div>
	</body>
</html>