<%@ page import="java.lang.*"%>
<%@ page import="com.talentsearch.service.EmployeeService"%>

<%
	String userid=(String)(request.getParameter("userid"));
	String designation=(String)(request.getParameter("designation"));
	String username=(String)(request.getParameter("username"));
	String email = (String)(request.getParameter("email"));
	String mobile=(String)(request.getParameter("mobile"));
	String experience=(String)(request.getParameter("experience"));
	String tags=(String)(request.getParameter("tags"));
	

	
	new EmployeeService().updateEmployee(Integer.parseInt(userid.trim()), username, designation, email, mobile, experience, tags);
	
    	
	//out.print(ja);
	
	
	out.flush();
%>