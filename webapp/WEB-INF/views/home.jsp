<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<a href="/MakePost.jsp">Make Post!</a>
		</c:when>
		<c:otherwise>
			<a href="SignIn">Log In!</a>
			<a href="SignUp">Create Account!</a>
		</c:otherwise>
	</c:choose>
</body>
</html>