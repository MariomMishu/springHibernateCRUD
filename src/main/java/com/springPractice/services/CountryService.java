package com.springPractice.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springPractice.config.HibernateConfig;
import com.springPractice.models.Country;

@Service
public class CountryService {
	private final HibernateConfig hibernateConfig;
	//private static List<Country> countries = new ArrayList<Country>();
	
	@Autowired
	public CountryService(HibernateConfig hibernateConfig) {
		this.hibernateConfig= hibernateConfig;
	}
	@Transactional
	public void add(Country country) {
		var session= hibernateConfig.getSession();
		var transaction = session.beginTransaction();
		session.save(country);
		transaction.commit();
	}
	public Country getByCode(String countryCode) {
		// **************************** HQL Start ******************************//
//		var session = hibernateConfig.getSession();
//		var transaction = session.beginTransaction();
//		var query = session
//				.getEntityManagerFactory()
//				.createEntityManager()
//				.createQuery("SELECT c from com.spring5.practice.model.Country c where c.countryCode=:countryCode", Country.class);
//		query.setParameter("countryCode", countryCode);
		// **************************** HQL End ******************************//

		// **************************** Criteria Query Start
		// **************************//
		CriteriaBuilder cb = hibernateConfig.getCriteriaBuilder();
		CriteriaQuery<Country> cq = cb.createQuery(Country.class);
		Root<Country> root = cq.from(Country.class);
		cq.where(cb.equal(root.get("countryCode"), countryCode));
		var result = hibernateConfig.getSession().getEntityManagerFactory().createEntityManager().createQuery(cq)
				.getResultList();

		// **************************** Criteria Query End **************************//
		return Optional.ofNullable(result.get(0))
				.orElseThrow(() -> new RuntimeException("Country not found with this code"));
	}

	public List<Country> getAll(){
		var session= hibernateConfig.getSession();
		var transaction = session.beginTransaction();
		if(!transaction.isActive()) {
			transaction= session.beginTransaction();
		}
		///HQL Start
		/*
		 * var session = hibernateConfig.getSession(); var transaction =
		 * session.beginTransaction(); var query =
		 * session.getEntityManagerFactory().createEntityManager().
		 * createQuery("SELECT c FROM Country c", Country.class); return
		 * query.getResultList();
		 */
		//HQL End
		//criteria Start
		CriteriaBuilder cb= hibernateConfig.getCriteriaBuilder();
		CriteriaQuery<Country> cq =cb.createQuery(Country.class);
		Root<Country> root = cq.from(Country.class);
		cq.select(root);
		List<Country> countries = hibernateConfig.getSession().getEntityManagerFactory().createEntityManager().createQuery(cq).getResultList();
		return countries;
	}
	public Country getById(long countryId) {
		CriteriaBuilder cb= hibernateConfig.getCriteriaBuilder();
		CriteriaQuery<Country> cq =cb.createQuery(Country.class);
		Root<Country> root = cq.from(Country.class);
		cq.where(cb.equal(root.get("id"),countryId));
		var result = hibernateConfig.getSession().getEntityManagerFactory().createEntityManager().createQuery(cq).getResultList();
		//return Optional.ofNullable(result.get(0)).orElseThrow(()->new RunTimeException("Country Not Found with this id"));
	 return result.get(0);
	}
	public void edit(Country country) {
		var session = hibernateConfig.getSession();
		var trans = session.getTransaction();
		if(!trans.isActive()) {
			trans = session.beginTransaction();
		}
		try {
			session.update(country);
			trans.commit();
		}catch(HibernateException e ) {
			if(trans !=null) {
				trans.rollback();
			}
			e.printStackTrace();
		}
	}
	
}
