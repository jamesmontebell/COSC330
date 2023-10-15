package org.example;

import org.example.Client;
import org.example.Model;
import org.example.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;


public class Controller{
    private Model model;
    private View view;
    Client application;
    String message = "";

    public Controller(Model m , View v)
    {
        model = m;
        view = v;
        v.setListeners(new ActionOnClick());
        v.setRandomListener(new RandomOnClick());
        application = new Client("127.0.0.0");
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.runClient(); // run client application
    }

    public void waitForServer()
    {
        message = application.getMessage();
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
        model.setTurn(0);
        view.setTurn("My turn!");

    }

    private class ActionOnClick implements ActionListener{
        public void actionPerformed( ActionEvent event )
        {
            JButton but = (JButton)event.getSource();
            if(model.getTurn() == 0){
                application.sendData(but.getName() + 1);

                if(model.recieveHit(message)){
                    but.setBackground(Color.RED);
                }
                else{
                    but.setBackground(Color.white);
                }
                model.setTurn(1);
                view.setTurn("Opponent's turn!");

            }
        }
    }

    private class RandomOnClick implements ActionListener{
        public void actionPerformed( ActionEvent event )
        {
//            JLabel[][] g = view.getMyGrid();
//            Destroyer d = new Destroyer(2, "Destroyer");
//
//            g[8][0].setIcon(d.getFront().getIcon());
//            g[9][0].setIcon(d.getBack().getIcon());
//
//            view.setMyGrid(g);
            view.createDestroyer();
        }
    }





} 