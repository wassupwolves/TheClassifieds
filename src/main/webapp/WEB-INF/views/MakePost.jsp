<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ page isELIgnored="false"%>
<title>Make Post</title>
</head>
<body>
	<h1>
		<c:if test="${not empty msg}">
			${msg}
		</c:if>
	</h1>	
	<form action="/TheClassifieds/BuildPost" method="post" enctype="multipart/form-data">
		Title:<input type="text" name="postTitle"><br><br>	
		Description:<textarea rows="10" cols="100" name="postDescription">Post Description...</textarea><br><br>
		Price:<input type="number" step="0.1" name="postPrice" min="1"><br><br>
		Picture:<input type="file" name="postPictures" accept=".png, .jpg" multiple><br><br>	
		<input type="checkbox" name="appliances">Appliances<br>
		<input type="checkbox" name="auto">Auto<br>
		<input type="checkbox" name="electronics">Electronics<br>		
		<input type="checkbox" name="entertainment">Entertainment<br>
		<input type="checkbox" name="furniture">Furniture<br>
		<input type="checkbox" name="other">Other<br>
		<input type="submit" value="Create Post"><br>
		<br> <a href="/TheClassifieds/home">Cancel Post</a>
	</form>
</body>
</html>