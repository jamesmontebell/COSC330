package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan= new Scanner(System.in);
        Board game = new Board();


//        b.printBoard();
//        b.insertX(0, 0);
//        b.insertX(1, 1);
//        b.insertX(2, 2);
//        b.printBoard();
//        boolean win = b.checkWin();

        while(true)
        {
            if (game.checkWin())
            {
                break;
            }

            game.printBoard();

            while(true)
            {
                System.out.println("PLayer X enter row: ");
                int row= scan.nextInt();
                System.out.println("PLayer X enter column: ");
                int column = scan.nextInt();
                boolean i = game.insertX(row, column);
                if(i)
                {
                    break;
                }
                else
                {
                    game.printBoard();
                }
            }

            game.printBoard();
            if (game.checkWin())
            {
                break;
            }

            while(true)
            {
                System.out.println("PLayer O enter row: ");
                int row= scan.nextInt();
                System.out.println("PLayer O enter column: ");
                int column = scan.nextInt();
                boolean i = game.insertO(row, column);
                if(i)
                {
                    break;
                }
                else
                {
                    game.printBoard();
                }
            }
            if (game.checkWin())
            {
                break;
            }
        }
    }

}