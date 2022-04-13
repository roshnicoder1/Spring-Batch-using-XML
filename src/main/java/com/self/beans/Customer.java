package com.self.beans;



public class Customer {
	private int custcode;
	private String custname;
	private double amount;
	public int getCustcode() {
		return custcode;
	}
	public void setCustcode(int custcode) {
		this.custcode = custcode;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int custcode, String custname, double amount) {
		super();
		this.custcode = custcode;
		this.custname = custname;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Customer [custcode=" + custcode + ", custname=" + custname + ", amount=" + amount + "]";
	}
}
