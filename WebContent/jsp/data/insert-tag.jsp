<%@ page import="java.lang.*"%>
<%@ page import="com.talentsearch.db.dao.TagsDAO"%>

<%
String tag=(String)(request.getParameter("tag"));

 new TagsDAO().addTag(tag);
 out.flush();
%>