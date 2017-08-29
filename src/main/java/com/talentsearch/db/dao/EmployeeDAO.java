package com.talentsearch.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.talentsearch.db.util.SQLQuery;
import com.talentsearch.db.vo.EmployeeVO;

public class EmployeeDAO extends SQLQuery{


private ArrayList employees = new ArrayList();


@Override
public boolean copyDataFromResultSet(ResultSet rs) {
	try{
		EmployeeVO vo = new EmployeeVO(rs.getInt(1), rs.getInt(2),rs.getString(3), rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11));
		employees.add(vo);
		return true;
	}catch(Exception e)
	{
		return false;
	}
	
}

public ArrayList getEmployeeDetails(int id){
	String sql = "Select * from " + dbschema + ".employee where id = "+id;
	employees = new ArrayList();
	ArrayList data = new ArrayList();
	try
	{	
		getMatchResultSet(sql, data);
	} catch(Exception e)
	{
		e.printStackTrace();
	}
	return employees;
}

public ArrayList getEmployees(){
	String sql = "Select * from " + dbschema + ".employee";
	employees = new ArrayList();
	ArrayList data = new ArrayList();
	try
	{	
		getMatchResultSet(sql, data);
	} catch(Exception e)
	{
		e.printStackTrace();
	}
	return employees;
}

public int insertEmployee(int id, int parentid,String name, String designation, String mailid, String mobilenumber, String experience,
		String tags, String waittime, String value,String resource) throws Exception{
	String sql = "insert into " + dbschema + ".employee ( id,  parentid, name, designation, mailid, mobileno, experience,tags, waittime, value,resource) values (?,?,?,?,?,?,?,?,?,?,?)";
	ArrayList vals = new ArrayList();
	vals.add(id);
	vals.add(parentid);
	vals.add(name);
	vals.add(designation);
	vals.add(mailid);
	vals.add(mobilenumber);
	vals.add(experience);
	vals.add(tags);
	vals.add(waittime);
	vals.add(value);
	vals.add(resource);
	executeUpdate(sql,vals);
	return id;
}

public void updateEmployee(int id, int parentid,String name, String designation, String mailid, String mobilenumber, String experience,
		String tags, String waittime, String value,String resource) throws Exception{
	String sql = "update " + dbschema + ".employee set parentid=?, name=?, designation=?, mailid=?, mobileno=?, experience=?, tags=?, waittime=?, value=? resource=? where id=?";
	ArrayList vals = new ArrayList();
	
	vals.add(parentid);
	vals.add(name);
	vals.add(designation);
	vals.add(mailid);
	vals.add(mobilenumber);
	vals.add(experience);
	vals.add(tags);
	vals.add(waittime);
	vals.add(value);
	vals.add(resource);
	vals.add(id);
	executeUpdate(sql,vals);
}

public void updateEmployee(int id,String name, String designation, String mailid, String mobilenumber, String experience,
		String tags) throws Exception{
	String sql = "update " + dbschema + ".employee set  name=?, designation=?, mailid=?, mobileno=?, experience=?, tags=? where id=?";
	ArrayList vals = new ArrayList();
	
	
	vals.add(name);
	vals.add(designation);
	vals.add(mailid);
	vals.add(mobilenumber);
	vals.add(experience);
	vals.add(tags);
	
	vals.add(id);
	executeUpdate(sql,vals);
}

public void deleteEmployee(int id) throws Exception{
	String sql = "delete from " + dbschema + ".employee where id=?";
	ArrayList vals = new ArrayList();
	vals.add(id);
	executeUpdate(sql,  vals);
}
}
