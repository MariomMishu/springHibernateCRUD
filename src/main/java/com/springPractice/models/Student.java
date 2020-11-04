package com.springPractice.models;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable{
private long id;
private String name;
private int age;
private String email;
private String gender;
private List<Course> courses;
private Country country;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public List<Course> getCourses() {
	return courses;
}
public void setCourses(List<Course> courses) {
	this.courses = courses;
}
public Country getCountry() {
	return country;
}
public void setCountry(Country country) {
	this.country = country;
}
public Student(long id, String name, int age, String email, String gender, List<Course> courses, Country country) {
	super();
	this.id = id;
	this.name = name;
	this.age = age;
	this.email = email;
	this.gender = gender;
	this.courses = courses;
	this.country = country;
}
public Student() {
	super();
}
@Override
public String toString() {
	return "Student [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", gender=" + gender
			+ ", courses=" + courses + ", country=" + country + "]";
}

}
