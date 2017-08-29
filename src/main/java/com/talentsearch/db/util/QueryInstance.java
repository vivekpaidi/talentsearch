package com.talentsearch.db.util;

import java.sql.ResultSet;

public class QueryInstance {

	Sequence SQ = new Sequence();
	
	public int getNextUniqueEmployeeId() 	
	{	
		try
		{ 
			SQ.setTable("empseq");
			return SQ.getNext(); 
		}
		catch(Exception e)
		{
			return -1;
		}
	}
	
	public int getNextTeamId() 	
	{	
		try
		{ 
			SQ.setTable("teamseq");
			return SQ.getNext(); 
		}
		catch(Exception e)
		{
			return -1;
		}
	}

}
