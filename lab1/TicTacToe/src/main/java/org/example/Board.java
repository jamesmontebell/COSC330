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

    public void insertX(int row, int columns){
        if(matrix[row][columns] == " "){
            matrix[row][columns] = "X";
        }
        else
        {
            System.out.println("Invalid");
        }
    }

    public void insertO(int row, int columns){
        if(matrix[row][columns] == " "){
            matrix[row][columns] = "O";
        }
        else
        {
            System.out.println("Invalid");
        }
    }

    public boolean checkWin(){
        for(int i = 0; i < 3; i++ )
        {
            if(matrix[i][0] == matrix[i][1] && matrix[i][1] == matrix[i][2] && matrix[i][0] != " ")
            {
                System.out.print(matrix[0][0] + " WINS!");
                //WINS
                return true;
            }
            else if(matrix[0][i] == matrix[1][i] && matrix[1][i] == matrix[2][i] && matrix[0][i] != " ")
            {
                System.out.print(matrix[0][0] + " WINS!");
                //WINS
                return true;
            }
        }
       if(matrix[0][0] ==  matrix[1][1] && matrix[1][1] == matrix[2][2] && matrix[0][0] != " ")
       {
           // matrix[0][0] wins
           System.out.print(matrix[0][0] + " WINS!");
           return true;
       }
       else if(matrix[0][0] == matrix[1][1]  && matrix[1][1] == matrix[2][2] && matrix[2][0] != " ")
       {
           // matrix[2][0] wins
           System.out.print(matrix[0][0] + " WINS!");
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

