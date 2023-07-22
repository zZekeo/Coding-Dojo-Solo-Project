<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login and Registration</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Welcome to the TODO App!</h1>
		
		<form:form action="/register" method="post" modelAttribute="newUser">
			<p>
				<form:label for="userNameReg" path="userName">Username</form:label>
				<form:input class="form-control" type="text" id="userNameReg" path="userName"/>
				<form:errors path="userName"/>
			</p>
			
			<p>
				<form:label for="emailReg" path="email">Email</form:label>
				<form:input class="form-control" type="email" id="emailReg" path="email"/>
				<form:errors path="email"/>
			</p>
			
			<p>
				<form:label for="passwordReg" path="password">Password</form:label>
				<form:input class="form-control" type="password" id="passwordReg" path="password"/>
				<form:errors path="password"/>
			</p>
			
			<p>
				<form:label for="confirmReg" path="confirm">Confirm Password</form:label>
				<form:input class="form-control" type="password" id="confirmReg" path="confirm"/>
				<form:errors path="confirm"/>
			</p>
		<button class="btn btn-primary">Register</button>
		</form:form>
		
		<hr/>
		
		<form:form action="/login" method="post" modelAttribute="newLogin">
			<p>
				<form:label for="emailLog" path="email">Email</form:label>
				<form:input class="form-control" type="email" id="emailLog" path="email"/>
				<form:errors path="email"/>
			</p>
			
			<p>
				<form:label for="passwordLog" path="password">Password</form:label>
				<form:input class="form-control" type="password" id="passwordLog" path="password"/>
				<form:errors path="password"/>
			</p>
			
			<button class="btn btn-primary">Login</button>
		</form:form>
	</div>
</body>
</html>