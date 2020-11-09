<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show All Country</title>
</head>
<body>
<table>
<tr>
<th>Id</th>
<th>Country Code</th>
<th>Country Name</th>
<th>Action</th>
</tr>
<c:forEach items="${countries}" var="country">
<tr>
<td>${country.id}</td>
<td>${country.countryCode}</td>
<td>${country.countryName}</td>
<td><a href="edit?id=${ country.id }">Edit</a></td>
</tr>
</c:forEach>
</table>
</body>
</html>