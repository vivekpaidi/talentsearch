package com.talentsearch.service;

import java.io.IOException;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.talentsearch.db.dao.EmployeeDAO;
import com.talentsearch.db.dao.ResourceDAO;
import com.talentsearch.db.dao.UserDAO;
import com.talentsearch.db.vo.EmployeeVO;
import com.talentsearch.db.vo.UserVO;

public class UserService {
	
	public UserService(){
		try {
			StartService.setSchema();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JsonArray validateUser(int userid,String password){
		JsonArray ja = new JsonArray();
		EmployeeDAO dao = new EmployeeDAO();
		EmployeeVO evo = (EmployeeVO)(dao.getEmployeeDetails(userid).get(0));
		List lst = new UserDAO().validateUser(userid, password);
		JsonObject currentRecord = new JsonObject();
		if(!lst.isEmpty()){
			System.out.println("got result");
			UserVO vo = (UserVO)lst.get(0);
			currentRecord.addProperty("valid",1);
			currentRecord.addProperty("admin",vo.getAdmin());
			currentRecord.addProperty("username",vo.getUsername());
			currentRecord.addProperty("resource", evo.getResource());
		}
		else{
			currentRecord.addProperty("valid", 0);
		}
		ja.add(currentRecord);
		return ja;
	}
	
	public JsonArray getUsers()
	{
		JsonArray ja = new JsonArray();
		try {
			EmployeeDAO dao = new EmployeeDAO();
			List lst = new UserDAO().getUsers();
			for(int i=0; i<lst.size(); ++i)
			{
				UserVO vo = (UserVO)lst.get(i);
				EmployeeVO evo = (EmployeeVO)(dao.getEmployeeDetails(vo.getUserid()).get(0));
				JsonObject currentRecord = new JsonObject();
				currentRecord.addProperty("UserId", vo.getUserid());
				currentRecord.addProperty("UserName", vo.getUsername());
				currentRecord.addProperty("Password", vo.getPassword());
				currentRecord.addProperty("Admin", vo.getAdmin());
				currentRecord.addProperty("Resource", evo.getResource());
				currentRecord.addProperty("ParentId", evo.getParentId());
				
				ja.add(currentRecord);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ja;
	}
	
	public void insertUser(int userid, String username, String password, String admin,String resource,int parentid){
		
			try {
				new UserDAO().insert(userid, username, password, admin);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				new EmployeeDAO().insertEmployee(userid, parentid, username, "", "", "", "", "", "", "", resource);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				new ResourceDAO().insertResource(userid, "", 0, parentid, "Y", "", "", "", "", "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void updateUser(int userid, String username, String password, String admin,String resource){
		
			try {
				new UserDAO().update(userid, username, password, admin);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void deleteUser(int userid){
		try {
			new UserDAO().delete(userid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			new ResourceDAO().deleteResource(userid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			new EmployeeDAO().deleteEmployee(userid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
