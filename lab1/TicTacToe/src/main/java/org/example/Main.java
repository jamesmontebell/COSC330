package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan= new Scanner(System.in);
        Board game = new Board();
        int turn = 0;

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
                    turn++;
                    break;
                }
                else
                {
                    game.printBoard();
                }
            }

            if(turn == 9)
            {
                game.printBoard();
                System.out.println("DRAW!");
                break;
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
                    turn++;
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