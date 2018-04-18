<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Please Log In</title>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
		<script src="js/login.js"></script>

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="styles/login.css">
	</head>
	<body>
		<div id="login-container">
			<div id="title">
				<h1>Login</h1>
			</div>
			<div id="inputdiv">
				<input type="text" class="form-control" id="username" placeholder="Enter Username">
				<input type="password" class="form-control" id="password"  placeholder="Enter Password">	
			</div>
			<div id="button-container">
				<button id="loginbutton" class="btn btn-primary">Log In</button>
			</div>
		</div>
	</body>
</html>

