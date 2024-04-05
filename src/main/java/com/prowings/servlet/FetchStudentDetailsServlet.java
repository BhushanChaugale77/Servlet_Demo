package com.prowings.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.prowings.dao.HibernateUtil;
import com.prowings.entity.Student;

public class FetchStudentDetailsServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);

		System.out.println(">>>>>>>>>>>> Started Fetch Student Servlet <<<<<<<<<<<<<<");

		int rollno = Integer.parseInt(req.getParameter("rollno"));

		System.out.println("fetching student details for roll number :" + rollno);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		String hql = "FROM Student s WHERE s.rollno =" + rollno;

		Query<Student> query = session.createQuery(hql);

		Student fetchedstudent = query.uniqueResult();

		System.out.println(fetchedstudent);

		tx.commit();
		session.close();

		if (fetchedstudent != null) {
			req.setAttribute("student", fetchedstudent);
			// forward the request to jsp
			req.getRequestDispatcher("/Error.jsp").forward(req, resp);
		} else {
			req.setAttribute("error", new Error("No Student Present with given rollNo"));
			req.getRequestDispatcher("/Error.jsp").forward(req, resp);
		}
	}
}
