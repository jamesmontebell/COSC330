package org.example;

public class Main {
    public static void main(String[] args) {
        Course course = new Course(new BubbleSort());
        course.sort();
    }
}