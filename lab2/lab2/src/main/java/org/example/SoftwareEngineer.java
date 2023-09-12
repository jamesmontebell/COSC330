package org.example;

public class SoftwareEngineer extends TechnicalStaff
{
    private int overtimePay;

    public SoftwareEngineer(String lastName, String firstName, String jobTitle, int baseSalary, int profitSharing, int overtimePay) {
        super(lastName, firstName, jobTitle, baseSalary, profitSharing);
        this.overtimePay = overtimePay;
    }
    public int getOvertimePay()
    {
        return overtimePay;
    }
    public int calculateSalary()
    {
        return(getBaseSalary() + overtimePay + getProfitSharing());
    }
    public void displayOvertimePay()
    {
        System.out.println(overtimePay);
    }
    public void displayInformation()
    {
        super.displayInformation();
        displayOvertimePay();
    }
}
