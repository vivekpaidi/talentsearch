<%@ page import="java.lang.*"%>
<%@ page import="com.google.gson.*"%>
<%@ page import="com.talentsearch.service.EmployeeService"%>

<%
	
	int id = Integer.parseInt(request.getParameter("id"));
	JsonArray ja;

	
	EmployeeService es = new EmployeeService();
    	ja = es.getTags(id);
	
    	
	out.print(ja);
	
	
	out.flush();
%>