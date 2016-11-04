<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>TheClassifieds</title>
	<%@ page isELIgnored="false"%>
</head>
<body>
	<h1>Welcome user, please enter your information</h1>
	<form action="/TheClassifieds/BuildUser" method="post">
		Username:<br> <input type="text" name="username"><br><br>	
		Password:<br> <input type="password" name="password"><br><br>
		Confirm Password:<br> <input type="password" name="confirmpassword"><br><br>
		Email:<br> <input type="text" name="email"><br><br>
		<input type="submit" value="Sign Up">
	</form>
</body>
</html>