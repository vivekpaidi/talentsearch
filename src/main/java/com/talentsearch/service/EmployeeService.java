package com.talentsearch.service;


import java.util.HashSet;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.talentsearch.db.dao.EmployeeDAO;
import com.talentsearch.db.dao.TagsDAO;
import com.talentsearch.db.util.DBCache;
import com.talentsearch.db.util.QueryInstance;
import com.talentsearch.db.vo.EmployeeVO;

public class EmployeeService {
	
	QueryInstance QI = new QueryInstance();
	
	public JsonArray insertEmployee(int parentid,String name, String designation, String mailid, String mobilenumber, String experience,
			String tags, String waittime, String value,String resource){
		int id = QI.getNextUniqueEmployeeId();
		JsonArray ja = new JsonArray();
		JsonObject currentRecord = new JsonObject();
		
		try {
			DBCache.insertEmployee(id, parentid, name, designation, mailid, mobilenumber, experience, tags, waittime, value, resource);
			currentRecord.addProperty("Status", "Success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			currentRecord.addProperty("Status", "Fail");
			e.printStackTrace();
		}
		return ja;
	}
	
	public JsonArray updateEmployee(int id,String name, String designation, String mailid, String mobilenumber, String experience,
			String tags){
	
		JsonArray ja = new JsonArray();
		JsonObject currentRecord = new JsonObject();
		
		try {
			new EmployeeDAO().updateEmployee(id, name, designation, mailid, mobilenumber, experience, tags);
			currentRecord.addProperty("Status", "Success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			currentRecord.addProperty("Status", "Fail");
			e.printStackTrace();
		}
		return ja;
	}
	
	public JsonArray getEmployeeDetails(int id){
		JsonArray ja = new JsonArray();
		JsonObject currentRecord = new JsonObject();
		List lst = new EmployeeDAO().getEmployeeDetails(id);
		for(int i=0;i<lst.size();i++){
			EmployeeVO vo = (EmployeeVO)lst.get(i);
			JsonObject jo = new JsonObject();
			jo.addProperty("Id",vo.getId());
			jo.addProperty("Name",vo.getName());
			jo.addProperty("Designation",vo.getDesignation());
			jo.addProperty("MailId",vo.getMailid());
			jo.addProperty("MobileNo",vo.getMobilenumber());
			jo.addProperty("Experience",vo.getExperience());
			jo.addProperty("Tags",vo.getTags());
			
			ja.add(jo);
		}
		return ja;
	}
	
	
	public JsonArray getEmployeeDetails(){
		JsonArray ja = new JsonArray();
		JsonObject currentRecord = new JsonObject();
		List lst = new EmployeeDAO().getEmployees();
		for(int i=0;i<lst.size();i++){
			EmployeeVO vo = (EmployeeVO)lst.get(i);
			
			if(vo.getResource().equals("Y")){
			JsonObject jo = new JsonObject();
			jo.addProperty("Id",String.valueOf(vo.getId()));
			jo.addProperty("Name",vo.getName());
			jo.addProperty("MailId",vo.getMailid());
			jo.addProperty("Tags",vo.getTags());
			jo.addProperty("Match",0);
			ja.add(jo);
			}
		}
		return ja;
	}
	
	
	
		public JsonArray getTags(int id){
				
			JsonArray ja = new JsonArray();
			Gson gson=new GsonBuilder().create();
			
			String availabletags = "";
			String assignedtags = "";
			
			try {
				availabletags = new TagsDAO().getTags();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(id>0){
			EmployeeVO vo = (EmployeeVO)new EmployeeDAO().getEmployeeDetails(id).get(0);
			assignedtags = vo.getTags();
			}
			//System.out.println("avtag"+availabletags.split(",")[0]);
			if(id ==-1){
				 JsonObject jo = new JsonObject();
				 
				 jo.add("available", gson.toJsonTree(availabletags.split(",")));
				 ja.add(jo);
				 
			 }else{
				 JsonObject jo = new JsonObject();
				 String [] totaltags = availabletags.split(",");
				 String [] assigned = assignedtags.split(",");
				 HashSet<String> availableSet = new HashSet<String>();
				 for(int i = 0;i< totaltags.length;i++){
					if(!totaltags[i].trim().isEmpty()) 
				 availableSet.add(totaltags[i].trim());
				 }
				 for(int i = 0;i<assigned.length;i++){
					 availableSet.remove(assigned[i].trim());
				 }
				 System.out.println(availableSet);
				 jo.add("available", gson.toJsonTree(availableSet.toArray()));
				 jo.add("assigned", gson.toJsonTree(assigned));
				 ja.add(jo);
			 }
			return ja;
			}
	
}
