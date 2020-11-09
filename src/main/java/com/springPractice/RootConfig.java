package com.springPractice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.springPractice.config.HibernateConfig;


@ComponentScan(basePackages = {"com.springPractice.services"})
public class RootConfig {
	@Bean 
	HibernateConfig hibernateConfig() {
		return new HibernateConfig();
	}
}
