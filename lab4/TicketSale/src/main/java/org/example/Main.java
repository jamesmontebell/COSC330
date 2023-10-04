package org.example;

public class Main {
    public static void main(String[] args) {
        TicketSale s = new TicketSale(new Adult(), new US());
        TicketSale s1 = new TicketSale(new Children(), new Canada());
        TicketSale s2 = new TicketSale(new Senior(), new Germany());

        System.out.println(s.getAmt());
        System.out.println(s1.getAmt());
        System.out.println(s2.getAmt());

    }
}