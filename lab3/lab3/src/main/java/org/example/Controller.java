package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model m, View v)
    {
        model = m;
        view = v;
        view.addListeners(new Listener());
    }

    public class Listener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton myButton = (JButton) e.getSource();
            if(view.getPlayersTurn() == "X" && myButton.getText() == "")
            {
                myButton.setText("X");
                view.setPlayersTurn("O");
                view.setPlayersTurnDisplay("O");
                String[] pos = myButton.getName().split("");

                int x = Integer.parseInt(pos[0]);
                int y = Integer.parseInt(pos[1]);

                model.getBoard().insertX(x, y);
                model.getBoard().printBoard();

                view.addTurn();

            }
            else if (view.getPlayersTurn() == "O" && myButton.getText() == "")
            {
                myButton.setText("O");
                view.setPlayersTurn("X");
                view.setPlayersTurnDisplay("X");
                String[] pos = myButton.getName().split("");

                int x = Integer.parseInt(pos[0]);
                int y = Integer.parseInt(pos[1]);

                model.getBoard().insertO(x, y);
                model.getBoard().printBoard();
                view.addTurn();
            }
            if(model.checkWin())
            {
                String winner = model.getWin();
                view.displayWin(winner);
            }
            else
            {
                if(view.getTurnCount() == 9)
                {
                    view.displayDraw();
                }

            }
        }
    }
}
