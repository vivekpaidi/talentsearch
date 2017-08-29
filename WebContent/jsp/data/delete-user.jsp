<%@ page import="java.lang.*"%>
<%@ page import="com.talentsearch.service.UserService"%>

<%
String userid=(String)(request.getParameter("userid"));

 new UserService().deleteUser(Integer.parseInt(userid.trim()));
 out.flush();
%>