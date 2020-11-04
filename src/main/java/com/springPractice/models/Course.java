package com.springPractice.models;

import java.io.Serializable;

public class Course implements Serializable{
	private long courseId;
	private String courseCode;
	private String courseTitle;
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
