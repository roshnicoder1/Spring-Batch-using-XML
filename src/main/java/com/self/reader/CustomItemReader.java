package com.self.reader
;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.self.beans.Customer;

public class CustomItemReader implements ItemReader<ArrayList<Customer>>{
	private static Logger logger = Logger.getLogger(CustomItemReader.class);
	
	private static ResourceBundle rsBundle = ResourceBundle.getBundle("com.self.resources.query");
	ArrayList<Customer> cusList =new ArrayList<Customer>();
	String selectQuery 	= rsBundle.getString("selectQuery");
	boolean read=false;
	JdbcTemplate jdbcTemplates;
	public CustomItemReader(DataSource ds) {
		 jdbcTemplates=new JdbcTemplate(ds);	
	}
	@Override
	public ArrayList<Customer> read() throws Exception,
			UnexpectedJobExecutionException, ParseException,
			NonTransientResourceException {
		// TODO Auto-generated method stub
		System.out.println("\n Inside Custom Reader");
		System.out.println("sql:::: "+selectQuery.toString());
		logger.info("In CustomItemReader::  SQL-->"+selectQuery.toString());
		try{
			if(read){
			return null;
		}
			cusList=getAllDetails();
			System.out.println("CustomItemReader.read().size()-->"+cusList.size());
			read=true;
		}
		catch(Exception e){
			logger.error("Exception in CustomItemReader :"+e);
			System.out.println("Error occured in read method of reader"+e);
		}
		return cusList;
	}
	public ArrayList<Customer> getAllDetails(){
		return (ArrayList<Customer>) jdbcTemplates.query(selectQuery,new RowMapper<Customer>(){  
		    public Customer mapRow(ResultSet rs, int rownumber) throws SQLException {  
		    	Customer appBean = new Customer();
		    	//select custcode,custname,amount from customer;
		    	appBean.setCustcode(Integer.parseInt(rs.getString(1) == null ?"": rs.getString(1).trim()));
		    	appBean.setCustname(rs.getString(2) == null ?"": rs.getString(2).trim());
		    	appBean.setAmount(Double.parseDouble(rs.getString(3) == null ?"": rs.getString(3).trim()));
			
			  
			  System.out.println("ItemReader.getAllPolicyDetails():: -->custcode:"+appBean.getCustcode()+">>custname:"+appBean.getCustname());
			  logger.info("ItemReader.getAllPolicyDetails()::");
				return appBean;
		   }
		});
	}

}
