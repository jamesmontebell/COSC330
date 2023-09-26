package org.example;

public class SaleOrder {
    private SaleTax saleTax;

    public SaleOrder(SaleTax st)
    {
        saleTax = st;
    }
    public double calTax(){
        return saleTax.calcTax();
    }
}
