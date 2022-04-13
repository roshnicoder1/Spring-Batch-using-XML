package com.self.writer;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.self.beans.TRNWLBean;

public class CustomItemWriter implements ItemWriter<ArrayList<TRNWLBean>>{
	private static Logger logger = Logger.getLogger(CustomItemWriter.class);
	private List<String> totalRecord = new ArrayList<String>();
	private static ResourceBundle rsBundle = ResourceBundle.getBundle("com.self.resources.query");
	JdbcTemplate sqlTemplate;
	private String strUpdate =rsBundle.getString("strUpdate");
	public CustomItemWriter(JdbcTemplate sqlTemplate) {
		this.sqlTemplate=sqlTemplate;	
	}



	@Override
	public void write(List<? extends ArrayList<TRNWLBean>> list)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("CustomItemWriter.write().size()-->"+list);
		logger.info("In CustomItemWriter.write()");
		for (ArrayList<TRNWLBean> arrayList : list) {
			System.out.println(">>arrayList:"+arrayList);
			totalRecord.add(arrayList.size()+"");
			for (TRNWLBean trnwlBean : arrayList) {
			updateData(trnwlBean.getCustcode(),trnwlBean.getCustname(),trnwlBean.getAmount());
			}
		
		}
		
		
	}
	private void updateData(int custcode,String custname, Double amount) {
		System.out.println("CustomItemWriter.updateData()");
		try {
		
			int res = sqlTemplate.update(strUpdate,custcode+2,custname,amount);
			System.out.println("Upadte record ::"+res);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("WRITER  UPDATE-->"+e);
		}
	}

}
