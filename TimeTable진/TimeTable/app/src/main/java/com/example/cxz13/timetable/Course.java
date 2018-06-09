package com.example.cxz13.timetable;

public class Course {
    String courseUniversity;
    String courseMajor;
    String courseGrade;
    String courseArea;
    String courseCode;
    String courseTitle;
    String courseCredit;
    String courseProfessor;
    String courseTime;

    public String getCourseUniversity() {
        return courseUniversity;
    }

    public void setCourseUniversity(String courseUniversity) {
        this.courseUniversity = courseUniversity;
    }

    public String getCourseMajor() {
        return courseMajor;
    }

    public void setCourseMajor(String courseMajor) {
        this.courseMajor = courseMajor;
    }

    public String getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    public String getCourseArea() {
        return courseArea;
    }

    public void setCourseArea(String courseArea) {
        this.courseArea = courseArea;
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

    public String getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(String courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getCourseProfessor() {
        return courseProfessor;
    }

    public void setCourseProfessor(String courseProfessor) {
        this.courseProfessor = courseProfessor;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public Course(String courseUniversity, String courseMajor, String courseGrade, String courseArea, String courseCode, String courseTitle, String courseCredit, String courseProfessor, String courseTime) {
        this.courseUniversity = courseUniversity;
        this.courseMajor = courseMajor;
        this.courseGrade = courseGrade;
        this.courseArea = courseArea;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseCredit = courseCredit;
        this.courseProfessor = courseProfessor;
        this.courseTime = courseTime;
    }
}
