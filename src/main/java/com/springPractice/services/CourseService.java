package com.springPractice.services;

import java.util.List;

import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import com.springPractice.config.HibernateConfig;
import com.springPractice.models.Course;

@Service
public class CourseService {
	
	  private HibernateConfig hibernateConfig;
	  
	  public CourseService(HibernateConfig hibernateConfig) { 
		  this.hibernateConfig = hibernateConfig; }
	 
	  public Course getByCode(String courseCode) {
		var session = hibernateConfig.getSession();
		Transaction tx = session.getTransaction();
		if (!tx.isActive())
			tx = session.beginTransaction();

		var query = session.getEntityManagerFactory().createEntityManager()
				.createQuery("select c from Course c where courseCode=:courseCode ", Course.class);
		query.setParameter("courseCode", courseCode);
		Course course = query.getResultList().get(0);
		session.close();
		return course;
	}


	public List<Course> getAll() {
		var session = hibernateConfig.getSession();
		Transaction tx = session.getTransaction();
		if (!tx.isActive())
			tx = session.beginTransaction();
		var query = session.getEntityManagerFactory().createEntityManager().createQuery("select c from Course c ",
				Course.class);
		var courseList = query.getResultList();
		session.close();
		return courseList;

	}
		
}