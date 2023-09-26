package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View {
    private JFrame frame;
    private JPanel game;
    private JTextArea turn;
    private JButton[][] buttons;
    private String playersTurn = "X";
    private int turnCount = 0;
    public View(){
        frame = new JFrame("TicTacToe");
        frame.setLayout(new GridBagLayout());
        frame.setPreferredSize(new Dimension(350, 350));

        game = new JPanel();
        game.setLayout(new GridLayout(3, 3));

        buttons = new JButton[3][3];

        turn = new JTextArea();
        turn.setText("Player X's turn!");
        frame.add(turn);

        for (int i=0; i < 3; i++)
        {
            for (int j=0; j < 3; j++)
            {
                buttons[i][j] = new JButton();
                buttons[i][j].setName(i + "" + j);
                game.add(buttons[i][j]);
            }
        }
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
    }

    public void addListeners(ActionListener listener)
    {
        for (int i=0; i < 3; i++)
        {
            for (int j=0; j < 3; j++)
            {
                buttons[i][j].addActionListener(listener);
            }
        }
    }

    public String getPlayersTurn()
    {
        return playersTurn;
    }

    public String setPlayersTurn(String s)
    {
        return playersTurn = s;
    }

    public void displayWin(String s)
    {
        if(s == "X")
        {
            turn.setText("Player X wins!");
        }
        else
        {
            turn.setText("Player O wins!");
        }
    }
    public int addTurn()
    {
        return turnCount++;
    }

    public int getTurnCount()
    {
        return turnCount;
    }

    public void displayDraw()
    {
        turn.setText("DRAW!");
    }

    public void setPlayersTurnDisplay(String s)
    {
        turn.setText("Player "+s+ "'s turn!");
    }




}
