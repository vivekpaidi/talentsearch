<%
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

System.out.println("validating");
session=request.getSession();
String userid= (String)session.getAttribute("userid");
if(userid == null)
{

System.out.println("invalid session");
response.sendRedirect("login.jsp"); 
	

}

%>