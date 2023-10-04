package org.example;

public abstract class CanadaTax implements SaleTax {
    @Override
    public double calcTax() {
        return 100.100;
    }
}
