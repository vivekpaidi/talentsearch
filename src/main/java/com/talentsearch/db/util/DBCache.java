package com.talentsearch.db.util;

import java.util.ArrayList;

import com.talentsearch.db.dao.EmployeeDAO;
import com.talentsearch.db.dao.ResourceDAO;
import com.talentsearch.db.dao.TeamDAO;

public class DBCache {
	
	private static String schema = "";
	private static ArrayList employees = null;
	private static ArrayList resources = null;
	private static ArrayList teams = null;
	
	//getters
	public static ArrayList getEmployees() {
		if(employees == null)
			loadEmployees();
		return employees;
	}
	public static ArrayList getResources() {
		if(resources == null)
			loadResources();
		return resources;
	}
	public static ArrayList getTeams() {
		if(teams == null)
			loadTeams();
		return teams;
	}
	
	
	
	//loaders
	public static void loadEmployees()
	{
		
		employees = new EmployeeDAO().getEmployees();
		return ;
	}
	public static void loadResources()
	{
		
		resources = new ResourceDAO().getResources();
		return ;
	}
	public static void loadTeams()
	{
		
		resources = new TeamDAO().getTeams();
		return ;
	}
	
	
	//edits and updates
	public static int insertEmployee(int id, int parentid,String name, String designation, String mailid, String mobilenumber, String experience,
			String tags, String waittime, String value,String resource) throws Exception{
		int val = new EmployeeDAO().insertEmployee(id, parentid, name, designation, mailid, mobilenumber, experience, tags, waittime, value, resource);
		refreshEmployees();
		return val;
			
	}
	
	public static void updateEmployee(int id, int parentid,String name, String designation, String mailid, String mobilenumber, String experience,
			String tags, String waittime, String value,String resource) throws Exception{
		new EmployeeDAO().updateEmployee(id, parentid, name, designation, mailid, mobilenumber, experience, tags, waittime, value, resource);
		refreshEmployees();
			
	}
	
	public void deleteEmployee(int id) throws Exception{
		new EmployeeDAO().deleteEmployee(id);
		refreshEmployees();
	}
	
	public static int insertResource(int id, String islead,int currentleadid,int currentparentid, String currentavailability, String notavailtill, String interestedbidids, String fixedbidid,
			String fixedfrom, String fixedtill) throws Exception{
		int val = new ResourceDAO().insertResource(id, islead, currentleadid,currentparentid, currentavailability, notavailtill, interestedbidids, fixedbidid, fixedfrom, fixedtill);
		refreshResources();
		return val;
	}
	
	public static void updateResource(int id, String islead,int currentleadid,int currentparentid, String currentavailability, String notavailtill, String interestedbidids, String fixedbidid,
			String fixedfrom, String fixedtill) throws Exception{
		new ResourceDAO().updateResource(id, islead, currentleadid,currentparentid, currentavailability, notavailtill, interestedbidids, fixedbidid, fixedfrom, fixedtill);
		refreshResources();
		
	}
	
	public void deleteResource(int id) throws Exception{
		new ResourceDAO().deleteResource(id);
		refreshResources();
	}
	
	public static int insertTeam(int teamid, String name,int leadid,int parentid, String description, String startdate, String enddate) throws Exception{
		int val = new TeamDAO().insertTeam(teamid, name, leadid, parentid, description, startdate, enddate);
		refreshTeams();
		return val;
	}
	
	public static void updateTeam(int teamid, String name,int leadid,int parentid, String description, String startdate, String enddate) throws Exception{
		new TeamDAO().updateTeam(teamid, name, leadid, parentid, description, startdate, enddate);
		refreshTeams();
		
	}
	
	public void deleteTeam(int teamid) throws Exception{
		new TeamDAO().deleteTeam(teamid);
		refreshTeams();
	}
	
	
	
	//refresh after insert and updates
	public static void refreshEmployees(){
		loadEmployees();
	}
	public static void refreshResources(){
		loadResources();
	}
	public static void refreshTeams(){
		loadTeams();
	}
}
