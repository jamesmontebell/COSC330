package org.example;

public class MainServer {
    public static void main(String[] args){


        View v = new View();
        Model m = new Model();
        ControllerServer c = new ControllerServer(m, v);
    }
}