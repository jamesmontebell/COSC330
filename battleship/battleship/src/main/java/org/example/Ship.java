package org.example;

public class Ship {
    private int length;
    private String name;
    Ship(int length, String name)
    {
        this.length = length;
        this.name = name;
    }

    public int getSize()
    {
        return length;
    }
    public void setLength(int l)
    {
        length = l;
    }

}
