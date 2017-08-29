<%@include file="checkusersession.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
session = request.getSession();
String resource = String.valueOf(session.getAttribute("resource"));
pageContext.setAttribute("resource", resource);
%>
<html>
<head>
		<title>User</title>
		<link rel="stylesheet" href="../css/bootstrap.min.css" type="text/css"/>
		<link rel="stylesheet" href="../scripts/jqwidgets/styles/jqx.base.css" type="text/css"/>
		<link rel="stylesheet" href="../css/base.css" type="text/css"/>
		
		<script type="text/javascript" src="../scripts/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="../scripts/header.js" defer></script>
		<c:if test="${resource == 'Y'}">
		<%@include file="../html/employee/employeedata.html"%>
		</c:if>
		<c:if test="${resource != 'Y'}">
		<%@include file="../html/manager/managerdata.html"%>
		</c:if>
</head>
	<body>
		<%@include file="../html/header.html"%>
		<c:if test="${resource == 'Y'}">
		<%@include file="employee.jsp"%>
		</c:if>
		<c:if test="${resource != 'Y'}">
		<%@include file="manager.jsp"%>
		</c:if>
		
	</body>
</html>