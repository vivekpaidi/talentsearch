package com.talentsearch.db.vo;

public class TeamVO {

	private int teamid;
	private String name;
	private int leadid;
	private int parentid;
	private String description;
	private String startdate;
	private String enddate;
	
	
	
	public TeamVO(int teamid, String name, int leadid, int parentid, String description, String startdate,
			String enddate) {
		this.teamid = teamid;
		this.name = name;
		this.leadid = leadid;
		this.parentid = parentid;
		this.description = description;
		this.startdate = startdate;
		this.enddate = enddate;
	}
	
	public int getTeamid() {
		return teamid;
	}
	public String getName() {
		return name;
	}
	public int getLeadid() {
		return leadid;
	}
	public int getParentid() {
		return parentid;
	}
	public String getDescription() {
		return description;
	}
	public String getStartdate() {
		return startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	
}
