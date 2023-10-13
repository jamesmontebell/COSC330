package org.example;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        View v = new View();
        Model m = new Model();
        Controller c = new Controller(m, v);
//        Model board1 = new Model();
//        Model board2 = new Model();
//
//        Scanner scan= new Scanner(System.in);
//        board1.placeRandom();
//        board2.placeRandom();
//
//        while(true)
//        {
//            System.out.println("Player 1");
//            board1.printBoard();
//            System.out.println();
//            System.out.println();
//            System.out.println();
//            System.out.println();
//            System.out.println();
//            System.out.println();
//            System.out.println();
//            System.out.println();
//            System.out.println();
//            System.out.println("Player 2");
//            board2.printBoard();
//
//            System.out.println("PLayer 1 enter row: ");
//            int row= scan.nextInt();
//            System.out.println("PLayer 1 enter column: ");
//            int column = scan.nextInt();
//
//            board1.shoot(row, column, board2);
//            if(board1.checkWin())
//            {
//                System.out.println("Player 1 wins!");
//                break;
//            }
//            else if (board2.checkWin())
//            {
//                System.out.println("Player 2 wins!");
//                break;
//            }
//
//            System.out.println("PLayer 2 enter row: ");
//            int row2= scan.nextInt();
//            System.out.println("PLayer 2 enter column: ");
//            int column2 = scan.nextInt();
//            board2.shoot(row2, column2, board1);
//
//            if(board1.checkWin())
//            {
//                System.out.println("Player 1 wins!");
//                break;
//            }
//            else if (board2.checkWin())
//            {
//                System.out.println("Player 2 wins!");
//                break;
//            }
//        }
    }
}