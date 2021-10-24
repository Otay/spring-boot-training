package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		// read spring config java class		
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container		
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// call the business method				
		theAccountDAO.addAccount();
		
		//call  method with same name form other class
		
		MembershipDAO themembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		System.out.println("\n call the method of the second object");
		
		themembershipDAO.addAccount();
		
		//close the context
		context.close();

	}

}
