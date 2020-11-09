package com.springPractice.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


public class Course implements Serializable {

	
	private long courseId;
	
	private String courseTitle;

	private String courseCode;
	
	
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public Course(long courseId, String courseCode, String courseTitle) {
		super();
		this.courseId = courseId;
		this.courseCode = courseCode;
		this.courseTitle = courseTitle;
	}
	public Course() {
		super();
	}
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseCode=" + courseCode + ", courseTitle=" + courseTitle + "]";
	}
	

}
