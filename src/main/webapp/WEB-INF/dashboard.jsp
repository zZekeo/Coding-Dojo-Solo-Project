<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Welcome, <c:out value="${loggedUser.userName}"/>!</h1>
		
		<form action="/logout" method="post">
			<button class="btn btn-danger">Log Out</button>
		</form>
		
		<h2 class="mb-3 mt-3 text-center">TODO'S</h2>
		
		<div>
			<table class="table table-dark">
				<thead>
					<tr>
						<th>TODO</th>
						<th>Due Date</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="eachToDo" items="${allToDos}">
						<tr>
						<!-- only allow users who created their todo, be able to see, edit, and delete them. -->
						<c:if test="${user_id == eachToDo.creator.id}">
							<td><a class="btn btn-secondary" href="/todos/${eachToDo.id}"><c:out value="${eachToDo.name}"/></a></td>
							<td><c:out value="${eachToDo.dueDate}"/></td>
								<td><a class="btn btn-primary" href="/todos/${eachToDo.id}/edit">Edit</a></td>
								
								<form action="/todos/${eachToDo.id}/delete" method="post">
									<input type="hidden" name="_method" value="delete">
									<td><button class="btn btn-danger">Delete</button></td>
								</form>
						</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div class="text-center">
			<a href="/todos/new"><button class="btn btn-primary align-items-center">Create New ToDo</button></a>
		</div>
		
		
	</div>
</body>
</html>