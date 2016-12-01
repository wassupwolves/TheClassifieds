<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>${msg}</h1>
<table>
		<c:forEach var="post" items="${userPosts}">
			<tr>
				<td><a href="/TheClassifieds/ViewPost/${post.post_id}">${post.post_title}</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<br><a href="/TheClassifieds/home">Home</a>
</body>
</html>