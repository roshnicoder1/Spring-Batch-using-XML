package com.self.beans;

public class TRNWLBean {
	
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
	
	public TRNWLBean() {
		super();
	}
	public TRNWLBean(int custcode, String custname, double amount) {
		super();
		this.custcode = custcode;
		this.custname = custname;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "TRNWLBean [custcode=" + custcode + ", custname=" + custname + ", amount=" + amount + "]";
	}
	
	

}
