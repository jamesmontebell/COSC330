package org.example;

public class Executive extends Manager
{
    private int stockOption;
    public Executive(String lastName, String firstName, String jobTitle, int baseSalary, int bonusPayment, int stockOption) {
        super(lastName, firstName, jobTitle, baseSalary, bonusPayment);
        this.stockOption = stockOption;
    }

    public int calculateSalary()
    {
        return(stockOption + getBaseSalary() + getBonusPayment());
    }
    public void displayStockOption()
    {
        System.out.println(stockOption);
    }
    public void displayInformation()
    {
        super.displayInformation();
        displayStockOption();
    }
}
