package com.talentsearch.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.talentsearch.db.util.SQLQuery;
import com.talentsearch.db.vo.ResourceVO;

public class ResourceDAO extends SQLQuery {

	private ArrayList resources = new ArrayList();
	@Override
	public boolean copyDataFromResultSet(ResultSet rs) {
		try {
			ResourceVO vo = new ResourceVO(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getInt(4), rs.getString(5),rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9), rs.getString(10));
			resources.add(vo);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	public ArrayList getResources(){
		String sql = "Select * from " + dbschema + ".resource";
		resources = new ArrayList();
		ArrayList data = new ArrayList();
		try
		{	
			getMatchResultSet(sql, data);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return resources;
	}
	
	public int insertResource(int id, String islead,int currentleadid,int currentparentid, String currentavailability, String notavailtill, String interestedbidids, String fixedbidid,
			String fixedfrom, String fixedtill) throws Exception{
		String sql = "insert into " + dbschema + ".resource ( id,  islead, currentleadid,currentparentid, currentavailability, notavailtill, interestedbidids, fixedbidid,fixedfrom, fixedtill) values (?,?,?,?,?,?,?,?,?,?)";
		ArrayList vals = new ArrayList();
		vals.add(id);
		vals.add(islead);
		vals.add(currentleadid);
		vals.add(currentparentid);
		vals.add(currentavailability);
		vals.add(notavailtill);
		vals.add(interestedbidids);
		vals.add(fixedbidid);
		vals.add(fixedfrom);
		vals.add(fixedtill);
		executeUpdate(sql,vals);
		return id;
	}
	
	public void updateResource(int id, String islead,int currentleadid,int currentparentid, String currentavailability, String notavailtill, String interestedbidids, String fixedbidid,
			String fixedfrom, String fixedtill) throws Exception{
		String sql = "update " + dbschema + ".resource set islead=?,currentleadid =?, currentparentid=?, currentavailability=?, notavailtill=?, interestedbidids=?, fixedbidid=?, fixedfrom=?, fixedtill=? where id=?";
		ArrayList vals = new ArrayList();
		
		vals.add(islead);
		vals.add(currentleadid);
		vals.add(currentparentid);
		vals.add(currentavailability);
		vals.add(notavailtill);
		vals.add(interestedbidids);
		vals.add(fixedbidid);
		vals.add(fixedfrom);
		vals.add(fixedtill);
		vals.add(id);
		executeUpdate(sql,vals);
	}

	public void deleteResource(int id) throws Exception{
		String sql = "delete from " + dbschema + ".resource where id=?";
		ArrayList vals = new ArrayList();
		vals.add(id);
		executeUpdate(sql,vals);
	}
}
