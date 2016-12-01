<%@ 
	page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ page isELIgnored="false"%>
<title>Sign Up</title>
</head>
<body>
	<h1>
		<c:if test="${not empty msg}">
				${msg}
		</c:if>
	</h1>
	<form action="/TheClassifieds/BuildUser" method="post">
			 Username:<br> <input type="text" name="username"><br>
		<br> Password:<br> <input type="password" name="password"><br>
		<br> Confirm Password:<br> <input type="password" name="confirmpassword"><br>
		<br> Email:<br> <input type="text" name="email"><br>
		<br> <input type="submit" value="Sign Up"><br>
		<br> <a href="SignIn">Sign In?</a>
	</form>
</body>
</html>