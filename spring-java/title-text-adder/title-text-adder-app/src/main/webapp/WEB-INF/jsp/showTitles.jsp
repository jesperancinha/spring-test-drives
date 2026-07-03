<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Titles Added using Ajax</title>
</head>
<body>
	<h1>Title text adder</h1>
	<h2>My texts</h2>

	The following are the filtered  by "${text_filter}" titles added in the database:
	<br>
	<ul style="color: blue;">
		<c:forEach items="${Titles}" var="title">
			<li>Title : <c:out value="${title.title}" />; text : <c:out
					value="${title.text}" />
		</c:forEach>
	</ul>
	<a href="/title-text-adder-app/AddsTitle.htm">Add title</a>
</body>
</html>