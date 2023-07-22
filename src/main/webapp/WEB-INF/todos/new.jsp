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
		<h1>New TODO</h1>
		<a class="btn btn-secondary" href="/dashboard">Dashboard</a>
		
		<div>
			<form:form action="/todos/new" method="post" modelAttribute="toDoObj">
				<!-- pass in the user's id to user_id, under the path name creator -->
				<!-- under the pathname "creator", pass in the current logged in user's id as the value so it is mapped to the current user -->
				<form:input type="hidden" path="creator" value="${user_id}"/>
				
				<br>
				
				<p>
					<form:label path="name">Name:</form:label>
					<form:input class="form-control" type="text" path="name"/>
					<form:errors class="text-danger" path="name"/>
				</p>
				
				<p>
					<form:label path="dueDate">Due Date:</form:label>
					<form:input class="form-control" type="date" path="dueDate"/>
					<form:errors class="text-danger" path="dueDate"/>
				</p>
				
				<p>
					<form:label path="description">Description</form:label>
					<form:textarea class="form-control" path="description"/>
					<form:errors class="text-danger" path="description"/>
				</p>
				
				<button class="btn btn-primary">Submit</button>
			</form:form>
		</div>
	</div>
</body>
</html>