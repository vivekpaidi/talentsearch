package com.talentsearch.db.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.talentsearch.db.util.SQLQuery;
import com.talentsearch.db.vo.UserVO;
import com.talentsearch.util.MD5Encription;

public class UserDAO extends SQLQuery{
	
	private ArrayList users = new  ArrayList();

	@Override
	public boolean copyDataFromResultSet(ResultSet rs) {
		try
		{
			UserVO vo = new UserVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)); 
			users.add(vo);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList getUsers() 
	{
		String sql = "SELECT userid, username, password, admin from " + dbschema + ".user";
		return getUsersQuery(sql);
	}
	
	public ArrayList validateUser(int iuserid, String ipassword)
	{
		try
		{
			String encodedpassword = MD5Encription.encrypt(ipassword);
			
			String sql = "SELECT userid, username, password, admin from " + dbschema + ".user where userid='" + iuserid + "' and password='" + encodedpassword + "'";
			System.out.println(sql);
			return getUsersQuery(sql);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	private ArrayList getUsersQuery(String sql) 
	{
		users = new ArrayList();
		ArrayList data = new ArrayList();
		try
		{	
			getMatchResultSet(sql, data);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return users;
	}

	public int insert(int userid, String username, String password, String admin) throws Exception
	{
		String epassword = MD5Encription.encrypt(password);
		String sql = "insert into " + dbschema + ".user (userid, username, password, admin) values (?, ?, ?, ?)";
		ArrayList vals = new ArrayList();
		vals.add(userid);
		vals.add(username);
		vals.add(epassword);
		vals.add(admin);
		executeUpdate(sql,  vals);
		return 0;
	}
	
	public int update(int userid, String username, String password, String admin) throws Exception
	{
		
		String sql = "update " + dbschema + ".user set username=?, password=?, admin=? where userid  =?";
		ArrayList vals = new ArrayList();
		vals.add(username);
		vals.add(password);
		vals.add(admin);
		vals.add(userid);
		executeUpdate(sql,  vals);
		return 0;
	}
	
	public void delete(int userid) throws Exception
	{
		String sql = "delete from " + dbschema + ".user where userid=?";
		ArrayList vals = new ArrayList();
		vals.add(userid);
		executeUpdate(sql,  vals);
	}
	
	
	
}
