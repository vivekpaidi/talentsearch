<%@ page import="java.lang.*"%>
<%@ page import="com.google.gson.*"%>
<%@ page import="com.talentsearch.service.EmployeeService"%>

<%
	session = request.getSession();
	int id = Integer.parseInt(String.valueOf(session.getAttribute("userid")));
	JsonArray ja;

	
	EmployeeService es = new EmployeeService();
    	ja = es.getEmployeeDetails(id);
	
    	
	out.print(ja);
	
	
	out.flush();
%>