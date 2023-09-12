package org.example;

public class Manager extends Employee {
    private int bonusPayment;
    public Manager(String lastName, String firstName, String jobTitle, int baseSalary, int bonusPayment) {
        super(lastName, firstName, jobTitle, baseSalary);
        this.bonusPayment = bonusPayment;
    }

    public int getBonusPayment()
    {
        return bonusPayment;
    }

    public int calculateSalary()
    {
        return(bonusPayment + getBaseSalary());
    }
    public void displayBonus()
    {
        System.out.println(bonusPayment);
    }
    public void displayInformation()
    {
        super.displayInformation();
        displayBonus();
    }
}
