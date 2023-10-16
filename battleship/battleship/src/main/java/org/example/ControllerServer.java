package org.example;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class ControllerServer{
    private Model model;
    private View view;
    Server application;
    public ControllerServer(Model m , View v)
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
                application.processConnection();
                waitForClient();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
        }
    }

    public void waitForClient() throws IOException {
        if(model.getTurn() == "Client")
        {
            // recieve shot from client

            String[] split2 = application.recieveMessage().split("");
            System.out.println(split2[0]+ split2[1]);
            int x = Integer.parseInt(split2[0]);
            int y = Integer.parseInt(split2[1]);

            boolean hit = model.checkHit(x, y);

            // send shot data to client
            if(hit)
            {
                application.sendData("1");
            }
            else
            {
                application.sendData("0");
            }
        }
        model.setTurn("Server");
        view.setTurn("My turn!");
    }

    private class ActionOnClick implements ActionListener{
        public void actionPerformed( ActionEvent event )
        {
            JButton but = (JButton)event.getSource();
            System.out.println(model.getTurn());

            if(model.getTurn() == "Server"){

                application.sendData(but.getName());
                System.out.println("bruh");

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
                model.setTurn("Client");
                view.setTurn("Opponent's turn!");
              }
        }
    }



    private class RandomOnClick implements ActionListener{
        public void actionPerformed(ActionEvent e){
            model.placeRandom();
            JLabel viewMyGrid[][] = view.getMyGrid();
            String modelMyBoard[][] = model.getMyBoard();
            Ship[] s = model.getShips();
            view.rand.setEnabled(false);

            for(int i = 0; i <5; i++){
                placeRandomShip(s[i], viewMyGrid);
            }

            view.setMyGrid(viewMyGrid);
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