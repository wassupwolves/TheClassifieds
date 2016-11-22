<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>TheClassifieds</title>
	<%@ page isELIgnored="false"%>
</head>
<body>
	<h1>Welcome!</h1>
	<c:choose>
		<c:when test="${not empty msg}">
			<h2>${msg}</h2>
			<a href="MakePost">Make Post!</a>
		</c:when>
		<c:otherwise>
			<a href="SignIn">Log In!</a>
			<a href="SignUp">Create Account!</a>
		</c:otherwise>
	</c:choose>
</body>
</html>