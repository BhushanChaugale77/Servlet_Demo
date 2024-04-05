package com.prowings.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.prowings.dao.HibernateUtil;
import com.prowings.entity.Student;

public class StudentServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 		super.doPost(req, resp);
 		
 		String name = req.getParameter("name");
 		String rollno = req.getParameter("rollno");
 		String address = req.getParameter("address");
 		
 		System.out.println("Name : "+name);
 		System.out.println("RollNo : "+rollno);
 		System.out.println("Address : "+address);
 		
 		Student student = new Student();
 		
 		student.setName(name);
 		student.setRollNo(Integer.parseInt(rollno));
 		student.setAddress(address);
 		
 		System.out.println(student);
 		
 		System.out.println("<<<<<<<<<<<  saving Student to DB >>>>>>>>>>>>>");
 		
 		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
 		
 		Session session = sessionFactory.openSession();
 		
 		Transaction tx = session.beginTransaction();
 		
 		session.save(student);
 		
 		tx.commit();
 		
 		session.close();
 		sessionFactory.close();
	}
	
	
	
	

}
