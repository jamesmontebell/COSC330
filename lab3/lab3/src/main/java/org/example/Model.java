package org.example;


public class Model {
    private Board board;
    private boolean win;

    public Model()
    {
        board = new Board();
    }
    public Board getBoard()
    {
        return board;
    }
    public boolean checkWin()
    {
        win = board.checkWin();
        return(win);
    }
    public String getWin()
    {
        return board.winner;
    }


}
