package com.talentsearch.db.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public abstract class SQLQuery {

	public abstract boolean copyDataFromResultSet(ResultSet rs);
	public static String dbschema = "";
	
	
	
	public Connection getConnection() throws Exception{
		return MySQLConnection.getConnection();
	}
	
	
	public int executeUpdate(String sql,ArrayList data) throws Exception{
		PreparedStatement preparedstatement = null;
		try{
			preparedstatement = getConnection().prepareStatement(sql);
			if(!data.isEmpty()){
				for(int i =0;i<data.size();i++){
					Object ob = data.get(i);
					if(ob == null)
						ob = "";
					preparedstatement.setObject(i+1, ob);
				}
			}
			return preparedstatement.executeUpdate(); 
		}
		catch(Exception e){
			throw e;
		}finally {
			try
			{
				if(preparedstatement!=null)
					preparedstatement.close();
				
			}
			catch (Exception e) {
			}
		}   
		
	}
	
	public void getMatchResultSet(String sql,ArrayList data) throws Exception{
		PreparedStatement ps = null;
		try{
			ps = getConnection().prepareStatement(sql);
			if(!data.isEmpty()){
				for(int i =0;i<data.size();i++){
					Object ob = data.get(i);
					if(ob == null)
	    	        	ob = "";
					ps.setObject(i+1, ob);
				}
			}
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				rs.beforeFirst();
				while ( rs.next() )	// this will step through our data row-by-row
				{
				    if(!copyDataFromResultSet(rs))
				    	break;
				}
				return;
			}
		}catch(Exception e){
			throw e;
		}finally {
			try
			{
				if(ps!=null)
					ps.close();
				
			}
			catch (Exception e) {
			}
		}   
		
	}
}
