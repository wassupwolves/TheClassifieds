<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Testing Okay2</title>
	<link rel='stylesheet' href='<c:url value="/CSS/style.css" />' type='text/css' media='all' />
	<%@ page isELIgnored="false" %> 
</head>
<body>
	<h2>Hello World, Spring MVC</h2>

	<p>Welcome, ${name}</p>
</body>
</html>