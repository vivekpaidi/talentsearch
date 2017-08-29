<%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

session=request.getSession();
session.invalidate();

response.sendRedirect("login.jsp"); 
	


%>