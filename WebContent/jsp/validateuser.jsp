<%@ page import="java.lang.*"%>
<%@ page import="com.google.gson.*"%>
<%@ page import="com.talentsearch.service.UserService"%>
<%
	int userid=Integer.parseInt(request.getParameter("username").trim());
	String password = (String)(request.getParameter("password"));
	JsonArray ja = new JsonArray();
	ja = new UserService().validateUser(userid, password);
	JsonObject jo = (JsonObject)ja.get(0);
	int val = Integer.parseInt(String.valueOf(jo.get("valid")));
	String admin = String.valueOf(jo.get("admin")).replace('"', ' ').trim();
	if(val == 1){
		session=request.getSession();
		session.setAttribute("userid",String.valueOf(userid));
		session.setAttribute("username",String.valueOf(jo.get("username"))); 
		System.out.println(String.valueOf(jo.get("resource")).replace('"', ' ').trim());
		session.setAttribute("resource",String.valueOf(jo.get("resource")).replace('"', ' ').trim());
		if(admin.equals("Y")){
    		session.setAttribute("admin","Y");
    	}
		out.print(1);
	}
	else
	out.print(0);
	
	out.flush();
%>