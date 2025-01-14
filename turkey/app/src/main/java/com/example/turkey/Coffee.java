package com.example.turkey;

public class Coffee {
    double cost = 4.00;
    boolean isCream;
    boolean isChocolate;
    int quantity;

    public void setIsCream(boolean x) {
        isCream = x;
    }
    public void setIsChocolate(boolean x) {
        isChocolate = x;
    }
    public void updateQuantity(int x) {
        if(x == 0) {
            if (quantity == 0)
                quantity = 0;
            else
                quantity--;
        } else if (x == 1)
            quantity++;
    }

    public void updateCost(double x) { cost += x; }

    public String getIsCream() {
        if (isCream == true)
            return "yes";
        else
            return "no";
    }

    public String getIsChocolate() {
        if(isChocolate == true)
            return "yes";
        else
            return "no";
    }

    public int getQuantity() { return quantity; }

    public double getCost() {
        double temp = 0;
        if (isCream == true)
            temp += 0.5;
        if(isChocolate == true)
            temp += 1.0;

        return (cost + temp) * quantity; }

}
