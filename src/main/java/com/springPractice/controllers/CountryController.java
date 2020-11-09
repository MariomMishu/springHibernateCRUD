package com.springPractice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springPractice.models.Country;
import com.springPractice.services.CountryService;

@Controller
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@GetMapping("/country/add")
	public String addCountry_GET(Model model) {
		model.addAttribute("country",new Country());
		return "country/add";
	}
	
	@PostMapping("/country/add")
	public String addCountry(Model model, @ModelAttribute(name="country") Country country) {
		countryService.add(country);
		model.addAttribute("message", "Country Added Successfully");
		return "redirect:/country/add";
	}
	
	@GetMapping("country/show-all")
	public String showAll_GET(Model model) {
		model.addAttribute("countries", countryService.getAll());
		model.addAttribute("message", "Show All Countries");
		return "country/show-all";
	}
	@GetMapping("/country/edit")
	public String editCountry_GET(Model model, @RequestParam("id") long id) {
		Country c = countryService.getById(id);
		//System.out.println(c.toString());
		model.addAttribute("country",c);
		return "country/edit";
	}
	
	@PostMapping("/country/edit")
	public String edit(Model model, @ModelAttribute(name="country") Country country) {
		countryService.edit(country);
		model.addAttribute("message", "Country Edited Successfully");
		return "redirect:/country/show-all";
	}
}
