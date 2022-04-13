package com.self.job;

import java.util.ResourceBundle;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.log4j.Logger;

@SpringBootApplication
public class ReportExtractBatchApplication {
	private static ResourceBundle rsBundle = ResourceBundle.getBundle("com.self.resources.config");
	private static Logger logger = Logger.getLogger(ReportExtractBatchApplication.class);
	public static void main(String[] args) {
	logger.info("Execution started...");
	try {
	String[] springConfig  =  {rsBundle.getString("SPRING_CONFIG")};
	 
	// Creating the application context object  
	ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);
	     
	// Creating the job launcher
	JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
	Job dbJob=(Job)context.getBean("exportDataJob");
	     
	// Executing the JOB
	 
	JobExecution execution=jobLauncher.run(dbJob,new JobParameters());
	logger.info("Exit Status : " + execution.getStatus());
	System.out.println("Exit Status : " + execution.getStatus());
	} catch (JobExecutionAlreadyRunningException e) {
	logger.error("Exeption in JobRunner :"+e);
	e.printStackTrace();
	} catch (JobRestartException e) {
	logger.error("Exeption in JobRunner :"+e);
	e.printStackTrace();
	} catch (JobInstanceAlreadyCompleteException e) {
	logger.error("Exeption in JobRunner :"+e);
	e.printStackTrace();
	} catch (JobParametersInvalidException e) {
	logger.error("Exeption in JobRunner :"+e);
	e.printStackTrace();
	}  
	}
}


