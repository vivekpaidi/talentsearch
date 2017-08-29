package com.talentsearch.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.talentsearch.db.util.SQLQuery;
import com.talentsearch.db.vo.TeamVO;

public class TeamDAO extends SQLQuery{

	private ArrayList teams = new ArrayList();
	
	@Override
	public boolean copyDataFromResultSet(ResultSet rs) {
		try {
			TeamVO vo = new TeamVO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getInt(4), rs.getString(5),rs.getString(6), rs.getString(7));
			teams.add(vo);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public ArrayList getTeams(){
		String sql = "Select * from " + dbschema + ".teams";
		teams = new ArrayList();
		ArrayList data = new ArrayList();
		try
		{	
			getMatchResultSet(sql, data);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return teams;
	}
	
	public int insertTeam(int teamid, String name,int leadid,int parentid, String description, String startdate, String enddate) throws Exception{
		String sql = "insert into " + dbschema + ".teams ( teamid,  name, leadid,parentid, description, startdate, enddate) values (?,?,?,?,?,?,?)";
		ArrayList vals = new ArrayList();
		vals.add(teamid);
		vals.add(name);
		vals.add(leadid);
		vals.add(parentid);
		vals.add(description);
		vals.add(startdate);
		vals.add(enddate);
		executeUpdate(sql,vals);
		return teamid;
	}
	
	public int updateTeam(int teamid, String name,int leadid,int parentid, String description, String startdate, String enddate) throws Exception{
		String sql = "update " + dbschema + ".teams set name=?,leadid =?, parentid=?, description=?, startdate=?, enddate=? where teamid=?";
		ArrayList vals = new ArrayList();
		
		vals.add(name);
		vals.add(leadid);
		vals.add(parentid);
		vals.add(description);
		vals.add(startdate);
		vals.add(enddate);
		vals.add(teamid);
		executeUpdate(sql,vals);
		return teamid;
	}
	
	public void deleteTeam(int teamid) throws Exception{
		String sql = "delete from " + dbschema + ".teams where teamid=?";
		ArrayList vals = new ArrayList();
		vals.add(teamid);
		executeUpdate(sql,vals);
	}
}
