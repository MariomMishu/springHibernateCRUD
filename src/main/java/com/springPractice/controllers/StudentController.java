package com.springPractice.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springPractice.models.Student;
import com.springPractice.services.CountryService;
import com.springPractice.services.CourseService;
import com.springPractice.services.StudentService;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CourseService courseService;

	@GetMapping("/student/add")
	public String add_GET(Model model) {
		model.addAttribute("student", new Student());
		model.addAttribute("country_list", countryService.getAll());
		model.addAttribute("course_list", courseService.getAll());
		return "student/add";
	}
	@PostMapping("/student/add")
	public String add(Model model, @ModelAttribute("student") Student student) {
		studentService.add(student);
		model.addAttribute("message", "New Student Added Successfully");
		return "redirect:/student/show-all";
	}
	@GetMapping("/student/show-all")
	public String show_all(Model model) {
		model.addAttribute("student", new Student());
		model.addAttribute("student_list", studentService.getAll());
		model.addAttribute("message", "Showing All Students");
		return "student/show-all";
	}
	@GetMapping("/student/edit")
	public String edit_GET(Model model, @RequestParam("id") long id) {
		Student student = studentService.getById(id);
		List<String> courses = new ArrayList<String>();
		student.getCourses().forEach(course->{
			courses.add(course.getCourseCode());
			});
		student.setCourseCodes(courses);
		model.addAttribute("student",student);
		model.addAttribute("course_list", courseService.getAll());
		model.addAttribute("country_list", countryService.getAll());
		return "student/edit";
	}
	@PostMapping("/student/edit")
	public String edit(Model model, @ModelAttribute(name = "student") Student student) {
		studentService.edit(student);
		model.addAttribute("message", "Student Edited Successfully");
		return "redirect:/student/show-all";
	}
	
	@GetMapping("/student/delete")
	public String deleteCourse_GET(Model model, @RequestParam("id") long id) {
		studentService.delete(id);
		model.addAttribute("message","Student deleted successfully");
		return "redirect:/student/show-all";
	}

}
