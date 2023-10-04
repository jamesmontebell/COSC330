package org.example;

public class Main {
    public static void main(String[] args) {

        SaleOrder order = new SaleOrder(new USTax() {
        });
        SaleOrder order1 = new SaleOrder(new CanadaTax() {
        });
        SaleOrder order2 = new SaleOrder(new GermanyTax() {
        });
        System.out.println(order.calTax());
        System.out.println(order1.calTax());
        System.out.println(order2.calTax());
    }
}