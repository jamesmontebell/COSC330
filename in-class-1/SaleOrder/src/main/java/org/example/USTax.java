package org.example;

public abstract class USTax implements SaleTax{
    @Override
    public double calcTax() {
        return 0.5;
    }
}
