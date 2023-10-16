package org.example;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;

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
                application.processConnection();
                //  waitForClient();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
        }
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





} 