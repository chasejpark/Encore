package com.encore.j1117.model.vo;

public class MemberInfoVO {
	
	  private String id;
	  private String pass;	
	  private String name;	
	  private int ssn1;	
	  private int ssn2;	
	  private String tel;	
	  private String addr;	
	  private String job;
	  
	  public MemberInfoVO() {
		  
	}

	public MemberInfoVO(String id, String pass, String name, int ssn1, int ssn2, String tel, String addr, String job) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.ssn1 = ssn1;
		this.ssn2 = ssn2;
		this.tel = tel;
		this.addr = addr;
		this.job = job;
	}
	
	

	@Override
	public String toString() {
		return "MemberInfoVO [id=" + id + ", pass=" + pass + ", name=" + name + ", ssn1=" + ssn1 + ", ssn2=" + ssn2
				+ ", tel=" + tel + ", addr=" + addr + ", job=" + job + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSsn1() {
		return ssn1;
	}

	public void setSsn1(int ssn1) {
		this.ssn1 = ssn1;
	}

	public int getSsn2() {
		return ssn2;
	}

	public void setSsn2(int ssn2) {
		this.ssn2 = ssn2;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	
	  
	  
}
