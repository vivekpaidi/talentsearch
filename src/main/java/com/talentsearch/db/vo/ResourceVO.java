package com.talentsearch.db.vo;

public class ResourceVO {
	private int id;
	private String islead;
	private int currentleadid;
	private int currentparentid;
	private String currentavailability;
	private String notavailtill;
	private String interestedbidids;
	private String fixedbidid;
	private String fixedfrom;
	private String fixedtill;
	
	
	
	public ResourceVO(int id, String islead, int currentleadid,int currentparentid, String currentavailability, String notavailtill,
			String interestedbidids, String fixedbidid, String fixedfrom, String fixedtill) {
		this.id = id;
		this.islead = islead;
		this.currentleadid = currentleadid;
		this.currentparentid = currentparentid;
		this.currentavailability = currentavailability;
		this.notavailtill = notavailtill;
		this.interestedbidids = interestedbidids;
		this.fixedbidid = fixedbidid;
		this.fixedfrom = fixedfrom;
		this.fixedtill = fixedtill;
	}
	public int getId() {
		return id;
	}
	public String getIslead() {
		return islead;
	}
	public int getCurrentleadid() {
		return currentleadid;
	}
	public int getCurrentparentid() {
		return currentparentid;
	}
	public String getCurrentavailability() {
		return currentavailability;
	}
	public String getNotavailtill() {
		return notavailtill;
	}
	public String getInterestedbidids() {
		return interestedbidids;
	}
	public String getFixedbidid() {
		return fixedbidid;
	}
	public String getFixedfrom() {
		return fixedfrom;
	}
	public String getFixedtill() {
		return fixedtill;
	}
	
	
	
}
