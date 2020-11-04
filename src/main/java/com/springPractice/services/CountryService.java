package com.springPractice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.springPractice.models.Country;

@Service
public class CountryService {
	public static List<Country> countries= new ArrayList<Country>();
	private static final String[] COUNTRIES = {"Bangladesh", "India"};
	
	public CountryService() {
		Stream.of(COUNTRIES).forEach(country->{
			addCountry(country);
		});
	}
	private void addCountry(String countryName) {
		var cObj = new Country();
		cObj.setId(countries.size()+1);
		cObj.setCountryName(countryName);
		cObj.setCountryCode(countryName.substring(0,3));
		countries.add(cObj);
	}
	public void addCountry(Country country) {
		country.setId(countries.size()+1);
		countries.add(country);
	}
	public List<Country> getAll(){
		return countries;
	}
	
}
