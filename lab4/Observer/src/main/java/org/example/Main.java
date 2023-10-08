package org.example;

public class Main {
    public static void main(String[] args) {
        WeatherData w = new WeatherData();
        CurrentConditionsDisplay c = new CurrentConditionsDisplay(w);

        w.registerObserver(c);
        w.setMeasurements(100,56, 93);
        w.deleteObserver(c);
    }
}