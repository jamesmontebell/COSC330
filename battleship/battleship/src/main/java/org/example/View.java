package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class View {
    private JFrame frame;
    private JPanel myGame;
    private JPanel oppGame;
    private JButton[][] myButtons;
    private JButton[][] oppButtons;

    public View() {
        frame = new JFrame("Battleship");
        frame.setLayout(new GridLayout(2, 1));
        frame.setPreferredSize(new Dimension(750, 1000));

        myGame = new JPanel();
        myGame.setLayout(new GridLayout(10, 10));

        oppGame = new JPanel();
        oppGame.setLayout(new GridLayout(10, 10));

        myButtons = new JButton[10][10];
        oppButtons = new JButton[10][10];

        for (int i=0; i < 10; i++)
        {
            for (int j=0; j < 10; j++)
            {
                myButtons[i][j] = new JButton();
                myButtons[i][j].setBounds(new Rectangle(10, 10));
                myButtons[i][j].setName(i + "" + j);
                myGame.add(myButtons[i][j]);

                oppButtons[i][j] = new JButton();
                oppButtons[i][j].setPreferredSize(new Dimension(10, 10));
                oppButtons[i][j].setName(i + "" + j);
                oppGame.add(oppButtons[i][j]);
            }
        }

        frame.add(myGame);
        frame.add(oppGame);

        frame.pack();
        frame.setVisible(true);
    }
}
