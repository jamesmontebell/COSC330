package org.example;

public class Course {
    private String title;
    private String instructor;
    private String[] students;
    private Sort st;

    public Course(Sort st){
        this.st = st;
    }

    public void sort()
    {
        st.sort();
    }


 }
