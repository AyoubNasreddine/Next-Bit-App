package com.example.nextbit_application_by_ayoubNextCode_2025.Classes;

public class Course {
    private String name,courseDescription;
    private int imageResource;
    private  boolean available;
    public Course(){}
    public Course(String name , int imageResource, boolean available,String courseDescription){
        setName(name);
        setImageResource(imageResource);
        setAvailable(available);
        setCourseDescription(courseDescription);
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getImageResource() {
        return imageResource;
    }
    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
}