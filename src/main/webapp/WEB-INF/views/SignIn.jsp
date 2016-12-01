<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign In</title>
</head>
<body>
	<h1>
		<c:if test="${not empty msg}">
			${msg}
		</c:if>
	</h1>	
	<form action="/TheClassifieds/VerifyLogIn" method="post">
		Username:<br> <input type="text" name="username"><br><br>	
		Password:<br> <input type="password" name="password"><br><br>
		<input type="submit" value="Sign In"><br>
		<br> <a href="SignUp">Sign Up?</a>
	</form>
</body>
</html>