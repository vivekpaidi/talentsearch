package com.talentsearch.db.util;

import java.sql.ResultSet;

public class Sequence extends SQLQuery {
	int seqno = 0;
	private String table;
	
	
	public void setTable(String table){
		this.table = table;
	}
	
	@Override
	public boolean copyDataFromResultSet(ResultSet rs) {
		try
		{
			seqno = rs.getInt(1) +1;
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public synchronized int getNext() throws Exception
	{
		String sql = "select seqno from "  + dbschema + "." + table; 
		getMatchResultSet(sql, null);
		sql = "update " +  dbschema + "." + table + " set seqno=" +  seqno;
		executeUpdate(sql, null);
		return seqno;
	}
	
	

	
}
