package org.example;

public class Main {
    public static void main(String[] args) {

        Board b = new Board();
        b.printBoard();
        b.insertX(0, 0);
        b.insertX(1, 1);
        b.insertX(2, 2);
        b.printBoard();
        boolean win = b.checkWin();
    }

}