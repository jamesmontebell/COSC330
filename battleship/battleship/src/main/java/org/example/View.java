package org.example;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;


import static javax.swing.SwingUtilities.getRootPane;

public class View {
    private JFrame frame;
    private JPanel myGame;
    private JPanel oppGame;
    private JPanel shipPanel;
    private JPanel userPanel;
    private JPanel parentPanel;
    private JButton[][] oppButtons;
    private JLabel[][] myGrid;
    private JLabel[] myShips;
    public JButton rand;
    private String turn = "Client's turn";
    private JTextArea displayTurn;

    JLabel carrier = new JLabel();
    JLabel battleship = new JLabel();
    JLabel cruiser = new JLabel();
    JLabel submarine = new JLabel();
    JLabel destroyer = new JLabel();


    public View() {
        frame = new JFrame("Battleship");
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(new GridBagLayout());
        frame.setPreferredSize(new Dimension(700, 800));
        frame.setMaximumSize(new Dimension(700, 800));
        frame.setMinimumSize(new Dimension(700, 800));
        frame.setBackground(new Color(11, 19, 30));
        frame.setLocationRelativeTo(null);
        getRootPane(frame).setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));

        myGame = new JPanel();
        myGame.setLayout(new GridLayout(10, 10));
        myGame.setBackground(new Color(11, 19, 30));
        myGame.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY));

        oppGame = new JPanel();
        oppGame.setLayout(new GridLayout(10, 10));
        oppGame.setPreferredSize(new Dimension(350, 350));
        oppGame.setMaximumSize(new Dimension(350, 350));
        oppGame.setMinimumSize(new Dimension(350, 350));
        oppGame.setBackground(new Color(11, 19, 30));
        oppGame.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY));

        userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout());
        userPanel.setPreferredSize(new Dimension(350, 350));
        userPanel.setMaximumSize(new Dimension(350, 350));
        userPanel.setMinimumSize(new Dimension(350, 350));
        userPanel.setBackground(new Color(11, 19, 30));
        userPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY));

        shipPanel = new JPanel();
        shipPanel.setLayout(new GridLayout(2, 3, 10, 30));
        shipPanel.setBackground(new Color(11, 19, 30));
        shipPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY));

        parentPanel = new JPanel();
        parentPanel.setLayout(new GridLayout(1, 2));
        parentPanel.setPreferredSize(new Dimension(700, 350));
        parentPanel.setMaximumSize(new Dimension(700, 350));
        parentPanel.setMinimumSize(new Dimension(700, 350));
        parentPanel.setBackground(new Color(11, 19, 30));
        parentPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GRAY));

        myGrid = new JLabel[10][10];
        oppButtons = new JButton[10][10];
        myShips = new JLabel[5];
        rand = new JButton();
        displayTurn = new JTextArea();

        rand.setText("Randomize Ships");
        displayTurn.setText(turn);
        displayTurn.setEditable(false);


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

        myShips[0].setIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_two.png"));
        myShips[1].setIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_three.png"));
        myShips[2].setIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_three.png"));
        myShips[3].setIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_four.png"));
        myShips[4].setIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_five.png"));

//        myGrid[7][0].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_top.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

        userPanel.add(rand);
        userPanel.add(displayTurn);

        parentPanel.add(myGame);
        parentPanel.add(shipPanel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0,0,0,0);
        frame.add(oppGame, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0,0,0,0);
        frame.add(userPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 5;
        gbc.weighty = 5;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0,0,0,0);
        frame.add(parentPanel, gbc);

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


    public void setTransfer() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                myGrid[i][j].setTransferHandler(new TransferHandler("icon"));

            }
        }
    }


    public void setMouseHandlers(MouseListener l) {
        for (int i = 0; i < 5; i++) {
            myShips[i].setTransferHandler(new TransferHandler("icon"));
            myShips[i].addMouseListener(l);
        }
    }

    public void setRandomListener(ActionListener l)
    {
        rand.addActionListener(l);
    }

    public void setTurn(String t)
    {
        turn = t;
        displayTurn.setText(t);
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

    public void setHit(int x, int y) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        myGrid[x][y].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/skull.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/boom.wav").getAbsoluteFile());
            try{
                // create clip reference
                Clip clip = AudioSystem.getClip();

                // open audioInputStream to the clip
                clip.open(audioInputStream);

                clip.loop(0);
            }catch (IOException | LineUnavailableException e)
            {}

        }catch (IOException e){

        }
    }
    public void missSound()
    {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new File("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/miss.wav").getAbsoluteFile());
            try{
                // create clip reference
                Clip clip = AudioSystem.getClip();

                // open audioInputStream to the clip
                clip.open(audioInputStream);

                clip.loop(0);
            }catch (IOException | LineUnavailableException e)
            {}

        }catch (IOException e){

        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }
    public void displayWin(String x)
    {
        JOptionPane.showMessageDialog(null, x);
    }

    public void removeCarrier(){
        myShips[4].setIcon(null);
        myShips[4].setTransferHandler(null);
        shipPanel.remove(myShips[4]);
        shipPanel.revalidate();
        shipPanel.repaint();
    }
    public void removeDestroyer(){
        myShips[0].setIcon(null);
        myShips[0].setTransferHandler(null);
        shipPanel.remove(myShips[0]);
        shipPanel.revalidate();
        shipPanel.repaint();
    }
    public void removeSubmarine(){
        myShips[1].setIcon(null);
        myShips[1].setTransferHandler(null);
        shipPanel.remove(myShips[1]);
        shipPanel.revalidate();
        shipPanel.repaint();
    }
    public void removeBattleship(){
        myShips[3].setIcon(null);
        myShips[3].setTransferHandler(null);
        shipPanel.remove(myShips[3]);
        shipPanel.revalidate();
        shipPanel.repaint();
    }
    public void removeCruiser(){
        myShips[2].setIcon(null);
        myShips[2].setTransferHandler(null);
        shipPanel.remove(myShips[2]);
        shipPanel.revalidate();
        shipPanel.repaint();
    }

}
