package org.example;

public class Course {
    private String[] students;
    private Sort srt;

    public Course(Sort st){
        this.srt = st;
    }

    public void coursesort()
    {
        srt.sort();
    }


 }
