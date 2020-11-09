package com.springPractice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
	private static List<Country> countries = new ArrayList<Country>();
	
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
	public List<Country> getAll(){
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
	/*
	 * public static List<Country> countries= new ArrayList<Country>(); private
	 * static final String[] COUNTRIES = {"Bangladesh", "India"};
	 * 
	 * public CountryService() { Stream.of(COUNTRIES).forEach(country->{
	 * addCountry(country); }); } private void addCountry(String countryName) { var
	 * cObj = new Country(); cObj.setId(countries.size()+1);
	 * cObj.setCountryName(countryName);
	 * cObj.setCountryCode(countryName.substring(0,3)); countries.add(cObj); }
	 * public void add(Country country) { country.setId(countries.size()+1);
	 * countries.add(country); }
	 */
	/*
	 * public List<Country> getAll(){ return countries; }
	 */
	
}
