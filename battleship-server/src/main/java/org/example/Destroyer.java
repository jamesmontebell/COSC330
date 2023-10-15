package org.example;

import javax.swing.*;
import java.awt.*;

public class Destroyer extends Ship{

    private JLabel front;
    private JLabel back;
    Destroyer(int length, String name) {
        super(length, name);
        this.setLength(2);
        front = new JLabel(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_top.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
        back = new JLabel(new ImageIcon(new ImageIcon("/Users/jamesmontebell/Github/cosc330/battleship/battleship/src/main/java/org/example/images/v_bottom.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
    }
    public JLabel getFront()
    {
        return front;
    }
    public JLabel getBack()
    {
        return back;
    }
}
