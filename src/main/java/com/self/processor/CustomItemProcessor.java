package com.self.processor;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.jdbc.core.JdbcTemplate;

import com.self.beans.Customer;
import com.self.beans.TRNWLBean;

public class CustomItemProcessor implements ItemProcessor<ArrayList<Customer>,ArrayList<TRNWLBean>>{
	private static Logger logger = Logger.getLogger(CustomItemProcessor.class);
	private static ResourceBundle rsBundle = ResourceBundle.getBundle("com.self.resources.query");
	JdbcTemplate db2Template;
	JdbcTemplate sqlTemplate;
	int custcode;
	String custname;
	double amount;
	String sys_dt="";
	 ArrayList<TRNWLBean> outputBean= new ArrayList<>();   ;
	 public CustomItemProcessor() {
			super();
			// TODO Auto-generated constructor stub
		}
		JdbcTemplate jdbcTemplates;
		public CustomItemProcessor(JdbcTemplate sqlTemplate) {
			this.sqlTemplate=sqlTemplate;	
		}


	@Override
	public ArrayList<TRNWLBean> process(ArrayList<Customer> list)
	
			throws Exception {
		logger.info("CustomItemProcessor.process() size->"+list.size());
		System.out.println("CustomItemProcessor.process() size->"+list.size());
		for (Customer appBean : list) {
			custcode= appBean.getCustcode();
			custname=appBean.getCustname();
			amount=appBean.getAmount();
		 System.out.println("CustomItemProcessor.process()   -->custcode :"+custcode+" custname :"+custname);
			 TRNWLBean trnwlBean = 
						getTRNWLBean(custcode,custname,amount);
			 System.out.println("\n after getTRNWLBean:"+trnwlBean);
			 outputBean.add(trnwlBean);
			System.out.println("trnwlBean--->1::"+trnwlBean);
			 System.out.println("CustomItemProcessor.process()   -->custcode :"+custcode+" custname :"+custname);
			
		}
		return outputBean;
	}
	

	
	private TRNWLBean getTRNWLBean(int custcode,String custname, Double amount) {
		System.out.println("\n Inside getTRNWLBean custcode:"+custcode+">>custname:"+custname+">>amount:"+amount);
			TRNWLBean trnwlBean = new TRNWLBean();
			trnwlBean.setCustcode(custcode);
			trnwlBean.setCustname(custname);		
			trnwlBean.setAmount(amount);

          return trnwlBean;
     }

}
