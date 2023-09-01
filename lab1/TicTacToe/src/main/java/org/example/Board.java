package org.example;

public class Board {
    public Board(){
        System.out.println("New Game!");
    }
    public String matrix[][] = {
            {" ", " ", " "},
            {" ", " ", " "},
            {" ", " ", " "}
    };

    public boolean insertX(int row, int columns){
        if(row > 2 || columns > 2)
        {
            System.out.println("Out of range");
            return false;
        }
        else if(matrix[row][columns] == " "){
            matrix[row][columns] = "X";
            return true;
        }
        else
        {
            System.out.println("Invalid");
            return false;
        }
    }

    public boolean insertO(int row, int columns){
        if(row > 2 || columns > 2)
        {
            System.out.println("Out of range");
            return false;
        }
        else if(matrix[row][columns] == " "){
            matrix[row][columns] = "O";
            return true;
        }
        else
        {
            System.out.println("Invalid");
            return false;
        }
    }

    public boolean checkWin(){
        for(int i = 0; i < 3; i++ )
        {
            if(matrix[i][0] == matrix[i][1] && matrix[i][1] == matrix[i][2] && matrix[i][0] != " ")
            {
                System.out.print(matrix[0][0] + " WINS!");
                //WINS
                printBoard();
                return true;
            }
            else if(matrix[0][i] == matrix[1][i] && matrix[1][i] == matrix[2][i] && matrix[0][i] != " ")
            {
                System.out.print(matrix[0][0] + " WINS!");
                //WINS
                printBoard();
                return true;
            }
        }
       if(matrix[0][0] ==  matrix[1][1] && matrix[1][1] == matrix[2][2] && matrix[0][0] != " ")
       {
           // matrix[0][0] wins
           System.out.print(matrix[0][0] + " WINS!");
           printBoard();
           return true;
       }
       else if(matrix[0][0] == matrix[1][1]  && matrix[1][1] == matrix[2][2] && matrix[2][0] != " ")
       {
           // matrix[2][0] wins
           System.out.print(matrix[0][0] + " WINS!");
           printBoard();
           return true;
        }
       else
       {
           return false;
       }
    }

    public void printBoard(){
        System.out.println("-------------");
        for(int i = 0; i < 3; i++) {
            System.out.print("|");
            for(int j = 0; j < 3; j++){
                System.out.print(" ");
                System.out.print(matrix[i][j]);
                System.out.print(" ");
                System.out.print("|");
            }

            System.out.println();
            System.out.print("-------------");
            System.out.println();
        }
    }
}

