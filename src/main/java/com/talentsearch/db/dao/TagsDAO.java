package com.talentsearch.db.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.talentsearch.db.util.SQLQuery;

public class TagsDAO extends SQLQuery{

	static String tags = "";
	String table = "tags";
	ArrayList data = new ArrayList();
	public TagsDAO(){
		System.out.println( "schema"+dbschema);
	}
	@Override
	public boolean copyDataFromResultSet(ResultSet rs) {
		try
		{
			tags = rs.getString(1);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public String getTags() throws Exception{
		String sql = "select tags from "  + dbschema + "." + table; 
		getMatchResultSet(sql, data);
		
		return tags;
	}
	
	public synchronized void updateTags(String tags) throws Exception
	{
		
		if(tags != null && !tags.equals("")){
		String sql = "update " +  dbschema + "." + table + " set tags='" +  tags+"'";
		executeUpdate(sql, data);
		}
	}
	
	public synchronized void addTag(String tag) throws Exception
	{
		String sql = "select tags from "  + dbschema + "." + table; 
		getMatchResultSet(sql, data);
		String newtags = tags + "," + tag;
		sql = "update " +  dbschema + "." + table + " set tags='" +  newtags+"'";
		System.out.println(sql);
		executeUpdate(sql, data);
	}
}
