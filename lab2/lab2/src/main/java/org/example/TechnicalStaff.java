package org.example;

public class TechnicalStaff extends Employee
{
    private int profitSharing;

    public TechnicalStaff(String lastName, String firstName, String jobTitle, int baseSalary, int profitSharing) {
        super(lastName, firstName, jobTitle, baseSalary);
        this.profitSharing = profitSharing;
    }

    public int getProfitSharing()
    {
        return profitSharing;
    }

    public int calculateSalary()
    {
        return(getBaseSalary() + profitSharing);
    }
    public void displayProfitSharing()
    {
        System.out.println(profitSharing);
    }
    public void displayInformation()
    {
        super.displayInformation();
        displayProfitSharing();
    }
}
