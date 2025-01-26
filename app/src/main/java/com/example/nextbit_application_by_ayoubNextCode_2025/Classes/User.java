package com.example.nextbit_application_by_ayoubNextCode_2025.Classes;

public class User {
    private String name,email,password;
    private int age ;
    private boolean javaCourse , pythonCourse , premium , csCourse;
    public User(String name, String email, String password, int age) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setAge(age);
    }
    public User(String name, String email, String password, int age, boolean javaCourse, boolean pythonCourse,boolean premium,boolean CsCourse) {
        setName(name);
        setEmail(email);
        setPassword(password);
        setAge(age);
        setJavaCourse(javaCourse);
        setPythonCourse(pythonCourse);
        setPremium(premium);
        setCsCourse(CsCourse);
    }
    public User(){}
    public boolean isPremium() {
        return premium;
    }

    public boolean isCsCourse() {
        return csCourse;
    }

    public void setCsCourse(boolean csCourse) {
        this.csCourse = csCourse;
    }
    public void setPremium(boolean premium) {
        this.premium = premium;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public boolean isJavaCourse() {
        return javaCourse;
    }
    public void setJavaCourse(boolean javaCourse) {
        this.javaCourse = javaCourse;
    }
    public boolean isPythonCourse() {
        return pythonCourse;
    }
    public void setPythonCourse(boolean pythonCourse) {
        this.pythonCourse = pythonCourse;
    }
}
