package com.talentsearch.db.vo;

public class EmployeeVO {
private int id;
private int parentid;
private String name;
private String designation;
private String mailid;
private String mobilenumber;
private String experience;
private String tags;
private String waittime;
private String value;
private String resource;




public EmployeeVO(int id, int parentid,String name, String designation, String mailid, String mobilenumber, String experience,
		String tags, String waittime, String value,String resource) {
	
	this.id = id;
	this.parentid = parentid;
	this.name = name;
	this.designation = designation;
	this.mailid = mailid;
	this.mobilenumber = mobilenumber;
	this.experience = experience;
	this.tags = tags;
	this.waittime = waittime;
	this.value = value;
	this.resource = resource;
}


public int getId() {
	return id;
}

public int getParentId() {
	return parentid;
}
public String getName() {
	return name;
}
public String getDesignation() {
	return designation;
}
public String getMailid() {
	return mailid;
}
public String getMobilenumber() {
	return mobilenumber;
}
public String getExperience() {
	return experience;
}
public String getTags() {
	return tags;
}
public String getWaittime() {
	return waittime;
}
public String getValue() {
	return value;
}

public String getResource() {
	return resource;
}
}
