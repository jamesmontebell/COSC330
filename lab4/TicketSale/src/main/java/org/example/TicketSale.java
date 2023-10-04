package org.example;

public class TicketSale {
    private PriceStrategy priceStrategy;
    private TaxStrategy taxStrategy;

    public TicketSale(PriceStrategy p, TaxStrategy t)
    {
        priceStrategy = p;
        taxStrategy = t;
    }
    public double getAmt(){
        return priceStrategy.getAmt() + taxStrategy.getAmt();
    }

}
