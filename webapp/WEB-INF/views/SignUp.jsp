<%@ 
	page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ page isELIgnored="false"%>
<title>Sign Up</title>
</head>
<body>
	<c:if test="msg">${msg}</c:if>
	<form action="/TheClassifieds/BuildUser" method="post">
			 Username:<br> <input type="text" name="username"><br>
		<br> Password:<br> <input type="password" name="password"><br>
		<br> Confirm Password:<br> <input type="password" name="confirmpassword"><br>
		<br> Email:<br> <input type="text" name="email"><br>
		<br> <input type="submit" value="Sign Up">
	</form>
</body>
</html>