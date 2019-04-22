<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page isErrorPage="true" %>

<html>
<head>
	<title>Error Page</title>
	<meta charset="UTF-8">
</head>

<body>
	<%-- <h4>${exception.getMessage()}</h4> --%>
	<h4>${exception.message}</h4>
	
	<ul>
		<%-- <c:forEach items="${exception.getStackTrace()}" var="stack"> --%>
		<c:forEach items="${exception.stackTrace}" var="stack">
			<li>${stack}</li>
		</c:forEach>
	</ul>
</body>
</html>
