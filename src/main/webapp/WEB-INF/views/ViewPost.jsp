<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SinglePost</title>
</head>
<body>
	<h1>${current_post.post_title}</h1>
	<h2>${current_post.post_description}</h2>
	<h3>${current_post.post_price}</h3>
	<h4>${current_post.post_date}</h4>
	<table>
		<c:forEach var="postimage" items="${postImages}">
			<tr>
				<td>
					<img src="${postimage.url}">				
				</td>
			</tr>
		</c:forEach>
	</table>
	<br> <a href="/TheClassifieds/UserPosts/${user.user_name}">View All Posts By ${user.user_name}</a>
	<br> <a href="/TheClassifieds/home">Home</a>
	
</body>
</html>