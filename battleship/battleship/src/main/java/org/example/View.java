package org.example;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.SwingUtilities.getRootPane;

public class View {
    private JFrame frame;
    private JPanel myGame;
    private JPanel oppGame;
    private JPanel shipPanel;
    private JPanel userPanel;
    private JButton[][] oppButtons;
    private JLabel[][] myGrid;
    private JLabel[] myShips;
    public JButton rand;
    private String turn;


    public View() {
        frame = new JFrame("Battleship");
        frame.setLayout(new GridLayout(2, 2));
        frame.setPreferredSize(new Dimension(750, 800));
        frame.setMaximumSize(new Dimension(750, 800));
        frame.setMinimumSize(new Dimension(750, 800));
        frame.setBackground(new Color(11, 19, 30));
        frame.setLocationRelativeTo(null);
        getRootPane(frame).setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));

        myGame = new JPanel();
        myGame.setLayout(new GridLayout(10, 10));
        myGame.setBackground(new Color(11, 19, 30));
        myGame.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY));

        oppGame = new JPanel();
        oppGame.setLayout(new GridLayout(10, 10));
        oppGame.setBackground(new Color(11, 19, 30));
        oppGame.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY));

        userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout());
        userPanel.setBackground(new Color(11, 19, 30));
        userPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY));

        shipPanel = new JPanel();
        shipPanel.setLayout(new GridLayout(2, 3, 10, 30));
        shipPanel.setBackground(new Color(11, 19, 30));
        shipPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY));

        myGrid = new JLabel[10][10];
        oppButtons = new JButton[10][10];
        myShips = new JLabel[5];
        rand = new JButton();


        for (int i=0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                myGrid[i][j] = new JLabel();
                myGrid[i][j].setName(i + "" + j);
                myGrid[i][j].setOpaque(true);
                myGrid[i][j].setBackground(new Color(11, 19, 30));
                myGrid[i][j].setForeground(Color.white);
                myGrid[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                myGame.add(myGrid[i][j]);

                oppButtons[i][j] = new JButton();
                oppButtons[i][j].setPreferredSize(new Dimension(50, 50));
                oppButtons[i][j].setName(i + "" + j);
                oppButtons[i][j].setOpaque(true);
                oppButtons[i][j].setBackground(new Color(11, 19, 30));
                oppButtons[i][j].setForeground(Color.white);
                oppButtons[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                oppGame.add(oppButtons[i][j]);
            }
        }

        for(int i = 0; i < 5 ; i++)
        {
            myShips[i] = new JLabel();
            shipPanel.add(myShips[i]);
        }

        myShips[0].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_two.png").getImage().getScaledInstance(32, 64, Image.SCALE_SMOOTH)));
        myShips[1].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_three.png").getImage().getScaledInstance(32, 96, Image.SCALE_SMOOTH)));
        myShips[2].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_three.png").getImage().getScaledInstance(32, 96, Image.SCALE_SMOOTH)));
        myShips[3].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_four.png").getImage().getScaledInstance(32, 128, Image.SCALE_SMOOTH)));
        myShips[4].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_five.png").getImage().getScaledInstance(32, 160, Image.SCALE_SMOOTH)));

//        myGrid[7][0].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_top.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

        userPanel.add(rand);
        frame.add(oppGame);
        frame.add(userPanel);
        frame.add(myGame);
        frame.add(shipPanel);

        frame.pack();
        frame.setVisible(true);
    }
    public void setListeners(ActionListener l) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                oppButtons[i][j].addActionListener(l);
            }
        }
    }

    public void setRandomListener(ActionListener l)
    {
        rand.addActionListener(l);
    }

    public void setTurn(String t)
    {
        turn = t;
    }

    public JLabel[][] getMyGrid()
    {
        return myGrid;
    }

    public void setMyGrid(JLabel[][] g)
    {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                myGrid[i][j] = g[i][j];
            }
        }
    }

    public void createDestroyer()
    {
        Destroyer d = new Destroyer(2, "Destroyer");
        myGrid[8][0].setIcon(d.getFront().getIcon());
    }

}
