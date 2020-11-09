package com.naveen.rest.webservices.restfulwebservices;

public class HelloWorldBean {

	private String name;
	private String branch;
	private String yop;
	private String message;
	public HelloWorldBean(String name, String branch, String yop) {
		this.name = name;
		this.branch = branch;
		this.yop = yop;
	}
	
	public HelloWorldBean(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getYop() {
		return yop;
	}

	public void setYop(String yop) {
		this.yop = yop;
	}

	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [name=" + name + ", branch=" + branch + ", yop=" + yop + "]";
	}
   
	
	
}
