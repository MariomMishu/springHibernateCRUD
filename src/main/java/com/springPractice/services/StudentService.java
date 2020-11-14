package com.springPractice.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springPractice.config.HibernateConfig;
import com.springPractice.models.Course;
import com.springPractice.models.Student;
@Service
public class StudentService {
	@Autowired
	private CountryService countryService;
	@Autowired
	private CourseService courseService;

	private HibernateConfig hibernateConfig;

	public StudentService(HibernateConfig hibernateConfig) {
		this.hibernateConfig = hibernateConfig;
	}

	public void add(Student student) {

		var country = countryService.getByCode(student.getCountry().getCountryCode());
		var course_list = new ArrayList<Course>();
		for (String courseCode : student.getCourseCodes()) {
			var course = courseService.getByCode(courseCode);
			course_list.add(course);
		}
		student.setCountry(country);
		student.setCourses(course_list);

		var session = hibernateConfig.getSession();
		var transaction = session.getTransaction();
		if (!transaction.isActive()) {
			transaction = session.beginTransaction();
		}
		try {
			session.save(student);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

	}

	public List<Student> getAll() {
		var session = hibernateConfig.getSession();
		var transaction = session.getTransaction();
		if (!transaction.isActive()) {
			transaction = session.beginTransaction();
		}
		
		// Start Criteria Query
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> sc = cb.createQuery(Student.class);
		Root<Student> root = sc.from(Student.class);
		sc.select(root);
		var query = session.getEntityManagerFactory().createEntityManager().createQuery(sc);
		// end Criteria Query

		var student_list = query.getResultList();
		session.close();
		return student_list;
	}

	public Student getById(long studentId) {

		var session = hibernateConfig.getSession();
		var transaction = session.getTransaction();
		if (!transaction.isActive()) {
			transaction = session.beginTransaction();
		}
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Student> sc = cb.createQuery(Student.class);
		Root<Student> root = sc.from(Student.class);
		sc.where(cb.equal(root.get("id"), studentId));
		// sc.select(root);
		var query = session.getEntityManagerFactory().createEntityManager().createQuery(sc);
		var student_list = query.getResultList();
		session.close();
		return Optional.ofNullable(student_list.get(0))
				.orElseThrow(() -> new RuntimeException("Student Not Found With Thid Id"));
	}

	public void edit(Student student) {
		var country = countryService.getByCode(student.getCountry().getCountryCode());
		var course_list = new ArrayList<Course>();
		for (String courseCode : student.getCourseCodes()) {
			var course = courseService.getByCode(courseCode);
			course_list.add(course);
		}
		student.setCountry(country);
		student.setCourses(course_list);

		var session = hibernateConfig.getSession();
		var transaction = session.getTransaction();
		if (!transaction.isActive()) {
			transaction = session.beginTransaction();
		}
		try {
			session.update(student);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	public void delete(long studentId) {
		var session = hibernateConfig.getSession();
		var transaction = session.getTransaction();
		if (!transaction.isActive()) {
			transaction = session.beginTransaction();
		}
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaDelete<Student> sc = cb.createCriteriaDelete(Student.class);
		Root<Student> root = sc.from(Student.class);
		sc.where(cb.equal(root.get("id"), studentId));
		var query = session.createQuery(sc);
		try {
			query.executeUpdate();
			session.close();
		}catch(HibernateException e) {
			if(transaction !=null) {
				transaction.rollback();
			}e.printStackTrace();
		}
		
	}

}
