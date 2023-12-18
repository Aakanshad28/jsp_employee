<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
String message= (String)request.getAttribute("message");
if(message!=null){
%>
<%=message %>
<%
}
%>

	<form action="login" method="post">
		<fieldset>
			Email:<input type="text" name="email"> <br> <br>
			Password:<input type="text" name="password"><br> <br>
			<button>login</button>
		</fieldset>

	</form>

</body>
</html>