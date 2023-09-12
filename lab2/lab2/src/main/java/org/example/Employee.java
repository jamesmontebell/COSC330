package org.example;

public class Employee {
    private String lastName;
    private String firstName;
    private String jobTitle;
    private int baseSalary;

    public Employee(String lastName, String firstName, String jobTitle, int baseSalary)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.jobTitle = jobTitle;
        this.baseSalary = baseSalary;
    }
    public int getBaseSalary()
    {
        return baseSalary;
    }
    public String getLastName()
    {
        return lastName;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public String getJobTitle()
    {
        return jobTitle;
    }
    public int calculateSalary()
    {
        return baseSalary;
    }
    public void displayFullname()
    {
        System.out.println(firstName + " " + lastName);
    }
    public void displaySalary()
    {
        System.out.println(calculateSalary());
    }
    public void displayJobTitle()
    {
        System.out.println(jobTitle);
    }
    public void displayInformation()
    {
        displayFullname();
        displayJobTitle();
        System.out.println(baseSalary);
    }

}
