<%@ page import="java.lang.*"%>
<%@ page import="com.talentsearch.service.UserService"%>

<%
	String userid=(String)(request.getParameter("userid"));
	String parentid=(String)(request.getParameter("parentid"));
	String username=(String)(request.getParameter("username"));
	String password = (String)(request.getParameter("password"));
	String adminpriv=(String)(request.getParameter("admin"));
	String resource=(String)(request.getParameter("resource"));
	
	
	String editpriv = "Y";
	String executepriv="Y";
	String applroot = (String)(request.getParameter("approot"));
	

	
	new UserService().insertUser(Integer.parseInt(userid.trim()), username, password, adminpriv, resource, Integer.parseInt(parentid));
	
    	
	//out.print(ja);
	
	
	out.flush();
%>