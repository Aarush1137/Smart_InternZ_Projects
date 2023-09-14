package com.example.dbapp;

public class Student {
    private String rollNo;
    private String name;
    private String marks;

    public Student(String rollNo, String name, String marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNo + "\nName: " + name + "\nMarks: " + marks;
    }
}
