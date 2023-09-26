package org.example;

public abstract class GermanyTax implements SaleTax{
    @Override
    public double calcTax() {
        return 500.500;
    }
}
