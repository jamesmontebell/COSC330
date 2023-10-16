package org.example;

public class Model {

    private String[][] myGrid = new String[10][10];
    private String[][] myOppGrid = new String[10][10];
    private Ship ships[];
    private int totalShips;
    private int totalHits;

    private String turn;


    Model()
    {
        for (int row = 0; row < 10; row++)
            for (int col = 0; col < 10; col++)
                myGrid[row][col] = "   ";

        for (int row = 0; row < 10; row++)
            for (int col = 0; col < 10; col++)
                myOppGrid[row][col] = "   ";

        ships = new Ship[5];
        ships[0] = new Ship(2, "destroyer");
        ships[1] = new Ship(3, "sub");
        ships[2] = new Ship(3, "cruiser");
        ships[3] = new Ship(4, "battleship");
        ships[4] = new Ship(5, "carrier");

        totalShips = 17;
        totalHits = 0;

        turn = "Client";
    }

    public void placeRandom()
    {
        for(int i = 0; i < 5; i++)
        {
            boolean collides = true;
            int horiz = (int)(Math.random() * 2);
            int shipRow, shipCol;
            boolean horizontal = (horiz == 1) ? true : false;
            do{
                if(horizontal){
                    shipCol = (int)(Math.random() * (9 - ships[i].getSize()+1 + 1));
                    shipRow = (int)(Math.random() * (9 + 1));
                }
                else{
                    shipCol = (int)(Math.random() * (9 + 1));
                    shipRow = (int)(Math.random() * (9-ships[i].getSize()+1 + 1));
                }
              collides = placeShip(shipRow, shipCol, horizontal, ships[i]);
            }
            while(!collides);
        }
    }
    public boolean placeShip( int row, int col, boolean horizontal, Ship ship)
    {
        int length = ship.getSize();
        int iter = horizontal ? col : row;


        for (int i = iter; i < iter+length; i++) {
            if(horizontal) {
                if(myGrid[row][i].equals(" s ")) return false;}
            else {
                if(myGrid[i][col].equals(" s ")) return false; }
        }

        for(int i = iter; i < iter + length; i++)
        {
            if(horizontal)
            {
                myGrid[row][i] = " s ";
            }
            else
            {
                myGrid[i][col] = " s ";
            }
        }
        return true;
    }

    public void shoot(int x, int y, Model opp)
    {
        if(opp.checkHit(x, y))
        {
            myOppGrid[x][y] = " X ";
            totalHits++;
            System.out.println("HIT");
        }
        else
        {
            myOppGrid[x][x] = " O ";
            System.out.println("MISS");
        }
    }

    public boolean checkHit(int x, int y)
    {
        if(myGrid[x][y] == " s ")
        {
            totalHits--;
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean recieveHit(String check){
        int hit = Integer.parseInt(check);
        if(hit == 0){
            return false;
        }
        else if(hit == 1){
            return true;
        }
        else{
            return false;
        }

    }

    public boolean checkWin()
    {
        if(totalHits == 17)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkLose()
    {
        if(totalShips == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getTurn()
    {
        return turn;
    }

    public void setTurn(String x)
    {
        turn = x;
    }

    public String[][] getMyBoard()
    {
        return myGrid;
    }

    public String[][] getBoard(){
        return myGrid;
    }
    public Ship[] getShips(){
        return ships;
    }
    public void setGridPos(int x, int y, String val)
    {
        myGrid[x][y] = val;
    }
    public String getPos(int x, int y){
        return myGrid[x][y];
    }

    public void printBoard(){
        System.out.println("Opponent Grid");
        System.out.println("-----------------------------------------");
        for(int i = 0; i < 10; i++) {
            System.out.print("|");
            for(int j = 0; j < 10; j++){
                System.out.print("");
                System.out.print(myOppGrid[i][j]);
                System.out.print("");
                System.out.print("|");
            }

            System.out.println();
            System.out.print("-----------------------------------------");
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("My Grid");
        System.out.println("-----------------------------------------");
        for(int i = 0; i < 10; i++) {
            System.out.print("|");
            for(int j = 0; j < 10; j++){
                System.out.print("");
                System.out.print(myGrid[i][j]);
                System.out.print("");
                System.out.print("|");
            }

            System.out.println();
            System.out.print("-----------------------------------------");
            System.out.println();
        }
    }


}
