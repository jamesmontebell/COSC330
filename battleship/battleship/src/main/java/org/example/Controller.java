package org.example;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Controller{
    private Model model;
    private View view;
    Client application;
    int myShips=17;


    public Controller(Model m , View v)
    {
        model = m;
        view = v;

        v.setListeners(new ActionOnClick());
        v.setRandomListener(new RandomOnClick());
        application = new Client("127.0.0.1");
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.runClient(); // run client application
        while(true){
            try {
                application.processConnection();
                waitForServer();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void waitForServer() {
        if(model.getTurn() == "Server")
        {
            // recieve shot from Server

            String[] split2 = application.recieveMessage().split("");
            System.out.println(split2[0]+ split2[1]);
            int x = Integer.parseInt(split2[0]);
            int y = Integer.parseInt(split2[1]);

            boolean hit = model.checkHit(x, y);

            // send shot data to client
            if(hit)
            {
                application.sendData("1");
                myShips--;
                // view change ship image
                try {
                    view.setHit(x, y);
                } catch (UnsupportedAudioFileException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                }
                if(myShips <= 0)
                {
                    //view display win
                    view.displayWin("Server wins!");
                }
            }
            else
            {
                application.sendData("0");
                view.missSound();
            }
            model.setTurn("Client");
            view.setTurn("Client's turn!");
        }

    }

    private class ActionOnClick implements ActionListener{
        public void actionPerformed( ActionEvent event )
        {
            JButton but = (JButton)event.getSource();
            if(model.getTurn() == "Client")
            {
                application.sendData(but.getName());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if(model.recieveHit(application.recieveMessage())){
                    but.setBackground(Color.RED);
                }
                else{
                    but.setBackground(Color.white);
                }

                model.setTurn("Server");
                view.setTurn("Server's turn!");

            }
        }
    }


    private class RandomOnClick implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JLabel viewMyGrid[][] = view.getMyGrid();
            String modelMyBoard[][] = model.getMyBoard();
            Ship[] s = model.getShips();
            view.rand.setEnabled(false);

            for(int i = 0; i <5; i++){
                placeRandomShip(s[i], viewMyGrid);
            }

            view.setMyGrid(viewMyGrid);
            model.printBoard();
        }
    }
    public void placeRandomShip(Ship ship, JLabel[][] g) {
        boolean collides = true;
        int horiz = (int) (Math.random() * 2);
        int boardRow, boardCol;
        boolean horizontal = (horiz == 1) ? true : false;
        //  String name = ship.getName();
        do {
            if (horizontal) {
                boardCol = (int) (Math.random() * (9 - ship.getSize() + 1));
                boardRow = (int) (Math.random() * (9 + 1));
            } else {
                boardCol = (int) (Math.random() * (9 + 1));
                boardRow = (int) (Math.random() * (9 - ship.getSize() + 1));
            }
            collides = placeShip(boardRow, boardCol, horizontal, ship);
        }
        while (!collides);
        if (horizontal) {
            g[boardRow][boardCol].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_left.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
            for (int i = 0; i < ship.getSize()-2; i++) {
                try {
                    g[boardRow][boardCol+i+1].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_middle.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

                    model.setGridPos(boardRow,boardCol, " s ");

                } catch (Exception err) {
                    System.out.println("Couldn't set icon: " + err);
                }
            }
            g[boardRow][boardCol+ship.getSize()-1].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_right.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

        } else {
            g[boardRow][boardCol].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_top.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

            for (int i = 0; i < ship.getSize()-2; i++) {
                try {
                    g[boardRow+i+1][boardCol].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_middle.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
                    model.setGridPos(boardRow, boardCol, " s ");

                } catch (Exception err) {
                    System.out.println("Couldn't set icon: " + err);
                }

            }
            g[boardRow+ship.getSize()-1][boardCol].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_bottom.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));


        }
    }

    boolean placeShip(int row, int col, boolean horizontal, Ship ship)
    {
        int length = ship.getSize();
        int iter = horizontal ? col : row;

        // check if the ship will collide with any ships.
        for (int i = iter; i < iter+length; i++) {
            if(horizontal) {
                if(model.getPos(row, i) == " s ") return false;}
            else {
                if(model.getPos(i, col) == " s ") return false; }
        }

        //place the ship
        for (int i = iter; i < iter+length; i++) {
            if(horizontal) model.setGridPos(row, i, " s ");
            else model.setGridPos(i, col, " s ");
        }
        return true;
    }

}