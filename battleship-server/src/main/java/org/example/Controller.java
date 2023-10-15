package org.example;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Controller{
    private Model model;
    private View view;
    Server application;
    String message = "";
    int connection = 0;
    public Controller(Model m , View v)
    {
        model = m;
        view = v;
        v.setListeners(new ActionOnClick());
        v.setRandomListener(new RandomOnClick());
        application = new Server();
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.runServer(); // run client application
        while(true){



            try {
                System.out.println("serbruhhh stick");


                application.processConnection();
                //  waitForClient();
                System.out.println("serbruhhh stick2");

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
            System.out.println("serbruhhh stick3");


            // System.out.println("serbruhhh stick4");

        }
        //application.closeConnection();

    }

    public void waitForClient()
    {
        message = application.recieveMessage();
        if(message != null){
            String[] split2 = message.toString().split("");
            int x = Integer.parseInt(split2[0]);
            int y = Integer.parseInt(split2[1]);

            boolean hit = model.checkHit(x, y);

            if(hit)
            {
                application.sendData("1");
            }
            else
            {
                application.sendData("0");
            }
            model.setTurn(1);
            view.setTurn("My turn!");
        }

    }

    private class ActionOnClick implements ActionListener{
        public void actionPerformed( ActionEvent event )
        {
            JButton but = (JButton)event.getSource();
            //if(model.getTurn() == 1){
            application.sendData(but.getName() + 1);

            if(model.recieveHit(message)){
                but.setBackground(Color.RED);
            }
            else{
                but.setBackground(Color.white);
            }
            model.setTurn(0);
            view.setTurn("Opponent's turn!");

            //  }
        }
    }


    private class RandomOnClick implements ActionListener{
        public void actionPerformed(ActionEvent e){
//            System.out.println("HELPPPPPP");
//            //add code here to iterate through ships and add to map based on call random
//            JLabel g[][] = view.getMyGrid();
//            int play[][] = model.getBoard();
//            Ship[] s = model.getShips();
//            view.randPlace.setEnabled(false);
//            for(int i = 0; i <5; i++){
//                placeRandomShip(s[i], g);
//            }
//
//            view.setMyGrid(g);

        }
    }
//    public void placeRandomShip(Ship ship, JLabel[][] g) {
//        boolean collides = true;
//        int horiz = (int) (Math.random() * 2);
//        int boardRow, boardCol;
//        boolean horizontal = (horiz == 1) ? true : false;
//        //  String name = ship.getName();
//        do {
//            if (horizontal) {
//                boardCol = (int) (Math.random() * (9 - ship.getSize() + 1));
//                boardRow = (int) (Math.random() * (9 + 1));
//            } else {
//                boardCol = (int) (Math.random() * (9 + 1));
//                boardRow = (int) (Math.random() * (9 - ship.getSize() + 1));
//            }
//            collides = placeShip(boardRow, boardCol, horizontal, ship);
//        }
//        while (!collides);
//        if (horizontal) {
//            g[boardRow][boardCol].setIcon(new ImageIcon(new ImageIcon("shipImages/h_left.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
//            for (int i = 0; i < ship.getSize()-2; i++) {
//                try {
//                    g[boardRow][boardCol+i+1].setIcon(new ImageIcon(new ImageIcon("shipImages/h_middle.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
//
//                    model.setGridPos(boardRow,boardCol, 1);
//
//                } catch (Exception err) {
//                    System.out.println("Couldn't set icon: " + err);
//                }
//            }
//            g[boardRow][boardCol+ship.getSize()-1].setIcon(new ImageIcon(new ImageIcon("shipImages/h_right.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
//
//        } else {
//            g[boardRow][boardCol].setIcon(new ImageIcon(new ImageIcon("shipImages/v_top.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
//
//            for (int i = 0; i < ship.getSize()-2; i++) {
//                try {
//                    g[boardRow+i+1][boardCol].setIcon(new ImageIcon(new ImageIcon("shipImages/v_middle.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
//                    model.setGridPos(boardRow, boardCol, 1);
//
//                } catch (Exception err) {
//                    System.out.println("Couldn't set icon: " + err);
//                }
//
//            }
//            g[boardRow+ship.getSize()-1][boardCol].setIcon(new ImageIcon(new ImageIcon("shipImages/v_bottom.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
//
//
//        }
//    }
//
//    boolean placeShip(int row, int col, boolean horizontal, Ship ship)
//    {
//        int length = ship.getSize();
//        int iter = horizontal ? col : row;
//
//        // check if the ship will collide with any ships.
//        for (int i = iter; i < iter+length; i++) {
//            if(horizontal) {
//                if(model.getPos(row, i) == 1) return false;}
//            else {
//                if(model.getPos(i, col) == 1) return false; }
//        }
//
//        //place the ship
//        for (int i = iter; i < iter+length; i++) {
//            if(horizontal) model.setGridPos(row, i, 1);
//            else model.setGridPos(i, col, 1);
//            model.incrementCount();
//        }
//        return true;
//    }
//





} 