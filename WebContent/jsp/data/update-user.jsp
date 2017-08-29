<%@ page import="java.lang.*"%>
<%@ page import="com.talentsearch.service.UserService"%>
<%@ page import="com.talentsearch.util.MD5Encription"%>

<%
	String userid=(String)(request.getParameter("userid"));
	String parentid=(String)(request.getParameter("parentid"));
	String username=(String)(request.getParameter("username"));
	String oldpassword = (String)(request.getParameter("oldpassword"));
	String newpassword = (String)(request.getParameter("newpassword"));
	String adminpriv=(String)(request.getParameter("admin"));
	String resource=(String)(request.getParameter("resource"));
	String password;
	
	String editpriv = "Y";
	String executepriv="Y";
	String applroot = (String)(request.getParameter("approot"));
	

	if(newpassword == null){
		password = oldpassword;
	}else{
		password = newpassword;
	}
	String epassword = MD5Encription.encrypt(password);
	new UserService().updateUser(Integer.parseInt(userid.trim()), username, epassword, adminpriv, resource);
	
    	
	//out.print(ja);
	
	
	out.flush();
%>