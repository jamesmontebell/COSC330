package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class View {
    private JFrame frame;
    private JPanel myGame;
    private JPanel oppGame;
    private JPanel shipPanel;
    private JButton[][] myButtons;
    private JButton[][] oppButtons;
    private JLabel[] ships;


    public View() {
        frame = new JFrame("Battleship");
        frame.setLayout(new GridLayout(2, 2));
        frame.setPreferredSize(new Dimension(750, 800));

        myGame = new JPanel();
        myGame.setLayout(new GridLayout(10, 10));

        oppGame = new JPanel();
        oppGame.setLayout(new GridLayout(10, 10));

        shipPanel = new JPanel();
        shipPanel.setLayout(new GridLayout(3, 2, 10, 10));

        myButtons = new JButton[10][10];
        oppButtons = new JButton[10][10];

        ships = new JLabel[5];

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


        Icon icon = new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/ship_boatv.png");
        ships[0] = new JLabel(icon);
        shipPanel.add(ships[0]);

        Icon icon1 = new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/ship_submarinev.png");
        ships[1] = new JLabel(icon1);
        shipPanel.add(ships[1]);

        Icon icon2 = new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/ship_cruiserv.png");
        ships[2] = new JLabel(icon2);
        shipPanel.add(ships[2]);

        Icon icon3 = new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/ship_destroyerv.png");
        ships[3] = new JLabel(icon3);
        shipPanel.add(ships[3]);

        Icon icon4 = new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/ship_airCarrierv.png");
        ships[4] = new JLabel(icon4);
        shipPanel.add(ships[4]);

        frame.add(oppGame);
        frame.add(shipPanel);
        frame.add(myGame);

        frame.pack();
        frame.setVisible(true);
    }
}
