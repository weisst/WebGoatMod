<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.owasp.webgoat.session.WebSession"%>
<%
WebSession webSession = ((WebSession)session.getAttribute("websession"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Configuration Page</title>
</head>
<body>
<% response.sendRedirect(webSession.getCurrentLesson().getLink() +
		        "&succeeded=yes"); 
%>

</body>
</html>