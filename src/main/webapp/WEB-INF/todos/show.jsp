<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1><c:out value="${oneToDo.name}"/></h1>
		<a class="btn btn-secondary" href="/dashboard">Dashboard</a>
		
		<div class="mt-4">
			<p class="lead">
				Name: <c:out value="${oneToDo.name}"/> <br>
				Due Date: <c:out value="${oneToDo.dueDate}"/> <br>
				Description: <c:out value="${oneToDo.description}"/> <br>
			</p>
		</div>
	</div>
</body>
</html>