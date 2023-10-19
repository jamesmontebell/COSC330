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

        MouseListener mouseListener = new MouseAdapter() {
            public void mousePressed(MouseEvent e)
            {
                JLabel c = (JLabel) e.getSource();
                if(e.getButton() == MouseEvent.BUTTON3) {
                    if(c.getIcon().toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_two.png")
                        c.setIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_two.png"));
                    else if(c.getIcon().toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_three.png")
                        c.setIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_three.png"));
                    else if(c.getIcon().toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_threetwo.png")
                        c.setIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_threetwo.png"));
                    else if(c.getIcon().toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_four.png")
                        c.setIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_four.png"));
                    else if(c.getIcon().toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_five.png")
                        c.setIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_five.png"));
                    else if(c.getIcon().toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_two.png")
                        c.setIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_two.png"));
                    else if(c.getIcon().toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_three.png")
                        c.setIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_three.png"));
                    else if(c.getIcon().toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_threetwo.png")
                        c.setIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_threetwo.png"));
                    else if(c.getIcon().toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_four.png")
                        c.setIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_four.png"));
                    else if(c.getIcon().toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_five.png")
                        c.setIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_five.png"));
                }

                TransferHandler handler = c.getTransferHandler();
                handler.exportAsDrag(c, e, TransferHandler.COPY); // export copy of clicked component: Can we add a ship class object to the components?
            }

            public void mouseExited(MouseEvent x) {
                lookThrough();
            }
        };

        v.setListeners(new ActionOnClick());
        v.setRandomListener(new RandomOnClick());
        v.setTransfer();
        v.setMouseHandlers(mouseListener);
        v.setClearListener(new ClearOnClick());

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
                view.missSound(x, y);
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
            view.clearBoard();
            model.clearBoard();
            JLabel viewMyGrid[][] = view.getMyGrid();
            String modelMyBoard[][] = model.getMyBoard();
            Ship[] s = model.getShips();
            view.rand.setEnabled(false);
            for(int i = 0; i <5; i++){
                placeRandomShip(s[i], viewMyGrid);
            }

            view.setMyGrid(viewMyGrid);
            model.printBoard();
            view.myGame.repaint();
            view.myGame.revalidate();
        }
    }

    private class ClearOnClick implements ActionListener{
        public void actionPerformed(ActionEvent e){
            view.clearBoard();
            model.clearBoard();
            view.myGame.repaint();
            view.myGame.revalidate();
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
        System.out.println("Rwo: " + row + ", col: " + col);
        if(horizontal){
            if((col+ship.getSize()-1) < 10) {
                for (int i = iter; i < iter+length; i++) {
                    if(horizontal) {
                        if(model.getPos(row, i) == " s ") return false;
                    }
                }
            }
            else{
                return false;
            }
        }
        else{
            if(((row+ship.getSize()-1) < 10)){
                for (int i = iter; i < iter+length; i++) {
                    if(!horizontal) {
                        if(model.getPos(row, i) == " s ") return false;}
                }

            }
            else{
                return false;
            }
        }


        //place the ship
        for (int i = iter; i < iter+length; i++) {
            if(horizontal) model.setGridPos(row, i, " s ");
            else model.setGridPos(i, col, " s ");
            model.incrementCount();
        }
        return true;
    }
    public void lookThrough(){
        JLabel g[][] = view.getMyGrid();
        Ship[] s = model.getShips();
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){

                if((g[row][col].getIcon()).toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_five.png"){

                    if(placeShip(row,col, false, s[4])){
                        draggedShip(row, col, s[4], g, false);
                        view.removeCarrier();
                    }
                    else{
                        g[row][col].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/trans.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

                    }
                }
                if((g[row][col].getIcon()).toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_five.png"){

                    if(placeShip(row,col, true, s[4])){         // Works but even if horizontal, it drops the ship vertically
                        draggedShip(row, col, s[4], g, true);
                        view.removeCarrier();
                    }
                    else{
                        g[row][col].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/trans.png.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

                    }
                }
                if((g[row][col].getIcon()).toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_four.png"){

                    if(placeShip(row,col, false, s[3])){
                        draggedShip(row, col, s[3], g, false);
                        view.removeBattleship();
                    }
                    else{
                        g[row][col].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/trans.png.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

                    }
                }
                if((g[row][col].getIcon()).toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_four.png"){

                    if(placeShip(row,col, true, s[3])){
                        draggedShip(row, col, s[3], g, true);
                        view.removeBattleship();
                    }
                    else{
                        g[row][col].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/trans.png.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

                    }
                }
                if((g[row][col].getIcon()).toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_three.png"){

                    if(placeShip(row,col, false, s[2])){
                        draggedShip(row, col, s[2], g, false);

                        view.removeCruiser();

                    }
                    else{
                        g[row][col].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/trans.png.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

                    }
                }
                if((g[row][col].getIcon()).toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_three.png"){

                    if(placeShip(row,col, true, s[2])){
                        draggedShip(row, col, s[2], g, true);

                        view.removeCruiser();

                    }
                    else{
                        g[row][col].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/trans.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

                    }
                }
                if((g[row][col].getIcon()).toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_threetwo.png"){

                    if(placeShip(row,col, false, s[1])){
                        draggedShip(row, col, s[1], g, false);

                        view.removeSubmarine();

                    }
                    else{
                        g[row][col].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/trans.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

                    }
                }
                if((g[row][col].getIcon()).toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_threetwo.png"){

                    if(placeShip(row,col, true, s[1])){
                        draggedShip(row, col, s[1], g, true);

                        view.removeSubmarine();

                    }
                    else{
                        g[row][col].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/trans.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

                    }
                }
                if((g[row][col].getIcon()).toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_two.png"){

                    if(placeShip(row,col, false, s[0])){
                        draggedShip(row, col, s[0], g, false);
                        view.removeDestroyer();
                    }
                    else{
                        g[row][col].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/trans.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

                    }
                }
                if((g[row][col].getIcon()).toString() == "/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_two.png"){

                    if(placeShip(row,col, true, s[0])){
                        draggedShip(row, col, s[0], g, true);
                        view.removeDestroyer();
                    }
                    else{
                        g[row][col].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/trans.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

                    }
                }

            }
        }
        model.printBoard();
        view.setMyGrid(g);
    }
    public void draggedShip(int boardRow, int boardCol, Ship ship, JLabel[][] g, boolean hor){
        if(hor){
            if((boardCol+ship.getSize()-1) < 10){
                g[boardRow][boardCol].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_left.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

                for (int i = 0; i < ship.getSize()-2; i++) {
                    try {
                        g[boardRow][boardCol+i+1].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_middle.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
                        //model.setGridPos(boardRow, boardCol, 1);

                    } catch (Exception err) {
                        System.out.println("Couldn't set icon: " + err);
                    }

                }
                g[boardRow][boardCol+ship.getSize()-1].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/h_right.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

            }
        }
        else{
            if((boardRow+ship.getSize()-1) < 10){
                g[boardRow][boardCol].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_top.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

                for (int i = 0; i < ship.getSize()-2; i++) {
                    try {
                        g[boardRow+i+1][boardCol].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_middle.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
                        //model.setGridPos(boardRow, boardCol, 1);

                    } catch (Exception err) {
                        System.out.println("Couldn't set icon: " + err);
                    }

                }
                g[boardRow+ship.getSize()-1][boardCol].setIcon(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_bottom.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));

            }
        }
    }

}