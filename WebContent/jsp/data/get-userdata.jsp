<%@ page import="java.lang.*"%>
<%@ page import="com.google.gson.*"%>
<%@ page import="com.talentsearch.service.UserService"%>

<%
	JsonArray ja;

	
	UserService us = new UserService();
    	ja = us.getUsers();
	
    	
	out.print(ja);
	
	
	out.flush();
%>