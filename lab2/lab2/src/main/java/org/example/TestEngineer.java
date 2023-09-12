package org.example;

public class TestEngineer extends TechnicalStaff
{
    private int profitSharing;

    public TestEngineer(String lastName, String firstName, String jobTitle, int baseSalary, int profitSharing) {
        super(lastName, firstName, jobTitle, baseSalary, profitSharing);
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
    }
}
