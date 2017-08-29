<% 
session=request.getSession();
String admin = (String)session.getAttribute("admin");
	if(admin !=null){
		response.sendRedirect("admin.jsp");
	}else{
		response.sendRedirect("user.jsp");
	}
%>